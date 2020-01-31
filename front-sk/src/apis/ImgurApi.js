import Axios from "axios"

const CLIENT_ID = '489cf71f2491ea6';
const ROOT_URL = 'https://api.imgur.com'


// Axios.defaults.headers.post['Authorization'] = CLIENT_ID

const uploadProfile = (data, callback, errorCallback) => {
    console.log(data)

    const fd = new FormData();
    fd.append('image', data);

    // 1) Imgur에 파일을 업로드
    Axios.post('https://api.imgur.com/3/image', fd, {
            headers: {
                Authorization: 'Client-ID 489cf71f2491ea6'
            }
        })
        .then(response => {
            callback(response.data);
            // console.log(response.data)
            console.log('성공')
        })
        .catch(exp => {
                errorCallback(exp);
            }

        )
}

const ImgurApi = {
    uploadProfile: (data, callback, errorCallback) => uploadProfile(data, callback, errorCallback)
}

export default ImgurApi