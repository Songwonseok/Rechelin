import Axios from "axios"

const URL = 'http://70.12.246.134:8080' // 김주연 ip
    // const URL = 'http://70.12.246.51:8080' //  조장님 ip

const requestReviewList = (data, callback, errorCallback) => {
    const params = new URLSearchParams();
    params.append('email', data);

    // Axios.post(URL + '/follow/starList', params)
    //     .then(response => {
    //         if (response.data.status == true)
    //             callback(response.data.object)
    //     })
    //     .catch(exp => {
    //         errorCallback(exp);
    //     })
}

const requestBookmarkList = (data, callback, errorCallback) => {
    const params = new URLSearchParams();
    params.append('email', data);

    Axios.post(URL + '/review/bookmark/getList', params)
        .then(response => {
            if (response.data.status == true)
                callback(response.data.object)
        })
        .catch(exp => {
            errorCallback(exp);
        })
}
const ReviewApi = {
    requestReviewList: (data, callback, errorCallback) => requestReviewList(data, callback, errorCallback),
    requestBookmarkList: (data, callback, errorCallback) => requestBookmarkList(data, callback, errorCallback)
}

export default ReviewApi