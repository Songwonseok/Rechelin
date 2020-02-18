import { requestfetchUserList } from '../apis/index.js';
import Userapi from '../apis/UserApi.js';
import SearchApi from '../apis/SearchApi.js';
import Axios from "axios"
import router from '../main.js';
// const URL = 'http://70.12.246.134:8080' // 김주연 ip
//const URL = "http://70.12.246.51:8080"
 const URL = "http://54.180.160.87:8080" // aws
const auth = {
    headers: {
        Authorization: 'Bearer ' + sessionStorage.getItem("userToken")
    }
}

export default {
    LOADING_USERDATA(context) {
        console.log('hi');
        requestfetchUserList().then(
            response => {
                console.log(response);
                console.log('hi2');
                console.log(response.data.object);
                var jbAry = new Array();
                var jaAry = new Array();
                for (var i = 0; i < response.data.object.length; i++) {
                    jbAry[i] = response.data.object[i].nickname;
                    console.log(jbAry[i]);
                }
                for (var j = 0; j < response.data.object.length; j++) {
                    jaAry[j] = response.data.object[j].email;
                    console.log(jbAry[j]);
                }
                context.commit('SET_USER', jbAry);
                context.commit('SET_EMAIL', jaAry);
            }).catch(error => {
            console.log(error);
        })
    },
    LOADING_RECENTUSERDATA({ commit }, email) {
        SearchApi.requestFetchUserData({ commit }, email).then(
            response => {
                console.log("최근 검색 내용, action->LOADING_RECENTUSERDATA");
            }).catch(error => {
            console.log(error);
        })
    },
    FETCH_ADR({ commit }, address) { //google 에서부터 음식점 주소를 FETCH 해옴
        
        SearchApi.requestFetchAdrData({ commit }, address).then(
            response => {
                console.log('recent')
                var aJsonArray = new Array(); //선택된 데이터만 

                var aJsonArray2 = new Array(); //전체 데이터 


                for (var i = 0; i < response.data.results.length; i++) {
                    var item = {};
                    var item2 = {};
                    item['주소'] = response.data.results[i].formatted_address;
                    item2['address'] = response.data.results[i].formatted_address;
                    item2['lat'] = response.data.results[i].geometry.location.lat;
                    item2['lng'] = response.data.results[i].geometry.location.lng;
                    item2['sname'] = response.data.results[i].name;
                    item['지점명'] = response.data.results[i].name;

                    if (response.data.results[i].hasOwnProperty('photos')) {
                        item2['img'] = response.data.results[i].photos[0].photo_reference
                    } else {
                        item2['img'] = null;
                    }
                    JSON.stringify(item);
                    JSON.stringify(item2);
                    aJsonArray.push(item);
                    aJsonArray2.push(item2);
                }
                console.log(aJsonArray); //표에 뿌려줄것
                console.log(aJsonArray2); //db에 들어가는것
                commit('SET_GOOGLEMAP_TOTAL', aJsonArray2);
                commit('SET_GOOGLEMAP', aJsonArray);

            }).catch(error => {
            console.log(error);
        })

    },

    logout({ commit }) {
        commit('logout')
    },
    login({ commit }, payload) {
        commit('login', payload)
    },
    userpageGo({ commit }, payload) {

        const params = new URLSearchParams();
        params.append('email', payload)
            // var options = Object.assign({}, defaults, params)


        Axios.post(URL + '/account/selectEmail', params, auth)
            .then(response => {
                commit('userpageGo', response.data.object)
                router.push({
                    name: "UserPage",
                    params: {
                        id: payload,
                    },
                    query: {
                        userInfo: response.data.object
                    }
                })

            }).catch(exp => {
                console.log('실패')
            })

    },
    // userFans({ commit }, payload) {

    //     const params = new URLSearchParams();
    //     params.append('email', payload);
    //     Axios.post(URL + '/follow/fanList', params, auth)
    //         .then(response => {
    //             commit('userFans', response.data.object)
    //         }).catch(exp => {
    //             console.log('실패')
    //         })

    // },
    userStars({ commit }, payload) {
        const params = new URLSearchParams();
        params.append('email', payload);
        Axios.post(URL + '/follow/starList', params, auth)
            .then(response => {
                commit('userStars', response.data.object)
                console.log(this.state.userPageInfo.stars, '스타스타스타')
            }).catch(exp => {
                console.log('실패')
            })

    },
    notificationGet({ commit }, payload) {
        const params = new URLSearchParams();
        params.append('id', payload)
        Axios.post(URL + '/follow/alarmList', params, auth)
            .then(res => {
                console.log(res)
                commit('notificationGet', res.data.object)
            }).catch(exp => {
                console.log('실패')
            })

    },
    followRequest({ commit }, payload) {
        const params = new URLSearchParams();
        params.append('fan', payload.fan)
        params.append('star', payload.star)
        Axios.post(URL + '/follow/request', params, auth)
            .then(res => {
                console.log('요청 성공')
            }).catch(exp => {
                console.log('실패')
            })

    },
    followAccept({ commit }, payload) {
        const params = new URLSearchParams();
        params.append('fan', payload.fan)
        params.append('star', payload.star)
        console.log(payload.fan, payload.star)
        Axios.post(URL + '/follow/accept', params, auth)
            .then(res => {
                console.log('요청 성공')
            }).catch(exp => {
                console.log('실패')
            })
    },
    followDecline({ commit }, payload) {
        const params = new URLSearchParams();
        params.append('fan', payload.fan)
        params.append('star',payload.star)
        console.log(payload)
        Axios.post(URL + '/follow/decline', params, auth)
            .then(res => {
                console.log('요청 성공')
            }).catch(exp => {
               
                console.log('실패')
            })

    },
    likeStore({ commit }, payload) {
        const params = new URLSearchParams();
        params.append('id', payload.id)
        params.append('snum', payload.snum)
        // console.log(payload, '???????????????????????')
        Axios.post(URL + "/store/checkBook", params, auth)
            .then(res => {
                console.log('요청 성공')
                console.log(res)
                if (res.data.status==false) {
                    console.log('북마크 등록')
                    Axios.post(URL + "/store/addBook", params, auth)
                        .then(response => {
                            console.log('북마크 등록 성공')
                        }).catch(exp => {
                            console.log('북마크 등록 실패')
                        })
                    
                } else {
                    Axios.post(URL+"/store/removeBook",  params, auth)
                    .then(response => {
                        console.log('북마크 삭제')
                    }).catch(exp => {
                        console.log('북마크 삭제 실패')
                    })
                }
            }).catch(exp => {
                console.log('실패')
            })

    },

    // 리뷰의 댓글 관련
    commentsOfreview({ commit }, review) {
        console.log(review, "???")
            //리뷰 아이디 집어 넣으면, 해당 리뷰 아이디를 가진 댓글을 불러오겠지
        Axios.get(URL + `/review/comment/${review}`, auth)
            .then(res => {
                var data = {
                    reviewInfo: review,
                    comments: res.data.object
                }
                commit('commentsOfreview', data)
                Axios.get(URL + `/review/detail/${review}`, auth)
                    .then(res => {
                        commit('reviewDetail', res.data.object)
                        router.push({ name: 'comments' })
                    })
                console.log('요청 성공', res.data.object)

            }).catch(exp => {
                console.log('실패')
            })

    },
    createComment({ commit }, newComment) {

        Axios.post(URL + `/review/comment`, newComment, auth)
            .then(res => {
                console.log('요청 성공')
                commit('createComment', newComment)
            }).catch(exp => {
                console.log('실패')
            })

    },
    commentDelete({ commit }, num) {

        Axios.delete(URL + `/review/comment/${num}`, auth)
            .then(res => {
                console.log('댓글 삭제 성공')
            }).catch(exp => {
                console.log('실패')
            })
    },
    reviewDelete({ commit }, num) {

        Axios.delete(URL + `/review/delete`, num, auth)
            .then(res => {
                console.log('댓글 삭제 성공')
            }).catch(exp => {
                console.log('실패')
            })
    },
    reviewLike({ commit }, payload) {
        let data = {
            'review': {
                'rnum': payload.num
            },
            'user': {
                'id': sessionStorage.getItem('userid')
            },
            'status': payload.status
        }
        let options = {
            headers: { 'Content-Type': 'application/json', Authorization: 'Bearer ' + sessionStorage.getItem("userToken") },
            url: URL + '/review/like',
            method: 'post',
            data: JSON.stringify(data)
        }
        Axios(options)
            .then(res => {
                console.log('요청 성공')
            }).catch(exp => {
                console.log('실패')
            })
    },
    storeHashtags({ commit }, num) {
        console.log('들어옴??')
        const params = new URLSearchParams();
        params.append('num', num);
        Axios.post(URL + '/store/tags', params, auth)
            .then(res => {
                console.log(res, 'hashtags')
                commit('storeHashtags', res.data.object)
                Axios.post(URL + '/store/selectOne', params, auth)
                    .then(responseOne => {
                        console.log(responseOne, 'storeInformation')
                        let data = {
                            resData: responseOne.data.object,
                            id: num
                        }
                        commit('storeinfoGet', data)
                        Axios.get(URL + `/review/${num}`, auth)
                            .then(resTwo => {
                                console.log('성공', resTwo)

                                commit('reviewsGet', resTwo.data.object)
                                router.push({
                                    name: 'storeDetail',
                                    params: {
                                        id: num
                                    }
                                })

                            })
                    }).catch(exp => {
                        console.log('실패')
                    })

            }).catch(exp => {
                console.log('해쉬태그 가져오기 실패')
            })

    }




}