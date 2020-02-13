<template>
  <div>
    <div>
      <v-alert border="bottom" colored-border color="warning" elevation="2">
        <h1>{{storeInfoSave.sname}}</h1>
      </v-alert>

      <!-- <v-row> -->
      <!-- 사진 -->
      <!-- <v-col v-for="n in 3" :key="n">
          <v-card class="pa-2" outlined tile style="text-align:center;">
            <img :src="storeInfoSave.image" :alt="storeInfoSave.image" style="height: 300px; width: 300px">
          </v-card>
        </v-col>
      </v-row> -->
      <!-- 식당 상세정보 -->
    </div>
    <hr>
    <v-row no-gutters>
      <v-col cols="12" sm="6" md="8">
        <b-card-text style="text-align: center;">
          <div>
            {{storeInfoSave.name}} <br>
            {{storeInfoSave.address}}
            <br>

            <span v-for="(h, index) in Storehashtags" :key="index">

              <v-chip class="ma-2" color="orange" text-color="white">
                <v-icon>{{icons.mdiPoundBox  }}</v-icon>
                {{h.keyword}}
              </v-chip>
              
            </span>
          </div>
          <div>
            <v-btn class="ma-2" outlined fab color="indigo">
              <v-icon>{{icons.pencilOutline}}</v-icon>
            </v-btn>

            <v-btn class="ma-2" outlined fab color="indigo" @click="likeStore">
              <v-icon>{{icons.bookmark}}</v-icon>
            </v-btn>
          </div>
        </b-card-text>

      </v-col>
      <v-col cols="6" md="4">
        <div>
          <gmap-map :center="{'lat': storeInfoSave.lat, 'lng':storeInfoSave.lng}" :zoom="16"
            style="width: 500px; height: 300px">
            <gmap-marker :position="{'lat': storeInfoSave.lat, 'lng':storeInfoSave.lng}" :clickable="true"
              :draggable="true" @click="center=m.position">
            </gmap-marker>
          </gmap-map>
        </div>
      </v-col>
    </v-row>
    <!-- 그리드 구분 -->
    <hr>

    <div>
      <router-view></router-view>
    </div>

  </div>

</template>

<script>
  import * as VueGoogleMaps from 'vue2-google-maps';
  import Vue from 'vue';
  import {
    mdiBookmark,
    mdiPencilOutline,
    mdiPoundBox 
  } from '@mdi/js';

  Vue.use(VueGoogleMaps, {
    load: {
      key: 'AIzaSyDC4sonH281FHJ-YyPmeXLRdBYuqcjUkGE',
      v: 'OPTIONAL VERSION NUMBER',
      libraries: 'places', //// If you need to use place input
    }
  });

  export default {
    data() {
      return {
        icons: {
          bookmark: mdiBookmark,
          pencilOutline: mdiPencilOutline,
          mdiPoundBox 
        },
        storeDetails: {
          num: '',
          name: '',
          address: '',
          img: '',
          lat: '',
          lng: '',
        },
        hashtags: '',
      }
    },
    computed: {
      storeInfoSave() {

        return this.$store.state.storeInfo
      },
      Storehashtags() {
        console.log(this.$store.state.storeHashtags, 'computed')
        return this.$store.state.storeHashtags
      }


    },

    methods: {
      likeStore() {
        var payload = {
          user: this.$store.state.userEmail,
        }
        this.$store.dispatch('likeStore', payload)
      },
    },
    created() {

      this.$store.dispatch('reviewsGet', 1)

    },
    mounted() {


    },

  }
</script>