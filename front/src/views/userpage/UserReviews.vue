<template>
    <v-container>
        <hr>
        <h4>
            <v-icon>{{mdiBookmarkMultiple}}</v-icon>
            리뷰 북마크
        </h4>
        <hr>
        <carousel-3d>
            <slide class="review-card" v-for="(bookmark, index) in UserBookmarks" :key="key-`${index}`" :index="index">
                <div class="review-card-inner">
                    <div class="review-front">
                        <img :src="bookmark.review.picture"
                            style="max-width: auto; max-height: auto; width: 100%; height: 100%;">
                    </div>
                    <div class="review-back">
                        <div style="text-align: center;">
                            <div class="review-content">
                                <v-chip class="ma-2" color="warning" outlined>
                                    <v-icon>{{mdiStore}}</v-icon>
                                    STORE
                                </v-chip> <br>

                                <h4 @click="storeDetail(bookmark.review.store.num)" class="store-name">
                                    {{bookmark.review.store.sname}}</h4>
                                <p>
                                    <v-chip class="ma-2" color="warning" outlined>
                                        <v-icon>{{mdiPoundBox}}</v-icon>
                                        hashtag
                                    </v-chip>

                                </p>
                                <span v-for="(h, i) in bookmark.review.hashtag.split(',').slice(0, 5)" :key="i">
                                    <span>
                                        <v-chip class="ma-2" color="warning" outlined x-small>
                                            <v-icon>{{mdiPoundBox}}</v-icon> {{h}}
                                        </v-chip>
                                    </span>

                                </span>


                            </div>
                        </div>
                    </div>

                </div>
            </slide>
        </carousel-3d>
        <hr>
        <h4>
            <v-icon>{{mdiMessageDraw}}</v-icon>
            작성한 리뷰
        </h4>
        <hr>

        <carousel-3d>
            <slide class="review-card" v-for="(review, index) in UserReviews" :key="key-`${index}`" :index="index">
                <div class="review-card-inner">
                    <div class="review-front">
                        <img :src="review.picture"
                            style="max-width: auto; max-height: auto; width: 100%; height: 100%;">
                    </div>
                    <div class="review-back">
                        <div style="text-align: center;">
                            <div class="review-content">
                                <v-chip class="ma-2" color="warning" outlined>
                                    <v-icon>{{mdiStore}}</v-icon>
                                    STORE
                                </v-chip> <br>
                                <h4 @click="storeDetail(review.store.num)" class="store-name">{{review.store.sname}}
                                </h4>

                                <!-- <v-chip
                        class="ma-2"
                        color="warning"
                        outlined
                        >
                        <v-icon>{{mdiPoundBox}}</v-icon>  HASHTAG
                        </v-chip>  -->
                                <p>
                                    <v-chip class="ma-2" color="warning" outlined>
                                        <v-icon>{{mdiPoundBox}}</v-icon>
                                        hashtag
                                    </v-chip>

                                </p>
                                <span v-for="(h, i) in review.hashtag.split(',').slice(0, 5)" :key="i+h">
                                    <span>
                                        <v-chip class="ma-2" color="warning" outlined x-small>
                                            <v-icon>{{mdiPoundBox}}</v-icon>{{h}}
                                        </v-chip>
                                    </span>

                                </span>
                                <br>
                                <!-- <v-chip
                        class="ma-2"
                        color="warning"
                        outlined
                        >
                        <v-icon>{{mdiTableOfContents}}</v-icon>
                        CONTENT 
                        </v-chip><br>
                        {{review.str}} -->
                            </div>
                        </div>
                    </div>
                </div>
            </slide>
        </carousel-3d>
    </v-container>
</template>

<script>
    import {
        Carousel3d,
        Slide
    } from 'vue-carousel-3d';
    import {
        mdiStore,
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
            storeDetail(num) {
               
                this.$store.dispatch('storeHashtags', num)
            }
        },
        created() {
           
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
        box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
    }

    .review-card:hover .review-card-inner {
        transform: rotateY(180deg);
    }

    .review-front,
    .review-back {
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

    .review-content {
        margin-top: 10px;
    }

    .store-name:hover {
        color: #ff7f00;
        font-size: 200%;
        font-weight: bold;
    }
</style>