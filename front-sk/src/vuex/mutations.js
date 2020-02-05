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
    updateInfo(state, info) {
        state.info = info;
    },
    updatePhone(state, phone) {
        state.phone = phone;
    },
    updateIsSubmit(state, submit) {
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
        const params = new URLSearchParams();
        console.log(payload)
        params.append('email', payload);
        Axios.post('http://70.12.246.51:8080/account/selectEmail', params)
            .then(response => {
                router.push({
                    name: "UserPage",
                    params: {
                        id: payload,
                    },
                    query: {
                        userInfo: response.data.object
                    }
                })
            state.userPageInfo.nickname = response.data.object.nickname
            state.userPageInfo.email = response.data.object.email
            }).catch(exp => {
                console.log('실패')
            })
    },
    userStars(state, payload) {
        const params = new URLSearchParams();
        params.append('email', payload);
        Axios.post('http://70.12.246.51:8080/follow/starList', params)
            .then(response => {
                this.state.userPageInfo.stars = response.data.object
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
                this.state.userPageInfo.fans = response.data.object
            }).catch(exp => {
                console.log('실패')
            })
    },
    notificationGet(state, payload) {
        const params = new URLSearchParams();
        params.append('email', payload)
        Axios.post('http://70.12.246.51:8080/follow/alarmList', params)
            .then(res => {
                this.state.notifications = res.data.object

            }).catch(exp => {
                console.log('실패')
            })
    },
    followRequest(state, payload) {
        const params = new URLSearchParams();
        params.append('fan', payload.fan)
        params.append('star', payload.star)
        Axios.post('http://70.12.246.51:8080/follow/request', params)
            .then(res => {
                console.log('요청 성공')
            }).catch(exp => {
                console.log('실패')
            })
    },
    followAccept(state, payload) {
        const params = new URLSearchParams();
        params.append('fan', payload.fan)
        params.append('star', payload.star)
        Axios.post('http://70.12.246.51:8080/follow/accept', params)
            .then(res => {
                console.log('요청 성공')
            }).catch(exp => {
                console.log('실패')
            })
    },
    followDecline(state, payload) {
        const params = new URLSearchParams();
        params.append('fan', payload.fan)
        params.append('star', payload.star)
        Axios.post('http://70.12.246.51:8080/follow/decline', params)
            .then(res => {
                console.log('요청 성공')
            }).catch(exp => {
                console.log('실패')
            })
    },
    storeinfoGet(state, payload){
        const params = new URLSearchParams();
        params.append('id', payload.id)
        Axios.post('http://70.12.246.51:8080/store/selectOne', params)
            .then(res => {
                console.log('요청 성공')
            }).catch(exp => {
                console.log('실패')
            })

    },
    tagsGet(state, payload) {
        const params = new URLSearchParams();
        params.append('id', payload.id) // store id로 store에 걸린 tag 검색
        Axios.post('', params)
            .then(res => {
                console.log('요청 성공')
            }).catch(exp => {
                console.log('실패')
            })
    },
    commentsOfreview(state, payload) {
        const params = new URLSearchParams();
        //리뷰 아이디 집어 넣으면, 리뷰의 댓글들 목록이 올 것.
        params.append('id', payload.id)
        Axios.post('', params)
            .then(res => {
                console.log('요청 성공')
                state.comments = ''
            }).catch(exp => {
                console.log('실패')
            })
    },
     createComment(state, payload) {
        const params = new URLSearchParams();
        //리뷰 아이디 집어 넣으면, 해당 리뷰 아이디를 가진 댓글이 생성될 것
        params.append('id', payload.id)
        Axios.post('', params)
            .then(res => {
                console.log('요청 성공')
            }).catch(exp => {
                console.log('실패')
            })
     }

}
