<template>
    <div>
        <v-row no-gutters>
            <v-col cols="12" sm="6" md="8" lg="8" v-for="(review, i) in storeReviewSave" :key="i"
                style="margin-bottom: 15px;">
                 {{review}}
                <v-card id="reviews" class="mx-auto" max-width="344" outlined>
                    <v-list-item three-line>
                        <v-list-item-content>
                           
                            
                            <!-- <div class="overline mb-4">{{review.store.sname}}</div>
                            <v-list-item-title class="headline mb-1">{{review.user.nickname}}</v-list-item-title> -->
                            <!-- <v-list-item-subtitle>{{review.weak}}</v-list-item-subtitle>
                            {{review.picture}}, 제목: {{review.title}}, 조회수 : {{review.views}}, 쓴 날짜 : {{review.wdate}}
                            총점 {{review.total}}, 맛: {{review.taste}}, 가격:ㅣ {{review.taste}}, 친절| {{review.kindness}} -->
                        </v-list-item-content>

                    </v-list-item>

                    <v-card-actions>
                        <!-- <v-btn text @click="reviewLike">좋아요 {{review.like}}</v-btn>
                        <v-btn text @click="reviewLike">싫어요 {{review.dislike}}</v-btn>
                        <v-btn text @click="reviewDetail(i)">
                            <v-icon v-if="!visible[i]">{{icons.arrowDownDropCircle}}</v-icon>
                            <v-icon v-else>{{icons.arrowUpDropCircle}}</v-icon>
                        </v-btn> -->
                    </v-card-actions>

                </v-card>
                <div v-show="visible[i]" style="margin-bottom: 10px;">
                    <router-view></router-view>
                </div>

            </v-col>


        </v-row>


        <div v-infinite-scroll="reviewsScroll" infinite-scroll-disabled="busy" infinite-scroll-distance="10">
            로딩중
        </div>

    </div>
</template>

<script>
    import {
        mdiArrowDownDropCircle,
        mdiArrowUpDropCircle
    } from '@mdi/js';

    export default {
        data() {
            return {
                allReviews: this.$route.query.reviewsOfstore,
                x: 0,
                storeReviewSave: [],
                visible: [],
                icons: {
                    arrowDownDropCircle: mdiArrowDownDropCircle,
                    arrowUpDropCircle: mdiArrowUpDropCircle
                }
            }
        },
        computed: {

        },
        methods: {
            reviewsScroll() {
                for (var i = 0; i < this.allReviews.length; i++) {
                    this.storeReviewSave.push(this.allReviews[this.x + i])
                    // dispatch, i자리에 리뷰 아이디가 들어가야함
                    this.x = this.x + 1
                }
            },
            reviewDetail(i) {

                let list = [...this.visible];
                list.splice(i, 1, !list[i])
                this.visible = list;
                // this.$store.dispatch('commentsOfreview', i)
            },
            reviewLike() {
                // var payload = {
                //     user: this.$store.state.userEmail,
                //     review: 'review.id',

                // }

                // this.$store.dispatch('reviewLike', payload)
            },

        },
        mounted() {
            this.reviewsScroll();
            for (let n = 0; n < this.allReviews.length; n++) {
                this.visible.push(false)
            }
           

        },
        created() {

            


       
        }
        

    }
</script>