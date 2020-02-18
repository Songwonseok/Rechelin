<template>
  <div>
    <div>
      <v-alert border="bottom" colored-border color="warning" elevation="2" style="margin-top: 30px;">
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
          <div style="margin-top: 30px;">
            <br>
            {{storeInfoSave.address}}
            <br>

            <span v-for="(h, index) in Storehashtags" :key="index">

              <v-chip class="ma-2" color="orange" text-color="white" style="margin-top: 20px !important;">
                <v-icon>{{icons.mdiPoundBox}}</v-icon>
                {{h.keyword}}
              </v-chip>

            </span>
          </div>
          <div>
            <v-btn class="ma-2" small :outlined='bookTrue' fab color="warning"
              @click="$router.push({name: 'createReview'})">
              <v-icon color="warning">{{icons.pencilOutline}}</v-icon>
            </v-btn>

            <v-btn class="ma-2" small outlined fab color="warning" @click="likeStore">
              <v-icon :color="bookmarkColor">{{icons.bookmark}}</v-icon>
            </v-btn>
            <!-- <v-btn class="ma-2" outlined fab color="warning" @click="browserlocation">
              <v-icon color="warning">{{icons.mdiCrosshairsGps}}</v-icon>
            </v-btn> -->
            <v-btn class="ma-2" small outlined fab color="warning" @click="getMylocation">
              <v-icon color="warning">{{icons.mdiCrosshairsGps}}</v-icon>
            </v-btn>
          </div>
        </b-card-text>
        <b-card-text style="text-align: center;">
          {{distance}}
        </b-card-text>

      </v-col>
      <v-col cols="6" md="4">

        <gmap-map :center="{lat: center.latitude, lng: center.longitude}" :zoom="15" style="width: 100%; height: 300px">
          <gmap-marker :position="{lat: parseFloat(storeInfoSave.lat), lng:parseFloat(storeInfoSave.lng)}"
            :clickable="true" :icon="{ url: require('../../assets/images/arrive.png')}" :draggable="true"
            @click="center=m.position">
          </gmap-marker>
          <gmap-marker :position="{lat: recentlocation.latitude, lng: recentlocation.longitude}" :clickable="true"
            :icon="{ url: require('../../assets/images/recent.png')}" :draggable="true" @click="center=m.position">
          </gmap-marker>
            <gmap-polyline v-bind:path.sync="walkCoordinates" v-bind:options="{ strokeColor:'#008000'}">
         </gmap-polyline>

        </gmap-map>

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
  import {
    haversine_distance
  } from '../../../public/js/location.js'
  import Vue from 'vue';
  import {
    mdiBookmark,
    mdiPencilOutline,
    mdiPoundBox,
    mdiCrosshairsGps
  } from '@mdi/js';
  import Axios from "axios"



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
          mdiPoundBox,
          mdiCrosshairsGps
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
        distance: null,
        recentlocation: {
          latitude: null,
          longitude: null,
        },
        center: {
          latitude: null,
          longitude: null
        },
         walkCoordinates: null,
        // css 용 변수
        bookTrue: true,
        bookmarkColor: 'warning'
      }
    },
    computed: {
      storeInfoSave() {

        return this.$store.state.storeInfo
      },
      Storehashtags() {
        return this.$store.state.storeHashtags
      }


    },

    methods: {
      likeStore() {
        var payload = {
          id: this.$store.state.userid,
          snum: this.$store.state.storeInfo.num
        }
        this.$store.dispatch('likeStore', payload)
        this.bookTrue = !this.bookTrue
        if (this.bookmarkColor == 'warning') {
          this.bookmarkColor = 'red'
          this.bookTrue = !this.bookTrue
        } else {
          this.bookmarkColor = 'warning'
          this.bookTrue = !this.bookTrue
        }


      },
      getMylocation() {
        navigator.geolocation.getCurrentPosition(pos => {
          this.recentlocation = pos.coords
          this.center = pos.coords
          console.log(this.recentlocation)
          // tmap
          var prtcl;
          var headers = {};
          headers['appKey'] = 'b3c90f6a-a54a-4a52-8f04-271742b2d731';
          Axios({
            type: "POST",
            headers: headers,
            url: "https://apis.openapi.sk.com/tmap/routes/pedestrian?version=1&format=json",
            async: false,
            contentType: "application/json",
            data: JSON.stringify({
              "reqCoordType": "WGS84GEO",
              "resCoordType": "WGS84GEO",
              "startName": "출발",
              "startX": this.recentlocation.longitude.toFixed(6),
              "startY": this.recentlocation.latitude.toFixed(6),
              "startTime": "201711121314",
              "endName": "도착",
              "endX": this.storeInfoSave.lng.toFixed(6),
              "endY": this.storeInfoSave.lat.toFixed(6),
              "searchOption": "0",
              "trafficInfo": "N",
            }),
            success: function (response) {
              prtcl = response;
              var style_red = {
                fillColor: "#FF0000",
                fillOpacity: 0.2,
                strokeColor: "#FF0000",
                strokeWidth: 3,
                strokeDashstyle: "solid",
                pointRadius: 2,
                title: "this is a red line"
              };
              this.walkCoordinates = []
              for (let j = 0; j < prtcl.features.length; j++) {
                    let sktcoordinate = prtcl.features[j].geometry
                    if (sktcoordinate.type == "Point") {
                        let pos = {
                            lat: sktcoordinate.coordinates[1],
                            lng: sktcoordinate.coordinates[0]
                        };
                        this.walkCoordinates.push(pos)
                    }
                }
            }
          })
        })
      }
    },
    created() {


    },
    mounted() {

      this.$router.push({
        name: 'storeReviews'
      })
      this.center.latitude = parseFloat(this.$store.state.storeInfo.lat)
      this.center.longitude = parseFloat(this.$store.state.storeInfo.lng)

    },

  }
</script>