<template>
  <div id="app">
    <b-row class="text-center">
      <b-col>
        <v-card class="mx-auto" max-width="300" tile>
          <v-list rox unded>
            <v-list-item-group color="warning">
              <v-list-item router-link :to="{name : 'adminUserList'}">
                <v-list-item-content >
                  <span><v-icon>{{ mdiFormatListBulleted }}</v-icon>
                  회원관리</span>
                  </v-list-item-content> 
              </v-list-item>
              <v-list-item router-link :to="{name: 'adminContentList'}">
                <v-list-item-content >
                  <span><v-icon>{{ mdiPodiumGold}}</v-icon>
                  컨텐츠 내용 필터링</span>
                   </v-list-item-content>
              </v-list-item>
                <v-list-item router-link :to="{name : 'adminStatistics'}" @click="storeDetail">
                <v-list-item-content >방문 통계</v-list-item-content>
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
import { 
  mdiFormatListBulleted,
  mdiPodiumGold
   } from '@mdi/js';
export default {
  data() {
    return {
      //icons
       mdiFormatListBulleted,
       mdiPodiumGold
    }
  },
  components: {},
  methods: {
    storeDetail() {
      this.$store.dispatch('storeHashtags', 84)
      
    }
  }
};
</script>
<style>
</style>