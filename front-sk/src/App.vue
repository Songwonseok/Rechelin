<template>
  <div id="app">
    <Header></Header>

 <transition name="page">
   <b-container class="bv-example-row">
      <router-view></router-view>
     </b-container>
     </transition>
  </div>
</template>
<script>
  import {
    mdiMagnify,
    mdiAccountCircle,
    mdiForum,
    mdiCrown,
    mdiBell,
    mdiPencil,
    mdiPlus,
    mdiDelete
  } from '@mdi/js'  
  import SignUp from "./views/user/signUpForm.vue";
  import Header from "./views/header.vue";
  export default {
    name: 'app',
    components : {
      Header,
    },
    watch : {
        checkLogin : function(v){
            this.checkLogin2();
        },
    },
    data() {
      return {
        icons: {
          Magnify: mdiMagnify,
          AccountCircle: mdiAccountCircle,
          Forum: mdiForum,
          Crown: mdiCrown,
          Bell: mdiBell,
          Pencil: mdiPencil,
          Plus: mdiPlus,
          Delete: mdiDelete,
        },
        alarms: 'asdfasd',
        checkLogin : true,
      }
    },
    computed :{
      isLogin() {
        return this.$store.state.accessToken
      }
    },
    methods: {
      UserLogout() {
        this.$store.dispatch('logout')
        this.$router.push({
          path: "/"
        })
      },
      userpageGo() {
        this.$store.dispatch('userpageGo', this.$store.state.userEmail)
      },
      mainGo() {
        this.$router.push({
          name: "/"
        })
      },
      notificationGet() {
        var payload = this.$store.state.userEmail
        this.$store.dispatch('notificationGet', payload)
        this.alarms = this.$store.state.notifications
      },
      followAccept(f, index) {
        var payload = {
          fan: f,
          star: this.$store.state.userEmail
        }
        this.$store.dispatch('followAccept', payload)
        this.alarms.splice(index, 1)
      },
      followDecline(f, index) {
        var payload = {
          fan: f,
          star: this.$store.state.userEmail
        }
        this.$store.dispatch('followDecline', payload)
        this.alarms.splice(index, 1)
      },
      checkLogin2() {
        console.log(sessionStorage.getItem("userToken"))
        if(sessionStorage.getItem("userToken")){
          this.checkLogin = false;
        }
      }
      
    },

  };
</script>

<style>
.container {
  max-width: 1500px !important;
}
</style>