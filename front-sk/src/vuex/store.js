import Vue from 'vue'
import Vuex from 'vuex'
import getters from './getters'
import actions from './actions'
import mutations from './mutations'


Vue.use(Vuex)

const state = {
    isUser: false,
    allUsers : [
        {email: 'temp@naver.com', name: '팽수', pw: 'asdfasdfasdf', picture: 'https://upload.wikimedia.org/wikipedia/ko/thumb/d/d4/%ED%8E%AD%EC%88%98.jpg/450px-%ED%8E%AD%EC%88%98.jpg'}
    ]
}

export default new Vuex.Store({
    state,
    mutations,
    getters,
    actions
})