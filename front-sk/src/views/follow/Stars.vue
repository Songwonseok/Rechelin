<template>
  <v-card max-width="500" class="mx-auto">
    <v-container 
    elevation="5"
    style="
    text-align: left;
    background: #ff7f00 !important;
    color: white;">
  
        <v-icon color="white">{{mdiAccountStar}}</v-icon>
        Following
    </v-container>
    <v-list>
      <v-list-item v-for="(s, index) in Stars" :key="index" router-link="router-link" :to="{name: 'userpage', params : {id : s.id}}">
<v-list-item-avatar v-if="s">
                
                       
                <img :src="s.profile" v-if="s.profile">
            
                <img :src="defaultProfile" v-else>
            
        </v-list-item-avatar>
        <!-- <v-list-item-content @click="goUserpage(s.id)"> -->
          <v-list-item-content @row-click = "starsClick">
            
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
        return this.$route.params.stars;
      },

    },
    created() {
    },
    data () {
      return {
        mdiAccountStar,
        defaultProfile : "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_960_720.png",
      }
    },
    methods: {
      
      // goUserpage(num) {
      //   this.$router.push({name: 'userpage', 
      //   params: {
      //     id: num
      //   }})
      // }
    }
  }
</script>