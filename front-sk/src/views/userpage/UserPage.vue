<template>
  <div>
    <div class="content-profile-page">
      <div class="profile-user-page card">
        <div class="img-user-profile">
          <img class="profile-bgHome" src="https://cdn.pixabay.com/photo/2016/03/27/21/34/restaurant-1284351_1280.jpg" />
          <template v-if="UserInfo.profile==null">
            <img class="avatar" src="../../assets/images/ssafy.jpg" :alt="UserInfo.nickname" />
          </template>
          <template v-else>
            <img class="avatar" :src="UserInfo.profile" :alt="UserInfo.nickname" />
          </template>
        </div>
        <!--자기 자신이면 Follow button 숨기기 -->
        <!-- 팔로우 요청 중 or 이미 팔로우이면 버튼 다르게 하기 -->
        <template v-if="uid != id" >
            <div v-if="followstatus==0 || followstatus==''">
              <button @click="followRequest">Follow</button>
            </div>
            <div v-if="followstatus==1">
              <button @click="followRequest">요청됨</button>
            </div>
            <div v-if="followstatus==2">
              <button @click="followRequest">UnFollow</button>
            </div>
        </template>
        
        <div class="user-profile-data">
          
          <h1>{{UserInfo.nickname}}</h1>
          <p>{{UserInfo.email}}</p>
          <!-- 자기 자신일때만  Edit 보여주기-->
          <router-link :to="{name: 'useredit'}" style="color: #ff7f00;"><strong v-if="id == uid"> Edit</strong></router-link>
        </div>

         <ul class="data-user">
         <li>
            <router-link 
            :to="{name: 'bookmarks', 
            params: {
          bookmarks: this.bookmarkList, 
          reviews : this.reviewList, // 향후 db에서 받아오는 값으로 수정할 애들
        }}" ><strong>{{this.reviewList.length}}</strong><span>Posts</span></router-link>
          </li>
          <li>
            <router-link :to="{name:'fans', params: { fans : this.fanList}}">
              <strong>{{fanList.length}}</strong><span>Followers</span></router-link>
          </li>
          <li>
            <router-link :to="{name: 'stars', params: { stars: this.starList}}"><strong>
                {{starList.length}}</strong><span>Following</span></router-link>
          </li>
        </ul> 
      </div>
      <router-view></router-view>
    </div>

    <footer>
      <h4>Design by 싸피 4조 쏘아올리조</h4>
    </footer>
  </div>

</template>

<script>
  import {
    mapState
  } from 'vuex'
  import {
    mdiPencil,
    mdiAccountCircle,
    
  } from '@mdi/js'
  import UserApi from '../../apis/UserApi'
  import FollowApi from '../../apis/FollowApi'
  import ReviewApi from '../../apis/ReviewApi'
  

  export default {
    created(){
      console.log()
    },
    data() {
      return {
        svgPath: mdiPencil,
        accountIcon: mdiAccountCircle,
        // URL에서 가져온 User
        id : sessionStorage.getItem('userid'), // session id
        email: sessionStorage.getItem('userEmail'),
        UserInfo:{ // id로 가져온 정보들
          email:'',
          nickname:'',
          phone:'',
          profile:''
        },
        fanList:[],
        starList:[],
        bookmarkList:[],
        reviewList:[],
        followstatus:'',
        // myEmail:this.$store.state.userEmail // session에 저장된 User
      }
    },
    watch : {
      changeMonitorUserInfo : function(v){
        console.log('UserPage');
        this.getUser();
        this.getFanList();
        this.getStarList();
        this.getBookmarkList();
        this.getReviewList();
        // let t = ['t'];
        // let list = [...t];
        // console.log(this.UserInfo);
        // list = [...this.UserInfo];
        // this.UserInfo = list;
      },
      paramId : function(v){
        //이 곳은 user page로 router가 이동할때 param.id 를 불러옵니다. 갱신해줍니다.
        this.getUser();
        this.getFanList();
        this.getStarList();
        this.getBookmarkList();
        this.getReviewList();
      }
    },
    computed: {
      // url에서 가져온 user id by 김현지
      uid() {
        return this.$route.params.id
      },
      changeMonitorUserInfo(){
        return this.$store.state.userPageStatus;
      },
      paramId(){
        return this.$route.params.id;
      }
    },
    methods: {
      followRequest() {
        FollowApi.requestFollow(this.id, this.uid, res=>{
          this.followstatus = 1;
        }, error=>{
          alert('팔로우 요청 실패!')
        })
      },
      getFanList(){
        FollowApi.requestFanList(this.uid, res=>{
          this.fanList = res;
          console.log('fan 성공')
        }, error=>{
          alert('FanList가져오기 실패')
        })
      },
      getStarList(){
        FollowApi.requestStarList(this.uid, res=>{
          this.starList = res;
          console.log('star 성공')
          console.log(this.starList);
        }, error=>{
          alert('StarList가져오기 실패')
        })
      },
      getBookmarkList(){
        ReviewApi.requestBookmarkList(this.uid, res=>{
          this.bookmarkList = res;
        }, error=>{
          alert('BookmarkList 가져오기 실패')
        })
      }
      ,getReviewList(){
         ReviewApi.requestMyReviewList(this.uid, res=>{
          this.reviewList = res;
        }, error=>{
          alert('ReviewList 가져오기 실패')
        })
      }
      ,getUser(){
        
          UserApi.requestId( this.uid,res=>{
            console.log(this.uid);
            console.log('getUser');
            this.UserInfo.email = res.object.email;
            this.UserInfo.nickname = res.object.nickname;
            this.UserInfo.phone = res.object.phone;
            this.UserInfo.profile = res.object.profile;
            console.log(this.UserInfo);
           
          },error =>{
            alert('정보 가져오기 실패 !');
          })

          
        },
        getStatus(){
          FollowApi.requestStatus(this.id, this.uid, res=>{
            this.followstatus = res;
          }, error =>{
            alert('follow status 확인 실패!');
          })
        }


    },
    mounted() {
      this.getUser();
      this.getFanList();
      this.getStarList();
      this.getBookmarkList();
      this.getReviewList();
      this.getStatus();
      console.log(this.UserInfo, 'jhgjhgjhg')
    },

  }
</script>

<style scoped>
  body {
    background: #f8f5f0;
    font-family: "Open sans", sans-serif;
  }

  a {
    text-decoration: none;
    color: #ff7f00;
  }

  .content-profile-page {
    margin: 1em auto;
    width: 44.23em;
  }

  .card {
    background: #fff;
    border-radius: 0.3rem;
    box-shadow: 0 1px 1px rgba(0, 0, 0, 0.2);
    border: .1em solid rgba(0, 0, 0, 0.2);
    margin-bottom: 1em;
  }

  .profile-user-page .img-user-profile {
    margin: 0 auto;
    text-align: center;
  }

  .profile-user-page .img-user-profile .profile-bgHome {
    border-bottom: .2em solid #f5f5f5;
    width: 44.23em;
    height: 16em;
  }

  .profile-user-page .img-user-profile .avatar {
    margin: 0 auto;
    background: #fff;
    width: 7em;
    height: 7em;
    padding: 0.25em;
    border-radius: .4em;
    margin-top: -10em;
    box-shadow: 0 0 .1em rgba(0, 0, 0, 0.35);
  }

  .profile-user-page button {
    position: absolute;
    font-size: 13px;
    font-weight: bold;
    cursor: pointer;
    width: 7em;
    background: #ff7f00;
    border: 1px solid #ff7f00;
    color: #fff;
    outline: none;
    border-radius: 0 .6em .6em 0;
    padding: .80em;
  }

  .profile-user-page button:hover {
    background: #ff7f00;
    transition: background .2s ease-in-out;
    border: 1px solid #ff7f00;
  }

  .profile-user-page .user-profile-data,
  .profile-user-page .description-profile {
    text-align: center;
    padding: 0 1.5em;
  }

  .profile-user-page .user-profile-data h1 {
    font-family: "Lato", sans-serif;
    margin-top: 0.35em;
    color: #292f33;
    margin-bottom: 0;
  }

  .profile-user-page .user-profile-data p {
    font-family: "Lato", sans-serif;
    color: #8899a6;
    font-size: 1.1em;
    margin-top: 0;
    margin-bottom: 0.5em;
  }

  .profile-user-page .description-profile {
    color: #75787b;
    font-size: 0.98em;
  }

  .profile-user-page .data-user {
    font-family: "Quicksand", sans-serif;
    margin-bottom: 0;
    cursor: pointer;
    padding: 0;
    list-style: none;
    display: table;
    width: 100.15%;
  }

  .profile-user-page .data-user li {
    margin: 0;
    padding: 0;
    width: 33.33334%;
    display: table-cell;
    text-align: center;
    border-left: 0.1em solid transparent;
  }

  .profile-user-page .data-user li:first-child {
    border-left: 0;
  }

  .profile-user-page .data-user li:first-child a {
    border-bottom-left-radius: 0.3rem;
  }

  .profile-user-page .data-user li:last-child a {
    border-bottom-right-radius: 0.3rem;
  }

  .profile-user-page .data-user li a,
  .profile-user-page .data-user li strong {
    display: block;
  }

  .profile-user-page .data-user li a {
    background-color: #f7f7f7;
    border-top: 1px solid rgba(242, 242, 242, 0.5);
    border-bottom: .2em solid #f7f7f7;
    box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.4), 0 1px 1px rgba(255, 255, 255, 0.4);
    padding: .93em 0;
    color: #46494c;
  }

  .profile-user-page .data-user li a strong,
  .profile-user-page .data-user li a span {
    font-weight: 600;
    line-height: 1;
  }

  .profile-user-page .data-user li a strong {
    font-size: 2em;
  }

  .profile-user-page .data-user li a span {
    color: #717a7e;
  }

  .profile-user-page .data-user li a:hover {
    background: rgba(0, 0, 0, 0.05);
    border-bottom: .2em solid #ff7f00;
    color: #ff7f00;
  }

  .profile-user-page .data-user li a:hover span {
    color: #ff7f00;
  }

  footer h4 {
    display: block;
    text-align: center;
    clear: both;
    font-family: "Coda", sans-serif;
    color: #566965;
    line-height: 6;
    font-size: 1em;
  }

  footer h4 a {
    text-decoration: none;
    color: #ff7f00;
  }
</style>