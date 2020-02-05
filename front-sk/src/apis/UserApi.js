import Axios from "axios"
import store from "../vuex/store"
import Vue from 'vue';
import AxiosPlugin from 'vue-axios-cors';
 
Vue.use(AxiosPlugin)
//header('Access-Control-Allow-Origin', 'http://localhost:3000')
const config = {
    baseUrl : 'https://maps.googleapis.com/maps/api/place/textsearch/json?input='
}
const requestsignUp = async(data, callback, errorCallback) => {
    // const params = new URLSearchParams();
    // params.append('request', data);   
    // console.log(params);
    let options = {
        headers: { 'Content-Type': 'application/json' },
        url: 'http://70.12.246.51:8080/account/signup',
        method: 'post',
        data: JSON.stringify(data)
    }

    let response = await Axios(options)
        .then(response => {
            console.log(response)
            callback(response);
            console.log('성공')
        }).catch(exp => {
            errorCallback(exp);
            console.log('실패');
        })
}

const requestName = (data, callback, errorCallback) => {
    const params = new URLSearchParams();
    params.append('nickname', data);
    Axios.post('http://70.12.246.51:8080/account/selectName', params)
        .then(response => {
            console.log(response)
            callback(response.data);
            console.log('성공')
        }).catch(exp => {
            errorCallback(exp);
            console.log('실패')
        })
}

const requestEmail = (data, callback, errorCallback) => {
    const params = new URLSearchParams();
    params.append('email', data);

    Axios.post('http://70.12.246.51:8080/account/selectEmail', params)
        .then(response => {
            console.log(response);
            callback(response.data);
            console.log('성공')

        }).catch(exp => {
            errorCallback(exp);
            console.log('실패')
        })
}

const requestLogin = (data, callback, errorCallback) => {
    //백앤드와 로그인 통신하는 부분
    // http://localhost:8080/account/login
    // Post 
    const params = new URLSearchParams();
    params.append('email', data.email);
    params.append('password', data.password);
    // Axios({
    //     method: 'post',
    //     url: 'http://localhost:8080/account/login',
    //     data: params
    // });
    Axios.post('http://70.12.246.51:8080/account/login', params)
        .then(response => {
            callback(response.data);
            sessionStorage.setItem("userToken", JSON.stringify({
                userToken: response.data.object.token,
                info:response.data.object.user
            }))
            sessionStorage.setItem("userToken", response.data.object.token)
            sessionStorage.setItem("userEmail", response.data.object.user.email)
            sessionStorage.setItem("userNickname", response.data.object.user.nickname)
        let payload = {
            useremail: sessionStorage.getItem("userEmail"),
            usernickname: sessionStorage.getItem("userNickname"),
            token: sessionStorage.getItem("userToken")
        } 
        store.dispatch('login', payload)
        }).catch(exp => {
            console.log(exp)
            errorCallback(exp);
            console.log('실패')
        })

}

const requestJoin = (data, callback, errorCallback) => {
    const params = {
        "email": data.email,
        "nickname": data.nickName,
        "password": data.password
    }
    console.log(params)

    ///////////// response body로 받기
    Axios.post('http://localhost:8080/account/signup', params)
        .then(response => {
            callback(response.data.object);
            console.log('성공')
        }).catch(exp => {
            errorCallback(exp);
            console.log('실패')
        })

}

const requestProfile = (data, callback, errorCallback) => {
    const params = new URLSearchParams();
    params.append("email", data);

    Axios.post('http://70.12.246.51:8080/account/getProfile', params)
        .then(response => {
            callback(response.data);
            console.log('성공')
        }).catch(exp => {
            errorCallback(exp);
            console.log('fdsdfasasdf실패')
        })

}


const requestEdit = async(data, callback, errorCallback) => {

    let options = {
        headers: { 'Content-Type': 'application/json' },
        url: 'http://70.12.246.51:8080/account/update',
        method: 'put',
        data: JSON.stringify(data),
    }
    let res = await Axios(options)
        .then(res => {
            callback(res.data.object);
            console.log('성공')
        }).catch(exp => {
            errorCallback(exp);
            console.log('실패')
        })

}

const requestUpload = (email, profile, callback, errorCallback) => {
    // data - eamil, photoUrl 
    console.log('프로필 업로드!!!!!!!!!')
    const params = new URLSearchParams();
    params.append("email", email);
    params.append("profile", profile);

    Axios.post('http://70.12.246.51:8080/account/uploadProfile', params)
        .then(response => {
            callback(response.data);
            console.log('성공')
        }).catch(exp => {
            errorCallback(exp);
        })
}

const requestUserpage = (data, callback, errorCallback) => {

    Axios.post('http://70.12.246.51:8080/account/selectEamil',data)
    .then(response => {
        callback(response.data.object);
        console.log('성공')
    }).catch(exp => {
        errorCallback(exp);
        console.log('실패')
    })
    
}

function requestfetchUserList(){
    //return axios.get(config.baseUrl+'news/1.json');
    return Axios.post(`http://70.12.246.51:8080/account/list`);
}

const searchUserHistory = async(data, callback, errorCallback) => {

    const params = new URLSearchParams();
    params.append('email', data.email);
    params.append('searchname', data.nickname);
    Axios.post('http://70.12.246.51:8080/search/user/', params)
        .then(response => {
            console.log(response);
            callback(response); //return type true/false 
            console.log('성공')
        }).catch(exp => {
            errorCallback(exp)
            console.log('실패')
        })
}

function requestFetchUserData({commit}, email){
    //코딩컨벤션
    const params = new URLSearchParams();
    params.append('email', email);
    
    Axios.post('http://70.12.246.51:8080/search/recentUser', params)
    .then(response => {
        console.log('dd')
        console.log(response);
      
                var jcAry = new Array();
                for(var i =0; i<response.data.object.length; i++){ 
                    jcAry[i] = response.data.object[i].searchname;
                    console.log(jcAry[i]);
                   
                }
                commit('SET_RECENTUSER', jcAry);
        console.log('성공')
    }).catch(exp => {
       
        console.log('실패')
    })   
}
//http://70.12.246.134:8080/store/create
//http://70.12.246.51:8080/store/review
const requestAddPlace = async(data, callback, errorCallback) => {
        const params = new URLSearchParams();
        console.log(data.address);
        console.log(data.lat);
        console.log(data.lng);
        console.log(data.sname);
        console.log(data.img);
         params.append('address', data.address);
         params.append('lat',data.lat);
         params.append('lng',data.lng);
         params.append('sname',data.sname);
         params.append('img',data.img);       
        // var temp = {};
        // var aJsonArray2 = new Array(); //전체 데이터 
        // temp['address'] = data.address;
        // temp['lat'] = data.lat;
        // temp['lng'] = data.lng;
        // temp['sname'] = data.sname;
        // temp['img'] = data.img;
        //JSON.stringify(temp);
    //console.log(temp);
    //console.log(data);

    
    
    Axios.post('http://70.12.246.51:8080/store/review', params)
        .then(response => {
            console.log(response);
            callback(response); //return type true/false 
            console.log('성공')
        }).catch(exp => {
            
            console.log('실패')
        })
    // var data2 = JSON.stringify({
    //     address : data.address,
    //     lat : data.lat,
    //     lng : data.lng,
    //     sname : data.sname,
    //     img : data.img,
    // });
    // let options = {
    //     accept:  'application/json' ,
    //     url: 'http://70.12.246.134:8080/store/create',
    //     method: 'post',
    //     data: JSON.stringify(data)
    // }
    // let response = await Axios(options)
    // .then(response => {
    //     console.log(response)
    //     callback(response);
    //     console.log('성공')
    // }).catch(exp => {
    //     errorCallback(exp);
    //     console.log('실패');
    // })
    // console.log(options.data);
    //'http://70.12.246.134:8080/store/create'
    console.log(data);


    // let options = {
    //     headers: { 'Content-Type': 'application/json', 
    //                 'Accept' : 'application/json',
    // },
    //     url: 'http://70.12.246.51:8080/store/review',
    //     method: 'post',
    //     data: JSON.stringify(data2)
    // }

    // let response = await Axios(options)
    //     .then(response => {
    //         console.log(response)
    //         callback(response);
    //         console.log('성공')
    //     }).catch(exp => {
    //         errorCallback(exp);
    //         console.log('실패');
    //     })
    
        // Axios.post(
        //     'http://70.12.246.51:8080/store/review', JSON.stringify(JSON.stringify(data)),{
         
        //         headers: { "content-type": "application/json",
        //         "Accept": "application/json"},
        //     }
        // )
        // .then(response => {
        //     console.log(response)
        //     callback(response);
        //     console.log('성공')
        // }).catch(error => {
        //     if(error.response){
        //         this.errors(error.response.message);
        //       }else if (error.request) {
        //         console.log('error.request');
        //       } else {
        //         console.log('Error', error);
        //       }
        //       console.log("rejected");
        //     console.log('실패');
        // })


        //  const params = new URLSearchParams();
        //   params.append('data', temp);   
        // Axios.post('http://70.12.246.51:8080/store/review', params)
        // .then(response => {
        //     console.log('dd')
        //     callback(response); //return type true/false 
        //     console.log('성공')
        // }).catch(exp => {
        //    console.log(exp);
        //     console.log('실패')
        // })   
}


function requestFetchAdrData({commit}, address){
    //Axios.get()
     let mykey = 'AIzaSyDo6iueX6Cf9SPVlg9TdH8FIdfMgRshX9k'
     
    return Axios.get(`https://cors-anywhere.herokuapp.com/https://maps.googleapis.com/maps/api/place/textsearch/json?input=${address}&inputtype=textquery&region=kr&language=ko&fields=formatted_addres,name,geometry&key=${mykey}`);   
}
const UserApi = {
    requestLogin: (data, callback, errorCallback) => requestLogin(data, callback, errorCallback),
    requestJoin: (data, callback, errorCallback) => requestJoin(data, callback, errorCallback),
    requestEdit: (data, callback, errorCallback) => requestEdit(data, callback, errorCallback),
    requestName: (data, callback, errorCallback) => requestName(data, callback, errorCallback),
    requestEmail: (data, callback, errorCallback) => requestEmail(data, callback, errorCallback),
    requestsignUp : (data, callback, errorCallback) => requestsignUp(data, callback, errorCallback),
    requestUserpage : (data, callback, errorCallback) => requestUserpage(data, callback, errorCallback),
    requestProfile: (data, callback, errorCallback) => requestProfile(data, callback, errorCallback),
    requestUpload: (email, profile, callback, errorCallback) => requestUpload(email, profile, callback, errorCallback),
    searchUserHistory : (data,callback, errorCallback) => searchUserHistory(data,callback,errorCallback),
    requestAddPlace : (data,callback,errorCallback) => requestAddPlace(data,callback,errorCallback),
    requestfetchUserList,
    requestFetchUserData,
    requestFetchAdrData,
}

export default UserApi