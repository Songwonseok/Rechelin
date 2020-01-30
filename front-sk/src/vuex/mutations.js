import Axios from "axios"
import router from '../main'

export default {
    updateName(state, name) {
        state.name = name;
    },
    updateEmail(state, email) {
        state.email = email;    
    },
    updatePassword(state, password) {
        state.password = password;
    },
    updateUser(state, user) {
        state.user = user;
    },
    updateCode(state, code) {
        state.code = code;
    },
    updateInfo(state,info){
        state.info = info;
    },
    updatePhone(state,phone){
        state.phone = phone;
    },
    updateIsSubmit(state,submit){
        state.phone = submit;
    },
    //로그인, 로그아웃
    logout(state){
        state.accessToken = null
        state.userEmail = null
        state.userNickname = null
        sessionStorage.removeItem("userToken")
        sessionStorage.removeItem("userEmail")
        sessionStorage.removeItem("userNickname")
    },
    login(state, payload) {
        this.state.userEmail = payload.useremail
        this.state.userNickname = payload.usernickname
        this.state.accessToken = payload.token
    },
    userpageGo(state, payload) {
        const params = new URLSearchParams();
        params.append('email', payload);
        Axios.post('http://70.12.246.51:8080/account/selectEmail', params)
        .then(response => {
            router.push({name: "UserPage", params: {
                id: payload,
            }, query: {
                userInfo: response.data.object
            }})
        }).catch(exp => {
            console.log('실패')
        })
    },
    userStars(state, payload) {
        const params = new URLSearchParams();
        params.append('email', payload);
        Axios.post('http://70.12.246.51:8080/follow/starList', params)
        .then(response => {
        this.state.userPageInfo.stars.push(response.data.object)
        console.log(this.state.userPageInfo.stars, '스타스타스타')
        }).catch(exp => {
            console.log('실패')
        })
        
    },
    userFans(state, payload) {
        const params = new URLSearchParams();
        params.append('email', payload);
        Axios.post('http://70.12.246.51:8080/follow/fanList', params)
        .then(response => {
           this.state.userPageInfo.fans.push(response.data.object)
        }).catch(exp => {
            console.log('실패')
        })
    }
   
}