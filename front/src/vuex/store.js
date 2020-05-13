import Vue from 'vue'
import Vuex, {
    mapState
} from 'vuex'
import getters from './getters'
import actions from './actions'
import mutations from './mutations'
Vue.use(Vuex)
export default new Vuex.Store({
    state: {
        name: '',
        email: '',
        password: '',
        user: '',
        searchUser: {
           
        }, //searchBar에서 User가 User를 검색하기 위한 info를 불러와 저장한다.
        recentUser: [], //searchBar에서 User가 검색했던 recentUser를 불러와 저장한다.  
        phone: '010',
        code: '',
        isSubmit: false,
        info: {
            nickname: "",
            email: "",
            pw: "",
            profile: null,
            phone: '010',
        },
        confirmation: '',
        randomNumber: '',
        isUser: false,
        //스토어 정보
        storeInfo: '',
        // store hash tag 정보
        storeHashtags: null,
        storeAvg: {
            priceAvg: '',
            tasteAvg: '',
            totalAvg: '',
            kindAvg: '',
        },
        // 로그인 상태 관리 파트 && 유저 email과 닉네임
        accessToken: sessionStorage.getItem("userToken"),
        userEmail: sessionStorage.getItem("userEmail"),
        userNickname: sessionStorage.getItem("userNickname"),
        userid: sessionStorage.getItem("userid"),
        // 유저페이지에서 갈때 정보
        userPageInfo: {
            nickname: '',
            email: '',
            fans: [],
            stars: [],
            reviews: [],
            bookmarks: []
        },
        // 알람 관리
        notifications: [],
        // 음식점 위치 정보
        googleStorePlace: [],
        googleStorePlaceView: [],
        searchEmail: '',
        searchCode: '',
        ratings: null, //리스트로 올것
        // commets of REVIEWS
        commentsOfreview: null, // 아마 리스트로 올 것  
        reviewsOfstore: null,
        newReturnComment: null,
        reviewInfo: '',
        data: 1,
        isClickBtnStore: false,
        storeList: [],
        storeList_paging: [],
        UserInfo: { // id로 가져온 정보들
            email: '',
            nickname: '',
            phone: '',
            profile: ''
        },
        directiveStoreDetail: '',
        page: 0,
        userPage: 0,
        pageStatus: true, //storeSearch 에서 pagnation 을 보여주기 위함입니다. Main에서 처리되고 storeSearch 페이지에 검색바가 있기 때문에 store에 두었습니다. 
        userPageStatus: 0,




        //
        detail: null,
        feedUserList : [],
        storeTweets : [],
    },
    // state가 아닌 부분
    mutations,
    getters,
    actions,
   
})