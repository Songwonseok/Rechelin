<template>
  <v-card max-width="500" class="mx-auto">
        <v-container 
          elevation="5"
          style="
          text-align: left;
          background: #ff7f00 !important;
          color: white;">
  
        <v-icon color="white">{{mdiAccountMultipleCheckOutline }}</v-icon>
        Followers
        </v-container>
    <v-list>
      <v-list-item v-for="(p, index) in UserFans" :key="index" @click="goUserpage(p.id)">
       
        <v-list-item-avatar v-if="p">
                
                       
                <img :src="p.profile" v-if="p.profile">
            
                <img :src="defaultProfile" v-else>
            
        </v-list-item-avatar>
        <v-list-item-content v-if="p" >
          <v-list-item-title v-html="p.nickname"></v-list-item-title>
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
        mdiAccountMultipleCheckOutline,
        defaultProfile : "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_960_720.png",
      }
    }


  }
</script>

<style scoped>

</style>