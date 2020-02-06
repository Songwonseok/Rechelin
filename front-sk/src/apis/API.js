import axios from "axios";

export default axios.create({
    baseURL: "http://70.12.246.134:8080", // 김주연 ip
    //   baseURL: "http://70.12.246.51:8080", // 조장님 ip
    withCredentials: false,
    headers: {
        Accept: "application/json",
        "Content-Type": "application/json"
    }
});