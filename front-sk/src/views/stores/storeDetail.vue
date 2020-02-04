<template>
  <div>
    <div class="text-center" style="text-align: center;">
      <div>
        <b-card :title="storeInfoSave.name" :img-src="storeInfoSave.image" :img-alt="storeInfoSave.name" img-top
          tag="article" style="max-width: 20rem;" class="mb-2">
          <b-card-text>
            {{storeInfoSave.address}}
            <br>


          </b-card-text>
        </b-card>
      </div>
      <div>
        <gmap-map :center="storeInfoSave.gps" :zoom="16" style="width: 500px; height: 300px">
          <gmap-marker :position="storeInfoSave.gps" :clickable="true" :draggable="true" @click="center=m.position">
          </gmap-marker>
        </gmap-map>
      </div>
      <span v-for="(h, index) in storeInfoSave.hashtag" :key="index">
        #{{h}}
      </span>

    </div>
    <div>

      <router-view></router-view>
    </div>

  </div>

</template>

<script>
  import * as VueGoogleMaps from 'vue2-google-maps';
  import Vue from 'vue';


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


      }
    },
    computed: {
      storeInfoSave() {
        return this.$store.state.tempStores[1]
      },


    },
    methods: {

    },
    created() {
      this.$router.push({
        name: 'storeReviews',
        params: {
          
        }
      })
    }

  }
</script>