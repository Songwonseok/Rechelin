<template>
  <!-- <div class="wrap">  -->
  <!-- <div class="ele2"> --> 
    <div style="position: sticky;
  top: 85%; left: 120%; margin-right: 0px;" class="vertical-menu" id="banner">
  <v-app id="inspire">
     <b-row>
      <v-speed-dial
        v-model="fab"
        style="margin-right: 7px;"
      >
        <template v-slot:activator>
          <v-btn
            v-model="fab"
            color="#ff7f00"
            dark
            fab
            outlined
            small
          >
          <kinesis-container>
            
              <kinesis-element :strength="10">
                   <v-icon v-if="fab" color="warning">mdi-close</v-icon>
                   <v-icon color="#ff7f00" v-else>mdi-dots-horizontal-circle</v-icon>
           
              </kinesis-element>
          
          </kinesis-container>
  
          </v-btn>
        </template>
        <v-btn
          fab
          dark
          outlined
          small
          color="green"
          @click="$router.push({name: 'createReview'})"
        >
          <v-icon color="green">mdi-pencil</v-icon>
        </v-btn>
        <v-btn
          fab
          dark
          outlined
          small
          color="indigo"
          @click="UserLogout"
        >
          <v-icon color="indigo">mdi-logout</v-icon>
        </v-btn>
         <v-btn
          fab
          outlined
          dark
          small
          @click="userpageGo"
          color="blue">
          <v-icon color="blue">mdi-account</v-icon>
        </v-btn>
         
      </v-speed-dial>



      <v-dialog
        v-model="dialog"
        width="500"
        
      >
        <template v-slot:activator="{ on }">
          <v-btn
            color="orange lighten-2"
            dark
            outlined
            fab
            small
            v-on="on"
            value = "favorites"
          >
               <kinesis-container>
            
              <kinesis-element :strength="10">
                   <v-icon color="warning">mdi-crown</v-icon>
           
              </kinesis-element>
          
          </kinesis-container>
          </v-btn>
        </template>
  
        <v-card>
          <v-card-title
            class="headline orange lighten-2"
            primary-title
          >
            <span style="color : white">🏆 User Review Ranking </span>
          </v-card-title>
  
 
      
  
    
        <template v-for="(item,i) in topUserInfo"  >
          <!-- <v-subheader
            v-if="item.header"
            :key="item.header"
            v-text="item.header"
          ></v-subheader>
  
          <v-divider
            v-else-if="item.divider"
            :key="index"
            :inset="item.inset"
              
          ></v-divider>
   -->
          <v-list-item
          
            :key="item.email"
            class="userList"
            router-link="router-link" :to="{name: 'userpage', params : {id : item.id}}"
            
            @click = "userClick(), dialog = false"

          >
            <v-list-item-avatar>
              <v-img :src="item.profile"></v-img>
            </v-list-item-avatar>
  
            <v-list-item-content>
              <v-list-item-title v-html="item.nickname"></v-list-item-title>
            </v-list-item-content>
            
            <v-list-item-content>
            {{crown[i]}} 등록된 리뷰 개수 : {{item.reviewLength}}
            </v-list-item-content>
          </v-list-item>
        </template>
      
    
         
  
          <v-divider></v-divider>
  
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
              color="orange darken-1"
              text
              style = "background-color:#FFC107;"
              @click="dialog = false"
            >
              I accept
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>


      <!-- 여기에 알람 버튼 넣기 -->
      <v-badge
        :content="alarms.length"
        :value="alarms.length"
        color="green"
        overlap
      >
    
      <v-btn fab 
      small
      outlined
      dark 
      color="red" 
      id="popover-target-1"
       @click="notificationGet"
        @mouseover="notificationGet"
        style="margin-left: 7px;"
        >
           <kinesis-container>
            
              <kinesis-element :strength="10">
                   <v-icon color="red">mdi-bell</v-icon>
           
              </kinesis-element>
          
          </kinesis-container>
      </v-btn>
        </v-badge>
      <b-popover target="popover-target-1" triggers="hover" placement="topleft" style="min-width: 250px;">
        팔로우 요청
      
        <b-list-group-item v-for="(alarm, index) in alarms" :key="index">
           <router-link :to="{name: 'userpage', params: {
             id: alarm.fan.id
           }}" style="color: black; margin-right: 5px;">{{alarm.fan.nickname}}</router-link>
         <span> 
          <v-btn x-small  @click="followAccept(alarm.fan.id, index)" style="color: red !important; background-color: #8BC34A;">accept</v-btn>
          <v-btn x-small @click="followDecline(alarm.fan.id, index)" style="color: blue !important; background-color: #FFC107;">decline</v-btn>
        </span>
        </b-list-group-item>
      </b-popover>
</b-row>
  </v-app>
</div>
    <!-- </div> -->
<!-- </div>   -->
</template>

<script>
// import userRankListDetail from './userRankListDetail.vue'
import Vue from 'vue'
import { KinesisContainer, KinesisElement } from 'vue-kinesis'
import UserApi from '../../apis/UserApi.js';
import { mdiBell } from '@mdi/js';
Vue.component('kinesis-container', KinesisContainer)
Vue.component('kinesis-element', KinesisElement)
export default {
  created(){
      this.init();
  },
  data: () => ({
    items: [
     
    ],
    dialog: false,
    notifications: false,
    sound: true,
    widgets: false,
    topUserInfo : [],
    reviewLength : 0,
    crown : ['🥇', '🥈', '🥉'],
    fab: false,

    // icons
    mdiBell,
  }),
  methods : {
    userRank(){
       // eslint-disable-next-line no-console
      console.log('clicked');
      
      this.$router.push({path : '/userRankListDetail'})
    },
    async fetchUserReviewList(data){
        return UserApi.requestUserReviewCnt(data)
       .then(response =>{
         console.log(this);
        this.reviewLength =response.data.object.length;
        console.log('data fetch');
        console.log(this.reviewLength);
    })
    },
    async init(){
 UserApi.requestUserRanking()
    .then(async response => {
      console.log(response);
      let topUserList = new Array();
      
      for(let i = 0; i<response.data.object.length; i++){
        let item = [];
        item['header'] = 'today'
        item['email'] = response.data.object[i].email;
        item['nickname'] = response.data.object[i].nickname;
        item['id'] = response.data.object[i].id;
        
        await this.fetchUserReviewList(response.data.object[i].id);
        
        console.log(this.reviewLength);
        item['reviewLength'] = this.reviewLength;
        if(response.data.object[i].profile)
          item['profile'] = response.data.object[i].profile;
        else
          item['profile'] = "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_960_720.png";
        JSON.stringify(item);
        topUserList.push(item);
      }
      
      this.topUserInfo = topUserList;
      this.topUserInfo.sort(function(a,b){
        return b['reviewLength'] - a['reviewLength'];
      })
      
      console.log('top user 받아오기');
      console.log(this.topUserInfo);
    })
    },
     UserLogout() {
        this.$store.dispatch('logout')
        this.$router.push({
          name: "login"
        })
      },
      notificationGet() {
        var payload = this.$store.state.userid
        this.$store.dispatch('notificationGet', payload)
        this.alarms = this.$store.state.notifications
      },
      userpageGo() {
        this.$router.push({name: 'userpage', params: {
            id: sessionStorage.getItem('userid')
        }})
      },
      followAccept(f, index) {

        var payload = {
          fan: f,
          star: this.$store.state.userid
        }
        this.$store.dispatch('followAccept', payload)
        this.alarms.splice(index, 1)
      },
      followDecline(f, index) {
        var payload = {
          fan: f,
          star: this.$store.state.userid
        }
        this.$store.dispatch('followDecline', payload)
        this.alarms.splice(index, 1)
      },
      userClick(){

      this.$store.state.userPageStatus += 1;
    }
  },
  computed: {
    alarms() {
      return this.$store.state.notifications
    },
    
    
    
  }, 
}
</script>

<style>
#inspire {
   background: rgb(204, 204, 204);
    height : 0px;
}

#create .v-speed-dial {
  position: absolute;
}

#create .v-btn--floating {
  position: relative;
}



.vertical-menu {
  position : sticky;
  z-index: 9;
  right : 70px;
  width: 200px; /* Set a width if you like */
}
.v-speed-dial__list {
  width: 300px;
}

</style>