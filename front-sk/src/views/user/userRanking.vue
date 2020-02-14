<template>
  <!-- <div class="wrap">  -->
  <!-- <div class="ele2"> --> 
    <div style="position: sticky;
  top: 300px;
  left : 2500px;" class="vertical-menu">
  <v-app id="inspire">

     <div class="text-center">
      <v-dialog
        v-model="dialog"
        width="500"
      >
        <template v-slot:activator="{ on }">
          <v-btn
            color="orange lighten-2"
            dark
            v-on="on"
            value = "favorites"
          >
          
          

               <kinesis-container>
            
              <kinesis-element :strength="10">
                   <v-icon>mdi-food</v-icon>
           <span> User Ranking</span>
              
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
  
 
      
  
    
        <template v-for="(item,i) in topUserInfo" >
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
              style = "background-color : #FFC107"
              @click="dialog = false"
            >
              I accept
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </div>
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

Vue.component('kinesis-container', KinesisContainer)
Vue.component('kinesis-element', KinesisElement)
export default {
  created(){
      this.init();
  },
  data: () => ({
    items: [
      { header: 'Today' },
      {
        avatar: 'https://cdn.vuetifyjs.com/images/lists/1.jpg',
        title: 'Brunch this weekend?',
        subtitle: "<span class='text--primary'>Ali Connors</span> &mdash; I'll be in your neighborhood doing errands this weekend. Do you want to hang out?",
      },
      { divider: true, inset: true },
      {
        avatar: 'https://cdn.vuetifyjs.com/images/lists/2.jpg',
        title: 'Summer BBQ <span class="grey--text text--lighten-1">4</span>',
        subtitle: "<span class='text--primary'>to Alex, Scott, Jennifer</span> &mdash; Wish I could come, but I'm out of town this weekend.",
      },
      { divider: true, inset: true },
      {
        avatar: 'https://cdn.vuetifyjs.com/images/lists/3.jpg',
        title: 'Oui oui',
        subtitle: "<span class='text--primary'>Sandra Adams</span> &mdash; Do you have Paris recommendations? Have you ever been?",
      },
    ],
    dialog: false,
    notifications: false,
    sound: true,
    widgets: false,
    topUserInfo : [],
    reviewLength : 0,
    crown : ['🥇', '🥈', '🥉']
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
      console.log('top user 받아오기');
      console.log(this.topUserInfo);
    })
    }
  }
}
</script>

<style>
#inspire {
   background: rgb(204, 204, 204);
    height : 0px;
}

/* .wrap {
  position: absolute;
  width: 100%;
  height: 100%;
  left : 20

}  */
 /* .ele2 { 
  display: inline-block;
  position: sticky;
  top: 300px;
  right : 400px;
  background: blue;
  
}  */

.vertical-menu {
  position : sticky;
  z-index: 9;
  right : 70px;
  width: 200px; /* Set a width if you like */
}

/* .userList{
  height : 30px;
} */
/* 
.vertical-menu a { */
  /*background-color: #eee; /* Grey background color */
  /*color: black; /* Black text color */
  /*display: block; /* Make the links appear below each other */
  /*padding: 12px; /* Add some padding */
  /*text-decoration: none; /* Remove underline from links */
/* } */

/*.vertical-menu a:hover {
 /* background-color: yellowgreen; /* Dark grey background on mouse-over } */

/*.vertical-menu a.active {
  background-color: #4CAF50; /* Add a green color to the "active/current" link */
  /*color: white;
}*/
/* 
.theme--light.v-application {
    background: rgb(204, 204, 204);

}
.v-card>:last-child:not(.v-btn):not(.v-chip) {
    border-bottom-left-radius: inherit;
    border-bottom-right-radius: inherit;
    height: 250px;
}
.theme--light.v-list-item:not(.v-list-item--active):not(.v-list-item--disabled) {
    min-height: 33px;
    color: rgba(0, 0, 0, 0.87) !important;
}
.theme--light.v-card {
    background-color: #fff;
    color: rgba(0,0,0,.87);
    left: 350px;
} */
</style>