<template>
  <div id="app">
  <v-app id="inspire">
    <v-card>
      <v-toolbar flat color="orange" dark>
         <v-toolbar-title>   <i class="fas fa-user fa-3x"></i>유저페이지 </v-toolbar-title>  
      </v-toolbar>
      <v-tabs vertical>
        
      
        <v-tab>
          <v-icon left>mdi-account</v-icon>
          유저페이지
        </v-tab>

         <v-tab>
          <v-icon left>mdi-account</v-icon>
          팔로우 리스트
        </v-tab>

      

        
  
        <v-tab-item>
          <v-card flat>
            <v-card-text>
               <user-page></user-page>
            </v-card-text>
          </v-card>
        </v-tab-item>
        <v-tab-item>
          <v-card flat>
            <v-card-text>
      
            </v-card-text>
          </v-card>
        </v-tab-item>
        <v-tab-item>
          <v-card flat>
            <v-card-text>
              
            </v-card-text>
          </v-card>
        </v-tab-item>
      </v-tabs>
    </v-card>
  </v-app>
</div>
</template>

<script>
import userPage from '../../views/userpage/UserPage.vue'

export default {
  
    components :{
        userPage,
    
     
        
    },
    data(){
      return{
        
      }
    },
    watch : {
      
    },
    computed :{
      
    },
}
</script>

<style>

</style>