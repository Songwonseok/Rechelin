<template>

    <div style="text-align: center;">
        <div style="margin-bottom: 10px;">
            <h1>유저 페이지</h1>
            <hr>
            <!-- 앞에 url에 아이디가 받아져 있으면, 뒤에 파람즈로 보내주지 않아도 됨 -->
            <v-btn
              @click="UserFan">팔로우: {{UserInfo.fans.length}} </v-btn>
            <v-btn
              @click="UserStar">팔로잉: {{UserInfo.stars.length}} </v-btn>
            <hr>
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
        <router-view></router-view>
    </div>
    

</template>

<script>
import {mapState} from 'vuex'
import { mdiPencil } from '@mdi/js'

export default {
    data() {
        return {
            svgPath: mdiPencil,

           
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


    },
    methods: {
      UserStar() {
        this.$router.push({name: 'Stars', params: { stars: this.UserInfo.stars}}).catch(err =>{})
      },
      UserFan() {
        this.$router.push({name:'Fans', params: { fans : this.UserInfo.fans}}).catch(err =>{});
      },

    },
    created() {
    
        this.$router.push({name: 'UserReviews', params: {
          info: this.UserInfo.stores
        }})
      
    }
}


</script>