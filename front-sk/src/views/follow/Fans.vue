<template>
  <v-card max-width="500" class="mx-auto">
    <v-toolbar color="warning" dark>

      <v-toolbar-title>
        <v-icon>{{mdiAccountMultipleCheckOutline }}</v-icon>
        Followers</v-toolbar-title>

      <v-spacer></v-spacer>


    </v-toolbar>
    <v-list>
      <v-list-item v-for="(p, index) in UserFans" :key="index" @click="goUserpage(p.id)">
        <v-list-item-content v-if="p" >
          <v-list-item-title v-text="p.nickname"></v-list-item-title>
        </v-list-item-content>
      </v-list-item>
    </v-list>
  </v-card>

</template>

<script>
import { mdiAccountMultipleCheckOutline } from '@mdi/js';
  export default {

    computed: {
      UserFans() {
        console.log( this.$route.params.fans, 'computed===========================')
        return this.$route.params.fans
      },
    },
    methods: {
       goUserpage(num) {
        this.$router.push({name: 'userpage', params: {
          id: num
        }})
      },
    },
    data() {
      return {
        mdiAccountMultipleCheckOutline 
      }
    }


  }
</script>

<style scoped>

</style>