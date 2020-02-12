<template>
  <div id="app">
  <v-app id="inspire">
    <v-card>
      <v-toolbar flat color="orange" dark>
         <v-toolbar-title>   <i class="fas fa-user fa-3x"></i>로그인페이지 </v-toolbar-title>  
      </v-toolbar>
      <v-tabs vertical>
        
        <div v-if="isLoginTrue">
        <v-tab>
          <v-icon left>mdi-account</v-icon>
          유저페이지
        </v-tab>

         <v-tab>
          <v-icon left>mdi-account</v-icon>
          팔로우 리스트
        </v-tab>

        </div>

        <div v-else>
          <v-tab>
          <v-icon left>mdi-account</v-icon>
          로그인
        </v-tab>

        </div>



        <v-tab v-if="!passwordRemove">
          <v-icon left>mdi-lock</v-icon>
          비밀번호 변경
        </v-tab>



        <v-tab v-if="!signUpRemove">
          <v-icon left>mdi-access-point</v-icon>
          회원가입
        </v-tab>
  
        <v-tab-item>
          <v-card flat>
            <v-card-text>
                <login-bar></login-bar>
            </v-card-text>
          </v-card>
        </v-tab-item>
        <v-tab-item>
          <v-card flat>
            <v-card-text>
            <search-page></search-page>
            </v-card-text>
          </v-card>
        </v-tab-item>
        <v-tab-item>
          <v-card flat>
            <v-card-text>
              <sign-up></sign-up>
            </v-card-text>
          </v-card>
        </v-tab-item>
      </v-tabs>
    </v-card>
  </v-app>
</div>
</template>

<script>
import loginBar from '../../views/user/Login.vue'
import searchPage from '../../views/user/searchPw.vue'
import signUp from '../../views/user/signUpForm.vue'
export default {
  
    components :{
        loginBar,
        signUp,
        searchPage,
        
    },
    data(){
      return{
        
      }
    },
    watch : {
      
    },
    computed :{
      isLoginTrue() {
        return this.$store.state.accessToken
      },
      convertLoginToUser(){
        return this.$store.state.accessToken
      },
      passwordRemove(){
        return this.$store.state.accessToken
      },
      signUpRemove(){
        return this.$store.state.accessToken
      }
    },
}
</script>

<style>

</style>