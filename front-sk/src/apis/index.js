import axios from 'axios';
const URL = "http://54.180.160.87:8080" //aws
function requestfetchUserList(){
    //return axios.get(config.baseUrl+'news/1.json');
    return axios.get(URL+`/account/list`);
}

export {
    requestfetchUserList,
}
