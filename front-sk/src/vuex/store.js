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
            nickname: [],
            email: [],
        }, //searchBar에서 User가 User를 검색하기 위한 info를 불러와 저장한다.
        recentUser: [

        ], //searchBar에서 User가 검색했던 recentUser를 불러와 저장한다.  
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
        allUsers: [{
                email: "ascv@yahoo.com",
                name: "펭수",
                picture: "https://i.pinimg.com/564x/8c/b3/95/8cb3958ee0f6bb3d06515c4f25daacf2.jpg",
                phone: "010-2345-1231",
                pw1: "asdf1234",
                stores: [{
                        name: '카페 413 프로젝트',
                        address: '서울 강남구 논현로 97길 19-11',
                        hashtag: [
                            '친구와', '강남', '아늑한', '분위기좋은'
                        ],
                        phone: '070-7798-0544',
                        picture: 'https://mp-seoul-image-production-s3.mangoplate.com/67078_1497540642492532.jpg'
                    },
                    {
                        name: '바나프레소 역삼점',
                        address: '역삼동 669-17번지 1층 강남구 서울특별시 KR',
                        hashtag: [
                            '친구와', '혼자', '강남', '역삼'
                        ],
                        phone: '',
                        picture: 'https://mblogthumb-phinf.pstatic.net/MjAxODA2MDVfMjU3/MDAxNTI4MTg4MDAwMzg1.2IvQiB0gDBR12GUqwBKs2H6VH5V1rjSXrSalF_09FQIg.89DIbuCipP_5FGOyPu8Z8rgre0r-c-IHimIYPwP3iq4g.JPEG.yhyang_hae/image_3952403311528187975089.jpg?type=w800'
                    }
                ],
                fans: ['ssafy1', 'ssafy2', '3', '4', '5'],
                stars: ['star1', 'star2', 'star3'],

            },

        ],
        tempStores: [{
                name: '카페 413 프로젝트',
                address: '서울 강남구 논현로 97길 19-11',
                hashtag: [
                    '친구와', '강남', '아늑한', '분위기좋은'
                ],
                phone: '070-7798-0544',
                picture: 'https://mp-seoul-image-production-s3.mangoplate.com/67078_1497540642492532.jpg'
            },
            {
                name: '바나프레소 역삼점',
                address: '역삼동 669-17번지 1층 강남구 서울특별시 KR',
                hashtag: [
                    '친구와', '혼자', '강남', '역삼'
                ],
                phone: '',
                picture: 'https://mblogthumb-phinf.pstatic.net/MjAxODA2MDVfMjU3/MDAxNTI4MTg4MDAwMzg1.2IvQiB0gDBR12GUqwBKs2H6VH5V1rjSXrSalF_09FQIg.89DIbuCipP_5FGOyPu8Z8rgre0r-c-IHimIYPwP3iq4g.JPEG.yhyang_hae/image_3952403311528187975089.jpg?type=w800'
            },
            {
                name: '양자강',
                address: '서울 역삼동 어딘가',
                hashtag: ['친구와', '강남', '시끄러운'],
                phone: '1234123',
                picture: 'https://upload.wikimedia.org/wikipedia/commons/thumb/e/e9/Korean.cuisine-Jajangmyeon-01.jpg/330px-Korean.cuisine-Jajangmyeon-01.jpg',
            },
            {
                name: '스타벅스',
                address: '멀캠 맞은 편',
                hashtag: ['노맛', '혼자', '시끄러운'],
                phone: '12313',
                picture: 'https://ww.namu.la/s/b9ae6fd83019ce31055107c03aaff8743d43aa5d90fa22e77e54c12c5e9cb12364bfd31c34e2b00001708cc5b0e12800f6c8ea63683c97ec8b5a534cd2546c5b5f94b6a88e9d226e34e400aab5c22fdad72e053f92e787c2350c4e1d947d2028558171b8e8d89dabc60b75210b14ad1b'
            }
        ],
        // 로그인 상태 관리 파트 && 유저 email과 닉네임
        accessToken: sessionStorage.getItem("userToken"),
        userEmail: sessionStorage.getItem("userEmail"),
        userNickname: sessionStorage.getItem("userNickname"),
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
    },

    // state가 아닌 부분
    mutations,
    getters,
    actions
})