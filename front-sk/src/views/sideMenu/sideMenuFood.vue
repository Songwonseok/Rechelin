<template>
  <div id="app">
<b-row class="text-center">
   <b-col> 
      <v-card class="mx-auto" max-width="300" tile>
          <v-list rounded>
            <v-subheader>REPORTS</v-subheader>
            <v-list-item-group v-model="item" color="primary">
              <v-list-item router-link :to="{name: 'storeSearch'}">
                <v-list-item-content>
                  검색
                </v-list-item-content>
              </v-list-item>
              <v-list-item >
                <v-list-item-content>
                  랜덤음식추천
                </v-list-item-content>
              </v-list-item>
            </v-list-item-group>
          </v-list>
        </v-card>   
</b-col>
<b-col cols="10">
<router-view></router-view>
  </b-col>
  </b-row>
</div>
</template>

<script>

import StoreApi from "../../apis/StoreApi";

export default {
  created(){
    this.$store.state.data +=1;
  },
  data() {
    return {
      keyword: "",
      randomList: []
    };
  },
  components: {
  
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