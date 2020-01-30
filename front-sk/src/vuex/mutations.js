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
        sessionStorage.removeItem("userInfo")
    },
    login(state, payload) {
        state.userEmail = payload.useremail
        state.userNickname = payload.usernickname
        state.accessToken = payload.token

    }
}