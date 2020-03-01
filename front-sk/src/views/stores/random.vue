<template>
  <v-container>
    <v-card 
    style="
    min-height: 480px;
    height: 105% !important;">
      <v-card-title class="headline">
        <!-- <v-chip  @click:close="keyword = ''">{{keyword}}</v-chip> -->
        <v-combobox
          v-model="keyword"
          chips
          clearable
          label="위치를 '하나만' 선택하세요"
          solo
          style="margin-top: 20px; font-family: 'Nanum Gothic Coding'"
        ></v-combobox>
      </v-card-title>

      <v-card-text>
        <v-divider></v-divider>
        
        <v-chip
          class="ma-2"
          color="#ff7f00"
          text-color="white"
          style="margin-bottom: 10px !important;
          font-family: 'Nanum Gothic Coding'
          "
        >
          강북
          <v-icon right>{{mdiCity}}</v-icon>
        </v-chip>
        <br />
        <v-row
        justify="center"
        >
        <v-col cols="8">
        <v-chip draggable="draggable" @click="insertTags('종로')">종로</v-chip>
        <v-chip draggable="draggable" @click="insertTags( '삼청동')">삼청동</v-chip>
        <v-chip draggable="draggable" @click="insertTags( '서촌')">서촌</v-chip>
        <v-chip draggable="draggable" @click="insertTags( '왕십리')">왕십리</v-chip>
        <v-chip draggable="draggable" @click="insertTags( '혜화')">혜화</v-chip>
        <v-chip draggable="draggable" @click="insertTags( '상수')">상수</v-chip>
        <v-chip draggable="draggable" @click="insertTags( '한남동')">한남동</v-chip>
        <v-chip draggable="draggable" @click="insertTags( '이태원')">이태원</v-chip>
        <v-chip draggable="draggable" @click="insertTags( '홍대')">홍대</v-chip>
        <v-chip draggable="draggable" @click="insertTags( '광화문')">광화문</v-chip>
        <v-chip draggable="draggable" @click="insertTags( '여의도')">여의도</v-chip>
        <v-chip draggable="draggable" @click="insertTags( '연남동')">연남동</v-chip>
        <v-chip draggable="draggable" @click="insertTags( '합정')">합정</v-chip>
        <v-chip draggable="draggable" @click="insertTags( '경리단길')">경리단길</v-chip>
        <v-chip draggable="draggable" @click="insertTags( '명동')">명동</v-chip>
        <v-chip draggable="draggable" @click="insertTags( '신촌')">신촌</v-chip>
        <v-chip draggable="draggable" @click="insertTags( '대학로')">대학로</v-chip>
        <v-chip draggable="draggable" @click="insertTags( '마포')">마포</v-chip>
        <v-chip draggable="draggable" @click="insertTags( '용산')">용산</v-chip>
        <v-chip draggable="draggable" @click="insertTags( '서대문구')">서대문구</v-chip>
        <v-chip draggable="draggable" @click="insertTags( '중구')">중구</v-chip>
        <v-chip draggable="draggable" @click="insertTags( '성동구')">성동구</v-chip>
        <v-chip draggable="draggable" @click="insertTags( '동대문')">동대문</v-chip>
        <v-chip draggable="draggable" @click="insertTags( '연신내')">연신내</v-chip>
        <v-chip draggable="draggable" @click="insertTags( '노원')">노원</v-chip>
        <v-chip draggable="draggable" @click="insertTags( '뚝섬')">뚝섬</v-chip>
        <v-chip draggable="draggable" @click="insertTags( '홍대')">홍대</v-chip>
        </v-col>
        </v-row>
      </v-card-text>

      <v-chip
          class="ma-2"
          color="#ff7f00"
          text-color="white"
          style="margin-bottom: 10px !important;
          font-family: 'Nanum Gothic Coding';
          "
        >
          강남
          <v-icon right>{{mdiCity}}</v-icon>
        </v-chip>
        <br />
        <v-row
        justify="center"
        >
        <v-col cols="9">
        <v-chip draggable="draggable" @click="insertTags('강남')">강남</v-chip>
        <v-chip draggable="draggable" @click="insertTags('잠실')">잠실</v-chip>
        <v-chip draggable="draggable" @click="insertTags('사당')">사당</v-chip>
        <v-chip draggable="draggable" @click="insertTags('코엑스')">코엑스</v-chip>
        <v-chip draggable="draggable" @click="insertTags('고속터미널')">고속터미널</v-chip>
        <v-chip draggable="draggable" @click="insertTags('가로수길')">가로수길</v-chip>
        <v-chip draggable="draggable" @click="insertTags('서래마을')">서래마을</v-chip>
        <v-chip draggable="draggable" @click="insertTags('건대')">건대</v-chip>
        <v-chip draggable="draggable" @click="insertTags('역삼')">역삼</v-chip>
        <v-chip draggable="draggable" @click="insertTags('압구정')">압구정</v-chip>
        <v-chip draggable="draggable" @click="insertTags('신사')">신사</v-chip>
        <v-chip draggable="draggable" @click="insertTags('방배')">방배</v-chip>
        </v-col>
        </v-row>
        <v-divider></v-divider>
       
      <random></random>
      <loading :active.sync="isLoading_random" :can-cancel="true">
        <iframe
          src="https://giphy.com/embed/JpYJnxosqH4G76f6iL"
          width="200"
          height="200"
          frameborder="0"
          class="giphy-embed"
          allowfullscreen
        ></iframe>
        <p>
          <a
            href="https://giphy.com/gifs/kakao-friends-apeach-europe-JpYJnxosqH4G76f6iL"
          >음식점 가져오는 중 ...</a>
        </p>
      </loading>
      <v-btn 
      color="primary" 
      @click="getRandom"
      rounded
      style="font-family: 'Nanum Gothic Coding';"
      >랜덤 추천 start</v-btn>
       <v-divider></v-divider>
      
      <div 
      v-if="randomList.length>=1">
        <b-row 
        v-for="item in randomList" 
        :key="item.num"
        align-h="start" 
        style="margin-bottom: 30px;">

          <v-card 
          max-width="344" 
          class="mx-auto" 
          style="margin-top: 20px;"
          >
            
            <v-list-item>
              <v-list-item-content>
                <v-list-item-title 
                class="headline" 
                style="margin-top: 5px; 
                margin-bottom: 10px;

                ">

                  <v-btn 
                  @click="storeDetail(item.num)" 
                  text 
                  color="warning"
                  style="font-family: 'Noto Serif KR', ;"
                  >

                    <v-icon 
                    style="color: #ff7f00">{{mdiCity}}</v-icon>
                    <span 
                    style="color: #ff7f00;
                    font-family: 'Noto Serif KR';
                    ">{{item.sname}}</span>

                  </v-btn>

                </v-list-item-title>
                <v-container 
                v-if="item.img"
                style="padding: 0px;"
                >

                <v-img
                :src="`https://maps.googleapis.com/maps/api/place/photo?maxwidth=600&photoreference=${item.img}&sensor=true&key=AIzaSyDC4sonH281FHJ-YyPmeXLRdBYuqcjUkGE`"
                max-height="200"
                @click="storeDetail(item.num)" 
                ></v-img>
                </v-container>
                <v-container 
                v-else
                style="padding: 0px;"
                >

                  <v-img
                  src="https://cdn.pixabay.com/photo/2013/07/13/11/31/shop-158317_1280.png"
                  max-height="200"
                  @click="storeDetail(item.num)" 
                  >
                  </v-img>
                </v-container>

                <v-row>
                  <v-col>
                    <v-card-text>{{item.address}}</v-card-text>
                  </v-col>
                  <v-col>
                  <gmap-map
                  :center="{lat: parseFloat(item.lat), lng:parseFloat(item.lng)}"
                  :zoom="12"
                  style="width: 100%; height: 100%"
                  >
                  <gmap-marker
                    :position="{lat: parseFloat(item.lat), lng:parseFloat(item.lng)}"
                    :clickable="true"
                    :draggable="true"
                    @click="center=m.position"
                  ></gmap-marker>
                </gmap-map>
                  </v-col>
                </v-row>
         
              </v-list-item-content>
            </v-list-item>

            

            <!-- <v-card-actions>
              <v-btn icon>
                <v-icon>{{mdiShareVariant}}</v-icon>
              </v-btn>
            </v-card-actions> -->
          </v-card>
        </b-row>
      </div>

      <div v-else>
        <br />
        <kinesis-container>
          <kinesis-element :strength="10">
            <p 
            class="headline"
            style="color : orange">추천 랜덤 음식 결과값 입니다.</p>
          </kinesis-element>
          <kinesis-element :strength="20">
            <p 
            class="headline"
            style="color : orange">불러온 데이터가 없습니다.</p>
          </kinesis-element>
        </kinesis-container>
        <!-- https://giphy.com/embed/TI4Hsj7mNI27nn9I1P -->
        <iframe
          src="https://giphy.com/embed/TI4Hsj7mNI27nn9I1P"
          readonly
          width="150"
          height="150"
          frameborder="0"
          class="giphy-embed"
          allowfullscreen
        ></iframe>
        
      </div>
      <v-divider></v-divider>
    </v-card>
    
  </v-container>
</template>

<script>
import StoreApi from "../../apis/StoreApi";
import * as VueGoogleMaps from "vue2-google-maps";
import Vue from "vue";
import { mdiCity, mdiShareVariant } from "@mdi/js";
// Import component
import Loading from "vue-loading-overlay";
// Import stylesheet
import "vue-loading-overlay/dist/vue-loading.css";
Vue.use(VueGoogleMaps, {
  load: {
    key: "AIzaSyDC4sonH281FHJ-YyPmeXLRdBYuqcjUkGE",
    v: "OPTIONAL VERSION NUMBER",
    libraries: "places" //// If you need to use place input
  }
});
export default {
  data() {
    return {
      keyword: "",
      randomList: [],
      isLoading_random: false,
      //icons
      mdiCity,
      mdiShareVariant
    };
  },
  components: {
    Loading
  },
  methods: {
    insertTags(name) {
      if (name == this.keyword) this.keyword = "";
      else this.keyword = name;
    },
    getRandom() {
      console.log("getRandom()");
      console.log(this.isLoading);

      if (this.keyword != "") {
        this.isLoading_random = true;
        console.log(this.isLoading_random);
        console.log("this.keyword inner");
        StoreApi.requestRandom(
          this.keyword,
          res => {
            //this.$alert("랜덤 음식점 리스트 가져오기 성공", "success", "success", );
            this.isLoading_random = false;
            this.randomList = res;
          },
          error => {
            //this.$alert("랜덤 음식점 리스트 가져오기 실패", "Error", "warning", );
            //this.isloading = true;
            //alert("랜덤 음식점 리스트 가져오기 실패");
          }
        );
      } else {
        this.$alert("위치를 선택해주세요", "Error", "warning");
        //alert("위치를 선택해주세요");
      }
      this.isLoading_random = false;
    },
    storeDetail(num) {
      this.$store.dispatch("storeHashtags", num);
    }
  }
};
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css?family=Nanum+Gothic+Coding|Noto+Serif+KR&display=swap');


.v-application .primary {
  background-color: #ff7f00 !important;
  border-color: #ff7f00 !important;
}
iframe {
  pointer-events: none;
}
.v-chip {
  margin-right: 0.5% !important;
  margin-top: 0.3% !important;
}
</style>