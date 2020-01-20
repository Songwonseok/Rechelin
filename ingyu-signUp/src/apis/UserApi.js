/*
 User API 예시
 */
import {AXIOS} from '../http-commons.js';
import Vue from 'vue'

import 'v-slim-dialog/dist/v-slim-dialog.css'
import SlimDialog from 'v-slim-dialog'
const requestLogin = (data,callback,errorCallback) => {
    //백앤드와 로그인 통신하는 부분
    console.log(data.email);
    console.log(data.password);        
    var params = new URLSearchParams();
    params.append('email',data.email);
    params.append('password',data.password);
    Vue.use(SlimDialog);
    AXIOS.post(`/account/login`, params)
        .then(response => {
             // JSON responses are automatically parsed.
             callback(response.data.data);
             console.log(response.data);
             // data.user.email = response.data.email;
             //data.user.password = response.data.password;
             if(response.data.data == 'success'){
                 console.log('login success123123')
                 
                 
             }
             //console.log(response.data);
             })
           .catch(e => {
            errorCallback();
           console.log('connect fail');
        })

}

const UserApi = {
    requestLogin:(data,callback,errorCallback)=>requestLogin(data,callback,errorCallback)
}

export default UserApi
