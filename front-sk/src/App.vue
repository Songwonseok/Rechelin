<template>
  <div id="app">
    <v-app-bar dense >
      <v-app-bar-nav-icon></v-app-bar-nav-icon>

      <v-toolbar-title @click="mainGo">싸피 4조</v-toolbar-title>

      <v-spacer></v-spacer>

      <v-btn icon>
        <v-icon>{{icons.Forum}}</v-icon>
      </v-btn>
      <v-btn icon>
        <v-icon @click="userpageGo">{{icons.AccountCircle}}</v-icon>
      </v-btn>

      <!-- 로그인/로그아웃/signup 표시 -->
      <v-btn icon>
        <v-icon>{{icons.Magnify}}</v-icon>

      </v-btn>
    
      <div v-if="isLogin">
        <v-btn text @click="UserLogout">로그아웃</v-btn>
      </div>
      
      <div v-else>
        <v-btn text @click="UserLogout">로그인</v-btn>  
      </div>

      <v-btn fab dark small color="green" id="popover-target-1" @click="notificationGet" @mouseover="notificationGet">
        <v-icon>{{icons.Bell}}</v-icon>
      </v-btn>
      <b-popover target="popover-target-1" triggers="hover" placement="bottom">
        <template v-slot:title>팔로우 요청</template>
      
        <b-list-group-item v-for="(alarm, index) in alarms" :key="index">{{alarm.nickname}}
          <div>
          <v-btn @click="followAccept(alarm.fan, index)">accept</v-btn>
          <v-btn @click="followDecline(alarm.fan, index)">decline</v-btn>
          </div>
        </b-list-group-item>
      </b-popover>
    </v-app-bar>
  <transition v-bind:name="transitionName" mode="out-in">
    <Main></Main>
    <router-view></router-view>
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
  import Main from "./views/Main.vue";
  export default {
    name: 'app',
    components : {
      Main,
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
          name: "Login"
        })
      },
      userpageGo() {
        this.$store.dispatch('userpageGo', this.$store.state.userEmail)
      },
      mainGo() {
        this.$router.push({
          name: "Main"
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

  #inspire {
    right: 30px;
  }
  .v-toolbar--dense .v-toolbar__content, .v-toolbar--dense .v-toolbar__extension {
    background:  #ff9800 !important;
    background-color:  #ff9800 !important;
  }
  .out-up-enter-active,
.out-up-enter-active,
.out-up-leave-active,
.out-down-enter-active,
.out-down-leave-active,
.fade-enter-active,
.fade-leave-active {
  transition: all .3s ease;
}

.out-up-leave-to,
.out-down-enter {
  position: absolute;
  transform: translateY(-2.5em);
  opacity: 0;
}

.out-up-enter,
.out-down-leave-to {
  position: absolute;
  transform: translateY(2.5em);
  opacity: 0;
}

.fade-enter,
.fade-leave-to {
  opacity: 0;
  position: absolute;
}

</style>