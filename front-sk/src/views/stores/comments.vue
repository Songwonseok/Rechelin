<template>
    <div>
        <div class="card card--big">
            <router-link :to="{name: 'userpage', params: {
                id: reviewInfo.user.id
            }}" style="color: #ff7f00 !important"> 
             <v-chip class="ma-2" color="warning" outlined>
                        <v-icon>{{mdiLeadPencil}}</v-icon>
                        작성자
                    </v-chip> {{reviewInfo.user.nickname}}</router-link>
            <b-row>

                <b-col>
                 <img src="https://cdn.pixabay.com/photo/2017/05/12/08/29/coffee-2306471_1280.jpg" alt="" style="width: 350px; height: 350px; margin-top: 50px !important;">
                   <v-btn class="mt-2" @click="reviewLike(reviewInfo.rnum)" color="warning">
                                <vue-star animate="animated bounceIn" color="#F7D358">
                                    <i   slot="icon" class="fas fa-thumbs-up fa-lg" color="warning"></i>

                                </vue-star>
                            </v-btn>
                             
                            <v-btn class="mt-2" @click="reviewLike(reviewInfo.rnum)" color="warning">
                            <vue-star animate="animated bounceIn" color="#F7D358">
                                <i slot="icon" class="fas fa-thumbs-down fa-lg"></i>
                            </vue-star>
                        </v-btn>
                   
                
                <v-btn class="mt-2"  color="warning">
                    <vue-star animate="animated bounceIn" color="#F7FE2E">
                        <i slot="icon" class="fas fa-bookmark fa-2x"></i>
                    </vue-star>
                </v-btn>
                </b-col>
                <b-col>
                    <h2 class="card__title"></h2>
                    <p class="card__subtitle"> 
                        조회수: {{reviewInfo.views}}    |    작성일 :{{reviewInfo.wdate}}
                    </p>
                    <div>
                    <v-chip class="ma-2" color="warning" outlined>
                        <v-icon>{{mdiEmoticonHappyOutline}}</v-icon>
                        장점
                    </v-chip>

                    <p class="card__text">{{reviewInfo.str}}</p>
                    <v-chip class="ma-2" color="warning" outlined>
                        <v-icon>{{mdiEmoticonSadOutline}}</v-icon>
                        단점
                    </v-chip>
                    <p class="card__text">{{reviewInfo.weak}}</p>
                    </div>
                    <v-chip class="ma-2" color="warning" outlined>
                        <v-icon>{{mdiPound}}</v-icon>
                       해쉬태그
                    </v-chip>
                     <p>  #{{reviewInfo.hashtag.split(',').join('#')}}</p>
                    <div class="card__action-bar">
                         <v-chip class="ma-2" color="warning" outlined>
                        <v-icon>{{mdiSigma}}</v-icon>
                        총점
                    </v-chip>
                        <div ref="sumRating" class="sumRating" id="rating">
                            {{reviewInfo.total}}
                        </div>
                         <v-chip class="ma-2" color="warning" outlined>
                        <v-icon>{{ mdiEmoticonTongueOutline}}</v-icon>
                        맛
                    </v-chip>
                        <div ref="tasteRating" class="tasteRating" id="rating">{{reviewInfo.taste}}</div>
                         <v-chip class="ma-2" color="warning" outlined>
                        <v-icon>{{mdiCurrencyUsd}}</v-icon>
                        가격
                    </v-chip> 
                        <div ref="priceRating" class="priceRating" id="rating">{{reviewInfo.price}}</div>
                         <v-chip class="ma-2" color="warning" outlined>
                        <v-icon>{{mdiEmoticonOutline}}</v-icon>
                        친절도
                    </v-chip>
                        <div ref="kindRating" class="kindRating" id="rating"> {{reviewInfo.kindness}}</div>

                    </div>
                </b-col>
            </b-row>
        </div>


        <div class="card">
            <v-text-field v-model="newComment" :counter="30" label="comments" required @keyup.enter="submitComment">
            </v-text-field>
            <v-btn small color="warning" @click="submitComment" style="color: #ff7f00 !important">댓글등록</v-btn>


            <b-list-group v-for="(comment, index) in comments" :key="index">
                <b-list-group-item style="text-align: left;">
                    <p><router-link :to="{name: 'userpage', params: {
                        id: comment.user.id
                    }}" style="color: black;"><strong>{{comment.user.nickname}}</strong></router-link>   {{comment.content}}</p>
                  
                    <p style="text-align: right;">  <span class="written-time">작성시간 : {{comment.wdate}}</span>
                    
                    <v-btn icon v-if="samePerson(comment.user.id)" @click="commentDelete(comment)" >
                        <v-icon color="warning">{{mdiDelete}}</v-icon>
                    </v-btn></p>
                  
                </b-list-group-item>

            </b-list-group>
        </div>




    </div>
</template>

<script>
    import {
        mdiDelete,
        mdiEmoticonHappyOutline,
        mdiEmoticonSadOutline,
        mdiPound,
        mdiSigma,
        mdiCurrencyUsd,
        mdiEmoticonTongueOutline,
        mdiEmoticonOutline,
        mdiLeadPencil
    } from '@mdi/js';
    import {normal} from '../../../public/js/animejs';

    export default {
        data() {
            return {
                newComment: '',

                comments: this.$store.state.commentsOfreview.reverse(),


                //icons
                mdiDelete,
                mdiEmoticonHappyOutline,
                mdiEmoticonSadOutline,
                mdiPound,
                mdiSigma,
                mdiCurrencyUsd,
                mdiEmoticonTongueOutline,
                mdiEmoticonOutline,
                mdiLeadPencil,
                changeLike : true,
                
            }
        },
        mounted() {
            normal(this.$refs.sumRating, this.reviewInfo.total)
            normal(this.$refs.tasteRating, this.reviewInfo.taste)
            normal(this.$refs.priceRating, this.reviewInfo.price)
            normal(this.$refs.kindRating, this.reviewInfo.kindness)
            
        },
        computed: {
            reviewInfo() {
                return this.$store.state.reviewDetail
            },
            newReturnComment() {
                return this.$store.state.newReturnComment
            }


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


            },
             reviewLike(num) {
                this.changeLike = !this.changeLike
                this.$store.dispatch('reviewLike', num)
            },
         

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
    #rating {
        background-color: #FF7F00;
        color: white !important;
    }
    .written-time {
        font-size: 13px;
        text-align: right !important;
    }
    p {
        margin-top: 3px;
        margin-bottom: 0px;
    }
</style>