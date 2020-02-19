<template>
  <div id="app">
    <b-row class="text-center">
      <b-col>
        <v-card class="mx-auto" max-width="300" tile>
          <v-list rox unded>
            <!-- <v-subheader>REPORTS</v-subheader> -->
            <v-list-item-group color="warning">
              <v-list-item router-link :to="{name: 'userFeed'}">
                <v-list-item-content>
                  <span><v-icon>{{mdiFormatListText}}</v-icon>
                  피드 리스트</span>
                  </v-list-item-content>
              </v-list-item>
            </v-list-item-group>
          </v-list>
        </v-card>
      </b-col>
      <b-col cols="10" sm="12">
        <router-view></router-view>
      </b-col>
    </b-row>
  </div>
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