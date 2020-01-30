export default {
    logout({commit}) {
        commit('logout')
    },
    login({commit}, payload) {
        commit('login', payload)
    },
    userpageGo({commit}, payload) {
        commit('userpageGo', payload)
    },
    userFans({commit}, payload) {
        commit('userFans', payload)
    },
    userStars({commit}, payload) {
        commit('userStars', payload)
    }

}
