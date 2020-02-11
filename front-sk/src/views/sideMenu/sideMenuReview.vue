<template>
  <div id="app">
<b-row class="text-center">
   <b-col> 
      <v-card class="mx-auto" max-width="300" tile>
          <v-list rounded>
            <v-subheader>REPORTS</v-subheader>
            <v-list-item-group v-model="item" color="primary">
              <v-list-item>
                <v-list-item-content router-link :to="{name: 'createReview'}">
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
    components :{

    }
}
</script>

<style>

</style>