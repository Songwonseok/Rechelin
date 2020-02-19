<template>
  <div>
    <v-card style="min-height: 480px;">
      <v-card-title class="headline">
        <!-- <v-chip  @click:close="keyword = ''">{{keyword}}</v-chip> -->
        <v-combobox v-model="keyword" chips clearable label="위치를 '하나만' 선택하세요" solo style="margin-top: 20px;"></v-combobox>
      </v-card-title>

      <v-card-text>
        <v-divider></v-divider>
        <v-chip
      class="ma-2"
      color="orange"
      text-color="white"
      style="margin-bottom: 10px !important;"
    >
      강남
      <v-icon right>mdi-star</v-icon>
    </v-chip><br>
        <v-chip draggable @click="insertTags('강남')">강남</v-chip>
        <v-chip draggable @click="insertTags('잠실')">잠실</v-chip>
        <v-chip draggable @click="insertTags('사당')">사당</v-chip>
        <v-chip draggable @click="insertTags('코엑스')">코엑스</v-chip>
        <v-chip draggable @click="insertTags('고속터미널')">고속터미널</v-chip>
        <v-chip draggable @click="insertTags('가로수길')">가로수길</v-chip>
        <v-chip draggable @click="insertTags('서래마을')">서래마을</v-chip>

        <v-divider></v-divider>
        <v-divider></v-divider>
        <v-chip
      class="ma-2"
      color="orange"
      text-color="white"
      style="margin-bottom: 10px !important;"
    >
      강북
      <v-icon right>mdi-star</v-icon>
    </v-chip><br>
        <v-chip draggable @click="insertTags('건대')">건대</v-chip>
        <v-chip draggable @click="insertTags('종로')">종로</v-chip>
        <v-chip draggable @click="insertTags('삼청동')">삼청동</v-chip>
        <v-chip draggable @click="insertTags('서촌')">서촌</v-chip>
        <v-chip draggable @click="insertTags('왕십리')">왕십리</v-chip>
        <v-chip draggable @click="insertTags('혜화')">혜화</v-chip>
        <v-chip draggable @click="insertTags('상수')">상수</v-chip>
        <v-chip draggable @click="insertTags('한남동')">한남동</v-chip>
        <v-chip draggable @click="insertTags('이태원')">이태원</v-chip>
        <v-chip draggable @click="insertTags('홍대')">홍대</v-chip>
        <v-chip draggable @click="insertTags('광화문')">광화문</v-chip>
        <v-chip draggable @click="insertTags('여의도')">여의도</v-chip>
        <v-chip draggable @click="insertTags('연남동')">연남동</v-chip>
        <v-chip draggable @click="insertTags('합정')">합정</v-chip>
        <v-chip draggable @click="insertTags('경리단길')">경리단길</v-chip>
        <v-chip draggable @click="insertTags('명동')">명동</v-chip>
        <v-chip draggable @click="insertTags('신촌')">신촌</v-chip>
      </v-card-text>
       
      <random>

    
      </random>
      <loading :active.sync="isLoading_random"  
            :can-cancel="true" 
            
            >
       <iframe src="https://giphy.com/embed/JpYJnxosqH4G76f6iL" width="480" height="480" frameBorder="0" class="giphy-embed" allowFullScreen></iframe><p><a href="https://giphy.com/gifs/kakao-friends-apeach-europe-JpYJnxosqH4G76f6iL">음식점 가져오는 중 ...  </a></p>
            </loading>  
      <v-btn color="primary" @click="getRandom">랜덤 추천 시작</v-btn>

<div v-if = "randomList.length>=1">
      <b-row v-for="item in randomList" :key="item.num" style="margin-bottom: 30px;"> 
        
        <v-card max-width="344" class="mx-auto" style="margin-top: 20px;">
          <v-list-item>
            
            <v-list-item-content  >
              <v-list-item-title class="headline" style="margin-top: 5px; margin-bottom: 10px;">
                <v-btn @click="storeDetail(item.num)" text color="warning">
                  <v-icon style="color: #ff7f00">{{mdiCity}}</v-icon><span style="color: #ff7f00">{{item.sname}}</span></v-btn>
                </v-list-item-title>
                  <gmap-map :center="{lat: parseFloat(item.lat), lng:parseFloat(item.lng)}" :zoom="16"
              style="width: 300px; height: 194px">
              <gmap-marker :position="{lat: parseFloat(item.lat), lng:parseFloat(item.lng)}" :clickable="true"
                :draggable="true" @click="center=m.position">
              </gmap-marker>
          
            </gmap-map>
            </v-list-item-content>
          </v-list-item>

          <v-card-text>
            {{item.address}}
          </v-card-text>

          <v-card-actions>

            <v-btn icon>
              <v-icon>{{mdiShareVariant}}</v-icon>
            </v-btn>
          </v-card-actions>
        </v-card>
      </b-row>
    </div>

        <div v-else>
          <br>
          <kinesis-container>
                    <kinesis-element :strength="10">
                        <h1 style="color : orange">추천 랜덤 음식 결과값 입니다.</h1>
                    </kinesis-element>
                    <kinesis-element :strength="20">
                        <h1 style="color : orange">불러온 데이터가 없습니다. </h1>
                    </kinesis-element>
                </kinesis-container>
                <!-- https://giphy.com/embed/TI4Hsj7mNI27nn9I1P -->
                <iframe src="https://giphy.com/embed/TI4Hsj7mNI27nn9I1P" readonly width="200" height="200" frameBorder="0" class="giphy-embed" allowFullScreen></iframe><p></p>
      </div>
    </v-card>
  </div>
</template>

<script>
  import StoreApi from "../../apis/StoreApi";
    import * as VueGoogleMaps from 'vue2-google-maps';
    import Vue from 'vue';
     import {
   mdiCity,
   mdiShareVariant
  } from '@mdi/js';
    // Import component
    import Loading from 'vue-loading-overlay';
    // Import stylesheet
    import 'vue-loading-overlay/dist/vue-loading.css';
  Vue.use(VueGoogleMaps, {
    load: {
      key: 'AIzaSyDC4sonH281FHJ-YyPmeXLRdBYuqcjUkGE',
      v: 'OPTIONAL VERSION NUMBER',
      libraries: 'places', //// If you need to use place input
    }
  });
  export default {
    data() {
      return {
        keyword: "",
        randomList: [],
        isLoading_random : false,
        //icons
        mdiCity,
        mdiShareVariant
      };
    },
    components :{
       Loading,
    },
    methods: {
      insertTags(name) {
        if (name == this.keyword) this.keyword = "";
        else this.keyword = name;
      },
      getRandom() {
        console.log('getRandom()');
        console.log(this.isLoading);
        
        if (this.keyword != "") {
        this.isLoading_random = true;
        console.log(this.isLoading_random);
         console.log('this.keyword inner')
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
          this.$alert("위치를 선택해주세요", "Error", "warning", );
          //alert("위치를 선택해주세요");
        }
      },
      storeDetail(num) {
      this.$store.dispatch('storeHashtags', num)
      
    }
    }
  };
</script>

<style scoped>
  .v-application .primary {
    background-color: #ff7f00 !important;
    border-color: #ff7f00 !important;
  }
  iframe {
  pointer-events: none;
}
</style>