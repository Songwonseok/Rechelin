<template>
  <v-card max-width="500" class="mx-auto">
    <v-toolbar color="warning" dark>
      <v-toolbar-title>
        <v-icon>{{mdiAccountStar}}</v-icon>
        Following</v-toolbar-title>

      <v-spacer></v-spacer>


    </v-toolbar>
    <v-list>
      <v-list-item v-for="(s, index) in Stars" :key="index">

        <v-list-item-content @click="goUserpage(s.id)">
          <v-list-item-title v-text="s.nickname"></v-list-item-title>
        </v-list-item-content>
      </v-list-item>
    </v-list>
  </v-card>
</template>

<script>

import { mdiAccountStar } from '@mdi/js';
  export default {
    computed: {
      Stars() {
        return this.$route.params.stars
      }
    },
    created() {
    },
    data () {
      return {
        mdiAccountStar 
      }
    },
    methods: {
      goUserpage(num) {
        this.$router.push({name: 'userpage', 
        params: {
          id: num
        }})
      }
    }
  }
</script>