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
    SET_USER(state, searchUser){
        state.searchUser.nickname = searchUser;
    },
    SET_EMAIL(state, searchEmail){
        state.searchUser.email = searchEmail;
    },
    SET_RECENTUSER(state, recentUser){
        state.recentUser = recentUser;
    }
}
