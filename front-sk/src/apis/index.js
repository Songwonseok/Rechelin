import axios from 'axios';

function requestfetchUserList(){
    //return axios.get(config.baseUrl+'news/1.json');
    return axios.get(`http://70.12.246.51:8080/account/list`);
}

export {
    requestfetchUserList,
}
