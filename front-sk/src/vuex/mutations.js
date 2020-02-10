import Axios from "axios"
import router from '../main'

const URL = 'http://70.12.246.134:8080' // 김주연 ip
    // const URL = 'http://70.12.246.51:8080' //  조장님 ip

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
    updateInfo(state, info) {
        state.info = info;
    },
    updatePhone(state, phone) {
        state.phone = phone;
    },
    updateIsSubmit(state, submit) {
        state.phone = submit;
    },
    SET_USER(state, searchUser) {
        state.searchUser.nickname = searchUser;
    },
    SET_EMAIL(state, searchEmail) {
        state.searchUser.email = searchEmail;
    },
    SET_RECENTUSER(state, recentUser) {
        state.recentUser = recentUser;
    },
    //로그인, 로그아웃
    logout(state) {
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
        state.userPageInfo.nickname = payload.nickname
        state.userPageInfo.email = payload.email
    },
    userStars(state, payload) {
        this.state.userPageInfo.stars = payload

    },
    userFans(state, payload) {
        this.state.userPageInfo.fans = payload
        
    },
    notificationGet(state, payload) {
        this.state.notifications = payload
    },
    // 스토어 관련
    storeinfoGet(state, payload){
        this.state.dining = payload
    },

    tagsGet(state, payload) {
    
    },
    // 리뷰의 댓글관련
    commentsOfreview(state, payload) {
        state.reviewInfo = payload.reviewInfo
        state.commentsOfreview = payload.comments
    },
     createComment(state, payload) {
        let list = [...state.commentsOfreview]
        list.push(payload)
        state.commentsOfreview = [...list]
        state.newReturnComment = payload
        
     },
     commentDelete(state, comment) {
        let pos = state.commentsOfreview.indexOf(comment)
        let list = [...state.commentsOfreview]
        list.splice(pos, 1)
        state.commentsOfreview = [...list];
     },
     // 리뷰 관련
    reviewsGet(state, payload) {
        state.reviewsOfstore = payload
     },
    reviewDetail(state, payload) {
        state.reviewDetail = payload

    },
    SET_GOOGLEMAP_TOTAL(state, googlemap_total) {
        state.googleStorePlace = googlemap_total;
    },
    SET_GOOGLEMAP(state, googlemap) {
        state.googleStorePlaceView = googlemap;
    }

}
        
    

