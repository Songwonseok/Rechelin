<template>
  <div id="app">
  <v-app id="inspire">
    <v-card>
      <v-toolbar flat color="orange" dark>
         
         <v-toolbar-title>   <i class="fas fa-user fa-3x"></i></v-toolbar-title>  유저페이지
      </v-toolbar>
      <v-tabs vertical>
        
        <div v-if="isLoginTrue">
        <v-tab v-model="watchLogin">
          <v-icon left>mdi-account</v-icon>
          로그인
        </v-tab>
        </div>

        <div v-else>
          <v-tab>
          <v-icon left>mdi-account</v-icon>
          유저페이지
        </v-tab>

        </div>



        <v-tab>
          <v-icon left>mdi-lock</v-icon>
          비밀번호 찾기
        </v-tab>
        <v-tab>
          <v-icon left>mdi-access-point</v-icon>
          회원가입
        </v-tab>
  
        <v-tab-item>
          <v-card flat>
            <v-card-text>
              <!-- <router-link to="/login">Go to Foo</router-link> -->
              <login-bar></login-bar>
              
              <!-- <p class="mb-0">
                Phasellus dolor. Fusce neque. Fusce fermentum odio nec arcu. Pellentesque libero tortor, tincidunt et, tincidunt eget, semper nec, quam. Phasellus blandit leo ut odio.
              </p> -->
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
        isLoginTrue : true,
      }
    },
    watch :{
       watchLogin :function(){
         console.log('hi')
         if(sessionStorage.getItem("userToken").length>0){
           this.isLoginTrue = false;
         }
       }
    }
}
</script>

<style>

</style>