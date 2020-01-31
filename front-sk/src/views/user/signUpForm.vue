<template>
  <div class="main">
    <div id="app">
      <section class="signup">
        <!-- <img src="images/signup-bg.jpg" alt=""> -->
        <div class="container">
          <div class="signup-content">
            <form method="POST" id="signup-form" class="signup-form">
              <h2 class="form-title">Create account</h2>
              <!-- <KakaoLogin
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
              />-->
              <ValidationObserver ref="form" v-slot="{ passes }">
                <form @submit.prevent="passes(submit)">
                  <text-input2 v-model="name" rules="required|max:5" label="First Name" type="text" class="form-input"
                    name="name" id="name" @statusName="changeName" v-bind:propsdata="disabledName"
                    placeholder="닉네임을 적어 주세요." />
                  <span>
                    <text-input3 v-model="email" rules="required|email" label="Email Address" name="email" type="email"
                      class="form-input" id="email" :disabled="disabledEmail" v-bind:propsdata="disabledEmail"
                      @statusEmail="changeEmail" placeholder="e-mail을 적어 주세요." />
                    <!-- <input v-model="code"   class="codeInput" type="text" placeholder="Code">  <b-button variant="outline-primary">이메일 인증</b-button><b-button variant="outline-primary">인증번호 확인</b-button> -->
                  </span>


                  <!-- <input v-model="code" type="text" placeholder="Code">  <b-button variant="outline-primary">인증</b-button> -->

                  <!-- phone -->
                  <text-input v-model="phone" type="text"
                    :rules="{ required: true,  min:13, regex: /^\d{3}-\d{3,4}-\d{4}$/}" class="form-input" name="phone"
                    id="phone" placeholder="phone 번호를 적어 주세요." />

                  <text-input v-model="password" type="password" vid="pass"
                    :rules="{ required: true,  min:8, regex: /^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/ }"
                    class="form-input" name="password" id="password" placeholder="Password를 입력해주세요." />
                  <text-input type="password" vid="confirmation" rules="required|confirmed:pass" class="form-input"
                    name="re_password" id="re_password" placeholder="Password를 확인해주세요." />
                  <input type="checkbox" name="agree-term" id="agree-term" class="agree-term" />
                  <!-- <label for="agree-term" class="label-agree-term"><span><span></span></span>I agree all statements in  <a href="#" class="term-service">Terms of service</a></label> -->
                  <b-button id="show-btn"
                    style="width: 100%;height: 15%;color: cornflowerblue;background-color: aliceblue; margin-bottom: 10px;"
                    @click="$bvModal.show('bv-modal-example')">약관 보기</b-button>
                  <b-modal style="text-align: center; margin-bottom: 10px;" id="bv-modal-example" hide-footer>
                    <template v-slot:modal-title>이용 약관</template>
                    <div class="d-block text-center">
                      <h3>약관 동의 할꺼니? 동의 누르렴</h3>
                    </div>
                    <b-button
                      style="width: 100%;height: 20%; color: cornflowerblue;background-color: aliceblue; margin-bottom: 10px;"
                      class="mt-3" block @click="$bvModal.hide('bv-modal-example')">닫기</b-button>
                  </b-modal>
                  <b-form-radio-group id="radio-group" style="
    text-align: center;
    margin-bottom: 10px;
" name="radio-sub-component" required data-errormessage-value-missing="Please, pick one">
                    <b-form-radio name="some-radios" value="A">약관 동의</b-form-radio>
                    <b-form-radio name="some-radios2" value="B">약관 동의하지 않음</b-form-radio>
                  </b-form-radio-group>
                  <!-- <button class="form-submit" value="Sign up">Submit</button> -->
                  <input type="submit" name="submit" id="submit" class="form-submit" value="Sign up" />
                  <!-- <button class="form-submit">email 인증</button> -->
                </form>
              </ValidationObserver>
            </form>
            <p class="loginhere">
              Have already an account ?
              <a href="#" class="loginhere-link">Login here</a>
            </p>
          </div>
        </div>
      </section>
    </div>
  </div>
</template>

<script>
  import {
    ValidationProvider,
    ValidationObserver
  } from "vee-validate";
  import TextInput from "../../components/common/TextInput.vue";
  import TextInput2 from "../../components/common/TextInput2.vue";
  import TextInput3 from "../../components/common/TextInput3.vue";
  import {
    extend
  } from "vee-validate";
  import KakaoLogin from "vue-kakao-login";
  import NaverLogin from "vue-naver-login";
  import {
    Auth
  } from "aws-amplify";
  import * as EmailValidator from 'email-validator';
  let callbackFunction = status => {
    if (status) {
      /* (5) 필수적으로 받아야하는 프로필 정보가 있다면 callback처리 시점에 체크 */
      var email = NaverLogin.user.getEmail();
      if (email == undefined || email == null) {
        alert("이메일은 필수정보입니다. 정보제공을 동의해주세요.");
        /* (5-1) 사용자 정보 재동의를 위하여 다시 네아로 동의페이지로 이동함 */
        NaverLogin.reprompt();
        return;
      }

      window.location.replace(
        "http://" +
        window.location.hostname +
        (location.port == "" || location.port == undefined ?
          "" :
          ":" + location.port) +
        "/sample/main.html"
      );
    } else {
      console.log("callback 처리에 실패하였습니다.");
    }
  };
  let onSuccess = data => {
    console.log(data);
    console.log("success");
  };
  let onFailure = data => {
    console.log(data);
    console.log("failure");
  };

  export default {
    name: "App",
    /*eslint-disable*/
    components: {
      TextInput,
      TextInput2,
      TextInput3,
      ValidationObserver,
      ValidationProvider,
      KakaoLogin,
      NaverLogin
    },
    data: () => {
      return {
        isNameOk: '',
        isEmailOk: '',
        disabledName: true,
        disabledEmail: true,
      }
    },
    computed: {
      name: {
        get() {
          return this.$store.state.name;
        },
        set(value) {
          this.$store.commit("updateName", value);
        }
      },
      email: {
        get() {
          return this.$store.state.email;
        },
        set(value) {
          this.$store.commit("updateEmail", value);
        }
      },
      password: {
        get() {
          return this.$store.state.password;
        },
        set(value) {
          this.$store.commit("updatePassword", value);
        },
        phone: {
          get() {
            return this.$store.state.phone;
          },
          set(value) {
            this.$store.commit("updatePhone", value);
          }
        },
        isSubmit: {
          get() {
            return this.$store.state.isSubmit;
          },
          set(value) {
            this.$store.commit("updateIsSubmit", value);
          }
        }
      }
      // user : {
      //   get(){
      //     return this.$store.state.user;
      //   },
      //   set(value){
      //     this.$store.commit("updateUser", value);
      //   }
      // }
    },
    methods: {

      onSubmit() {
        alert("Form Submitted!");
      },
      changeName(status) {
        this.isNameOk = status;
        if (this.isNameOk === false && this.$store.state.name.length < 6 && this.$store.state.name.length > 0) {
          alert('사용할 수 있는 name 입니다.');
          this.disabledName = false;
        } else alert('이미 사용하고 있는 name 이거나 글자수 5글자를 초과했습니다.');
      },
      changeEmail(status) {
        this.isEmailOk = status;
        let exptext = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
        if (this.isEmailOk == false && exptext.test(this.$store.state.email) == true) {
          alert('사용할 수 있는 email 입니다.');
          this.disabledEmail = false;
        } else alert('이미 사용하고 있는 email 이거나 이메일 형식에 맞지 않습니다.');

      },
      submit() {
        if (this.isEmailOk === false && this.isNameOk === false) {
          alert("다음 페이지로 이동 합니다.");
          this.$store.state.randomNumber = String(Math.random());
          Auth.signUp({
              username: this.$store.state.randomNumber,
              password: this.$store.state.password,
              attributes: {
                email: this.$store.state.email // optional

                // Other custom attributes...
              },
              validationData: [] // optional
            })
            .then(data => this.$router.push({
              path: "/signUpConfirm"
            }))
            .catch(err => console.log(err));
        } else {
          alert('현재 사용하고 있는 name이거나 email 입니다.');
        }
      },
      onSuccess,
      onFailure,
      callbackFunction
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

  .btn[data-v-18c64894] {
    border: 1px solid currentColor;
    border-radius: 999999px;
    padding: 0.75rem 3rem;
    text-align: center;

    cursor: pointer;
    border-color: currentColor;
    width: -webkit-fit-content;
    width: -moz-fit-content;
    width: fit-content;
    font-weight: 700;
    position: relative;
    -webkit-transition: color 0.3s ease;
    transition: color 0.3s ease;
    width: 30%;
    height: 0.1ren;
    height: 5%;
    font-size: 7px;
  }

  .codeInput[data-v-18c64894] {
    margin-right: 10px;
    margin-bottom: 10px;
    padding-bottom: 18px;
    margin-bottom: 1rem;
    height: 30px;
    font-size: 10px;
    font-size: larger;
    text-align: -webkit-center;
    padding-top: 15px;
  }

  .TextInput[data-v-7466b77f] {
    padding-bottom: 18px;
    /* width: 50%; */
    margin-bottom: 15px;
  }
</style>