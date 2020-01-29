<template>
  <div>
   


     <section class="signup">
        <!-- <img src="images/signup-bg.jpg" alt=""> -->
        <div class="container">
          <div class="signup-content">
            <h2>Search Password</h2>
              <text-input3
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
                <b-button variant="outline-primary"   @click="send">Send</b-button>
        

           <text-input v-model="code"  class="form-input" type="text"  name ="code" rules="required|min:6" placeholder="Code" />
          <b-button variant="outline-primary"   @click="confirm">Confirm</b-button>
            
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
import { Auth } from "aws-amplify";
import UserApi from '../../apis/UserApi';
import TextInput3 from "../../components/common/TextInput3.vue";
import TextInput from "../../components/common/TextInput.vue";

export default {
  name: "App",
 data: () => {
            return {
                email : '',
                code : '',
                number : '',
                disabledEmail : true,
               }
 },
  components: {
    TextInput,
    TextInput3,

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
    send() {
       this.number = String(Math.random());
       Auth.signUp({
        username: this.number,
        password: "temp123!",
        attributes: {
          email: this.email // optional
          // Other custom attributes...
        },
        validationData: [] // optional
      })
        .then(data => console.log(data))
        .catch(err => console.log(err));
    },
    confirm() {
      // After retrieveing the confirmation code from the user
      if(this.code,length<6) alert('email을 확인해주세요.')
      Auth.confirmSignUp(this.number, this.code, {
        // Optional. Force user confirmation irrespective of existing alias. By default set to True.
        forceAliasCreation: true
      })
        .then(
          data => {
          alert("인증에 성공하셨습니다.");
          this.$router.push({ path: '/' })
          
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