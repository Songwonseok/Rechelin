<template>
  <div>



    <section class="signup">
      <div class="container">
        <div class="signup-content">

          <v-chip x-large class="ma-2" color="warning" label outlined style="margin-bottom: 60px !important;">
            <v-icon left>{{icons.ClipboardCheck}}</v-icon>
            Confirm SignUp
          </v-chip>

          <text-input v-model="code" class="form-input" type="text" name="code" rules="required|min:6"
            placeholder="Code" />
          <v-btn color="warning"  @click="confirm" style="width:350px;">
            <v-icon left>{{icons.AccountMultipleCheck}}</v-icon>
            Submit
          </v-btn>

          <p class="loginhere">
            Have already an account ?
            <router-link :to="{name: 'login'}">login</router-link>
          </p>
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
  import {
    mdiClipboardCheck,
    mdiAccountMultipleCheck
  } from '@mdi/js';
  export default {
    name: "App",
    components: {
      TextInput,

    },
    data() {
      return {
        icons: {
          ClipboardCheck: mdiClipboardCheck,
          AccountMultipleCheck: mdiAccountMultipleCheck,
        }
      }
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
                this.$store.state.info.phone = this.$store.state.phone,
                alert("인증에 성공하셨습니다.");
              this.$router.push({
                name: 'userpage'
              })
              console.log(this.$store.state.info)

              UserApi.requestsignUp(this.$store.state.info, res => {
                console.log(res);
              });

            }
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
.loginhere {
  margin-top: 10px;
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