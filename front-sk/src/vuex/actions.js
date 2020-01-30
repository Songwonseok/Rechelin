export default {
    logout({commit}) {
        commit('logout')
    },
    login({commit}, payload) {
        commit('login', payload)
    }

}
