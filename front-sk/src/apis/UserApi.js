import Axios from "axios"
import store from "../vuex/store"
import BASE from "./BaseApi.js"

//const URL = 'http://70.12.246.134:8080' // 김주연 ip
const URL = 'http://70.12.246.51:8080' //  조장님 ip
const auth = {
    headers: {
        Authorization: 'Bearer ' + sessionStorage.getItem("userToken")
    }
}
const requestsignUp = async(data, callback, errorCallback) => {



    let options = {
        headers: { 'Content-Type': 'application/json' },
        url: URL + '/auth/signup',
        method: 'post',
        data: JSON.stringify(data)
    }

    let response = await Axios(options)
        .then(response => {
            console.log(response)
            callback(response);
            console.log('성공')
        }).catch(exp => {
            errorCallback(exp);
            console.log('실패');
        })
}

const requestName = (data, callback, errorCallback) => {
    const params = new URLSearchParams();
    params.append('nickname', data);
    BASE.post('/account/selectName', params)
        .then(response => {
            console.log(response)
            callback(response.data);
            console.log('성공')
        }).catch(exp => {
            errorCallback(exp);
            console.log('실패')
        })
}

const requestEmail = (data, callback, errorCallback) => {
    const params = new URLSearchParams();
    params.append('email', data);
    Axios.post(URL + '/account/selectEmail', params, auth)
        .then(response => {
            console.log(response);
            if (response.data.status == true)
                callback(response.data.object);
            console.log('성공')

        }).catch(exp => {
            errorCallback(exp);
            console.log('실패')
        })
}

const requestLogin = (data, callback, errorCallback) => {

    const params = new URLSearchParams();
    params.append('email', data.email);
    params.append('password', data.password);

    Axios.post(URL + '/auth/login', params)
        .then(response => {
            console.log("로그인", response.object);

            callback(response.data);
            sessionStorage.setItem("userToken", JSON.stringify({
                userToken: response.data.object.token,
                info: response.data.object.user
            }))
            sessionStorage.setItem("userToken", response.data.object.accessToken)
            sessionStorage.setItem("userEmail", response.data.object.email)
            sessionStorage.setItem("userNickname", response.data.object.nickname)
            sessionStorage.setItem("userid", response.data.object.userid)
            let payload = {
                useremail: sessionStorage.getItem("userEmail"),
                usernickname: sessionStorage.getItem("userNickname"),
                token: sessionStorage.getItem("userToken"),
                id: sessionStorage.getItem("userid"),

            }
            store.dispatch('login', payload)
        }).catch(exp => {
            console.log(exp)
            errorCallback(exp);
            console.log('실패')
        })

}

const requestJoin = (data, callback, errorCallback) => {
    const params = {
        "email": data.email,
        "nickname": data.nickName,
        "password": data.password
    }
    console.log(params)

    ///////////// response body로 받기
    BASE.post('/auth/signup', params)
        .then(response => {
            callback(response.data.object);
            console.log('성공')
        }).catch(exp => {
            errorCallback(exp);
            console.log('실패')
        })

}

const requestProfile = (data, callback, errorCallback) => {
    const params = new URLSearchParams();
    params.append("email", data);

    BASE.post('/account/getProfile', params)
        .then(response => {
            callback(response.data);
            console.log('성공')
        }).catch(exp => {
            errorCallback(exp);
            console.log('fdsdfasasdf실패')
        })

}


const requestEdit = async(data, callback, errorCallback) => {

    let options = {
        headers: { 'Content-Type': 'application/json' },
        url: URL + '/account/update',
        method: 'put',
        data: JSON.stringify(data),
    }
    let res = await Axios(options)
        .then(res => {
            callback(res.data.object);
            console.log('성공')
        }).catch(exp => {
            errorCallback(exp);
            console.log('실패')
        })

}

const requestUpload = (email, profile, callback, errorCallback) => {
    // data - eamil, photoUrl 
    console.log('프로필 업로드!!!!!!!!!')
    const params = new URLSearchParams();
    params.append("email", email);
    params.append("profile", profile);

    BASE.post('/account/uploadProfile', params)
        .then(response => {
            callback(response.data);
            console.log('성공')
        }).catch(exp => {
            errorCallback(exp);
        })
}


function requestfetchUserList() {
    //return axios.get(config.baseUrl+'news/1.json');
    return BASE.post(`/account/list`);
}

const searchUserHistory = (data, callback, errorCallback) => {

    //const params = new URLSearchParams();
    //console.log(data.email + " " + data.nickname)
    // params.append('email', data.email);
    //params.append('searchname', data.nickname);
    var params = {
        'email': data.email,
        'searchname': data.nickname,
    }
    BASE.post('/search/user', params)
        .then(response => {
            console.log(response);
            callback(response); //return type true/false 
            console.log('성공')
        }).catch(exp => {
            errorCallback(exp)
            console.log(exp.response);
            console.log('실패')
        })
}

function requestFetchUserData({ commit }, email) {
    //코딩컨벤션
    //const params = new URLSearchParams();
    var params = {
        'email': email,

    }
    console.log(email);
    BASE.post('/search/recentUser', params)
        .then(response => {
            console.log('dd')
            console.log(response);

            var jcAry = new Array();
            for (var i = 0; i < response.data.object.length; i++) {
                jcAry[i] = response.data.object[i].searchname;
                console.log(jcAry[i]);

            }
            commit('SET_RECENTUSER', jcAry);
            console.log('성공')
        }).catch(exp => {

            console.log('실패')
        })
}
//http://70.12.246.134:8080/store/create
//http://70.12.246.51:8080/store/review
const requestAddPlace = (data, callback, errorCallback) => {

    console.log('requestAddPlace first')
    console.log(data);
    // var params = {
    //     'sname': data.sname,
    //     'address': data.address,
    //     'img': data.img,
    //     'lat': data.lat,
    //     'lng': data.lng
    // }


    let options = {
        headers: { 'Content-Type': 'application/json', Authorization: 'Bearer ' + sessionStorage.getItem("userToken") },
        url: URL + '/store/review',
        method: 'post',
        data: JSON.stringify(data)
    }



    Axios(options)
        // Axios.post('http://70.12.246.51:8080/store/review', params, auth)

    .then(response => {
        console.log(response);
        callback(response); //return type true/false 
        console.log('성공')
    }).catch(exp => {
        console.log(exp.response);
        console.log('실패')
    })
    console.log('finish')
    console.log(data);
}

const requestAddReview = async(data, callback, errorCallback) => {
    // let options = {
    //     headers: { 'Content-Type': 'application/json' },
    //     url: 'http://70.12.246.134:8080/review/register',
    //     method: 'post',
    //     data: data
    // }

    // let response = await Axios(options)
    //     .then(response => {
    //         console.log(response)
    //         callback(response);
    //         console.log('성공')
    //     }).catch(exp => {
    //         errorCallback(exp);
    //         console.log('실패');
    //     })
    console.log('requestAddReview')
    let options = {
            headers: { 'Content-Type': 'application/json', Authorization: 'Bearer ' + sessionStorage.getItem("userToken") },
            url: URL + '/review/register',
            method: 'post',
            data: JSON.stringify(data)
        }
        // let review = {
        //     'hashtag': data.hashtag,
        //     'picture': data.picture,
        //     'score_kindness': data.score_kindness,
        //     'score_price': data.score_price,
        //     'score_taste': data.score_taste,
        //     'score_total': data.score_total,
        //     'store_num': data.store_num,
        //     'str': data.str,
        //     'title': data.title,
        //     'user_email': 'ssafy@naver.com',
        //     'weak': data.weak,
        // }

    //const params = new URLSearchParams();
    //params.append("review", review);
    Axios(options)
        // Axios.post('http://70.12.246.134:8080/review/register', review)
        .then(response => {
            console.log(response);
            callback(response); //return type true/false 
            console.log('review api 성공')
        }).catch(exp => {
            console.log(exp.response)
            console.log('review api 실패')
        })
}

function requestFetchAdrData({ commit }, address) {
    //Axios.get()
    let mykey = 'AIzaSyDo6iueX6Cf9SPVlg9TdH8FIdfMgRshX9k'

    return Axios.get(`https://cors-anywhere.herokuapp.com/https://maps.googleapis.com/maps/api/place/textsearch/json?input=${address}&inputtype=textquery&region=kr&language=ko&fields=formatted_addres,name,geometry&key=${mykey}`);
}

const requestUpdatePw = async(data, callback, errorCallback) => {
    // let options = {
    //     headers: { 'Content-Type': 'application/json' }
    //     , url: 'http://70.12.246.134:8080/account/changePW'
    //     , method: 'post'
    //     , data: JSON.stringify(data)
    // }

    // let response = await Axios(options)
    //     .then(response => {
    //         console.log(response)
    //         callback(response);
    //         console.log('성공')
    //     }).catch(exp => {
    //         errorCallback(exp);
    //         console.log('실패');
    //     })

    const params = new URLSearchParams();
    params.append('email', data.email);
    params.append('password', data.pw);
    // Axios({
    //     method: 'post',
    //     url: 'http://localhost:8080/account/login',
    //     data: params
    // });
    BASE.post('/account/changePW', params)
        .then(response => {
            callback(response.data);
            console.log('성공')
        }).catch(exp => {
            errorCallback(exp);
            console.log('실패')
        })
}

const UserApi = {
    requestLogin: (data, callback, errorCallback) => requestLogin(data, callback, errorCallback),
    requestJoin: (data, callback, errorCallback) => requestJoin(data, callback, errorCallback),
    requestEdit: (data, callback, errorCallback) => requestEdit(data, callback, errorCallback),
    requestName: (data, callback, errorCallback) => requestName(data, callback, errorCallback),
    requestEmail: (data, callback, errorCallback) => requestEmail(data, callback, errorCallback),
    requestsignUp: (data, callback, errorCallback) => requestsignUp(data, callback, errorCallback),
    requestProfile: (data, callback, errorCallback) => requestProfile(data, callback, errorCallback),
    requestUpload: (email, profile, callback, errorCallback) => requestUpload(email, profile, callback, errorCallback),
    searchUserHistory: (data, callback, errorCallback) => searchUserHistory(data, callback, errorCallback),
    requestAddPlace: (data, callback, errorCallback) => requestAddPlace(data, callback, errorCallback),
    requestAddReview: (data, callback, errorCallback) => requestAddReview(data, callback, errorCallback),
    requestUpdatePw: (data, callback, errorCallback) => requestUpdatePw(data, callback, errorCallback),
    requestfetchUserList,
    requestFetchUserData,
    requestFetchAdrData,
}

export default UserApi