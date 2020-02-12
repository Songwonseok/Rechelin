<template>
  <div id="app">
<b-row class="text-center">
   <b-col> 
      <v-card class="mx-auto" max-width="300" tile>
          <v-list rox unded>
            <v-subheader>REPORTS</v-subheader>
<<<<<<< HEAD
            <v-list-item-group v-model="item" color="primary" >
=======
            <v-list-item-group  color="primary">
>>>>>>> 1d49eb95088034c2ad83381ecca3beea92afae71
              <v-list-item router-link :to="{name: 'createReview'}">
                <v-list-item-content>
                  리뷰등록
                </v-list-item-content>
              </v-list-item>
              <v-list-item>
                <v-list-item-content>
                  리뷰..?
                </v-list-item-content>
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

export default {
    created(){
      this.$store.state.data+=1;
    },
    components :{

    }
}
</script>

<style>

</style>