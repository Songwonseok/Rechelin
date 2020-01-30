<template>
<div>
    <!-- 네브바 -->

    <!-- 음식창과 검색창 -->

      <v-row
        align="start"
        no-gutters
        style="height: 130px;"
        class="mainSearch ">
        <v-col>
          
        </v-col>
        <v-col>
             <div class="search" >
                <input type="text" class="searchTerm" placeholder="What are you looking for?" >
                <button type="submit" class="searchButton">
                    
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
              <v-hover v-slot:default="{ hover }">
                <v-card
                  :loading="loading"
                  class="mx-auto my-12 storeRank"
                  max-width="374">
                
                  <v-img
                  height="200"
                  :src="store.picture">
                
                  <v-expand-transition>
                  <div
                    v-if="hover"
                    class="d-flex transition-fast-in-fast-out v-card--reveal white--text"
                    style="height: 100%;"
                    >
                식당 상세 정보
              </div>
              </v-expand-transition>
                
                
                </v-img>

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
               <v-chip-group
                   column
                  >
                    <v-chip v-for="(h, i) in store.hashtag" :key="i" >#{{h}}</v-chip>
                   </v-chip-group>
                </v-card-text>

              </v-card>
              </v-hover>
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
             <v-row align="center">
                <v-card
                  class="mx-auto"
                  max-width="400"
                  tile
                >
                  <v-list
                    flat=true
                    avatar=true
                    rounded=true
                  >
                    <v-subheader><v-icon>{{icons.Crown}}</v-icon>이달의 리뷰왕<v-icon>{{icons.Crown}}</v-icon></v-subheader>
                    <v-list-item-group v-model="item" color="primary">
                      <v-list-item
                        v-for="(item, i) in items"
                        :key="i"
                      >
                        <v-list-item-avatar v-if="item.avatar">
                          <v-img :src="item.avatar"></v-img>
                        </v-list-item-avatar>
                        <v-list-item-content>
                          <v-list-item-title>  {{i+1}}등: {{item.title}}</v-list-item-title>
                          <v-list-item-subtitle></v-list-item-subtitle>
                        </v-list-item-content>
                      </v-list-item>
                    </v-list-item-group>
                  </v-list>
                </v-card>
              </v-row>
        </v-col>
  
    </v-row>
    </v-container>


  </div>
</template>


<script>

import { mdiMagnify, mdiAccountCircle,mdiForum, mdiCrown } from '@mdi/js'
import {mapState} from 'vuex'

export default {
    data() {
        return {
            icons: {
                Magnify: mdiMagnify,
                AccountCircle: mdiAccountCircle,
                Forum: mdiForum,
                Crown: mdiCrown
            },
            loading: false,
            selection: 1,
                items: [
                          {
                            avatar: 'https://cdn.vuetifyjs.com/images/lists/1.jpg',
                            title: 'alex',
                            subtitle: "<span class='text--primary'>Ali Connors</span> &mdash; I'll be in your neighborhood doing errands this weekend. Do you want to hang out?",
                          },
                          {
                            avatar: 'https://cdn.vuetifyjs.com/images/lists/2.jpg',
                            title: 'emma',
                            subtitle: "<span class='text--primary'>to Alex, Scott, Jennifer</span> &mdash; Wish I could come, but I'm out of town this weekend.",
                          },
                          {
                            avatar: 'https://cdn.vuetifyjs.com/images/lists/3.jpg',
                            title: 'harry',
                            subtitle: "<span class='text--primary'>Sandra Adams</span> &mdash; Do you have Paris recommendations? Have you ever been?",
                          },
                          {
                            avatar: 'https://cdn.vuetifyjs.com/images/lists/4.jpg',
                            title: 'lebecca',
                            subtitle: "<span class='text--primary'>Trevor Hansen</span> &mdash; Have any ideas about what we should get Heidi for her birthday?",
                          },
                          {
                            avatar: 'https://cdn.vuetifyjs.com/images/lists/5.jpg',
                            title: 'ron',
                            subtitle: "<span class='text--primary'>Britta Holt</span> &mdash; We should eat this: Grate, Squash, Corn, and tomatillo Tacos.",
                          },
                        ],


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
    background-color: orange;
}
.search {
  width: 100%;
  position: relative;
  display: flex;
}

.searchTerm {
  width: 100%;
  border: 3px solid orangered;
  border-right: none;
  padding: 5px;
  height: 36px;
  border-radius: 5px 0 0 5px;
  outline: none;
  color: #9DBFAF;
  background-color: #fff
}

.searchTerm:focus{
  color: orangered;
}

.searchButton {
  width: 40px;
  height: 36px;
  border: 1px solid orangered;
  background: orangered;
  text-align: center;
  color: #fff;
  border-radius: 0 5px 5px 0;
  cursor: pointer;
  font-size: 20px;
}
.storeRank {
  margin-bottom: 30px;
}
.v-card__title{
  line-height: 0.5rem;
}
.v-card--reveal {
  align-items: center;
  bottom: 0;
  justify-content: center;
  opacity: .5;
  position: absolute;
  width: 100%;
  background-color: orange;
  left: 0px; 
  color: white;
  text-align: center;
}

</style>