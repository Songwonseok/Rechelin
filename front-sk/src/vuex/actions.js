
import {requestfetchUserList} from '../apis/index.js';
import Userapi from '../apis/UserApi.js';
import Axios from "axios"
import router from '../main.js';
// const URL = 'http://70.12.246.134:8080' // 김주연 ip
const URL = 'http://70.12.246.51:8080' //  조장님 ip
const auth = {
    headers: {
        Authorization: 'Bearer '+ sessionStorage.getItem("userToken")
    }
} 

export default {
    LOADING_USERDATA(context){
        console.log('hi');
        requestfetchUserList().then(
            response =>{
            console.log(response);
            console.log('hi2');
            console.log(response.data.object);
            var jbAry = new Array();
            var jaAry = new Array();
            for(var i =0; i<response.data.object.length; i++){
                jbAry[i] = response.data.object[i].nickname;
                console.log(jbAry[i]);
            }
            for(var j =0; j<response.data.object.length; j++){
                jaAry[j] = response.data.object[j].email;
                console.log(jbAry[j]);
            }
            context.commit('SET_USER',jbAry);
            context.commit('SET_EMAIL',jaAry);
        }).catch(error =>{
            console.log(error);
        })
    },
    LOADING_RECENTUSERDATA({commit}, email){
        Userapi.requestFetchUserData({commit},email).then(
            response => {
                console.log("최근 검색 내용, action->LOADING_RECENTUSERDATA");
            }).catch(error=>{
                console.log(error);
            })
    },
    FETCH_ADR({commit}, address){ //google 에서부터 음식점 주소를 FETCH 해옴
        Userapi.requestFetchAdrData({commit},address).then(
            response => {
                
              
                

                var aJsonArray = new Array(); //선택된 데이터만 
           
                var aJsonArray2 = new Array(); //전체 데이터 
                

                for(var i =0; i<response.data.results.length; i++){
                    var item = {};
                    var item2 = {};
                    item['주소']= response.data.results[i].formatted_address;
                    item2['address'] = response.data.results[i].formatted_address;
                    item2['lat'] = response.data.results[i].geometry.location.lat;
                    item2['lng'] = response.data.results[i].geometry.location.lng;
                    item2['sname'] = response.data.results[i].name;
                    item['지점명']= response.data.results[i].name;
                    
                    if(response.data.results[i].hasOwnProperty('photos')){
                        item2['img'] = response.data.results[i].photos[0].photo_reference
                    }else{
                        item2['img'] = null;
                    }
                    JSON.stringify(item);
                    JSON.stringify(item2);
                    aJsonArray.push(item);
                    aJsonArray2.push(item2); 
                }
                 commit('SET_GOOGLEMAP_TOTAL',aJsonArray2);
                 commit('SET_GOOGLEMAP',aJsonArray);
            }).catch(error=>{
                console.log(error);
            })
        
    },
    
    logout({commit}) {
        commit('logout')
    },
    login({commit}, payload) {
        commit('login', payload)
    },
    userpageGo({commit}, payload) {

        const params = new URLSearchParams();
        params.append('email', payload)
        // var options = Object.assign({}, defaults, params)

        Axios.post('http://70.12.246.51:8080/account/selectEmail', params, auth)
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
    userFans({commit}, payload) {

        const params = new URLSearchParams();
        params.append('email', payload);
        Axios.post('http://70.12.246.51:8080/follow/fanList', params, auth)
            .then(response => {
                commit('userFans', response.data.object)
            }).catch(exp => {
                console.log('실패')
            })
        
    },
    userStars({commit}, payload) {
        const params = new URLSearchParams();
        params.append('email', payload);
        Axios.post('http://70.12.246.51:8080/follow/starList', params, auth)
            .then(response => {
                commit('userStars', response.data.object)
                console.log(this.state.userPageInfo.stars, '스타스타스타')
            }).catch(exp => {
                console.log('실패')
            })
        
    },
    notificationGet({commit}, payload){
        const params = new URLSearchParams();
        params.append('email', payload)
        Axios.post('http://70.12.246.51:8080/follow/alarmList', params, auth)
            .then(res => {
                
                commit('notificationGet', res.data.object)
            }).catch(exp => {
                console.log('실패')
            })
        
    },
    followRequest({commit}, payload) {
        const params = new URLSearchParams();
        params.append('fan', payload.fan)
        params.append('star', payload.star)
        Axios.post('http://70.12.246.51:8080/follow/request', params, auth)
            .then(res => {
                console.log('요청 성공')
            }).catch(exp => {
                console.log('실패')
            })
        
    },
    followAccept({commit}, payload) {
        const params = new URLSearchParams();
        params.append('fan', payload.fan)
        params.append('star', payload.star)
        Axios.post('http://70.12.246.51:8080/follow/accept', params, auth)
            .then(res => {
                console.log('요청 성공')
            }).catch(exp => {
                console.log('실패')
            })
    },
    followDecline({commit}, payload) {
        const params = new URLSearchParams();
        params.append('fan', payload.fan)
        params.append('star', payload.star)
        Axios.post('http://70.12.246.51:8080/follow/decline', params, auth)
            .then(res => {
                console.log('요청 성공')
            }).catch(exp => {
                console.log('실패')
            })
        
    },
    storeinfoGet({commit}, payload) {
        const params = new URLSearchParams();
        params.append('id', payload.id)
        Axios.post('http://70.12.246.51:8080/store/selectOne', params, auth)
            .then(res => {
                console.log('요청 성공')
                commit('storeinfoGet', res.data.object)
            }).catch(exp => {
                console.log('실패')
            })
        
    },
    tagsGet({commit}, payload) {
        const params = new URLSearchParams();
        params.append('id', payload.id) // store id로 store에 걸린 tag 검색
        Axios.post('', params)
            .then(res => {
                console.log('요청 성공')
                commit('tagsGet', payload)
            }).catch(exp => {
                console.log('실패')
            })
        
    },
    likeStore({commit}, payload) {
          
        Axios.post("http://70.12.246.51:8080/review/bookmark", payload, auth)
        .then(res => {
           console.log('요청 성공')
       }).catch(exp => {
           console.log('실패')
       })

    },
    // 리뷰 관련
    reviewsGet({commit}, payload){
        Axios.get(`http://70.12.246.51:8080/review/${payload}`, auth)
            .then(res => {
                console.log('성공')
                commit('reviewsGet', res.data.object);
                console.log(res, '??????')
                router.push({name: 'reviewsOfstore', params: {
                    reviews: res.data.object
                }})
            }).catch(exp => {
                console.log('실패')
            })
    },
    // 리뷰의 댓글 관련
    commentsOfreview({commit}, review) {
        console.log(review, "???")
        //리뷰 아이디 집어 넣으면, 해당 리뷰 아이디를 가진 댓글을 불러오겠지
        Axios.get(`http://70.12.246.51:8080/review/comment/${review.rnum}`, auth)
            .then(res => {
                var data = {
                    reviewInfo: review,
                    comments: res.data.object
                }
                commit('commentsOfreview', data)
                console.log('요청 성공')
                router.push({name: 'comments'})
            }).catch(exp => {
                console.log('실패')
            })
       
    },
    createComment({commit}, newComment) {

        Axios.post(`http://70.12.246.51:8080/review/comment`, newComment, auth)
        .then(res => {
            console.log('요청 성공')
            commit('createComment', newComment)
        }).catch(exp => {
            console.log('실패')
        })
        
    },
    commentDelete({commit}, num) {

        Axios.delete(`http://70.12.246.51:8080/review/comment/${num}`, auth)
                    .then(res => {
                        console.log('댓글 삭제 성공')
                    }).catch(exp => {
                        console.log('실패')
                    })
    },
    reviewDelete({commit}, num) {
        
        Axios.delete(`http://70.12.246.51:8080/review/delete`, num, auth)
                    .then(res => {
                        console.log('댓글 삭제 성공')
                    }).catch(exp => {
                        console.log('실패')
                    })
    },
    reviewLike({commit}, num) {
         Axios.post("http://70.12.246.51:8080/review/like", num, auth)
        .then(res => {
           console.log('요청 성공')
       }).catch(exp => {
           console.log('실패')
       })
    },
    addStore({commit}, storeInfo) {

    }
    
  
    

}
