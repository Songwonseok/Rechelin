<template>
  <div id="app">
    <v-app id="inspire">
      <v-card>
        <v-toolbar flat color="orange" dark>
          <v-toolbar-title>
            <i class="fas fa-utensils fa-3x"></i> 음식점 검색
          </v-toolbar-title>
        </v-toolbar>
        <v-tabs vertical>
          <v-tab>
            <v-icon left>mdi-account</v-icon>음식점 검색
          </v-tab>
          <v-tab>
            <v-icon left>mdi-lock</v-icon>랜덤 음식점 룰렛
          </v-tab>
          <!-- <v-tab>
          <v-icon left>mdi-access-point</v-icon>
          회원가입
          </v-tab>-->

          <v-tab-item>
            <v-card flat>
              <v-card-text></v-card-text>
            </v-card>
          </v-tab-item>
          <v-tab-item>
            <v-card>
              .............................////랜덤음식
              음식점 리스트 뿌리기 테스트
              <br />
              <v-card-title class="headline">
                <!-- <v-chip  @click:close="keyword = ''">{{keyword}}</v-chip> -->
                <v-combobox v-model="keyword" chips clearable label="위치를 선택하세요" solo></v-combobox>
              </v-card-title>
              <v-card-text>
                <v-divider></v-divider>
                <h3>강남</h3>
                <v-chip draggable @click="insertTags('강남')">강남</v-chip>
                <v-chip draggable @click="insertTags('잠실')">잠실</v-chip>
                <v-chip draggable @click="insertTags('사당')">사당</v-chip>
                <v-chip draggable @click="insertTags('코엑스')">코엑스</v-chip>
                <v-chip draggable @click="insertTags('고속터미널')">고속터미널</v-chip>
                <v-chip draggable @click="insertTags('가로수길')">가로수길</v-chip>
                <v-chip draggable @click="insertTags('서래마을')">서래마을</v-chip>
                
                <v-divider></v-divider>
                <h3>강북</h3>
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
              <random></random>
              <v-btn color="primary" @click="getRandom">랜덤 추천 시작</v-btn>

              <template v-for="item in randomList">
                <h3 v-bind:key="item.num">{{item.sname}}</h3>
                {{item.address}}
              </template>
            </v-card>
          </v-tab-item>
          <v-tab-item>
            <v-card flat>
              <v-card-text>
                <p>Fusce a quam. Phasellus nec sem in justo pellentesque facilisis. Nam eget dui. Proin viverra, ligula sit amet ultrices semper, ligula arcu tristique sapien, a accumsan nisi mauris ac eros. In dui magna, posuere eget, vestibulum et, tempor auctor, justo.</p>

                <p
                  class="mb-0"
                >Cras sagittis. Phasellus nec sem in justo pellentesque facilisis. Proin sapien ipsum, porta a, auctor quis, euismod ut, mi. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nam at tortor in tellus interdum sagittis.</p>
              </v-card-text>
            </v-card>
          </v-tab-item>
        </v-tabs>
      </v-card>
    </v-app>
  </div>
</template>

<script>
import random from "../../views/stores/random.vue";
import StoreApi from "../../apis/StoreApi";

export default {
  data() {
    return {
      keyword: "",
      randomList: []
    };
  },
  components: {
    random
  },
  methods: {
    insertTags(name) {
      if(name == this.keyword) this.keyword = '';
      else this.keyword = name;
    },
    getRandom() {
      if (this.keyword != "") {
        StoreApi.requestRandom(
          this.keyword,
          res => {
            this.randomList = res;
          },
          error => {
            alert("랜덤 음식점 리스트 가져오기 실패");
          }
        );
      } else {
        alert("위치를 선택해주세요");
      }
    }
  }
};
</script>

<style>
</style>