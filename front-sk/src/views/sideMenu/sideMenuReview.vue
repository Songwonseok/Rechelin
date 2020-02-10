<template>
  <div id="app">
  <v-app id="inspire">
    <v-card>
      <v-toolbar flat color="orange" dark>
         <v-toolbar-title>  <i class="fas fa-utensils fa-3x" > </i></v-toolbar-title> 
       
      </v-toolbar>
      <v-tabs vertical>
        <v-tab>
          <v-icon left>mdi-account</v-icon>
            리뷰 등록
        </v-tab>
            
        <!-- <v-tab>
          <v-icon left>mdi-access-point</v-icon>
          회원가입
        </v-tab> -->
  
        <v-tab-item>
          <v-card flat>
            <v-card-text>
              <!-- <router-link to="/login">Go to Foo</router-link> -->
              <!-- <login-bar></login-bar> -->
              
              <!-- <p class="mb-0">
                Phasellus dolor. Fusce neque. Fusce fermentum odio nec arcu. Pellentesque libero tortor, tincidunt et, tincidunt eget, semper nec, quam. Phasellus blandit leo ut odio.
              </p> -->
            </v-card-text>
          </v-card>
        </v-tab-item>
        <v-tab-item>
          <v-card flat>
            <v-card-text>
            <!-- <user-page></user-page> -->
            </v-card-text>
          </v-card>
        </v-tab-item>
        <v-tab-item>
          <v-card flat>
            <v-card-text>
              <p>
                Fusce a quam. Phasellus nec sem in justo pellentesque facilisis. Nam eget dui. Proin viverra, ligula sit amet ultrices semper, ligula arcu tristique sapien, a accumsan nisi mauris ac eros. In dui magna, posuere eget, vestibulum et, tempor auctor, justo.
              </p>
  
              <p class="mb-0">
                Cras sagittis. Phasellus nec sem in justo pellentesque facilisis. Proin sapien ipsum, porta a, auctor quis, euismod ut, mi. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nam at tortor in tellus interdum sagittis.
              </p>
            </v-card-text>
          </v-card>
        </v-tab-item>
      </v-tabs>
    </v-card>
  </v-app>
</div>
</template>

<script>

export default {
    components :{
      
    }
}
</script>

<style>

</style>