<template>
    <div>
        <v-container class="grey lighten-5" >
            <v-row no-gutters>
            <v-col cols="12" sm="6" md="6" lg="5" v-for="(review, i) in allReviews" :key="i"
                style="margin: 20px;">

                <v-card id="reviews" class="mx-auto" max-width="600" outlined>
                    <v-list-item three-line>
                        <v-list-item-content>

                            <div class="overline mb-4">{{review.store.sname}}</div>
                            <v-list-item-title class="headline mb-1">  {{review.title}}</v-list-item-title>
                            <div  v-if ="review.picture === null  || reveiw.picture ===''">
                                <img src="https://upload.wikimedia.org/wikipedia/en/6/60/No_Picture.jpg" style="height: 300px;width: 400px">  
                            </div>
                            <div v-else>
                                <img :src="review.picture" style="height: 300px">   
                            </div>

                             글쓴이: {{review.user.nickname}}
                             <br>총점 {{review.total}}
                             <br>
                             조회수 : {{review.views}}
                              <br>{{review.wdate}}

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
            </v-container>

        
        
 
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
                newComment: '',
                
            }
        },
        computed: {
            allReviews() {
                return this.$store.state.reviewsOfstore
            },
            defaultImage(){
                return this.$store.state.reveiwDefault
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

<style scoped>




</style>