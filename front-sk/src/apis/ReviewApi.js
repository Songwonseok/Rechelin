import Axios from "axios"

const URL = "http://54.180.160.87:8080" //aws
// const URL = 'http://70.12.246.134:8080' // 김주연 ip
    // const URL = 'http://70.12.246.51:8080' //  조장님 ip
const auth = {
    headers: {
        Authorization: 'Bearer ' + sessionStorage.getItem("userToken")
    }
}

const requestMyReviewList = (data, callback, errorCallback) => {
    const params = new URLSearchParams();
    params.append('email', data);

    Axios.post(URL + '/review/myList', params, auth)
        .then(response => {
            if (response.data.status == true)
                callback(response.data.object)
        })
        .catch(exp => {
            errorCallback(exp);
        })
}

const requestBookmarkList = (data, callback, errorCallback) => {
    const params = new URLSearchParams();
    params.append('email', data);

    Axios.post(URL + '/review/bookmark/getList', params, auth)
        .then(response => {
            if (response.data.status == true)
                callback(response.data.object)
        })
        .catch(exp => {
            errorCallback(exp);
        })
}
const requestAddReview = async(data, callback, errorCallback) => {
    console.log('review add');
    let options = {
        headers: { 'Content-Type': 'application/json', Authorization: 'Bearer ' + sessionStorage.getItem("userToken") },
        url: URL + '/review/register',
        method: 'post',
        data: JSON.stringify(data)
    }

    Axios(options)
        .then(response => {
            console.log(response);
            callback(response); //return type true/false 
            console.log('review api 성공')
        }).catch(exp => {
            console.log(exp.response)
            console.log('review api 실패')
        })
}

const requestFeedList = (data, callback, errorCallback) => {
    const params = new URLSearchParams();
    params.append('email', "ssafy@naver.com");
    Axios.post(URL + '/review/feed', params, auth)
        .then(response => {
            callback(response.data.object);
        }).catch(exp => {
            errorCallback(exp);
        })
}

function requestfetcViewsrList() {
    return Axios.get(URL + `/review/viewsTop`, auth);
}

function requestfetchScoreList() {
    return Axios.get(URL + `/review/scoreTop`, auth);
}

function requestfetchLikeList() {
    return Axios.get(URL + `/review/likeTop`, auth);
}

const ReviewApi = {
    requestMyReviewList: (data, callback, errorCallback) => requestMyReviewList(data, callback, errorCallback),
    requestBookmarkList: (data, callback, errorCallback) => requestBookmarkList(data, callback, errorCallback),
    requestAddReview: (data, callback, errorCallback) => requestAddReview(data, callback, errorCallback),
    requestFeedList: (data, callback, errorCallback) => requestFeedList(data, callback, errorCallback),
    requestfetcViewsrList,
    requestfetchScoreList,
    requestfetchLikeList,
}

export default ReviewApi