<template>
<div>
    <!-- 네브바 -->
    <v-app-bar
      dense
      dark
    >
      <v-app-bar-nav-icon></v-app-bar-nav-icon>

      <v-toolbar-title>싸피 4조</v-toolbar-title>

      <v-spacer></v-spacer>

      <v-btn icon>
        <v-icon>{{icons.Forum}}</v-icon>
      </v-btn>
       <v-btn icon>
        <v-icon>{{icons.AccountCircle}}</v-icon>
      </v-btn>

      <v-btn icon>
        <v-icon>{{icons.Magnify}}</v-icon>
      </v-btn>
    </v-app-bar>
    <!-- 음식창과 검색창 -->
   <v-container
      class="grey lighten-5 mb-6 mainOut"
    >
      <v-row
        align="start"
        no-gutters
        style="height: 150px;"
        class="mainSearch ">
        <v-col>
          
        </v-col>
        <v-col>
      <v-toolbar
        dense
        floating
      >
      <v-icon>my_location</v-icon>
        <v-text-field
          hide-details
          single-line
        ></v-text-field>
  
  
      </v-toolbar>

        </v-col>
        <v-col>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>


<script>

import { mdiMagnify, mdiAccountCircle,mdiForum } from '@mdi/js'
export default {
    data() {
        return {
            icons: {
                Magnify: mdiMagnify,
                AccountCircle: mdiAccountCircle,
                Forum: mdiForum
            },

        }
    }
}
</script>

<style scoped>
.mainOut {
    padding: 0px;
}
.mainSearch {
    padding-top: 90px;
    height: 150px;
    background-color: yellow;
}

</style>