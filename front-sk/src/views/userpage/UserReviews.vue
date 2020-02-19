<template>
    <div>
        <hr>
        <h3>
            <v-icon>{{mdiBookmarkMultiple}}</v-icon>
            음식점 북마크</h3>
        <hr>
        <carousel-3d>
            <slide class="review-card" v-for="(bookmark, index) in UserBookmarks" :key="key-`${index}`" :index="index">
                 <div class="review-card-inner">
                    <div class="review-front">
                <img :src="bookmark.picture">
                   </div>
                <div class="review-back">
                    <div style="text-align: center;">
                        <div class="review-content">
                            <v-chip
                        class="ma-2"
                        color="warning"
                        outlined
                        >
                        <v-icon>{{mdiStore}}</v-icon>
                        STORE
                        </v-chip> <br>

                        <h4 @click="storeDetail(bookmark.store.num)" class="store-name">{{bookmark.store.sname}}</h4>
                        
                         <v-chip
                        class="ma-2"
                        color="warning"
                        outlined
                        >
                        <v-icon>{{mdiPoundBox}}</v-icon>  HASHTAG
                        </v-chip> 
                         <h6>
                        #{{bookmark.hashtag.split(' ').join('#')}}
                        </h6>

                         <v-chip
                        class="ma-2"
                        color="warning"
                        outlined
                        >
                        <v-icon>{{mdiTableOfContents}}</v-icon>
                        CONTENT 
                        </v-chip><br>
                        {{bookmark}}
                        </div>
                        </div>
                    </div>
             
                 </div>
            </slide>
        </carousel-3d>
        <hr>
        <h3>
            <v-icon>{{mdiMessageDraw}}</v-icon>
            작성한 리뷰</h3>
        <hr>

        <carousel-3d>
            <slide class="review-card" v-for="(review, index) in UserReviews" :key="key-`${index}`" :index="index">
                    <div class="review-card-inner">
                    <div class="review-front">
                <img :src="review.picture" >
                </div>
                <div class="review-back">
                    <div style="text-align: center;">
                        <div class="review-content">
                        <v-chip
                        class="ma-2"
                        color="warning"
                        outlined
                        >
                        <v-icon>{{mdiStore}}</v-icon>
                        STORE
                        </v-chip> <br>
                        <h4 @click="storeDetail(review.store.num)" class="store-name">{{review.store.sname}}</h4>
                        
                         <v-chip
                        class="ma-2"
                        color="warning"
                        outlined
                        >
                        <v-icon>{{mdiPoundBox}}</v-icon>  HASHTAG
                        </v-chip> 
                         <h6>
                        #{{review.hashtag.split('').join('#')}}
                        </h6>

                         <v-chip
                        class="ma-2"
                        color="warning"
                        outlined
                        >
                        <v-icon>{{mdiTableOfContents}}</v-icon>
                        CONTENT 
                        </v-chip><br>
                        {{review.str}}
                        </div>
                        </div>
                    </div>
                </div>
            </slide>
        </carousel-3d>
    </div>
</template>

<script>
    import {
        Carousel3d,
        Slide
    } from 'vue-carousel-3d';
    import { mdiStore, 
    mdiPoundBox,
    mdiTableOfContents,
    mdiBookmarkMultiple,
    mdiMessageDraw 
    } from '@mdi/js';

    export default {
        components: {
            Carousel3d,
            Slide
        },
        data() {
            return {
                mdiStore,
                mdiPoundBox,
                mdiTableOfContents,
                mdiBookmarkMultiple,
                mdiMessageDraw
            }
        },
        computed: {
            UserBookmarks() {
                return this.$route.params.bookmarks
            },
            UserReviews() {
                return this.$route.params.reviews
            }
        },
        methods: {
            storeDetail (num) {
                console.log(num)
                this.$store.dispatch('storeHashtags', num)
            }
        },
        created() {
            console.log('hh')
            console.log(this.UserReviews)
        }
    }
</script>

<style scoped>

.review-card {
  background-color: transparent;
  width: 100%;
  height: 100%;
  perspective: 1000px;
}

.review-card-inner {
  position: relative;
  width: 100%;
  height: 100%;
  text-align: center;
  transition: transform 0.6s;
  transform-style: preserve-3d;
  box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
}

.review-card:hover .review-card-inner {
  transform: rotateY(180deg);
}

.review-front, .review-back {
  position: absolute;
  width: 100%;
  height: 100%;
  -webkit-backface-visibility: hidden;
  backface-visibility: hidden;
}

.review-front {
  background-color: #bbb;
  color: black;
}

.review-back {
  background-color: white;
  color: #ff7f00;
  transform: rotateY(180deg);
}
.review-content{
    margin-top: 10px;
}
.store-name:hover {
    color: black;
    font-size: 200%;
    font-weight: bold;
}
</style>