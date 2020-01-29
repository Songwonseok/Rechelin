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

      <v-row
        align="start"
        no-gutters
        style="height: 150px;"
        class="mainSearch ">
        <v-col>
          
        </v-col>
        <v-col>
             <div class="search">
                <input type="text" class="searchTerm" placeholder="What are you looking for?">
                <button type="submit" class="searchButton">
                    <i class="fa fa-search"></i>
                </button>
            </div>
        </v-col>
        <v-col>
        </v-col>
      </v-row>

    <!-- 사진이랑 유저 랭킹 -->
    <v-container class="grey lighten-5">
    <v-row no-gutters>
      <v-col cols="9"> 
        <v-row no-gutters>
          <template v-for="(store, index) in Stores">
            <v-col :key="index">
              <v-card
                :loading="loading"
                class="mx-auto my-12 storeRank"
        
                max-width="374"
              >
                <v-img
                  height="250"
                  :src="store.picture"
                ></v-img>

                <v-card-title>{{store.name}}</v-card-title>

                <v-card-text>
                  <v-row
                    align="center"
                    class="mx-0"
                  >
                    <v-rating
                      :value="4.5"
                      color="amber"
                      dense
                      half-increments
                      readonly
                      size="14"
                    ></v-rating>

                    <div class="grey--text ml-4">4.5 (413)</div>
                  </v-row>

                  <div class="my-4 subtitle-1 black--text">
                    {{store.address}}
                  </div>

  
                </v-card-text>

                <v-divider class="mx-4"></v-divider>

                <v-card-title>HashTag</v-card-title>

                <v-card-text>
                  <v-chip-group
                   column
                  >
                    <v-chip v-for="(h, i) in store.hashtag" :key="i" >#{{h}}</v-chip>

                  </v-chip-group>
                </v-card-text>

                <v-card-actions>
                  <v-btn
                    color="deep-purple accent-4"
                    text
                    @click="reserve"
                  >
                    Detail
                  </v-btn>
                </v-card-actions>
              </v-card>
                </v-col>
                <v-responsive
                  v-if="n === 2"
                  :key="`width-${index}`"
                  width="100%"
                ></v-responsive>
              </template>
            </v-row>
      </v-col>
         
        <v-col cols="3">
            <v-card
            class="pa-2"
            outlined
            tile
          >
            One of three columns
          </v-card>
        </v-col>
  
    </v-row>
    </v-container>


  </div>
</template>


<script>

import { mdiMagnify, mdiAccountCircle,mdiForum, mdiPasta } from '@mdi/js'
import {mapState} from 'vuex'

export default {
    data() {
        return {
            icons: {
                Magnify: mdiMagnify,
                AccountCircle: mdiAccountCircle,
                Forum: mdiForum,
                Pasta: mdiPasta
            },
            loading: false,
            selection: 1,


        }
    },
    computed: {
        ...mapState(['tempStores']),
        Stores () {
            return this.tempStores
        }
    },
     methods: {
      reserve () {
        this.loading = true

        setTimeout(() => (this.loading = false), 2000)
      },
    },
}
</script>

<style scoped>
.mainOut {
    padding: 0px;
}
.mainSearch {
    padding-top: 90px;
    height: 250px !important; 
    background-color: yellow;
}
.search {
  width: 100%;
  position: relative;
  display: flex;
}

.searchTerm {
  width: 100%;
  border: 3px solid black;
  border-right: none;
  padding: 5px;
  height: 36px;
  border-radius: 5px 0 0 5px;
  outline: none;
  color: #9DBFAF;
  background-color: #fff
}

.searchTerm:focus{
  color: black;
}

.searchButton {
  width: 40px;
  height: 36px;
  border: 1px solid black;
  background: black;
  text-align: center;
  color: #fff;
  border-radius: 0 5px 5px 0;
  cursor: pointer;
  font-size: 20px;
}
.storeRank {
  margin-bottom: 30px;
}

</style>