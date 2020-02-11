import Axios from "axios"

const URL = 'http://70.12.246.134:8080' // 김주연 ip
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
    // let options = {
    //     headers: { 'Content-Type': 'application/json' },
    //     url: 'http://70.12.246.134:8080/review/register',
    //     method: 'post',
    //     data: data
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
    console.log('requestAddReview')
    let options = {
            headers: { 'Content-Type': 'application/json', Authorization: 'Bearer ' + sessionStorage.getItem("userToken") },
            url: URL + '/review/register',
            method: 'post',
            data: JSON.stringify(data)
        }
        // let review = {
        //     'hashtag': data.hashtag,
        //     'picture': data.picture,
        //     'score_kindness': data.score_kindness,
        //     'score_price': data.score_price,
        //     'score_taste': data.score_taste,
        //     'score_total': data.score_total,
        //     'store_num': data.store_num,
        //     'str': data.str,
        //     'title': data.title,
        //     'user_email': 'ssafy@naver.com',
        //     'weak': data.weak,
        // }

    //const params = new URLSearchParams();
    //params.append("review", review);
    Axios(options)
        // Axios.post('http://70.12.246.134:8080/review/register', review)
        .then(response => {
            console.log(response);
            callback(response); //return type true/false 
            console.log('review api 성공')
        }).catch(exp => {
            console.log(exp.response)
            console.log('review api 실패')
        })
}


const ReviewApi = {
    requestMyReviewList: (data, callback, errorCallback) => requestMyReviewList(data, callback, errorCallback),
    requestBookmarkList: (data, callback, errorCallback) => requestBookmarkList(data, callback, errorCallback),
    requestAddReview: (data, callback, errorCallback) => requestAddReview(data, callback, errorCallback),

}

export default ReviewApi