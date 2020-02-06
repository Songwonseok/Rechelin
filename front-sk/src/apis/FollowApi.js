import Axios from "axios"
import store from "../vuex/store"

const URL = 'http://70.12.246.134:8080' // 김주연 ip
    // const URL = 'http://70.12.246.51:8080' //  조장님 ip

const requestFanList = (data, callback, errorCallback) => {
    const params = new URLSearchParams();
    params.append('email', data);

    Axios.post(URL + '/follow/fanList', params)
        .then(response => {
            if (response.data.status == true)
                callback(response.data.object)
        })
        .catch(exp => {
            errorCallback(exp);
        })
}

const requestStarList = (data, callback, errorCallback) => {
    const params = new URLSearchParams();
    params.append('email', data);

    Axios.post(URL + '/follow/starList', params)
        .then(response => {
            if (response.data.status == true)
                callback(response.data.object)
        })
        .catch(exp => {
            errorCallback(exp);
        })
}
const FollowApi = {
    requestFanList: (data, callback, errorCallback) => requestFanList(data, callback, errorCallback),
    requestStarList: (data, callback, errorCallback) => requestStarList(data, callback, errorCallback)
}

export default FollowApi