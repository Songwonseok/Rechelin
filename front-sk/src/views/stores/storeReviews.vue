<template>
    <div>
        <v-row no-gutters>
            <v-col cols="12" sm="6" md="8" lg="8" v-for="(review, i) in storeReviewSave" :key="i" style="margin-bottom: 15px;">
                        <v-card id="reviews" class="mx-auto" max-width="344" outlined >
                            <v-list-item three-line>
                                <v-list-item-content>
                                    <div class="overline mb-4">{{$route.params.id}}</div>
                                    <v-list-item-title class="headline mb-1">{{review.nickname}}</v-list-item-title>
                                    <v-list-item-subtitle>{{review.content}}</v-list-item-subtitle>
                                </v-list-item-content>
                            </v-list-item>

                            <v-card-actions>
                                <v-btn text>좋아요 {{review.like}}</v-btn>
                                <v-btn text>싫어요 {{review.dislike}}</v-btn>
                                <v-btn text @click="reviewDetail(i)">
                                     <v-icon v-if="!visible[i]">{{icons.arrowDownDropCircle}}</v-icon>
                                     <v-icon v-else>{{icons.arrowUpDropCircle}}</v-icon>
                                </v-btn>
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
    import { mdiArrowDownDropCircle,  mdiArrowUpDropCircle } from '@mdi/js';
    export default {
        data() {
            return {
                allReviews: [],
                x: 0,
                storeReviewSave: [],
                visible: [],
                commentsList: [],
                icons: {
                    arrowDownDropCircle: mdiArrowDownDropCircle,
                    arrowUpDropCircle :  mdiArrowUpDropCircle
                }
            }
        },
        computed: {

        },
        methods: {
            reviewsScroll() {

                for (var i = 0; i < 5; i++) {
                    this.storeReviewSave.push(this.allReviews[this.x + i])
                    // dispatch, i자리에 리뷰 아이디가 들어가야함
                    this.$store.dispatch('commentsOfreview', i)
                    this.commentsList.push(this.$store.state.comments)
                    this.x = this.x + 1
                }
            },
            reviewDetail(i) {
                
            let list = [...this.visible];
            list.splice(i, 1, !list[i])
            this.visible = list;

            this.$router.push({name:'createReview', params: {
                reviewlist: 'asdfasdf'// commentsList[i]가  들어가게 될 것--db연동할떄 더 고민해보자
            }})
           
                
            }

        },
        created() {
            for (var y = 0; y < 30; y++) {
                this.allReviews.push(this.$store.state.fakeReviews[0])
            }
            this.reviewsScroll()
            for (let n=0;n<this.allReviews.length;n++) {
                this.visible.push(false)
            }

        },
        mounted() {



        }
    }
</script>