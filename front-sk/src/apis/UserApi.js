import Axios from "axios"
import store from "../vuex/store"

/*
 User API 예시
 */
const requestsignUp = async(data, callback, errorCallback) => {
    // const params = new URLSearchParams();
    // params.append('request', data);   
    // console.log(params);
    let options = {
        headers: { 'Content-Type': 'application/json' }
        , url: 'http://70.12.246.51:8080/account/signup'
        , method: 'post'
        , data: JSON.stringify(data)
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
    Axios.post('http://70.12.246.51:8080/account/selectName', params)
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
    
    Axios.post('http://70.12.246.51:8080/account/selectEmail', params)
        .then(response => {
            console.log(response);
            callback(response.data);
            console.log('성공')

        }).catch(exp => {
            errorCallback(exp);
            console.log('실패')
        })
}

const requestLogin = (data, callback, errorCallback) => {
    //백앤드와 로그인 통신하는 부분
    // http://localhost:8080/account/login
    // Post 
    const params = new URLSearchParams();
    params.append('email', data.email);
    params.append('password', data.password);
    // Axios({
    //     method: 'post',
    //     url: 'http://localhost:8080/account/login',
    //     data: params
    // });
    Axios.post('http://70.12.246.51:8080/account/login', params)
        .then(response => {
            callback(response.data);
            sessionStorage.setItem("userToken", JSON.stringify({
                userToken: response.data.object.token,
                info:response.data.object.user
            }))
            sessionStorage.setItem("userToken", response.data.object.token)
            sessionStorage.setItem("userEmail", response.data.object.user.email)
            sessionStorage.setItem("userNickname", response.data.object.user.nickname)
        let payload = {
            useremail: sessionStorage.getItem("userEmail"),
            usernickname: sessionStorage.getItem("userNickname"),
            token: sessionStorage.getItem("userToken")
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
    Axios.post('http://localhost:8080/account/signup', params)
        .then(response => {
            callback(response.data.object);
            console.log('성공')
        }).catch(exp => {
            errorCallback(exp);
            console.log('실패')
        })

}

const requestEdit = async(data, callback, errorCallback) => {

    let options = {
        headers: { 'Content-Type': 'application/json' },
        url: 'http://70.12.246.51:8080/account/update',
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
const UserApi = {
    requestLogin: (data, callback, errorCallback) => requestLogin(data, callback, errorCallback),
    requestJoin: (data, callback, errorCallback) => requestJoin(data, callback, errorCallback),
    requestEdit: (data, callback, errorCallback) => requestEdit(data, callback, errorCallback),
    requestName: (data, callback, errorCallback) => requestName(data, callback, errorCallback),
    requestEmail: (data, callback, errorCallback) => requestEmail(data, callback, errorCallback),
    requestsignUp : (data, callback, errorCallback) => requestsignUp(data, callback, errorCallback),
}

export default UserApi 