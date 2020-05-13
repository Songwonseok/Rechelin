import Axios from "axios"

 const URL = "http://54.180.160.87:8080" //aws
// const URL = 'http://70.12.246.134:8080' // 김주연 ip
    //const URL = 'http://70.12.246.51:8080' //  조장님 ip
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

    const params = new URLSearchParams();
    params.append('id', data.id);
    params.append('searchname', data.nickname);
    

    Axios.post(URL + '/search/user', params, auth)
        .then(response => {
          
            callback(response); //return type true/false 
      
        }).catch(exp => {
            errorCallback(exp)
          
        })
}

function requestFetchUserData({ commit }, id) {
    //코딩컨벤션
    //const params = new URLSearchParams();
    const params = new URLSearchParams();
    params.append('id', id);
    

    Axios.post(URL + '/search/recentUser', params)
        .then(response => {
   

            var jcAry = new Array();
            for (var i = 0; i < response.data.object.length; i++) {
                jcAry[i] = response.data.object[i].searchname;
              

            }
            commit('SET_RECENTUSER', jcAry);
            
        }).catch(exp => {

            
        })
}



function requestFetchAdrData({ commit }, address) {
    //Axios.get()
    let mykey = 'AIzaSyDo6iueX6Cf9SPVlg9TdH8FIdfMgRshX9k'

    return Axios.get(`https://cors-anywhere.herokuapp.com/https://maps.googleapis.com/maps/api/place/textsearch/json?input=${address}&inputtype=textquery&region=kr&language=ko&fields=formatted_addres,name,geometry&location=37.492326399999996,127.03943509999999&radius=30000&key=${mykey}`);
}
const SearchApi = {
    searchUserHistory: (data, callback, errorCallback) => searchUserHistory(data, callback, errorCallback),
    requestFetchUserData,
    requestFetchAdrData,
}

export default SearchApi