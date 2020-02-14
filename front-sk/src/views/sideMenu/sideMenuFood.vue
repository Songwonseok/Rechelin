<template>
  <div id="app">
    <b-row class="text-center">
      <b-col>
        <v-card class="mx-auto" max-width="300" tile>
          <v-list rox unded>
            <!-- <v-subheader>REPORTS</v-subheader> -->
            <v-list-item-group color="primary">
              <v-list-item router-link :to="{name : 'storeSearch'}">
                <v-list-item-content >음식점 리스트</v-list-item-content> <!--검색에서 음식점 리스트로 변경되었음-->
              </v-list-item>
              <v-list-item router-link :to="{name: 'random'}">
                <v-list-item-content >랜덤음식추천</v-list-item-content>
              </v-list-item>
                <v-list-item @click="storeDetail">
                <v-list-item-content >음식점 상세페이지(임시)</v-list-item-content>
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
export default {
  components: {},
  methods: {
    storeDetail() {
      this.$store.dispatch('storeHashtags', 3)
      
    }
  }
};
</script>
<style>
</style>