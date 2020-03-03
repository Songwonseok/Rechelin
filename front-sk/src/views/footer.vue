<template>
  <div id="footer-top">
  <v-app id="footer" style="min-height: 0px !important;">
      <v-card
      color="white lighten-4"
      flat
      height="200px"
      tile
    >
      </v-card>
    <v-footer
      dark
      padless
      fluid fill-height
    >
      <v-card
        flat
        tile
        height = "400px"
        width = "100%"
        class="orange lighten-1 white--text text-center"
      >
        <v-card-text>
          <!-- <v-btn
            v-for="icon in icons"
            :key="icon"
            class="mx-1 white--text btnColor"
            icon
          >
            <v-icon size="24px">{{ icon }}</v-icon>
          </v-btn> -->
           <!-- <v-btn text icon fab @click="Nshare"> -->
             
            <v-icon @click="Nshare" color="white" large>
                {{mdiAlphaNCircle}}
              </v-icon>
         
          <!-- </v-btn> -->
          <!-- <v-btn text icon fab @click="Fshare"> -->
            <v-icon @click="Fshare" color="white" large>{{mdiFacebook }}</v-icon>
          <!-- </v-btn> -->
        </v-card-text>
        
        <v-card-text class="white--text pt-5">
          <h5 style="color : white"><string><b>🍴리슐랭</b></string></h5> <br>
           
           <h5 style="color : white" ><b>Samsung Software Academy For Youth 2nd.</b></h5>
           
           
           <br>
          <h6 style="color : white" ><b>멀티캠퍼스 서울 특별시 강남구 언주로 508</b></h6>
          <br>
          <p style="color : white"><b>ⓒ2020 A404 All rights reserved.</b></p> 
<br>
 <b style="color : white">SSAFY ㅡ {{ new Date().getFullYear() }}</b><br>
          <h7><strong style="color : orange">김주연</strong></h7> &nbsp;
          <h7><strong style="color : orange">김현지</strong></h7> &nbsp;
          <h7><strong style="color : orange">송원석</strong></h7> &nbsp;
          <h7><strong style="color : orange">황인규</strong></h7> &nbsp;
        </v-card-text>
        <v-divider></v-divider>
        <v-card-text class="white--text">
          
          <!-- footer 밑동 -->
        </v-card-text>
      
        
      </v-card>

    </v-footer>
  </v-app>
</div>
</template>
<script>
 import {
    mdiFacebook,
    mdiAlphaNCircle,
  } from '@mdi/js';
  import Axios from 'axios'

export default {
  
  data: () => ({
    icons: [
      'fab fa-facebook',
      'fab fa-n',
      
    ],
    mdiAlphaNCircle,
    mdiFacebook
  }),
  methods : {
    Nshare() {
        var url = encodeURI(encodeURIComponent('http://i02a404.p.ssafy.io/'));
        var title = encodeURI('리뷰 맛집은 리슐랭');
        var shareURL = "https://share.naver.com/web/shareView.nhn?url=" + url + "&title=" + title;
        Axios.get(shareURL)
          .then(res => {
          })
        document.location = shareURL;

      },
      Fshare() {
        var shareURL = "https://www.facebook.com/sharer/sharer.php?u=http%3A%2F%2F54.180.160.87%2F&amp;src=sdkpreparse";
        Axios.get(shareURL)
          .then(res => {
          })
        document.location = shareURL;
      },
  }
}
</script>
<style scoped>
#footer-top :not(#inspire) {
  min-height: 0vh;
}
v-icon {
  color: white;
}
</style>