<template>
  <div id="app">
    <b-row class="text-center">
      <b-col>
        <v-card class="mx-auto" max-width="300" tile>
          <v-list rounded>
            <v-subheader>REPORTS</v-subheader>
            <v-list-item-group color="primary">
              <v-list-item router-link :to="{name: 'login'}">
                <v-list-item-content >
                  로그인
                </v-list-item-content>
              </v-list-item>
              <v-list-item router-link :to="{name: 'signup'}">
                <v-list-item-content>
                  회원가입
                </v-list-item-content>
              </v-list-item>
              <v-list-item router-link :to="{name: 'userpage', params : {
                id: userid
              }}">
                <v-list-item-content>
                  유저페이지
                </v-list-item-content>
              </v-list-item>
              <v-list-item>
                <v-list-item-content>
                  회원정보수정
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
  export default {
    data() {
      return {
        userid : sessionStorage.getItem('userid')
      }
    },
   
  }
</script>

<style>

</style>