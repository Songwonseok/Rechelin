<template>
  <div class="main">
    <div id="app">
      <section class="signup">
        <div class="container">
          <div class="signup-content">
            <form method="POST" id="signup-form" class="signup-form">
              <v-chip x-large class="ma-2" color="warning" label outlined>
                <v-icon left>{{icons.AccountBox}}</v-icon>
                Create account
              </v-chip>

              <hr>
              <!-- 닉네임 -->
              <ValidationObserver ref="form" v-slot="{ passes }">
                <v-chip class="ma-2" color="warning" outlined>
                  <v-icon left>{{icons.AccountCircleOutline}}</v-icon>
                  nickname
                </v-chip>
                <form @submit.prevent="passes(submit)">
                  <text-input2 v-model="name" rules="required|max:5" label="First Name" type="text" class="form-input"
                    name="name" id="name" @satusName="changeName" :propsdata="disabledName"
                    @statusName="changeName"
                    placeholder="닉네임을 적어 주세요." />
                  <span>

                    <!-- 이메일 -->


                    <v-chip class="ma-2" color="warning" outlined>
                      <v-icon left>{{icons.Email}}</v-icon>
                      E-mail
                    </v-chip>
                    <text-input3 v-model="email" rules="required|email" label="Email Address" name="email" type="email"
                      class="form-input" id="email" :disabled="disabledEmail" v-bind:propsdata="disabledEmail"
                      @statusEmail="changeEmail" placeholder="e-mail을 적어 주세요." />
                  </span>



                  <!-- 핸드폰 -->

                  <v-chip class="ma-2" color="warning" outlined>
                    <v-icon left>{{icons.Cellphone}}</v-icon>
                    Phone
                  </v-chip>
                  <text-input v-model="phone" type="text"
                    :rules="{ required: true,  min:13, regex: /^\d{3}-\d{3,4}-\d{4}$/}" class="form-input" name="phone"
                    id="phone" placeholder="phone 번호를 적어 주세요." />

                  <!-- 비밀번호 -->

                  <v-chip class="ma-2" color="warning" outlined>
                    <v-icon left>{{icons.LockQuestion}}</v-icon>
                    Password
                  </v-chip>
                  <text-input v-model="password" type="password" vid="pass"
                    :rules="{ required: true,  min:8, regex: /^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/ }"
                    class="form-input" name="password" id="password" placeholder="Password를 입력해주세요." />


                  <v-chip class="ma-2" color="warning" outlined>
                    <v-icon left>{{icons.LockQuestion}}</v-icon>
                    Confirm Password
                  </v-chip>
                  <text-input type="password" vid="confirmation" rules="required|confirmed:pass" class="form-input"
                    name="re_password" id="re_password" placeholder="Password를 확인해주세요." />

                  <!-- 약관동의 박스 -->

                  <v-btn color="warning" id="show-btn" dark @click="$bvModal.show('bv-modal-example')"
                    style="width: 350px; margin-bottom: 15px;">약관보기</v-btn>

                  <!-- 약관 동의 모달 -->
                  <b-modal style="text-align: center; margin-bottom: 10px;" id="bv-modal-example" hide-footer>
                    <template v-slot:modal-title>이용 약관</template>
                    <div class="d-block text-center">
                      <h3>약관 동의 Format</h3>
                    </div>
                    <v-btn block color="warning" dark @click="$bvModal.hide('bv-modal-example')">Close</v-btn>

                  </b-modal>
                  <b-form-radio-group id="radio-group" style="text-align: center; margin-bottom: 10px;"
                    name="radio-sub-component" required data-errormessage-value-missing="Please, pick one">

                    <b-form-radio name="some-radios" value="A">약관 동의</b-form-radio>
                    <b-form-radio name="some-radios2" value="B">약관 동의하지 않음</b-form-radio>

                  </b-form-radio-group>

                  <input type="submit" name="submit" id="submit" class="form-submit" value="Sign up" />

                </form>
              </ValidationObserver>
            </form>


            <p class="loginhere">
              Have already an account ?
              <router-link :to="{name: 'login'}">Login</router-link>
            </p>
          </div>
        </div>
      </section>
    </div>
  </div>
</template>

<script>
  import {
    mdiAccountCircleOutline,
    mdiAccountBox,
    mdiEmail,
    mdiCellphone,
    mdiLockQuestion
  } from '@mdi/js'

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
        icons: {
          AccountCircleOutline: mdiAccountCircleOutline,
          Email: mdiEmail,
          Cellphone: mdiCellphone,
          LockQuestion: mdiLockQuestion,
          AccountBox: mdiAccountBox
        }
      }
    },
    watch : {
      name : function(v){
        this.disabledName = true;
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
        console.log('changeName');
        //this.isNameOk = status;
        // if (this.isNameOk === false && this.$store.state.name.length < 6 && this.$store.state.name.length > 0) {
          if (this.$store.state.name.length < 6 && this.$store.state.name.length > 0) { 
          alert('사용할 수 있는 name 입니다.');
          this.disabledName = false;
        } else {
         
         alert('이미 사용하고 있는 name 이거나 글자수 5글자를 초과했습니다.');
          this.disabledName = true; 
        }
      },
      changeEmail(status) {
        console.log('changeName');
        this.isEmailOk = status;
        let exptext = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
        if (this.isEmailOk == false && exptext.test(this.$store.state.email) == true) {
          alert('사용할 수 있는 email 입니다.');
          this.disabledEmail = false;
        } else alert('이미 사용하고 있는 email 이거나 이메일 형식에 맞지 않습니다.');

      },
      submit() {
        if (this.isEmailOk === false && this.disabledName == false ) { //&& this.isNameOk === false
        //disabledName 는 name 이 중복 확인해서 없으면 false, 있으면 true 
         
          this.$alert("success","다음 페이지로 이동 합니다.","success");
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
            .then(data =>  
            {
              console.log('실행전!')
         
            this.$router.push({
              name: "signupconfirm"
            })}
            )
            .catch(err => console.log(err));
        } else {
          alert('현재 사용하고 있는 name이거나 email 입니다.');
        }
        //  this.name = '';
        //   this.email = '';
        //   this.phone = '';
        //   this.password = '';   
      },
      onSuccess,
      onFailure,
      callbackFunction
    }
  };
</script>

<style lang="scss" scoped>
  #submit {
    background-color: white;
    color: #ff7f00;
    border-color: #ff7f00 !important;
    border: 1px solid #ff7f00;
    border-radius: 8px;
    height: 50px;
    width: 350px;
    margin-top: 10px;
  }

  #submit:hover {
    background-color: #ff7f00;
    color: white;
    border-color: #ff7f00 !important;
    border: 1px solid #ff7f00;
    border-radius: 8px;
    height: 50px;
    width: 350px;
    margin-top: 10px;
  }

  .signup-content {
    text-align: center;
  }

  #signup-form {
    margin-top: 100px;
    margin-bottom: 50px;
  }

  .form-title {
    margin-bottom: 50px;
  }

  hr {
    margin-bottom: 100px;
  }

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