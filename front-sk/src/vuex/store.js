import Vue from 'vue'
import Vuex from 'vuex'
import getters from './getters'
import actions from './actions'
import mutations from './mutations'

Vue.use(Vuex)

const state = {
    isUser: false,
    allUsers: [
        {email: 'a@naver.com', name: '펭수', picture: 'https://i.pinimg.com/564x/8c/b3/95/8cb3958ee0f6bb3d06515c4f25daacf2.jpg', phone: '010-2345-1231'}
    ]
}

export default new Vuex.Store({
    state,
    mutations,
    getters,
    actions
})