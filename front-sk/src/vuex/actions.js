import {requestfetchUserList} from '../apis/index.js';
import Userapi from '../apis/UserApi.js';

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
    LOADING_RECENTUSERDATA(context){
        Userapi.requestFetchUserData().then(
            response => {
                var jcAry = new Array();
                for(var i =0; i<response.data.object.length; i++){
                    jcAry[i] = response.data.object[i].nickname;
                    console.log(jcAry[i]);
                    context.commit('SET_RECENTUSER', jcAry);
                }
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
    reviewsGet({commit}, payload){
        commit('reviewsGet', payload)
    }

}
