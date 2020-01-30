<template>
    <div id="app">
    <v-app-bar
      dense
      dark
    >
      <v-app-bar-nav-icon></v-app-bar-nav-icon>

      <v-toolbar-title>싸피 4조</v-toolbar-title>

      <v-spacer></v-spacer>

      <v-btn icon>
        <v-icon>{{icons.Forum}}</v-icon>
      </v-btn>
       <v-btn icon>
        <v-icon>{{icons.AccountCircle}}</v-icon>
      </v-btn>
<!-- 로그인/로그아웃/signup 표시 -->
      <v-btn icon>
        <v-icon>{{icons.Magnify}}</v-icon>
      </v-btn>
    </v-app-bar>


        <router-view></router-view> 
        
    </div>
</template>
<script>

    import { mdiMagnify, mdiAccountCircle,mdiForum, mdiCrown } from '@mdi/js'
    import SignUp from "./views/user/signUpForm.vue";
    export default {
        name: 'app',
        components : {
            
        
        },
        data() {
            return {
                icons: {
                Magnify: mdiMagnify,
                AccountCircle: mdiAccountCircle,
                Forum: mdiForum,
                Crown: mdiCrown
            },
            }
        }
    };
</script>
