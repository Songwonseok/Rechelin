<template>
    <div>
        <v-row no-gutters>
            <v-col cols="12" sm="6" md="8" lg="8" v-for="(review, i) in storeReviewSave" :key="i"
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
                        <v-btn text @click="reviewLike">좋아요 {{review.like}}</v-btn>
                        <v-btn text @click="reviewLike">싫어요 {{review.dislike}}</v-btn>

                        <v-btn text @click="reviewDetail(i, review.rnum)">
                            <v-icon v-if="!visible[i]">{{icons.arrowDownDropCircle}}</v-icon>
                            <v-icon v-else>{{icons.arrowUpDropCircle}}</v-icon>
                        </v-btn>
                    </v-card-actions>

                </v-card>
                <div v-if="visible[i]" style="margin-bottom: 10px;">
                    <b-modal v-model="visible[i]">
                        <b-form-textarea id="textarea" v-model="newComment" placeholder="Enter something..." rows="3"
                            max-rows="6" @keyup.enter="submitComment(newComment)"></b-form-textarea>
                        <div class="my-2">
                            <v-btn class="ma-2" text outlined small color="primary" @click="submitComment(newComment)">
                                댓글등록
                            </v-btn>
                        </div>
                        {{reviewDetailInfo}}
                        <hr>
                        {{commentsDetail}}
                        <b-list-group v-for="(comment, index) in commentsDetail" :key="index">
                            <b-list-group-item>{{ comment }} 
                                <v-btn text icon color="pink" v-if="samePerson(comment.user.email)">
                                    <v-icon>{{Deletes}}</v-icon>
                                </v-btn>
                            </b-list-group-item>
                        </b-list-group>
                    </b-modal>

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
        mdiArrowUpDropCircle,
        mdiDelete
    } from '@mdi/js';
    import Axios from "axios"

    export default {

        data() {
            return {
                allReviews: this.$store.state.reviewsOfstore,
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
            reviewDetailInfo() {
                return this.$store.state.reviewDetail
            },
            commentsDetail() {
                return this.$store.state.commentsOfreview
            }
        },
        methods: {
            reviewsScroll() {
                var i = 0
                while (i <= 5) {
                    if (this.allReviews.length > 0) {
                        var temp = this.allReviews.shift();
                        this.storeReviewSave.push(temp);
                        Axios.get(`http://70.12.246.134:8080/review/comment/${temp.rnum}`)
                            .then(res => {
                                var data = {
                                    reviewID: temp.rnum,
                                    comments: res.data.object
                                }
                                this.commentsOfreviews.push(data.comments)

                            }).catch(exp => {
                                console.log('실패')
                            })

                        i++;
                    } else {
                        break
                    }
                }

            },
            reviewDetail(i, num) {

                let list = [...this.visible];
                list.splice(i, 1, !list[i])
                this.visible = list;
                this.$store.dispatch('reviewDetail', num)

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
                if (sessionStorage.getItem('userEmail')===p) {
                    return true
                } else {
                    return false
                }
            }

        },
        mounted() {

        },
        created() {
            this.reviewsScroll();
            for (let n = 0; n < this.allReviews.length; n++) {
                this.visible.push(false)
            }
        }

    }
</script>