import Axios from "axios"
import store from "../vuex/store"
// import Api from "axios.js"

 const URL = 'http://70.12.246.134:8080' // 김주연 ip
 //const URL = 'http://54.180.160.87:8080'  //new DB ip
//const URL = 'http://70.12.246.51:8080' //  조장님 ip
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


const requestProfile = (data, callback, errorCallback) => {
    const params = new URLSearchParams();
    params.append("email", data);

    Axios.post(URL + '/account/getProfile', params)
        .then(response => {
            callback(response.data);
            console.log('성공')
        }).catch(exp => {
            errorCallback(exp);
            console.log('fdsdfasasdf실패')
        })

}


const requestEdit = (data, callback, errorCallback) => {
    Axios.put(URL + '/account/update', data, auth)
        .then(response => {
            if (response.data.status == true)
                callback(response.data.object);

            console.log('회원정보 수정 성공')
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
    return Axios.post(URL + `/account/list`, auth);
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
    Axios.post(URL + '/account/changePW', params)
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
    requestEdit: (data, callback, errorCallback) => requestEdit(data, callback, errorCallback),
    requestName: (data, callback, errorCallback) => requestName(data, callback, errorCallback),
    requestEmail: (data, callback, errorCallback) => requestEmail(data, callback, errorCallback),
    requestsignUp: (data, callback, errorCallback) => requestsignUp(data, callback, errorCallback),
    requestUserpage: (data, callback, errorCallback) => requestUserpage(data, callback, errorCallback),
    requestProfile: (data, callback, errorCallback) => requestProfile(data, callback, errorCallback),
    requestUpload: (email, profile, callback, errorCallback) => requestUpload(email, profile, callback, errorCallback),
    requestUpdatePw: (data, callback, errorCallback) => requestUpdatePw(data, callback, errorCallback),
    requestfetchUserList,

}

export default UserApi