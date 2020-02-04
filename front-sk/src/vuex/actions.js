
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
    LOADING_RECENTUSERDATA({commit}, email){
        Userapi.requestFetchUserData({commit},email).then(
            response => {
                console.log("최근 검색 내용, action->LOADING_RECENTUSERDATA");
            }).catch(error=>{
                console.log(error);
            })
    },
    FETCH_ADR({commit}, address){ //google 에서부터 음식점 주소를 FETCH 해옴
        Userapi.requestFetchAdrData({commit},address).then(
            response => {
                // formattedAddress : [],
                // locationIat : [],
                // locationIng : [],
                // name : [],
                // photeReference :[],
                
                console.log(response);
                console.log(response.data.results.length);
                var jadrAry = new Array();
                var jloclatAry = new Array();
                var jloclngAry = new Array();
                var jnaAry = new Array();
                var jphoReAry = new Array();
                

                var aJsonArray = new Array();
                var aJson = new Object();


                for(var i =0; i<response.data.results.length; i++){
                    //aJson.formatted_address[i] = response.data.results[i].formatted_address;
                    jadrAry[i] = response.data.results[i].formatted_address;
                    var item = {};
                    item['주소']= response.data.results[i].formatted_address;
                
                    //jloclatAry[i] = response.data.results[i].geometry.location.lat;
                    
                    //item['lat']= response.data.results[i].geometry.location.lat;
                    jloclngAry[i] = response.data.results[i].geometry.location.lng;
                    //item['lng']= response.data.results[i].geometry.location.lng;
                    
                    jnaAry[i] = response.data.results[i].name;
                    item['지역명']= response.data.results[i].name;
                    
                    let e = null;
                    if(response.data.results[i].hasOwnProperty('photos')){
                        //item['photo_reference']= response.data.results[i].photos[0].photo_reference
                        jphoReAry[i] = response.data.results[i].photos.photo_reference
                    }else
                        jphoReAry[i] = 'photo 값이 없습니다.';
                        //item['photo_reference']= 'photo 값이 없습니다.';
                    
                    //jphoReAry[i] = response.data.results[i].photos.photo_reference;
                    JSON.stringify(item);
                    aJsonArray.push(item);

                    console.log(item);

                }
               
                 commit('SET_ADR',jadrAry);
                 commit('SET_LOCX',jloclatAry);
                 commit('SET_LOCY',jloclngAry);
                 commit('SET_NAME',jnaAry);
                 commit('SET_PHOTOR',jphoReAry);
                 commit('SET_GOOGLEMAP',aJsonArray);
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

}
