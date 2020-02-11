<template>
  <div>
    <v-app id="inspire" class ="rootMain">
      <!-- 네브바 -->
        <div v-if="checkLogin" >
        <div style="text-align: center !important;">
          <v-btn color="warning" id="find" dark style="position: relative; top: 250px; z-index:1;" @click="open">Find
            Restaurant</v-btn>
        </div>
        <v-dialog v-model="openSearch" max-width="600">
          <v-card>
            <v-card-title class="headline">
              <v-combobox v-model="newSearch" chips clearable label="Your restaurant" multiple solo>
              </v-combobox>
            </v-card-title>

            <v-card-text>

              위치
              <v-divider></v-divider>
              강남
              <v-chip draggable @click="insertTags('locations', 0, '강남')">강남</v-chip>
              <v-chip draggable @click="insertTags('locations', 1, '잠실')">잠실</v-chip>
              <v-chip draggable @click="insertTags('locations', 2, '사당')">사당</v-chip>
              <v-chip draggable @click="insertTags('locations', 3, '코엑스')">코엑스</v-chip>
              <v-chip draggable @click="insertTags('locations', 4, '고속터미널')">고속터미널</v-chip>
              <v-chip draggable @click="insertTags('locations', 5, '가로수길')">가로수길</v-chip>
              <v-chip draggable @click="insertTags('locations', 6, '서래마을')">서래마을</v-chip>
              <v-chip draggable @click="insertTags('locations', 7, '건대')">건대</v-chip>



              <v-divider></v-divider>
              강북
              <v-chip draggable @click="insertTags('locations', 8, '종로')">종로</v-chip>
              <v-chip draggable @click="insertTags('locations', 9, '삼청동')">삼청동</v-chip>
              <v-chip draggable @click="insertTags('locations', 10, '서촌')">서촌</v-chip>
              <v-chip draggable @click="insertTags('locations', 11, '왕십리')">왕십리</v-chip>
              <v-chip draggable @click="insertTags('locations', 12, '혜화')">혜화</v-chip>
              <v-chip draggable @click="insertTags('locations', 13, '상수')">상수</v-chip>
              <v-chip draggable @click="insertTags('locations', 14, '한남동')">한남동</v-chip>
              <v-chip draggable @click="insertTags('locations', 15, '이태원')">이태원</v-chip>
              <v-chip draggable @click="insertTags('locations', 16, '홍대')">홍대</v-chip>
              <v-chip draggable @click="insertTags('locations', 17, '광화문')">광화문</v-chip>
              <v-chip draggable @click="insertTags('locations', 18, '여의도')">여의도</v-chip>
              <v-chip draggable @click="insertTags('locations', 19, '연남동')">연남동</v-chip>
              <v-chip draggable @click="insertTags('locations', 20, '합정')">합정</v-chip>
              <v-chip draggable @click="insertTags('locations', 21, '경리단길')">경리단길</v-chip>
              <v-chip draggable @click="insertTags('locations', 22, '명동')">명동</v-chip>
              <v-chip draggable @click="insertTags('locations', 23, '신촌')">신촌</v-chip>


              <v-divider></v-divider>
              누구와
              <v-divider></v-divider>

              <v-chip draggable @click="insertTags('withWho', 0, '혼밥')">혼밥</v-chip>
              <v-chip draggable @click="insertTags('withWho', 1, '친구와')">친구와</v-chip>
              <v-chip draggable @click="insertTags('withWho', 2, '연인과')">연인과</v-chip>
              <v-chip draggable @click="insertTags('withWho', 3, '직장동료')">직장동료</v-chip>
              <v-chip draggable @click="insertTags('withWho', 4, '가족과')">가족과</v-chip>


              <v-divider></v-divider>
              연령별
              <v-divider></v-divider>

              <v-chip draggable @click="insertTags('ages', 0, '10대')">10대</v-chip>
              <v-chip draggable @click="insertTags('ages', 1, '20대')">20대</v-chip>
              <v-chip draggable @click="insertTags('ages', 2, '30대')">30대</v-chip>
              <v-chip draggable @click="insertTags('ages', 3, '40450대')">4050대</v-chip>
              <v-chip draggable @click="insertTags('ages', 4, '그이상')">그이상</v-chip>


              <v-divider></v-divider>
              분위기
              <v-divider></v-divider>

              <v-chip draggable @click="insertTags('moods', 0, '시끌벅적한')">시끌벅적한</v-chip>
              <v-chip draggable @click="insertTags('moods', 1, '차분한')">차분한</v-chip>
              <v-chip draggable @click="insertTags('moods', 2, '소소한')">소소한</v-chip>
              <v-chip draggable @click="insertTags('moods', 3, '고급진')">고급진</v-chip>
              <v-chip draggable @click="insertTags('moods', 4, '데이트')">데이트</v-chip>

              <v-divider></v-divider>
              편의시설
              <v-divider></v-divider>

              <v-chip draggable @click="insertTags('facility', 0, '단체석')">단체석</v-chip>
              <v-chip draggable @click="insertTags('facility', 1, '주차장')">주차장</v-chip>
              <v-chip draggable @click="insertTags('facility', 2, '개별룸')">개별룸</v-chip>



            </v-card-text>

            <v-card-actions>
              <v-spacer></v-spacer>
            </v-card-actions>
          </v-card>
        </v-dialog>

        <div>
          <v-carousel cycle show-arrows-on-hover hide-delimiters v-ripple="true" class="text-center">
            <v-carousel-item v-for="(item,i) in items" :key="i" :src="item.src">
            </v-carousel-item>
          </v-carousel>
        </div>
        <!-- 탭부분 -->
        <v-tabs color="orange" v-model="tabs" grow>
          <v-tab><i class="fas fa-user fa-2x"></i> &nbsp;&nbsp;유저 페이지</v-tab>
          <v-tab><i class="fas fa-utensils fa-2x"></i> &nbsp;&nbsp;음식점 검색</v-tab>
          <v-tab><i class="fas fa-mail-bulk fa-2x"></i> &nbsp;&nbsp;리뷰 등록</v-tab>
          <v-tab>미정</v-tab>
          <v-tab>미정</v-tab>
        </v-tabs>

      <v-tabs-items v-model="tabs">
        <v-tab-item>
          <v-card flat>
            <v-card-text>
              <!-- Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. -->
              <!-- <side-menu-user></side-menu-user> -->
              
            </v-card-text>
          </v-card>
        </v-tab-item>

        <v-tab-item>
          <v-card flat>
            <v-card-title class="headline">음식점 검색</v-card-title>
            <v-card-text> 
              <side-menu-food></side-menu-food>
            </v-card-text>
          </v-card>
        </v-tab-item>

        <v-tab-item>
          
          <v-card flat>
            <v-card-title class="headline">리뷰</v-card-title>
             
            <v-card-text>
              <sideMenuReview></sideMenuReview>
            </v-card-text>
          </v-card>
        </v-tab-item>
      </v-tabs-items>



      <!-- 음식창과 검색창 -->



      <!-- 사진이랑 유저 랭킹 -->
      <!-- <v-container class="grey lighten-5" style="
    margin-top: 400px;
"> -->
        <!-- <v-row no-gutters>
          <v-col cols="9">
            <v-row no-gutters>

              <v-card :loading="loading" class="mx-auto my-12 storeRank" max-width="374">
              </v-card>

            </v-row>
          </v-col>
        </v-row> -->

<!-- -------------------------리뷰 작성하기 ------------------------------------- -->
<!-- 
        <div class="text-center">

          <br><br>
          <span style="font-size:30px"> 리뷰 작성하기</span><br>

        </div> -->
<!-- 
      </v-container> -->
  </div>

 
  </v-app>

  </div>
</template>


<script>
  import {
    mdiMagnify,
    mdiAccountCircle,
    mdiForum,
    mdiCrown,
    mdiPlus
  } from '@mdi/js'
  import {
    mapState
  } from 'vuex'
  //import userSearch from '../../src/components/common/userSearch';
  import UserApi from '../../src/apis/UserApi.js';
  import SearchApi from '../../src/apis/UserApi.js';
  import sideMenuFood from './sideMenu/sideMenuFood.vue';
  import sideMenuReview from './sideMenu/sideMenuReview.vue';

  export default {
    created() {
      this
        .$store
        .dispatch('LOADING_USERDATA');
    },
    watch : {
      tabs(t){
        console.log(t);
        if(t===0){
           //유저 페이지 
           this.$router.push({ path: "sideMenuUser" });
        }else if(t==1){
           //음식점 검색
            this.$router.push({ path: "sideMenuFood" });
        }else if(t==2){
          //리뷰 등록 
          this.$router.push({ path :'sideMenuReview'});
        }else if(t==3){
          //미정
        }else if(t==4){
          //미정
        }
      }
    },
    components : {
      // sideMenuUser,
      // sideMenuFood,
      // sideMenuReview,
    },
    data() {
      return {
        search: '',
        inputStatus: true,
        history: {
          email: '',
          nickname: '',
          moveUser: '',
        },
        icons: {
          Magnify: mdiMagnify,
          AccountCircle: mdiAccountCircle,
          Forum: mdiForum,
          Crown: mdiCrown,
          Plus: mdiPlus,
        },
        loading: false,
        selection: 1,

        newSearch: [],
        openSearch: false,
        value: ['apple', 'orange'],
        items: [{
            src: 'https://cdn.pixabay.com/photo/2017/05/07/08/56/pancakes-2291908_1280.jpg',
          },
          {
            src: 'https://cdn.pixabay.com/photo/2016/03/05/23/02/barbecue-1239434_1280.jpg',
          },
          {
            src: 'https://cdn.pixabay.com/photo/2016/12/26/17/28/food-1932466_1280.jpg',
          },
          {
            src: 'https://cdn.pixabay.com/photo/2017/03/30/15/47/churros-2188871_1280.jpg',
          },
        ],
        hashtags: {
          locations: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
          withWho: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
          ages: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
          moods: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
          facility: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
        },
        tabs: 0,
        checkLogin: true,
      }
    },

    computed: {
      ...mapState(['tempStores']),
      Stores() {
        return this.tempStores
      }
    },
    methods: {
      reserve() {
        this.loading = true

        setTimeout(() => (this.loading = false), 2000)
      },
      changeInput() { //if-else 
        if (this.inputStatus == true) this.inputStatus = false;
        else
          this.inputStatus = true;
      },
      changeInput2() { //enter 누를 때, user 정보가 db에 저장된다.
        let find = false;

        for (var j = 0; j < this.$store.state.searchUser.nickname.length; j++) {
          if (this.search == this.$store.state.searchUser.nickname[j]) {
            this.moveUser = this.$store.state.searchUser.email[j];
            //검색한 닉네임의 email
            find = true;
            break;
          }

        }
        if (find == false) this.moveUser = 'notEmail'
        //this.history.email = sessionStorage.getItem("userEmail");
        this.history.email = "ssafy@naver.com"
        this.history.nickname = this.search;


        SearchApi.searchUserHistory(this.history, res => {

          if (this.inputStatus == true) this.inputStatus = false;
          else {
            this.inputStatus = true;
            this.search = "";
            this.$store.dispatch('LOADING_RECENTUSERDATA', this.history.email);
          }
        }, error => {
          console.log("userSearch.vue, server 와 통신 실패");
        })
        //DB에 저장하고 email로 유저페이지 이동한다. 
        //this.$router.push({ name: 'userPage', params: { id: this.moveUser }}) 
        //다시 inpustState를 변화시키므로써, User가 최근 검색 history를 보여준다. 

      },
      open() {
        this.openSearch = !this.openSearch
      },
      insertTags(cate, n, name) {

        if (cate === "locations") {

          if (!this.hashtags.locations[n]) {
            this.hashtags.locations[n] = !this.hashtags.locations[n]
            let list = [...this.newSearch]
            list.push(name)
            this.newSearch = [...list]
            console.log(this.newSearch)
          } else {
            var pos = this.newSearch.indexOf(name)
            this.hashtags.locations[n] = !this.hashtags.locations[n]
            let list = [...this.newSearch]
            list.splice(pos, 1)
            this.newSearch = [...list]
          }

        } else if (cate === "withWho") {

          if (!this.hashtags.withWho[n]) {
            this.hashtags.withWho[n] = !this.hashtags.withWho[n]
            let list = [...this.newSearch]
            list.push(name)
            this.newSearch = [...list]
            console.log(this.newSearch)
          } else {
            let pos = this.newSearch.indexOf(name)
            this.hashtags.withWho[n] = !this.hashtags.withWho[n]
            let list = [...this.newSearch]
            list.splice(pos, 1)
            this.newSearch = [...list]
          }
        } else if (cate === "ages") {

          if (!this.hashtags.ages[n]) {
            this.hashtags.ages[n] = !this.hashtags.ages[n]
            let list = [...this.newSearch]
            list.push(name)
            this.newSearch = [...list]
            console.log(this.newSearch)
          } else {
            let pos = this.newSearch.indexOf(name)
            this.hashtags.locations[n] = !this.hashtags.locations[n]
            let list = [...this.newSearch]
            list.splice(pos, 1)
            this.newSearch = [...list]
          }

        } else if (cate === "moods") {
          if (!this.hashtags.moods[n]) {
            this.hashtags.moods[n] = !this.hashtags.moods[n]
            let list = [...this.newSearch]
            list.push(name)
            this.newSearch = [...list]
            console.log(this.newSearch)
          } else {
            let pos = this.newSearch.indexOf(name)
            this.hashtags.moods[n] = !this.hashtags.moods[n]
            let list = [...this.newSearch]
            list.splice(pos, 1)
            this.newSearch = [...list]
          }
        } else if (cate === "facility") {
          if (!this.hashtags.facility[n]) {
            this.hashtags.facility[n] = !this.hashtags.facility[n]
            let list = [...this.newSearch]
            list.push(name)
            this.newSearch = [...list]
            console.log(this.newSearch)
          } else {
            let pos = this.newSearch.indexOf(name)
            this.hashtags.facility[n] = !this.hashtags.facility[n]
            let list = [...this.newSearch]
            list.splice(pos, 1)
            this.newSearch = [...list]
          }
        }

      },
      checkLoginState() {
        this.checkLogin = true;
      }
    },

  }
</script>

<style scoped>
  .rootMain{
    height: 400px;

  }
  #inspire {
    width: 100%;
    right: 0px;
    margin-bottom: 100px;
    position: relative;
    bottom: 100px;

  }

  .btn b-form-tags-button py-0 btn-outline-secondary {
    height: 30px !important;
  }

  .v-tabs v-tabs--grow theme--light {
    margin-top: 0px;
  }

  .form-control {
    position: absolute;
    z-index: 1;
    top: 285px;
    left: 300px;
    height: 50px;
    width: 1000px;

  }

  #mainTab {

    margin-top: 0px;
  }

  .mb-2 {
    height: 50px !important;
  }

  #mainTop {
    background: #ff7f00;
  }

  #find {
    -webkit-transform: scale(1.2);
    -moz-transform: scale(1.2);
    -ms-transform: scale(1.2);
    -o-transform: scale(1.2);
    transform: scale(1.2);
    -webkit-transition: .3s;
    -moz-transition: .3s;
    -ms-transition: .3s;
    -o-transition: .3s;
    transition: .3s;
    background: #ff7f00;

  }

  #find:hover {
    -webkit-transform: scale(1.5);
    -moz-transform: scale(1.5);
    -ms-transform: scale(1.5);
    -o-transform: scale(1.5);
    transform: scale(1.5);
    background: white;
    color: #ff7f00;
    outline-color: #ff7f00
  }
</style>