<template>
  <v-container id="app">
    <b-row class="text-center">
      <b-col lg="2" sm="12">
        <v-card class="mx-auto" max-width="300" tile>
          <v-list rox unded>
            <!-- <v-subheader>REPORTS</v-subheader> -->
            <v-list-item-group color="warning">
              <v-list-item router-link :to="{name: 'userFeed'}">
                <v-list-item-content>
                  <span><v-icon>{{mdiFormatListText}}</v-icon>
                  피드리스트</span>
                  </v-list-item-content>
              </v-list-item>
            </v-list-item-group>
          </v-list>
        </v-card>
      </b-col>
      <b-col cols="12" xl="10" md="10" lg="10">
        <router-view></router-view>
      </b-col>
    </b-row>
  </v-container>
</template>
<script>
import { mdiFormatListText } from '@mdi/js';
export default {
  components: {},
  data() {
    return {
      //icons
      mdiFormatListText
    }
  }
};
</script>
<style>
</style>