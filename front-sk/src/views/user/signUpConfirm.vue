<template>
  <div>
   


     <section class="signup">
        <!-- <img src="images/signup-bg.jpg" alt=""> -->
        <div class="container">
          <div class="signup-content">
            
          <h2>Confirm Sign Up</h2>
          
           <text-input v-model="code"  class="form-input" type="text"  name ="code" rules="required|min:6" placeholder="Code" />
          <b-button variant="outline-primary"   @click="confirm">Submit</b-button>
            
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
import TextInput from "../../components/common/TextInput.vue";

export default {
  name: "App",
  components: {
    TextInput,

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
    confirm() {
      // After retrieveing the confirmation code from the user
      
      Auth.confirmSignUp(this.$store.state.randomNumber, this.code, {
        // Optional. Force user confirmation irrespective of existing alias. By default set to True.
        forceAliasCreation: true
      })
        .then(
          data => {
          this.$store.state.user = data.user,
          this.$store.state.info.nickname = this.$store.state.name,
          this.$store.state.info.email = this.$store.state.email,
          this.$store.state.info.pw = this.$store.state.password,
        //   this.$store.state.info.profile = '',
          this.$store.state.info.phone = this.$store.state.phone,
        //   this.$store.state.info.code = this.$store.state.code,
        //   this.$store.state.info.randomNumber = this.$store.state.randomNumber,
          alert("인증에 성공하셨습니다.");
          this.$router.push({ path: '/' })
          console.log(this.$store.state.info)
          
         UserApi.requestsignUp(this.$store.state.info, res=>{
              console.log(res);
          });

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