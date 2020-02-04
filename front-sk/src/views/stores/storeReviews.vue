<template>
    <div>
        <div v-for="(review, i) in storeReviewSave" :key="i" id="reviews">
            <v-card class="mx-auto" max-width="344" outlined>
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
                </v-card-actions>
            </v-card>
        </div>
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
        },
        created() {
            for (var y = 0; y < 100; y++) {
                this.allReviews.push(this.$store.state.fakeReviews[0])
            }
            this.reviewsScroll()

        },
        mounted() {
          
        }
    }
</script>