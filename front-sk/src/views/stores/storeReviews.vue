<template>
    <div>
        <v-row no-gutters>
            <v-col cols="12" sm="6" md="8" lg="12">
                <v-row no-gutters>
                    <v-col cols="12" sm="12" md="12" lg="6" v-for="(review, i) in storeReviewSave" :key="i">
                        <v-card id="reviews" class="mx-auto" max-width="344" outlined>
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
                                <b-button @click="reviewDetail(i)">
                                </b-button>
                            </v-card-actions>
                           
                        </v-card>
                        <div v-show="visible[i]">
                         <router-view></router-view>
                        </div>
                    </v-col>

                </v-row>

            </v-col>


        </v-row>


        <div v-infinite-scroll="reviewsScroll" infinite-scroll-disabled="busy" infinite-scroll-distance="10">
            로딩중
        </div>

    </div>
</template>

<script>
    export default {
        data() {
            return {
                allReviews: [],
                x: 0,
                storeReviewSave: [],
                visible: [],
            }
        },
        computed: {

        },
        methods: {
            reviewsScroll() {

                for (var i = 0; i < 5; i++) {
                    this.storeReviewSave.push(this.allReviews[this.x + i])
                    this.x = this.x + 1
                }
            },
            reviewDetail(i) {
                
            let list = [...this.visible];
            list.splice(i, 1, true)
            this.visible = list;
            this.$router.push({name:'createReview'})

                
            }

        },
        created() {
            for (var y = 0; y < 30; y++) {
                this.allReviews.push(this.$store.state.fakeReviews[0])
            }
            this.reviewsScroll()
            for (let i = 0; i < this.allReviews.length; i++) {
                this.visible.push(false)
            }
            console.log(this.visible)
            for (let n=0;n<this.allReviews.length;n++) {
                this.visible.push(false)
            }

        },
        mounted() {



        }
    }
</script>