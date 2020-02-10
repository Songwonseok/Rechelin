<template>
    <div>
        <v-row no-gutters>
            <v-col cols="12" sm="6" md="8" lg="8" v-for="(review, i) in allReviews" :key="i"
                style="margin-bottom: 15px;">

                <v-card id="reviews" class="mx-auto" max-width="344" outlined>
                    <v-list-item three-line>
                        <v-list-item-content>

                            <div class="overline mb-4">{{review.store.sname}}</div>
                            <v-list-item-title class="headline mb-1">{{review.user.nickname}}</v-list-item-title>
                            <v-list-item-subtitle>{{review.weak}}</v-list-item-subtitle>
                            {{review.picture}}, 제목: {{review.title}}, 조회수 : {{review.views}}, 쓴 날짜 : {{review.wdate}}
                            총점 {{review.total}}, 맛: {{review.taste}}, 가격:ㅣ {{review.taste}}, 친절| {{review.kindness}}

                            <div v-if="canReivewDelete(review.user.email)">
                                <v-btn class="ma-2" x-small outlined color="indigo" @click="reviewDelete(review.rnum)">
                                    리뷰 삭제</v-btn>
                            </div>
                        </v-list-item-content>

                    </v-list-item>

                    <v-card-actions>
                        <v-btn text @click="reviewLike">싫어요 {{review.dislike}}</v-btn>
                        <v-btn text @click="reviewDetail(review)">
                           리뷰 상세 보기
                        </v-btn>

                        <v-btn text>
                         <vue-star animate="animated bounceIn" color="#F05654">
                           <i slot="icon" class="fas fa-thumbs-up fa-lg"></i>
                         </vue-star>
                        </v-btn>

                        <v-btn text>
                        <vue-star animate="animated bounceIn" color="#F05654" >
                           <i slot="icon" class="fas fa-bookmark fa-2x"></i>
                         </vue-star>
                         </v-btn>
                    </v-card-actions>

                </v-card>
  

            </v-col>


        </v-row>
 
<!-- 
        <div v-infinite-scroll="reviewsScroll" infinite-scroll-disabled="busy" infinite-scroll-distance="10">
            로딩중
        </div> -->

                       
    </div>
</template>

<script>
    import {
        mdiArrowDownDropCircle,
        mdiArrowUpDropCircle,
        mdiDelete
    } from '@mdi/js';
    import Axios from "axios"
    import VueStar from 'vue-star'
    export default {

        data() {
            return {
                x: 0,
                storeReviewSave: [],
                visible: [],
                commentsOfreviews: [],
                icons: {
                    arrowDownDropCircle: mdiArrowDownDropCircle,
                    arrowUpDropCircle: mdiArrowUpDropCircle,
                    Deletes: mdiDelete,
                },
                newComment: ''
            }
        },
        computed: {
            allReviews() {
                return this.$store.state.reviewsOfstore
            }
        },
        methods: {
            reviewDetail(review) {
                this.$store.dispatch('commentsOfreview', review)   

            },
            canReivewDelete(user) {
                if (this.$store.state.userEmail === user) {
                    return true
                } else {
                    return false
                }
            },
            reviewDelete(rnum) {
                this.$store.dispatch('reviewDelete', rnum)
            },
            addComments(num) {
                this.$store.dispatch('commentsOfreview', num)
                return new Promise((resolve, reject) => {
                    resolve('성공')
                })
            },
            reviewLike(num) {
                this.$store.dispatch('reviewLike', num)
            },
            samePerson(p) {
                if (sessionStorage.getItem('userEmail') === p) {
                    return true
                } else {
                    return false
                }
            }

        },
        mounted() {
            this.reviewsScroll();
            for (let n = 0; n < this.allReviews.length; n++) {
                this.visible.push(false)
            }
        },
        components: {
            VueStar,
        },
    }
</script>