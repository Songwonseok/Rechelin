<template>
  <div>
    <section class="signup">
      <!-- <img src="images/signup-bg.jpg" alt=""> -->
      <div class="container">
        <div class="signup-content">
          <h3 class="title"> <v-icon large>{{ mdiLockReset }}</v-icon> Update New Password</h3>
          
          
          <text-input v-model="pw" type="password" vid="pass"
            :rules="{ required: true,  min:8, regex: /^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/ }"
            class="form-input" name="password" id="password" placeholder="Password를 입력해주세요." />
          
          <v-btn class="ma-2" color="warning"  @click="confirm"
            style="color: #ff7f00 !important; ">
            <v-icon left>{{mdiPencil}}</v-icon>
            EDIT
          </v-btn>
        </div>
      </div>
    </section>
  </div>



</template>

<script>
  import {
    Auth
  } from "aws-amplify";
  import UserApi from '../../apis/UserApi';
  import TextInput from "../../components/common/TextInput.vue";
  import Vue from "vue";
  //import {EventBus} from "../../event-bus.js";
  import {
    mdiPencil,
    mdiLockReset

  } from '@mdi/js'
  export default {
    name: "App",

    data: () => {
      return {
        email: '',
        code: '',
        pw: '',
        info: {
          id: '',
          pw: '',
        },
        mdiPencil,
         mdiLockReset 
      }
    },
    created() {
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
    computed: {

    },
    methods: {

      confirm() {
        console.log('hi');
        // After retrieveing the confirmation code from the user
        console.log(this.$store.state.searchEmail);
       UserApi.requestEmail(this.$store.state.searchEmail, res=>{
          console.log('userid 받아오기!');
          console.log(res);
        this.info.id = res.object.id;
        this.info.pw = this.pw;
          UserApi.requestUpdatePw(this.info, res => {
         
          this.$alert("비밀번호가 변경되었습니다.", "success", "success");
        
          this.$router.push({
            name: 'popular',
            // params: {
            //   id: sessionStorage.getItem('userid')
            // }
          })
        })
        })
        

      }
    },

  };
</script>

<style scoped>
.v-application .title {
  margin-bottom: 50px !important;
}
  .btn {
    padding: 7px 50px;
    text-align: center;
    display: inline-block;
    font-size: 16px;
    margin: 4px 2px;
    color: cornflowerblue;
    height: 40px;
    margin-top: 10px;

  }
</style>