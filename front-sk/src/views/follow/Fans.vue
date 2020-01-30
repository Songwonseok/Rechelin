<template>
    <v-card
    max-width="500"
    class="mx-auto"
   >
    <v-toolbar
      color="indigo"
      dark
     >
      
      <v-toolbar-title>Followers</v-toolbar-title>

       <v-spacer></v-spacer>


    </v-toolbar>
    <v-list>
      <v-list-item
        v-for="(p, index) in UserFans" 
        :key="index"
      >

        <v-list-item-content>
          <v-list-item-title v-text="p[0].nickname"></v-list-item-title>
        </v-list-item-content>
      </v-list-item>
    </v-list>
  </v-card>

</template>

<script>

export default {

    computed : {
       UserFans() {

           return this.$route.params.fans
       },
    },
    created () {

    }


}
</script>