<template>
  <v-container 
  style="padding: 0px;">
    <v-container 
  style="padding: 0px;">
      <v-app id="inspire" class="rootMain">
        <!-- 네브바 -->
        <div v-if="checkLogin">

          <v-container 
  
          style=" padding: 0px; text-align: center !important;">
            <v-btn 
            color="white" 
            id="find" 
            style=
            "position: relative; 
            top: 250px; 
            z-index:1;
            "
            @click="open" 
            rounded 
            @click.stop="openSearch = true"
            >
            <v-icon 
              style="color:#ff7f00"
              >{{mdiMagnify}}</v-icon>
              
            <font color="#ff7f00">
              search restaurant</font>
            
              </v-btn>
          </v-container>
          <v-dialog v-model="openSearch" max-width="600">
           
            <v-card>
              <v-card-title class="headline">
                <v-combobox 
                v-model="newSearch" 
                chips="chips" 
                readonly
                clearable="clearable"
                @click:clear = onClearClicked 
               
                label="Your restaurant"
                multiple="multiple" 
                solo="solo"
                @keyup.enter="searchBtnClick"
                ></v-combobox>
              </v-card-title>

              <v-card-text>

                음식종류

                <v-divider></v-divider>
                <v-chip-group 
                solo
                active-class="orange accent-4 white--text" 
                v-model = "tagLocation"
                >
                <v-chip  draggable="draggable" @click="insertTags('locations',24, '한식')">한식</v-chip>
                <v-chip draggable="draggable" @click="insertTags('locations',25, '양식')">양식</v-chip>
                <v-chip draggable="draggable" @click="insertTags('locations',26, '중식')">중식</v-chip>
                <v-chip draggable="draggable" @click="insertTags('locations',27, '일식')">일식</v-chip>
                <v-chip draggable="draggable" @click="insertTags('locations',28, '카페')">카페</v-chip>
                <v-chip draggable="draggable" @click="insertTags('locations',29, '밥집')">밥집</v-chip>
                <v-chip draggable="draggable" @click="insertTags('locations',30, '술집')">술집</v-chip>
                <v-chip draggable="draggable" @click="insertTags('locations',31, '고깃집')">고깃집</v-chip>
                <v-chip draggable="draggable" @click="insertTags('locations',32, '아시안')">아시안</v-chip>
                <v-chip draggable="draggable" @click="insertTags('locations',43, '퓨전')">퓨전</v-chip>
                <v-chip draggable="draggable" @click="insertTags('locations',48, '디저트')">디저트</v-chip>
                 </v-chip-group>
                <v-divider></v-divider>
                강북
                <v-divider></v-divider>
                 <v-chip-group 
                 v-model = "tagLocation_two"
                            active-class="orange accent-4 white--text" >
                <v-chip active-clas="orange accent-4 white--text" draggable="draggable" @click="insertTags('locations', 8, '종로')">종로</v-chip>
                <v-chip draggable="draggable" @click="insertTags('locations', 9, '삼청동')">삼청동</v-chip>
                <v-chip draggable="draggable" @click="insertTags('locations', 10, '서촌')">서촌</v-chip>
                <v-chip draggable="draggable" @click="insertTags('locations', 11, '왕십리')">왕십리</v-chip>
                <v-chip draggable="draggable" @click="insertTags('locations', 12, '혜화')">혜화</v-chip>
                <v-chip draggable="draggable" @click="insertTags('locations', 13, '상수')">상수</v-chip>
                <v-chip draggable="draggable" @click="insertTags('locations', 14, '한남동')">한남동</v-chip>
                <v-chip draggable="draggable" @click="insertTags('locations', 15, '이태원')">이태원</v-chip>
               
                <v-chip draggable="draggable" @click="insertTags('locations', 17, '광화문')">광화문</v-chip>
                <v-chip draggable="draggable" @click="insertTags('locations', 18, '여의도')">여의도</v-chip>
                <v-chip draggable="draggable" @click="insertTags('locations', 19, '연남동')">연남동</v-chip>
                <v-chip draggable="draggable" @click="insertTags('locations', 20, '합정')">합정</v-chip>
                <v-chip draggable="draggable" @click="insertTags('locations', 21, '경리단길')">경리단길</v-chip>
                <v-chip draggable="draggable" @click="insertTags('locations', 22, '명동')">명동</v-chip>
                <v-chip draggable="draggable" @click="insertTags('locations', 23, '신촌')">신촌</v-chip>
                <v-chip draggable="draggable" @click="insertTags('locations', 33, '대학로')">대학로</v-chip>
                <v-chip draggable="draggable" @click="insertTags('locations', 34, '마포')">마포</v-chip>
                <v-chip draggable="draggable" @click="insertTags('locations', 35, '용산')">용산</v-chip>
                <v-chip draggable="draggable" @click="insertTags('locations', 36, '서대문구')">서대문구</v-chip>
                <v-chip draggable="draggable" @click="insertTags('locations', 37, '중구')">중구</v-chip>
                <v-chip draggable="draggable" @click="insertTags('locations', 38, '성동구')">성동구</v-chip>
                <v-chip draggable="draggable" @click="insertTags('locations', 39, '동대문')">동대문</v-chip>
                <v-chip draggable="draggable" @click="insertTags('locations', 40, '연신내')">연신내</v-chip>
                <v-chip draggable="draggable" @click="insertTags('locations', 41, '노원')">노원</v-chip>
                <v-chip draggable="draggable" @click="insertTags('locations', 42, '뚝섬')">뚝섬</v-chip>
                <v-chip draggable="draggable" @click="insertTags('locations', 45, '홍대')">홍대</v-chip>
                 </v-chip-group>

                 <v-divider></v-divider>
                강남
                <v-divider></v-divider>
                 <v-chip-group 
                 v-model = "tagLocation_three"
                            active-class="orange accent-4 white--text" >
                <v-chip draggable="draggable" @click="insertTags('locations', 0, '강남')">강남</v-chip>
                <v-chip draggable="draggable" @click="insertTags('locations', 1, '잠실')">잠실</v-chip>
                <v-chip draggable="draggable" @click="insertTags('locations', 2, '사당')">사당</v-chip>
                <v-chip draggable="draggable" @click="insertTags('locations', 3, '코엑스')">코엑스</v-chip>
                <v-chip draggable="draggable" @click="insertTags('locations', 4, '고속터미널')">고속터미널</v-chip>
                <v-chip draggable="draggable" @click="insertTags('locations', 5, '가로수길')">가로수길</v-chip>
                <v-chip draggable="draggable" @click="insertTags('locations', 6, '서래마을')">서래마을</v-chip>
                <v-chip draggable="draggable" @click="insertTags('locations', 7, '건대')">건대</v-chip>
                <v-chip draggable="draggable" @click="insertTags('locations', 44, '역삼')">역삼</v-chip>
                <v-chip draggable="draggable" @click="insertTags('locations',46, '압구정')">압구정</v-chip>
                <v-chip draggable="draggable" @click="insertTags('locations',47, '신사')">신사</v-chip>
                <v-chip draggable="draggable" @click="insertTags('locations',49, '방배')">방배</v-chip>
                 </v-chip-group>
                <v-divider></v-divider>
                누구와
                <v-divider></v-divider>


                 <v-chip-group 
                 v-model = "tagWith"
                            active-class="orange accent-4 white--text" >
                <v-chip draggable="draggable" @click="insertTags('withWho', 0, '혼자')">혼자</v-chip>
                <v-chip draggable="draggable" @click="insertTags('withWho', 1, '친구')">친구</v-chip>
                <v-chip draggable="draggable" @click="insertTags('withWho', 2, '연인')">연인</v-chip>
                <v-chip draggable="draggable" @click="insertTags('withWho', 3, '직장동료')">직장동료</v-chip>
                <v-chip draggable="draggable" @click="insertTags('withWho', 4, '가족')">가족</v-chip>
                 </v-chip-group>
                <v-divider></v-divider>
                연령별
                <v-divider></v-divider>

                 <v-chip-group 
                 v-model = "tagAge"
                            active-class="orange accent-4 white--text" >
                <v-chip draggable="draggable" @click="insertTags('ages', 0, '10대')">10대</v-chip>
                <v-chip draggable="draggable" @click="insertTags('ages', 1, '20대')">20대</v-chip>
                <v-chip draggable="draggable" @click="insertTags('ages', 2, '30대')">30대</v-chip>
                <v-chip draggable="draggable" @click="insertTags('ages', 3, '40450대')">4050대</v-chip>
                <v-chip draggable="draggable" @click="insertTags('ages', 4, '그이상')">그이상</v-chip>
                 </v-chip-group>
                <v-divider></v-divider>
                분위기
                <v-divider></v-divider>
                 <v-chip-group 
                 v-model = "tagMood"
                            active-class="orange accent-4 white--text" >
                <v-chip draggable="draggable" @click="insertTags('moods', 0, '시끌벅적한')">시끌벅적한</v-chip>
                <v-chip draggable="draggable" @click="insertTags('moods', 1, '차분한')">차분한</v-chip>
                <v-chip draggable="draggable" @click="insertTags('moods', 2, '소소한')">소소한</v-chip>
                <v-chip draggable="draggable" @click="insertTags('moods', 3, '고급스러운')">고급스러운</v-chip>
                <v-chip draggable="draggable" @click="insertTags('moods', 4, '데이트')">데이트</v-chip>
                 </v-chip-group>
                <v-divider></v-divider>
                편의시설
                <v-divider></v-divider>
                 <v-chip-group 
                 v-model = "tagFacility"
                            active-class="orange accent-4 white--text" >
                <v-chip draggable="draggable" @click="insertTags('facility', 0, '단체석')">단체석</v-chip>
                <v-chip draggable="draggable" @click="insertTags('facility', 1, '주차장')">주차장</v-chip>
                <v-chip draggable="draggable" @click="insertTags('facility', 2, '개별룸')">개별룸</v-chip>
                <v-chip draggable="draggable" @click="insertTags('facility', 50, '흡연실')">흡연실</v-chip> <!--마지막 -->
                 </v-chip-group>
              </v-card-text>
              <div class="my-2 searchBtn" style="
    text-align: center;"> 
                  <loading :active.sync="isLoading" 
            :can-cancel="true" 
            
            >
      <iframe src="https://giphy.com/embed/JpYJnxosqH4G76f6iL" width="200" height="200" frameBorder="0" class="giphy-embed" allowFullScreen></iframe><p><a href="https://giphy.com/gifs/kakao-friends-apeach-europe-JpYJnxosqH4G76f6iL">음식점 가져오는 중 ...  </a></p>
            </loading>
                <v-btn color="warning" @click="searchBtnClick" :disabled="searchBtnActive" dark="dark">검색</v-btn>
              </div>
              <v-card-actions>
                <v-spacer></v-spacer>
              </v-card-actions>
            </v-card>
          </v-dialog>

            <UserRanking></UserRanking>
          <v-flex
          style="padding: 0px;"
          >
            <v-carousel 
            cycle="cycle" 
            show-arrows-on-hover="show-arrows-on-hover" 
            hide-delimiters="hide-delimiters"
            v-ripple="true"
            height="450"
            interval="10000"
            class="text-center">
              <v-carousel-item v-for="(item,i) in items" :key="i" :src="item.src" @click="$router.push({name: 'popular'})"></v-carousel-item>
            </v-carousel>

          </v-flex>

          <!-- 탭부분  -->
          <v-flex>
          <v-tabs fixed-tabs="fixed-tabs" background-color="transparent" color="#ff7f00">
            <v-tab @click="uservalid(userid)"  style="color: #ff7f00;">
                <template v-if="userToken==null">
                  로그인
                </template>
                <template v-else>
                  유저페이지
                </template>
            </v-tab>
          

            <v-tab @click="valid('random')" style="color: #ff7f00;">
              음식점
            </v-tab>
            <v-tab @click="valid('createReview')" style="color: #ff7f00;">
              리뷰
            </v-tab>
            <v-tab @click="valid('userFeed')" style="color: #ff7f00;">
              피드페이지
            </v-tab>

          </v-tabs>
          </v-flex>
          <div>
            
            
            <router-view>
                
              
            </router-view>
          
          
          
          </div>


         <footer-tag></footer-tag> 
        </div>

      </v-app>

    </v-container>
  </v-container>
</template>

<script>
 import FooterTag from "./footer.vue";
  
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
  // import userSearch from '../../src/components/common/userSearch';
  import UserApi from '../../src/apis/UserApi.js';
  import SearchApi from '../../src/apis/UserApi.js';
  import StoreApi from '../../src/apis/StoreApi.js';
  import Loading from 'vue-loading-overlay';
  import 'vue-loading-overlay/dist/vue-loading.css';
  import UserRanking from "./user/userRanking";
  export default {
    created() {
      // this
      //   .$store
      //   .dispatch('LOADING_USERDATA');
        
     if(sessionStorage.getItem('userid')==null)
        this.$router.push({name: "login"})
    else
        this.$router.push({name: "popular"})
        
    },
    watch: {
      openSearch : function(v){
          
          // this.hashtags.locations = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
          // this.hashtags.withWho = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
          // this.hashtags.ages = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
          // this.hashtags.moods = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
          // this.hashtags.facility =[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
          // let list = [...this.newSearch];
          // list = [];
          // this.newSearch = [...list];
      },
      newSearch: function (v) {
        if (this.newSearch.length >= 1) {
          this.searchBtnActive = false;
        } else if (this.newSearch.length == 0) {
          this.searchBtnActive = true;
        }
      },
      storeList : function(v){
               
                      
      },
      userid : function(v){
        
          this.$router.go({
  name: 'main',
  force: true
})  
       
        // this.userid = this.$store.state.userid;
      }
    },
    data() {
      return {
        tagLocation : [],
        tagLocation_two : [],
        tagLocation_three : [],
        tagWith : [],
        tagAge : [],
        tagMood : [],
        tagFacility : [],
        loading: false,
        selection: 1,
        searchBtnActive: false,
        newSearch: [],
        openSearch: false,
        value: ['apple', 'orange'],
        items: [{
            src: 'https://cdn.pixabay.com/photo/2017/01/30/13/49/pancakes-2020863_1280.jpg',
          },
          {
            src: 'https://cdn.pixabay.com/photo/2017/02/15/10/39/salad-2068220_1280.jpg',
          },
          {
            src: 'https://cdn.pixabay.com/photo/2017/03/10/13/57/cooking-2132874_1280.jpg',
          },
          {
            src: 'https://cdn.pixabay.com/photo/2016/02/05/15/34/pasta-1181189_1280.jpg',
          },
        ],
        hashtags: {
          locations: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
          withWho: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
          ages: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
          moods: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
          facility: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
        },
        tabs: 0,
        checkLogin: true,
        dialog : false,
        isLoading: false,
        userid_list : [],
        mdiMagnify
      }
    },
    computed: {
      ...mapState(['tempStores']),
      Stores() {
        return this.tempStores
      },
       userToken(){
         //return this.$store.state.accessToken
        return sessionStorage.getItem("userToken");
      },
      storeList(){
        return this.$store.state.storeList
      },
       userid(){
         return this.$store.state.userid
       //return sessionStorage.getItem("userid")
      },
     
      
    },
    components : {
       Loading,
       FooterTag,
       UserRanking,
    },
    methods: {
      onClearClicked(){
          this.hashtags.locations = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
          this.hashtags.withWho = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
          this.hashtags.ages = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
          this.hashtags.moods = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
          this.hashtags.facility =[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
          let list = [...this.newSearch];
          list = [];
          this.newSearch = [...list];   
          this.tagLocation = [];
          this.tagLocation_two = [],
          this.tagLocation_three = [],
          this.tagWith = [],
          this.tagAge = [],
          this.tagMood = [],
          this.tagFacility = []

      },
      valid(name){
        // session에 값이 있는지 확인해서 있으면 탭 이동
//                   this.$router.go({
//   name: 'main',
//   force: true
// })
        let accessToken = sessionStorage.getItem("userToken");
        if(accessToken!=null){
          this.$router.push({name : name});  
        }else{
          this.$alert('로그인 하세요 !',"warning","warning")
           this.$router.push('/login');  
        }

      },
      uservalid(val){
 
        if(this.userToken!=null){
     
          this.$router.push({name:'userpage', params:{id:this.userid}})
        }else{
          this.$router.push('/login');  
        }
      },
      reserve() {
        this.loading = true

        setTimeout(() => (this.loading = false), 2000)
      },
      searchBtnClick(){
       
         this.isLoading = true;
        this.$store.state.storeFlag +=1;
       this.hashtags.locations = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
          this.hashtags.withWho = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
          this.hashtags.ages = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
          this.hashtags.moods = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
          this.hashtags.facility =[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
          
          this.tagLocation = [];
          this.tagLocation_two = [],
          this.tagLocation_three = [],
          this.tagWith = [],
          this.tagAge = [],
          this.tagMood = [],
          this.tagFacility = []

        StoreApi.requestStoreList().then(response=>{
         
         
          let searchList = new Array();
          for(let i =0; i<response.data.object.length; i++){
            //tags 합치기
            let tagsString;  
            for(let j =0; j<response.data.object[i].tags.length; j++){
                  tagsString += response.data.object[i].tags[j].keyword;
            }//end of for loop
           
            let find = true;
            for(let j=0; j<this.newSearch.length; j++){
              if(tagsString.includes(this.newSearch[j])){
                find = false;
              }
            }//end of for loop 

            if(find == false){
              let item = {};
              item['num'] = response.data.object[i].store.num;
              item['sname'] = response.data.object[i].store.sname;
              item['address'] = response.data.object[i].store.address;
              item['img'] = response.data.object[i].store.img;
              item['lat'] = response.data.object[i].store.lat;
              item['lng'] = response.data.object[i].store.lng;
              JSON.stringify(item);
              searchList.push(item);

            }
            
          }//end for first for loop
          

          this.$store.state.storeList = searchList;

          /* 처음 검색 이후 rendering 되는 this.$store.state.storeList_page에 값을 추가해줘야함 */
          this.$store.state.storeList_paging = [];
          for(let i=0; i<6; i++){
                if(this.$store.state.storeList[i] == null)
                  continue;
                else
                 this.$store.state.storeList_paging.push(this.$store.state.storeList[i]);              
          }
          this.openSearch = false
          this.isLoading = false
          this.$store.state.pageStatus = true; //여기서 pagination을 보이게 다시 바꿉니다.
          if(this.$store.state.storeList.length==0){
            
            this.$store.state.isClickBtnStore = false;
           
          }
          else{
            this.$store.state.isClickBtnStore = true;
           
          }
           this.$router.push({name : "storeSearch"})
           this.newSearch = [];   
        }, error =>{
          this.isLoading =false;
          this.$alert("데이터를 불러오지 못헀습니다.","warning","warning");
          this.openSearch = false;
        })
      },
      // changeInput() { //if-else 
      //   if (this.inputStatus == true) this.inputStatus = false;
      //   else
      //     this.inputStatus = true;
      // },
      // changeInput2() { //enter 누를 때, user 정보가 db에 저장된다.
      //   let find = false;

      //   for (var j = 0; j < this.$store.state.searchUser.nickname.length; j++) {
      //     if (this.search == this.$store.state.searchUser.nickname[j]) {
      //       this.moveUser = this.$store.state.searchUser.email[j];
      //       //검색한 닉네임의 email
      //       find = true;
      //       break;
      //     }

      //   }
      //   if (find == false) this.moveUser = 'notEmail'
      //   //this.history.email = sessionStorage.getItem("userEmail");
      //   this.history.email = "ssafy@naver.com"
      //   this.history.nickname = this.search;


      //   SearchApi.searchUserHistory(this.history, res => {

      //     if (this.inputStatus == true) this.inputStatus = false;
      //     else {
      //       this.inputStatus = true;
      //       this.search = "";
      //       this.$store.dispatch('LOADING_RECENTUSERDATA', this.history.email);
      //     }
      //   }, error => {
      //     console.log("userSearch.vue, server 와 통신 실패");
      //   })
        //DB에 저장하고 email로 유저페이지 이동한다. 
        //this.$router.push({ name: 'userPage', params: { id: this.moveUser }}) 
        //다시 inpustState를 변화시키므로써, User가 최근 검색 history를 보여준다. 

      // },
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
           
          } else {
            let pos = this.newSearch.indexOf(name)
            this.hashtags.ages[n] = !this.hashtags.ages[n]
            let list = [...this.newSearch]
            list.splice(pos, 1)
            this.newSearch = [...list]
          }
        } else if (cate === "moods"){
          if (!this.hashtags.moods[n]) {
            this.hashtags.moods[n] = !this.hashtags.moods[n]
            let list = [...this.newSearch]
            list.push(name)
            this.newSearch = [...list]
           
          } else {
            let pos = this.newSearch.indexOf(name)
            this.hashtags.moods[n] = !this.hashtags.moods[n]
            let list = [...this.newSearch]
            list.splice(pos, 1)
            this.newSearch = [...list]
          }
        } else if (cate === "facility"){
          if (!this.hashtags.facility[n]) {
            this.hashtags.facility[n] = !this.hashtags.facility[n]
            let list = [...this.newSearch]
            list.push(name)
            this.newSearch = [...list]
           
          } else {
            let pos = this.newSearch.indexOf(name)
            this.hashtags.facility[n] = !this.hashtags.facility[n]
            let list = [...this.newSearch]
            list.splice(pos, 1)
            this.newSearch = [...list]
          }
        }
      },
      
    }
  }
        
</script>

<style scoped="scoped">
    .v-chip--disabled {
    opacity: 1 !important;
  }

  .rootMain {
    height: 400px;

  }
  
  
  #inspire {
    width: 100%;
    right: 0;
    margin-bottom: 100px;
    position: relative;
    bottom: 100px;

  }

  .btn b-form-tags-button py-0 btn-outline-secondary {
    height: 30px !important;
  }

  .v-tabs v-tabs--grow theme--light {
    margin-top: 0;
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

    margin-top: 0;
  }

  #find {
    
    -webkit-transform: scale(1.2);
    -moz-transform: scale(1.2);
    -ms-transform: scale(1.2);
    -o-transform: scale(1.2);
    transform: scale(1.2);
    -webkit-transition: 0.3s;
    -moz-transition: 0.3s;
    -ms-transition: 0.3s;
    -o-transition: 0.3s;
    transition: 0.3s;
    
  

  }

  #find:hover {
    -webkit-transform: scale(1.5);
    -moz-transform: scale(1.5);
    -ms-transform: scale(1.5);
    -o-transform: scale(1.5);
    transform: scale(1.5);
    -webkit-transition: 0.3s;
    -moz-transition: 0.3s;
    -ms-transition: 0.3s;
    -o-transition: 0.3s;
    transition: 0.3s;
    

  
  }

  .mb-2 {
    height: 50px !important;
  }

  #mainTop {
    background: #ff7f00;
  }

  .searchBtn[data-v-c1f1971a] {
    text-align: center;

  }
  .v-application .white {
    background-color: #fff!important;
    border-color: orange !important;
}

</style>

