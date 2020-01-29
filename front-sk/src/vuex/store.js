import Vue from 'vue'
import Vuex, { mapState } from 'vuex'
import getters from './getters'
import actions from './actions'
import mutations from './mutations'

Vue.use(Vuex)

const state = {
    isUser: false,
}

export default new Vuex.Store({
    state : { 
        name : '',
        email : '',
        password  : '',
        user : '',
        phone : '010',
        code  : '',
        isSubmit : false,
        info : {
            nickname : "",
            email : "",
            pw : "",
            profile : null,
            phone : '010',
            // code : '',
            // randomNumber : '',
        },
        confirmation : '',
        randomNumber : '',
    // },
    // computed : {
    //     ...mapState({
    //         name : state =>state.name,
    //         email : state =>state.email,
    //         password : state =>state.password,
    //         user : state =>state.user,
    //         code : state =>state.code,
    //         info : state =>state.info,
    //         confirmation : state =>state.confirmation,
    //     })
    // },
    // methods : {
    //     updateName (e) {
    //         this.$store.commit('updateName', e.target.value)
    //       },
    //     updateEmail(e){
    //         this.$store.commit('updateEmail', e.target.value);
    //     },
    //     updatePassword (e) {
    //         this.$store.commit('updatePassword', e.target.value)
    //       },
    //     updateUser(e){
    //         this.$store.commit('updateUser', e.target.value);
    //     },
    //     updateCode(e){
    //         this.$store.commit('updateCode', e.target.value);
    //     },
    //     updateInfo(e){
    //         this.$store.commit('updateInfo', e.target.value);
    //     },
     },
    mutations,
    getters,
    actions
})