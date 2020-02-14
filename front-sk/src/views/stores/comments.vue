<template>
    <div>
        <div class="card card--big">
            <router-link :to="{name: 'userpage', params: {
                id: reviewInfo.user.id
            }}"> 작성자 : {{reviewInfo.user.nickname}}</router-link>
            <b-row>

                <b-col>
                    <div v-if="reviewInfo.picture" class="card__image">
                        <b-carousel id="carousel-1" v-model="slide" :interval="4000" controls indicators
                            background="#ababab" img-width="300" img-height="480"
                            style="text-shadow: 1px 1px 2px #333;">
                            <!-- Text slides with image -->
                            <b-carousel-slide v-for="(picture, index) in reviewInfo.picture" :key="index"
                                :img-src="picture"></b-carousel-slide>
                        </b-carousel>

                    </div>
                </b-col>
                <b-col>
                    <h2 class="card__title"></h2>
                    <span class="card__subtitle"> 조회수: {{reviewInfo.views}} | 작성일 :
                        {{reviewInfo.wdate}}
                    </span>
                    <p>장점</p>
                    <p class="card__text">{{reviewInfo.str}}</p>
                    <p>단점</p>
                    <p class="card__text">단점 : {{reviewInfo.weak}}</p>
                    해쉬태그 : {{reviewInfo.hashtag}}
                    <div class="card__action-bar">
                        <h6>총점 ; {{reviewInfo.total}}</h6>
                        <h6>맛 ; {{reviewInfo.taste}}</h6>
                        <h6>가격 ; {{reviewInfo.price}}</h6>
                        <h6>친절도 ; {{reviewInfo.kindness}}</h6>

                    </div>
                </b-col>
            </b-row>
        </div>


        <div class="card">
            <v-text-field v-model="newComment" :counter="30" label="comments" required @keyup.enter="submitComment">
            </v-text-field>
            <v-btn text small @click="submitComment">댓글등록</v-btn>


            <b-list-group v-for="(comment, index) in comments" :key="index">
                <b-list-group-item>
                    <strong>{{comment.user.id.nickname}}</strong> <br> {{comment.content}}
                    <v-btn text icon v-if="samePerson(comment.user.id)" @click="commentDelete(comment)">
                        <v-icon>{{mdiDelete}}</v-icon>
                    </v-btn>
                </b-list-group-item>

            </b-list-group>
        </div>




    </div>
</template>

<script>
    import {
        mdiDelete
    } from '@mdi/js';
    import Vue from 'vue'
    Vue.nextTick();

    export default {
        data() {
            return {
                newComment: '',
                //icons
                mdiDelete,
                comments: this.$store.state.commentsOfreview.reverse()
            }
        },
        computed: {
            reviewInfo() {
                return this.$store.state.reviewInfo
            },
            newReturnComment() {
                return this.$store.state.newReturnComment
            }
            // comments() {
            //     return this.$store.state.commentsOfreview
            // }

        },
        watch: {
            newReturnComment: function () {
                this.comments.unshift(this.newReturnComment);
                this.newComment = '';
            }
        },
        methods: {
            submitComment() {
                if (this.newComment < 3) {
                    alert('댓글은 최소 3글자 이상 작성해주세요')
                    return false
                }
                var data = {
                    'content': this.newComment,
                    'user': {
                        'id': sessionStorage.getItem("userid")
                    },
                    'review': {
                        'rnum': this.reviewInfo.rnum
                    },
                }
                this.$store.dispatch('createComment', data)

                this.$nextTick((res) => {

                })



            },
            samePerson(num) {

                if (sessionStorage.getItem('userid') == num) {

                    return true
                } else {
                    return false
                }

            },
            commentDelete(comment) {
                this.$store.dispatch('commentDelete', comment).then((result) => {
                    this.$forceUpdate()
                })
                let list = [...this.comments]
                let pos = list.indexOf(comment)
                list.splice(pos, 1)
                this.comments = [...list]


            }

        },

    }
</script>

<style scoped>
    .card {
        padding: 16px;
        margin: 24px;
        border-radius: 2px;
        background-color: #fff;
        color: rgba(0, 0, 0, 0.87);
        box-shadow:
            0 2px 2px 0 rgba(0, 0, 0, 0.14),
            0 1px 5px 0 rgba(0, 0, 0, 0.12),
            0 3px 1px -2px rgba(0, 0, 0, 0.2);
    }
</style>