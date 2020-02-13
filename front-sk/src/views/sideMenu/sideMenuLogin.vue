<template>
  <div id="app">
    <b-row class="text-center">
      <b-col>
        <v-card class="mx-auto" max-width="300" tile>
          <v-list rox unded>
            <v-subheader>REPORTS</v-subheader>
            <v-list-item-group color="warning">
              <v-list-item router-link :to="{name: 'login'}">
                <v-list-item-content>
                  <span><v-icon>{{mdiLogin}}</v-icon>로그인</span>
                </v-list-item-content>
              </v-list-item>
              <v-list-item router-link :to="{name: 'signup'}">
                <v-list-item-content>
                   <span><v-icon>{{mdiAccountPlus}}</v-icon>회원가입</span>
                </v-list-item-content>
              </v-list-item>
               <v-list-item router-link :to="{name:'findPW'}" >
                <v-list-item-content>
                  <span><v-icon>{{mdiKey}}</v-icon>비밀번호변경</span>
                </v-list-item-content>
              </v-list-item>
            </v-list-item-group>
          </v-list>
        </v-card>
      </b-col>
      <b-col cols="10">
        <router-view></router-view>
      </b-col>
    </b-row>
  </div>
</template>
<script>
  import {
   mdiCheckBold,
   mdiLogin,
   mdiAccountPlus,
   mdiAccountDetails,
   mdiAccountEdit,
   mdiKey
  } from '@mdi/js'
  export default {
    created(){
      this.$store.state.data +=1;
    },
    data() {
      return {
        userid : sessionStorage.getItem('userid'),
        // icons
        mdiLogin,
        mdiAccountPlus,
        mdiAccountDetails,
        mdiAccountEdit,
        mdiKey
      }
    },
  }
</script>
<style>
</style>