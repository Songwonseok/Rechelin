import Vue from 'vue'
import Vuex from 'vuex'
import getters from './getters'
import actions from './actions'
import mutations from './mutations'

Vue.use(Vuex)

const state = {
    isUser: false,
    allUsers: [
        {email: "ascv@yahoo.com", name: "펭수", picture: "https://i.pinimg.com/564x/8c/b3/95/8cb3958ee0f6bb3d06515c4f25daacf2.jpg", phone: "010-2345-1231", pw1:"asdf1234",
        stores: [
            {name: '카페 413 프로젝트', address: '서울 강남구 논현로 97길 19-11', hashtag: [
                '친구와', '강남', '아늑한', '분위기좋은'
            ], phone: '070-7798-0544', picture: 'https://mp-seoul-image-production-s3.mangoplate.com/67078_1497540642492532.jpg'},
            {name: '바나프레소 역삼점', address: '역삼동 669-17번지 1층 강남구 서울특별시 KR', hashtag: [
                '친구와', '혼자', '강남', '역삼'
            ], phone: '', picture: 'https://mblogthumb-phinf.pstatic.net/MjAxODA2MDVfMjU3/MDAxNTI4MTg4MDAwMzg1.2IvQiB0gDBR12GUqwBKs2H6VH5V1rjSXrSalF_09FQIg.89DIbuCipP_5FGOyPu8Z8rgre0r-c-IHimIYPwP3iq4g.JPEG.yhyang_hae/image_3952403311528187975089.jpg?type=w800'}
        ],
        fans: ['ssafy1', 'ssafy2', '3', '4', '5'],
        stars: ['star1', 'star2', 'star3'],
           
            
        }
    ]
}

export default new Vuex.Store({
    state,
    mutations,
    getters,
    actions
})