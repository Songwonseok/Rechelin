<template>
  <div id="app">
  <v-app id="inspire">
    <v-card>
      <v-toolbar flat color="orange" dark>
        <v-toolbar-title><i class="fas fa-mail-bulk fa-3x" ></i> 리뷰등록 </v-toolbar-title>  
      </v-toolbar>


      <v-tabs vertical>
        <v-tab>
          <v-icon left>mdi-account</v-icon>
             리뷰 등록 
        </v-tab>
        <v-tab-item>
          <v-card flat>
            <v-card-text>
                <review-page></review-page>
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
import reviewPage from '../review/reviewPage.vue'

export default {
  
    components :{
       reviewPage,
    
     
        
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