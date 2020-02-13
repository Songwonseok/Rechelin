<template>
  <div id="app">
    <b-row class="text-center">
      <b-col>
        <v-card class="mx-auto" max-width="300" tile>
          <v-list rox unded>
            <v-subheader>REPORTS</v-subheader>
            <v-list-item-group color="warning">
    
              
              <v-list-item router-link :to="{name: 'userpage', params : {
                id: userid
              }}">
                <v-list-item-content>
                  <span><v-icon>{{mdiAccountDetails}}</v-icon>유저페이지</span>
                </v-list-item-content>
              </v-list-item>
              <v-list-item router-link :to="{name: 'UserEdit'}">
                <v-list-item-content>
                  <span><v-icon>{{mdiAccountEdit}}</v-icon>회원정보수정</span>
                </v-list-item-content>
              </v-list-item>
               <v-list-item router-link :to="{name:'updatepw'}" >
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