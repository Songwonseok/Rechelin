<template>
    <v-container
    style="padding:0px;">
    <v-container
    style="padding-bottom: 0px;"
    >
      <v-alert
        border="bottom"
        colored-border
        color="#ff7f00"
        elevation="2"
      >
        <p 
        class="headline"
        style="font-family: 'Nanum Gothic Coding', !important;">{{storeInfoSave.sname}}
        
        <span
        class="subtitle-1"
        >
          <v-icon 
          small
          color="warning">{{icons.mdiStarOutline}}</v-icon>
          {{storeAvg.totalAvg}}
        </span>
        </p>
  
      </v-alert>

      <!-- <v-row> -->
      <!-- 사진 -->
      <!-- <v-col v-for="n in 3" :key="n">
          <v-card class="pa-2" outlined tile style="text-align:center;">
            <img :src="storeInfoSave.image" :alt="storeInfoSave.image" style="height: 300px; width: 300px">
          </v-card>
        </v-col>
      </v-row>-->
      <!-- 식당 상세정보 -->
    </v-container>
    <hr />
    <v-row no-gutters>
      <v-col>
        <b-card-text style="text-align: center; ">
          <v-container 
          style="font-family: 'Nanum Gothic Coding', ;">
            <br />
            {{storeInfoSave.address}}
            <br />

            <span 
            v-for="(h, index) in Storehashtags" :key="index">
              <v-chip
                class="ma-2"
                color="orange"
                text-color="white"
                style="margin-top: 20px !important;
                font-family: 'Nanum Gothic Coding', ;
                "
              >
                <v-icon>{{icons.mdiPoundBox}}</v-icon>
                {{h.keyword}}
              </v-chip>
            </span>
          </v-container>
          <v-container>
            <v-btn
              class="ma-2"
              small
              :outlined="bookTrue"
              fab
              color="warning"
              @click="$router.push({name: 'createReview'})"
            >
              <v-icon color="warning">{{icons.pencilOutline}}</v-icon>
            </v-btn>

            <v-btn class="ma-2" small outlined fab color="warning" @click="likeStore">
              <v-icon :color="bookmarkColor">{{icons.bookmark}}</v-icon>
            </v-btn>
            <!-- <v-btn class="ma-2" outlined fab color="warning" @click="browserlocation">
              <v-icon color="warning">{{icons.mdiCrosshairsGps}}</v-icon>
            </v-btn>-->
            <v-btn class="ma-2" small outlined fab color="warning" :href="findRoad" >
              <v-icon color="warning">{{icons.mdiCrosshairsGps}}</v-icon>
            </v-btn>
          </v-container>

          <v-card 
          class="mx-auto text-center" 
          outlined 
          style="max-width: 55%; margin-top: 30px;">
              <v-chip
                  class="ma-2"
                  color="warning"
                  outlined
                  style="font-family: 'Nanum Gothic Coding', ;"
                >
                <v-icon color="warning">{{icons.mdiEmoticonTongueOutline}}</v-icon>
                  taste : {{storeAvg.tasteAvg}}
                </v-chip>
                  <v-chip
                  class="ma-2"
                  color="warning"
                  outlined
                >
                 <v-icon color="warning">{{icons.mdiCurrencyUsd}}</v-icon>
                  price : {{storeAvg.priceAvg}}
                </v-chip>
                  <v-chip
                  class="ma-2"
                  color="warning"
                  outlined
                >
                    <v-icon color="warning">{{icons.mdiEmoticonWinkOutline}}</v-icon>
                 kind : {{storeAvg.kindAvg}}
                </v-chip>
          </v-card>
        </b-card-text>
        <b-card-text style="text-align: center;">
          <p>{{distance.d}}</p>
          <p>{{distance.t}}</p>
        </b-card-text>
      </v-col>

      <v-col cols="12" md="4" >
        <v-container>
            <gmap-map
          :center="{lat: center.latitude, lng: center.longitude}"
          :zoom="15"
          style="width: 100%; height: 300px"
        >
          <gmap-marker
            :position="{lat: parseFloat(storeInfoSave.lat), lng:parseFloat(storeInfoSave.lng)}"
            :clickable="true"
            :icon="{ url: require('../../assets/images/arrive.png')}"
            :draggable="true"
            @click="center=m.position"
          ></gmap-marker>
          <gmap-marker
            :position="{lat: recentlocation.latitude, lng: recentlocation.longitude}"
            :clickable="true"
            :icon="{ url: require('../../assets/images/recent.png')}"
            :draggable="true"
            @click="center=m.position"
          ></gmap-marker>
          <gmap-polyline
            v-bind:path.sync="walkCoordinates"
            v-bind:options="{ strokeColor:'#008000'}"
          ></gmap-polyline>
        </gmap-map>


        </v-container>
      
         
      </v-col>
    </v-row>
    <!-- 그리드 구분 -->
    <hr />

    <v-container>
      <router-view></router-view>
    </v-container>
    </v-container >
</template>

<script>
import * as VueGoogleMaps from "vue2-google-maps";

import { haversine_distance } from "../../../public/js/location.js";
import Vue from "vue";
import {
  mdiBookmark,
  mdiPencilOutline,
  mdiPoundBox,
  mdiCrosshairsGps,
  mdiStarOutline,
  mdiCurrencyUsd,
  mdiEmoticonTongueOutline,
  mdiEmoticonWinkOutline
} from "@mdi/js";
import Axios from "axios";

Vue.use(VueGoogleMaps, {
  load: {
    key: "AIzaSyDC4sonH281FHJ-YyPmeXLRdBYuqcjUkGE",
    v: "OPTIONAL VERSION NUMBER",
    libraries: "places" //// If you need to use place input
  }
});

export default {
  data() {
    return {
      icons: {
        bookmark: mdiBookmark,
        pencilOutline: mdiPencilOutline,
        mdiPoundBox,
        mdiCrosshairsGps,
        mdiStarOutline,
        mdiCurrencyUsd,
        mdiEmoticonTongueOutline,
        mdiEmoticonWinkOutline
      },
      storeDetails: {
        num: "",
        name: "",
        address: "",
        img: "",
        lat: "",
        lng: ""
      },
      hashtags: "",
      distance: {
        d: null,
        t: null
      },
      recentlocation: {
        latitude: null,
        longitude: null
      },
      center: {
        latitude: null,
        longitude: null
      },
      walkCoordinates: null,
  
      // css 용 변수
      bookTrue: true,
      bookmarkColor: "warning"
    };
  },
  computed: {
    storeInfoSave() {
      return this.$store.state.storeInfo;
    },
    Storehashtags() {
      return this.$store.state.storeHashtags;
    },
    storeAvg() {
      return this.$store.state.storeAvg;
    },
     findRoad() {
       var url = 'https://map.kakao.com/link/to/'+ this.$store.state.storeInfo.sname +','+String(this.$store.state.storeInfo.lat)+','+String(this.$store.state.storeInfo.lng)
      return 	url
    },
   
  },

  methods: {
    
    likeStore() {
      var payload = {
        id: this.$store.state.userid,
        snum: this.$store.state.storeInfo.num
      };
      this.$store.dispatch("likeStore", payload);
      this.bookTrue = !this.bookTrue;
      if (this.bookmarkColor == "warning") {
        this.bookmarkColor = "red";
        this.bookTrue = !this.bookTrue;
      } else {
        this.bookmarkColor = "warning";
        this.bookTrue = !this.bookTrue;
      }
    },
    getMylocation() {
      navigator.geolocation.getCurrentPosition(pos => {
        this.recentlocation = pos.coords;
        this.center = pos.coords;

        let temp_d = this.haversine_distance(
          this.recentlocation.latitude,
          this.recentlocation.longitude,
          this.$store.state.storeInfo.lat,
          this.$store.state.storeInfo.lng
        );
        temp_d = temp_d.toFixed(2);

        let time = (temp_d / 5).toFixed(2);
        time = "도보 (약)" + time * 60 + "분";
        temp_d = "거리 (약)" + temp_d + "km";
        this.distance = {
          d: temp_d,
          t: time
        };

      });
      // 거리 구하자
    },
    haversine_distance(mk1_lat, mk1_lng, mk2_lat, mk2_lng) {
      //var R = 3958.8; // Radius of the Earth in miles
      mk2_lat = parseFloat(mk2_lat);
      mk2_lng = parseFloat(mk2_lng);
      var R = 6371.071; // in kilometer
      //var rlat1 = mk1.position.lat() * (Math.PI / 180); // Convert degrees to radians
      //var rlat2 = mk2.position.lat() * (Math.PI / 180); // Convert degrees to radians
      var rlat1 = mk1_lat * (Math.PI / 180);
      var rlat2 = mk2_lat * (Math.PI / 180);
      var difflat = rlat2 - rlat1; // Radian difference (latitudes)
      //var difflon = (mk2.position.lng() - mk1.position.lng()) * (Math.PI / 180); // Radian difference (longitudes)
      var difflon = (mk2_lng - mk1_lng) * (Math.PI / 180); // Radian difference (longitudes)
      var d =
        2 *
        R *
        Math.asin(
          Math.sqrt(
            Math.sin(difflat / 2) * Math.sin(difflat / 2) +
              Math.cos(rlat1) *
                Math.cos(rlat2) *
                Math.sin(difflon / 2) *
                Math.sin(difflon / 2)
          )
        );
      return d;
    }
  },
  created() {},
  mounted() {
    // this.$store.state.detail 이 null이면 comments 방문 목적이 아니기 때문에 review를 보여주고,
    //  그렇지 않은경우, detail에 review num이 들어있으므로 review 상세페이지를 보고자하는 목적 이므로
    if (this.$store.state.detail == null) {
          this.$router.replace({
          name: "storeReviews"
        });
    } else if (this.$store.state.detail != null) {
      this.$store.dispatch('commentsOfFeed', this.$store.state.detail)
    }

    this.center.latitude = parseFloat(this.$store.state.storeInfo.lat);
    this.center.longitude = parseFloat(this.$store.state.storeInfo.lng);

    this.$store.dispatch("scoreAvg", this.$store.state.storeInfo.num);

   
  }
};
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css?family=Nanum+Gothic+Coding|Nanum+Myeongjo:700&display=swap');

</style>