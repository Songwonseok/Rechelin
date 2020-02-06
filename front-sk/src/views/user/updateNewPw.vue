<template>
  <div>
     <section class="signup">
        <!-- <img src="images/signup-bg.jpg" alt=""> -->
        <div class="container">
          <div class="signup-content">
            <h2>Update New Password</h2>
             
             <text-input
                    v-model="pw"
                    type="password"
                    vid="pass"
                    :rules="{ required: true,  min:8, regex: /^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/ }"
                    class="form-input"
                    name="password"
                    id="password"
                    placeholder="Password를 입력해주세요."
                  />
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
import { Auth } from "aws-amplify";
import UserApi from '../../apis/UserApi';
import TextInput from "../../components/common/TextInput.vue";
import Vue from "vue";
//import {EventBus} from "../../event-bus.js";
export default {
  name: "App",
  
 data: () => {
            return {
                email : '',
                code : '',
                pw : '',
                info : {
                    email : '',
                    pw : '',
                }
            }
 },
 created(){
      //EventBus 와 Spring boot 에서 연동이 잘 안되고 있음
      //일부 해결된 기미가 보이지만, email이 나오지 않음 
        //  EventBus.$on('eventName', code =>{
        //     console.log('hi');
           
        //     this.code = code;
            
        //     console.log(this.code);
        // });
  },
  components: {
    TextInput,

  },
  computed:{
   
  },
  methods: {
   
      confirm() {
      console.log('hi');
      // After retrieveing the confirmation code from the user
          this.info.email = this.$store.state.searchEmail;
          this.info.pw = this.pw;
          alert("변경되었습니다.");
          
         UserApi.requestUpdatePw(this.info, res=>{
              console.log(res);  
            this.$router.push({ path: '/' })
          });
      
   }
  },
  
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
