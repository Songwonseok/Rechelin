<template>
<div class="main">
    <div id="app">
        <section class="signup">
            <!-- <img src="images/signup-bg.jpg" alt=""> -->
            <div class="container">
                <div class="signup-content">
                     <form method="POST" id="signup-form" class="signup-form">
                        <h2 class="form-title">Create account</h2>
  <KakaoLogin
      api-key="<api-key>"
      image="kakao_login_btn_large"
      :on-success=onSuccess
      :on-failure=onFailure
      />
<NaverLogin
      client-id="<client-id>"
      callback-url="<callback-url>"
      is-popup="false"
      :callbackFunction=callbackFunction
      />


                     <ValidationObserver v-slot="{ passes }">
                        <form @submit.prevent="passes(onSubmit)">
                   
                            <TextInput rules="required" label="First Name" type="text" class="form-input" name="name" id="name" placeholder="Your Name"/>
                        
                        
                            <TextInput rules="required|email" label="Email Address" name="email" type="email" class="form-input" id="email" placeholder="Your Email"/>
                       
                       
                            <TextInput v-model="password" type="password"  :rules="{ required: true,  regex: /^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/, confirmed:'confirmation'}" class="form-input" name="password" id="password" placeholder="Password"/>
                            <span toggle="#password" class="zmdi zmdi-eye field-icon toggle-password"></span>
                        
                       
                            <TextInput  v-model="confirmation" type="password" vid="confirmation" rules="required"  class="form-input" name="re_password" id="re_password" placeholder="Repeat your password"/>
                        
                        
                            <input type="checkbox" name="agree-term" id="agree-term" class="agree-term" />
                            <!-- <label for="agree-term" class="label-agree-term"><span><span></span></span>I agree all statements in  <a href="#" class="term-service">Terms of service</a></label> -->
                          <b-button id="show-btn" 
 style="width: 100%;height: 15%;color: cornflowerblue;background-color: aliceblue; margin-bottom: 10px;" @click="$bvModal.show('bv-modal-example')">약관 보기</b-button>
        <b-modal style="text-align: center; margin-bottom: 10px;" id="bv-modal-example" hide-footer>
          <template v-slot:modal-title>
            이용 약관
          </template>
          <div class="d-block text-center">
            <h3>약관 동의 할꺼니? 동의 누르렴</h3>
          </div>
          <b-button style="
    width: 100%;
    height: 20%;
    color: cornflowerblue;background-color: aliceblue; margin-bottom: 10px;
" class="mt-3" block @click="$bvModal.hide('bv-modal-example')">닫기</b-button>
        </b-modal>
       
                            
            <b-form-radio-group id="radio-group" style="
    text-align: center;
    margin-bottom: 10px;
" name="radio-sub-component" required>
                <b-form-radio  v-model="selected" name="some-radios" value="A" >약관 동의</b-form-radio>
                <b-form-radio v-model="selected" name="some-radios2" value="B">약관 동의하지 않음</b-form-radio>
              </b-form-radio-group>
            
                            
                            <!-- <button class="form-submit" value="Sign up">Submit</button> -->
                            <input type="submit" name="submit" id="submit" class="form-submit" value="Sign up"/>
                            </form>
                
                     </ValidationObserver>

                    <p class="loginhere">
                        Have already an account ? <a href="#" class="loginhere-link">Login here</a>
                    </p>

                     
                     
                      </form>
                </div>
            </div>
        </section>

    </div>
    </div>
   
</template>

<script>
import { ValidationObserver } from "vee-validate";
import TextInput from "../../components/common/TextInput.vue";
import { extend } from 'vee-validate';
import KakaoLogin from 'vue-kakao-login'
import NaverLogin from 'vue-naver-login'

let callbackFunction = (status) => {
    if (status) {
    /* (5) 필수적으로 받아야하는 프로필 정보가 있다면 callback처리 시점에 체크 */
    var email = NaverLogin.user.getEmail();
    if( email == undefined || email == null) {
      alert("이메일은 필수정보입니다. 정보제공을 동의해주세요.");
      /* (5-1) 사용자 정보 재동의를 위하여 다시 네아로 동의페이지로 이동함 */
      NaverLogin.reprompt();
      return;
    }

    window.location.replace("http://" + window.location.hostname + ( (location.port==""||location.port==undefined)?"":":" + location.port) + "/sample/main.html");
  } else {
    console.log("callback 처리에 실패하였습니다.");
  }
}
let onSuccess = (data) => {
  console.log(data)
  console.log("success")
}
let onFailure = (data) => {
  console.log(data)
  console.log("failure")
}

export default {
  name: "App",
  /*eslint-disable*/
  components: {
    TextInput,
    ValidationObserver,
    KakaoLogin,
      NaverLogin
  },
  data: () => ({
    pass: null,
    confirmedPass: null
  }),
  methods: {
    onSubmit () {
      alert('Form Submitted!');
    },
    onSuccess,
    onFailure,
    callbackFunction,
  }
};
</script>

<style lang="scss" scoped>
.btn {
  border: 1px solid currentColor;
  border-radius: 999999px;
  padding: 0.75rem 3rem;
  text-align: center;
  background: transparent;
  cursor: pointer;
  border-color: currentColor;
  width: fit-content;

  
  font-weight: 700;
  position: relative;
  transition: color 0.3s ease;
}
.mobile-page-container {
    
    #pass {
        -webkit-text-security: square;    
    }
}
</style>