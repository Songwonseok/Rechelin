<template>
  <div>
    <v-card>
         <v-card-title class="headline">
        <!-- <v-chip  @click:close="keyword = ''">{{keyword}}</v-chip> -->
        <v-combobox v-model="keyword" chips clearable label="위치를 '하나만' 선택하세요" solo></v-combobox>
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
  </div>
</template>

<script>
import StoreApi from "../../apis/StoreApi";

export default {
  data() {
    return {
      keyword: "",
      randomList: []
    };
  },
  methods: {
    insertTags(name) {
      if (name == this.keyword) this.keyword = "";
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

<style scoped>
.v-application .primary {
    background-color: #ff7f00 !important;
    border-color: #ff7f00 !important;
}
</style>