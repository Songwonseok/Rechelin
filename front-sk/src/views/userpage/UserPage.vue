<template>

    <div style="text-align: center;">
        <div style="margin-bottom: 10px;">
            <h1>유저 페이지</h1>
            <img :src="UserInfo.picture" :alt="UserInfo.name" style="width:300px; height:300px;">
            <h3>name: {{UserInfo.name}}</h3>
            <h3>email: {{UserInfo.email}}</h3>
            <h3>phone: {{UserInfo.phone}}</h3>
             <v-btn class="ma-2" tile outlined color="success"  router :to="{
            name: 'UserEdit', 
            params: {info: UserInfo}
            }" exact @click="edit">
            <v-icon left>{{ svgPath }}</v-icon>EDIT
        </v-btn>
        </div>

        <hr>
        <h2>{{UserInfo.name}}님이 남기신 리뷰</h2>
        <br>
        <v-layout row>
    <v-flex xs12 sm6 offset-sm3 >
      <v-card v-for="(store, index) in UserInfo.stores" :key="index"  style="margin-bottom: 30px;">
        <img :src="store.picture" :alt="index" height="200px" style="margin-top:10px;">
        <v-card-title primary-title style="text-align: center;">
          <div style="text-align: center;">
            <div class="headline">{{store.name}}</div>
            <span class="grey--text">{{store.address}}</span>
            <p class="grey--text">{{store.phone}}</p>
            <span class="pink" v-for="hash in store.hashtag" :key="hash" style="padding-right:10px;"># {{hash}}</span>
          </div>
        </v-card-title>
        <v-card-actions>
          <v-btn flat>Share</v-btn>
          <v-btn flat color="purple">Explore</v-btn>
          <v-spacer></v-spacer>
        </v-card-actions>
        <v-slide-y-transition>
          <v-card-text v-show="show">
            I'm a thing. But, like most politicians, he promised more than he could deliver. You won't have time for sleeping, soldier, not with all the bed making you'll be doing. Then we'll go with that data file! Hey, you add a one and two zeros to that or we walk! You're going to do his laundry? I've got to find a way to escape.
          </v-card-text>
        </v-slide-y-transition>
      </v-card>
    </v-flex>
  </v-layout>
        
    </div>
    

</template>

<script>
import {mapState} from 'vuex'
import { mdiPencil } from '@mdi/js'

export default {
    data() {
        return {
            svgPath: mdiPencil
        }
    },
    computed: {
        ...mapState(['allUsers']),
        UserInfo(){
            return this.allUsers[0]
        },
        UserBookMark() {
            return this.allUsers
        },

    }
}


</script>