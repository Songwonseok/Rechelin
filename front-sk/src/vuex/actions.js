import {requestfetchUserList} from '../apis/index.js';
import Userapi from '../apis/UserApi.js';
import Axios from "axios"
import router from '../main.js';

export default {
    LOADING_USERDATA(context){
        console.log('hi');
        requestfetchUserList().then(
            response =>{
            console.log(response);
            console.log('hi2');
            console.log(response.data.object);
            var jbAry = new Array();
            var jaAry = new Array();
            for(var i =0; i<response.data.object.length; i++){
                jbAry[i] = response.data.object[i].nickname;
                console.log(jbAry[i]);
            }
            for(var j =0; j<response.data.object.length; j++){
                jaAry[j] = response.data.object[j].email;
                console.log(jbAry[j]);
            }
            context.commit('SET_USER',jbAry);
            context.commit('SET_EMAIL',jaAry);
        }).catch(error =>{
            console.log(error);
        })
    },
    LOADING_RECENTUSERDATA({commit}, email){
        Userapi.requestFetchUserData({commit},email).then(
            response => {
                console.log("최근 검색 내용, action->LOADING_RECENTUSERDATA");
            }).catch(error=>{
                console.log(error);
            })
    },
    logout({commit}) {
        commit('logout')
    },
    login({commit}, payload) {
        commit('login', payload)
    },
    userpageGo({commit}, payload) {
        commit('userpageGo', payload)
    },
    userFans({commit}, payload) {
        commit('userFans', payload)
    },
    userStars({commit}, payload) {
        commit('userStars', payload)
    },
    notificationGet({commit}, payload){
        commit('notificationGet', payload)
    },
    followRequest({commit}, payload) {
        commit('followRequest', payload)
    },
    followAccept({commit}, payload) {
        commit('followAccept', payload)
    },
    followDecline({commit}, payload) {
        commit('followDecline', payload)
    },
    storeinfoGet({commit}, payload) {
        commit('storeinfoGet', payload)
    },
    tagsGet({commit}, payload) {
        commit('tagsGet', payload)
    },
    likeStore({commit}, payload) {
        commit('addBookmark', payload)
    },
    // 리뷰 관련
    reviewsGet({commit}, payload){
        Axios.get(`http://70.12.246.134:8080/review/${payload}`)
            .then(res => {
                commit('reviewsGet', res.data.object);
                router.push({name: 'reviewsOfstore',
                    query: {
                        reviewsOfstore: res.data.object
                    }})
            }).catch(exp => {
                console.log('실패')
            })
    },
    // 리뷰의 댓글 관련
    commentsOfreview({commit}, payload) {
        //리뷰 아이디 집어 넣으면, 해당 리뷰 아이디를 가진 댓글을 불러오겠지
        console.log(payload, '????')
        Axios.get(`http://70.12.246.134:8080/review/comment/${payload}`)
            .then(res => {
                console.log(res, '리뷰의 댓글 가져오기')
                var data = {
                    reviewID: payload,
                    comments: res.data.object
                }
                commit('commentsOfreview', data)
                console.log('요청 성공')
            }).catch(exp => {
                console.log('실패')
            })
       
    },
    createComment({commit}, newComment) {

        Axios.post(`http://70.12.246.134:8080/review/comment`, newComment)
        .then(res => {
            console.log('요청 성공')
            commit('createComment', newComment)
        }).catch(exp => {
            console.log('실패')
        })
        
    },
    commentDelete({commit}, num) {

        Axios.delete(`http://70.12.246.134:8080/review/comment/${num}`)
                    .then(res => {
                        console.log('댓글 삭제 성공')
                    }).catch(exp => {
                        console.log('실패')
                    })
    },
    reviewDelete({commit}, num) {
        
        Axios.delete(`http://70.12.246.134:8080/review/delete`, num)
                    .then(res => {
                        console.log('댓글 삭제 성공')
                    }).catch(exp => {
                        console.log('실패')
                    })
    },
    addStore({commit}, storeInfo) {

    }
    
  
    

}
