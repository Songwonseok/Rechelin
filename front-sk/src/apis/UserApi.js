import Axios from "axios"
import store from "../vuex/store"
import router from '../main.js';

// import Api from "axios.js"
const URL = "http://54.180.160.87:8080" //aws
    // const URL = 'http://70.12.246.134:8080' // 김주연 ip
    //const URL = 'http://54.180.160.87:8080'  //new DB ip

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
    Axios.post(URL + '/account/selectName', params)
        .then(response => {

            console.log(response)
            callback(response.data);

            console.log('성공')
        }).catch(exp => {
            alert('이미 존재하는 닉네임이 있습니다')
            errorCallback(exp);
            console.log('실패')
        })
}

const requestEmail = (data, callback, errorCallback) => {
    const params = new URLSearchParams();
    params.append('email', data);
    Axios.post(URL + '/account/selectEmail', params)
        .then(response => {
            console.log(response);

            // if (response.data.status == true)
            callback(response.data);
            console.log('성공')

        }).catch(exp => {
            alert('이미 존재하는 메일이 있습니다.')
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
            store.commit('login', payload)
        }).catch(exp => {
            console.log(exp)
            errorCallback(exp);
            console.log('실패')
        })

}



const requestEdit = (data, callback, errorCallback) => {

    let options = {
        headers: {
            'Content-Type': 'application/json',
            Authorization: 'Bearer ' + sessionStorage.getItem("userToken")
        },
        url: URL + '/account/update',
        method: 'put',
        data: JSON.stringify(data)
    }


    Axios(options)
        .then(response => {
            if (response.data.status == true)
                callback(response.data.object);
            router.push({
                name: 'userpage',
                params: {
                    id: sessionStorage.getItem('userid')
                }
            })
        }).catch(exp => {
            errorCallback(exp);
            console.log('실패')
        })

}

const requestUpload = (id, profile, callback, errorCallback) => {
    // data - eamil, photoUrl 
    console.log('프로필 업로드!!!!!!!!!')
    const params = new URLSearchParams();
    params.append("id", id);
    params.append("profile", profile);

    Axios.post(URL + '/account/uploadProfile', params, auth)
        .then(response => {
            if (response.data.status == true)
                callback(response.data.object);
            console.log('성공')
        }).catch(exp => {
            errorCallback(exp);
        })
}

const requestUserpage = (data, callback, errorCallback) => {

    Axios.post(URL + '/account/selectEamil', data)
        .then(response => {
            callback(response.data.object);
            console.log('성공')
        }).catch(exp => {
            errorCallback(exp);
            console.log('실패')
        })

}

function requestfetchUserList() {
    //return axios.get(config.baseUrl+'news/1.json');
    
    console.log('requestfetchUserList')

     return Axios.get(URL + `/account/list`, auth);
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
    params.append('id', data.id);
    params.append('password', data.pw);
    // Axios({
    //     method: 'post',
    //     url: 'http://localhost:8080/account/login',
    //     data: params
    // });
    Axios.post(URL + '/account/changePW', params, auth)
        .then(response => {
            callback(response.data);
            console.log('성공')
        }).catch(exp => {
            errorCallback(exp);
            console.log('실패')
        })
}

const requestId = async(data, callback, errorCallback) => {
    const params = new URLSearchParams();
    params.append('id', data);

    Axios.post(URL + '/account/selectId', params, auth)
        .then(response => {
            callback(response.data);
            console.log('성공')
        }).catch(exp => {
            errorCallback(exp);
            console.log('실패', exp)
        })
}

function requestUserRanking() {
    return Axios.get(URL + '/account/userTop', auth);
}


function requestUserReviewCnt(data) {
    const params = new URLSearchParams();
    params.append('num', data);
    return Axios.post(URL + '/review/myList', params, auth);
}

function requestUserDelete(id){
    // const params = new URLSearchParams();
    // params.append('id',id);
    return Axios.delete(URL + '/account/delete/' +id, auth);
}

const UserApi = {
    requestLogin: (data, callback, errorCallback) => requestLogin(data, callback, errorCallback),
    requestEdit: (data, callback, errorCallback) => requestEdit(data, callback, errorCallback),
    requestName: (data, callback, errorCallback) => requestName(data, callback, errorCallback),
    requestEmail: (data, callback, errorCallback) => requestEmail(data, callback, errorCallback),
    requestsignUp: (data, callback, errorCallback) => requestsignUp(data, callback, errorCallback),
    requestUserpage: (data, callback, errorCallback) => requestUserpage(data, callback, errorCallback),
    requestUpload: (id, profile, callback, errorCallback) => requestUpload(id, profile, callback, errorCallback),
    requestUpdatePw: (data, callback, errorCallback) => requestUpdatePw(data, callback, errorCallback),
    requestfetchUserList,
    requestUserRanking,
    requestUserReviewCnt,
    requestUserDelete,
    requestId: (data, callback, errorCallback) => requestId(data, callback, errorCallback),


}

export default UserApi