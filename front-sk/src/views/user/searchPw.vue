<template>
  <div>
     <section class="signup">
        <!-- <img src="images/signup-bg.jpg" alt=""> -->
        <div class="container">
          <div class="signup-content">
            <h2>Search Password</h2>
              <text-input4
                      v-model="email"
                      rules="required|email"
                      label="Email Address"
                      name="email"
                      type="email"
                      class="form-input"
                      id="email"
                      :disabled = "disabledEmail"
                      v-bind:propsdata ="disabledEmail"
                      @statusEmail ="changeEmail"
                      placeholder="e-mail을 적어 주세요."
                    />
                <b-button variant="outline-primary" :disabled="checkEmail"  @click="send">Send</b-button>
        

           <text-input v-model="code"  class="form-input" type="text"  name ="code" rules="required|min:6" placeholder="Code" />
          <b-button variant="outline-primary"  :disabled="checkEmail" @click="confirm">Confirm</b-button>
            
         <p class="loginhere">
              Have already an account ?
        <a href="#" class="loginhere-link">Login here</a>
            </p>
          </div>
        </div>
      </section>
  </div>


  
</template>

<script>
import Vue from "vue";
import { Auth } from "aws-amplify";
import UserApi from '../../apis/UserApi';
import TextInput4 from "../../components/common/TextInput4.vue";
import TextInput from "../../components/common/TextInput.vue";
//import {EventBus} from "../../event-bus.js";
export default {
  name: "App",
 data: () => {
            return {
                email : '',
                code : '',
                number : '',
                disabledEmail : true,
                checkEmail : true,
                isEmailOk : '',
               }
 },
  components: {
    TextInput,
    TextInput4,

  },
  computed: {
    code: {
      get() {
        return this.$store.state.code;
      },
      set(value) {
        this.$store.commit("updateCode", value);
      }
    },

  },
  methods: {
    changeEmail(status){
      this.isEmailOk = status;
      
      
      console.log(this.isEmailOk);
     
      if(this.isEmailOk == true) {
        alert("존재하는 email 입니다.");
        this.checkEmail = false;
        this.disabledEmail = false;
      }
      else{
         alert('존재하지 않는 email 입니다.');
         
      }
    },
    send() {
       this.number = String(Math.random());
       this.$store.state.randomNumber = this.number;
       alert('email을 확인해주세요.')
       Auth.signUp({
        username: this.number,
        password: "temp123!",
        attributes: {
          email: this.email // optional
          // Other custom attributes...
        },
        validationData: [] // optional
      })
        .then(data => {
              console.log(data)
        }
              )

        .catch(err => console.log(err));
    },
    confirm() {
      // After retrieveing the confirmation code from the user
      Auth.confirmSignUp(this.number, this.code, {
        // Optional. Force user confirmation irrespective of existing alias. By default set to True.
        forceAliasCreation: true
      })
        .then(
          data => {
          alert("인증에 성공하셨습니다.");
          console.log(this.code);

          // EventBus.$emit('eventName',this.email, this.code);
          
          this.$store.state.searchEmail = this.email;
          this.$store.state.searchCode = this.code;
          this.$router.push({ path: "/updateNewPw" })
          

          }
          //this.$router.push("/")
        )
        .catch(err => {
          if (err.code === "CodeMismatchException") {
            alert("인증에 실패했습니다.");
          }

          console.log(err);
        });
    }
  }
};
</script>

<style>
.btn {
    padding:  7px 50px;
    text-align: center;
    display: inline-block;
    font-size: 16px;
    margin: 4px 2px;
    color: cornflowerblue;
    height: 40px;
    margin-top: 10px;
    
}
</style>
