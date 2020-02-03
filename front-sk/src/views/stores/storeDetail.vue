<template>
  <div>
    <div class="text-center" style="text-align: center;">
      <div>
        <b-card :title="storeInfoSave.name" :img-src="storeInfoSave.image" :img-alt="storeInfoSave.name" img-top
          tag="article" style="max-width: 20rem;" class="mb-2">
          <b-card-text>
            {{storeInfoSave.address}}
            <br>
            <span v-for="(h, index) in storeInfoSave.hashtag" :key="index">
              #{{h}}
            </span>

          </b-card-text>
        </b-card>
      </div>
      <div>
        <gmap-map :center="storeInfoSave.gps" :zoom="16" style="width: 500px; height: 300px">
          <gmap-marker :position="storeInfoSave.gps" :clickable="true" :draggable="true" @click="center=m.position">
          </gmap-marker>
        </gmap-map>
      </div>

      <div v-for="(h, i) in storeReviewSave" :key="i">
        <v-card class="mx-auto" max-width="344" outlined>
          <v-list-item three-line>
            <v-list-item-content>
              <div class="overline mb-4">{{h.nickname}}</div>
              <v-list-item-title class="headline mb-1">{{h.content}}</v-list-item-title>
              <v-list-item-subtitle>{{h.hashtags}}</v-list-item-subtitle>
            </v-list-item-content>

            <v-list-item-avatar tile size="80" color="grey">
              <img :src="storeInfoSave.image" alt="">
            </v-list-item-avatar>
          </v-list-item>

          <v-card-actions>
            <v-btn text>{{h.like}} 좋아요</v-btn>
            <v-btn text>{{h.dislike}} 싫어요</v-btn>
          </v-card-actions>
        </v-card>
      </div>
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
        storeReviews: this.storeInfoSave,

      }
    },
    computed: {
      storeInfoSave() {
        return this.$store.state.tempStores[1]
      },
      storeReviewSave() {
        var temp = []
        for (var x = 0; x < 100; x++) {
          temp.push(this.$store.state.fakeReviews[0])
        }

        return temp
      },

    },
    methods: {

    },

  }
</script>