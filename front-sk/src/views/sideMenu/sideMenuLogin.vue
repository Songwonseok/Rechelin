<template>
  <div id="app">
    <b-row class="text-center">
      <b-col>
        <v-card class="mx-auto" max-width="300" tile>
          <v-list rox unded>
            <v-list-item-group color="warning">
              <template v-if=" userToken==null">
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
              </template>
              
              <template v-else>
              <!-- 유저페이지, 비밀번호 찾기, 회원정보 수정 -->
              <v-list-item router-link :to="{name: 'userpage', params : {
                id: userid
              }}">
              <!-- back 단에서 email로 보내줘서 찾는데 front에서는 id로 찾아야 한다. 그래야 페이지를 넘길 수 있다.  -->
                <v-list-item-content>
                  <span><v-icon>{{mdiAccountDetails}}</v-icon>유저페이지</span>
                </v-list-item-content>
              </v-list-item>
              <v-list-item router-link :to="{name: 'useredit'}">
                <v-list-item-content>
                  <span><v-icon>{{mdiAccountEdit}}</v-icon>회원정보수정</span>
                </v-list-item-content>
              </v-list-item>
              <v-list-item router-link :to="{name:'updatepw'}" >
                <v-list-item-content>
                  <span><v-icon>{{mdiKey}}</v-icon>비밀번호변경</span>
                </v-list-item-content>
              </v-list-item>
            </template>


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
        // userid : sessionStorage.getItem('userid'),
        // icons
        mdiLogin,
        mdiAccountPlus,
        mdiAccountDetails,
        mdiAccountEdit,
        mdiKey
      }
    },
    computed: {
      userToken(){
        return this.$store.state.accessToken
      },
      userid(){
        return this.$store.state.userid
      }
    }
  }
</script>
<style>
</style>