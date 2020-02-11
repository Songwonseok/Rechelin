import Axios from "axios"

const auth = {
    headers: {
        Authorization: 'Bearer ' + sessionStorage.getItem("userToken")
    }
}

const searchUserHistory = (data, callback, errorCallback) => {

    //const params = new URLSearchParams();
    //console.log(data.email + " " + data.nickname)
    // params.append('email', data.email);
    //params.append('searchname', data.nickname);
    var params = {
        'email': data.email,
        'searchname': data.nickname,
    }
    Axios.post(URL + '/search/user', params, auth)
        .then(response => {
            console.log(response);
            callback(response); //return type true/false 
            console.log('성공')
        }).catch(exp => {
            errorCallback(exp)
            console.log(exp.response);
            console.log('실패')
        })
}

function requestFetchUserData({ commit }, email) {
    //코딩컨벤션
    //const params = new URLSearchParams();
    var params = {
        'email': email,

    }
    console.log(email);
    Axios.post(URL + '/search/recentUser', params)
        .then(response => {
            console.log('dd')
            console.log(response);

            var jcAry = new Array();
            for (var i = 0; i < response.data.object.length; i++) {
                jcAry[i] = response.data.object[i].searchname;
                console.log(jcAry[i]);

            }
            commit('SET_RECENTUSER', jcAry);
            console.log('성공')
        }).catch(exp => {

            console.log('실패')
        })
}



function requestFetchAdrData({ commit }, address) {
    //Axios.get()
    let mykey = 'AIzaSyDo6iueX6Cf9SPVlg9TdH8FIdfMgRshX9k'

    return Axios.get(`https://cors-anywhere.herokuapp.com/https://maps.googleapis.com/maps/api/place/textsearch/json?input=${address}&inputtype=textquery&region=kr&language=ko&fields=formatted_addres,name,geometry&key=${mykey}`);
}
const SearchApi = {
    searchUserHistory: (data, callback, errorCallback) => searchUserHistory(data, callback, errorCallback),
    requestFetchUserData,
    requestFetchAdrData,
}

export default SearchApi