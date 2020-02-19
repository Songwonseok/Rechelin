<template>
    <div>
        <v-container class="grey lighten-5">
            <v-row no-gutters>
                <v-col cols="12" sm="6" md="6" lg="5" v-for="(review, i) in allReviews" :key="i" style="margin: 20px;">

                    <v-card id="reviews" class="mx-auto" max-width="600" outlined>
                        <v-list-item three-line>
                            <v-list-item-content>
                                <!-- 
                                <div class="overline mb-4">{{review.store.sname}}</div> -->

                                <!-- &nbsp;&nbsp;     class="headline mb-2"-->

                                <!-- <v-list-item-subtitle style=" align:left">  -->
                                <!-- <v-list-item-content> -->
                                <div id="rtitle">
                                    <span
                                        v-if="review.review.user.profile ===null || review.review.user.profile === '' ">
                                        <a><img id="profile" src="../../assets/images/ssafy.jpg" @click="$router.push(
                                            {name: 'userpage', 
                                            params: { id: review.review.user.id
                                            }})" /></a>
                                    </span>
                                    <span v-else>
                                        <a><img :src="review.review.user.profile" id="profile" @click="$router.push(
                                            {name: 'userpage', 
                                            params: { id: review.review.user.id
                                            }})" /> </a>
                                    </span>


                                    <a style="color:black; font-size:14pt;"><span @click="$router.push(
                                            {name: 'userpage', 
                                            params: { id: review.review.user.id
                                            }})">

                                            {{review.review.user.nickname}}

                                        </span></a>
                                    <v-divider></v-divider>
                                    <p><v-icon>{{icons.mdiCalendarEdit}}</v-icon>작성일자: {{review.review.wdate}}</p>

                                    <span style="float:right">

                                    </span>
                                </div>
                                <br> <br>
                                
                                <!-- 
                                    img 가져오는 순서
                                    1) Review에있는 img
                                    2) Store에있는 img
                                    3) default 이미지
                                -->
                                <div v-if="review.review.picture === null ||review.review.picture ===''" style="
                                    height:350px;width:350px;">
                                    <div v-if="review.review.store.img === null || review.review.store.img==''">
                                         <a><img src="../../assets/images/default.jpg"
                                            style="max-height:100%; max-weidth:100%; height:100% ;width:100%"
                                            @click="reviewDetail(review.review.rnum)" /></a>
                                    </div>
                                    <div v-else style="height:350px;width:350px">
                                        <a><img :src="`https://maps.googleapis.com/maps/api/place/photo?maxwidth=600&photoreference=${review.review.store.img}&sensor=true&key=AIzaSyDC4sonH281FHJ-YyPmeXLRdBYuqcjUkGE`"
                                            style="max-height:100%; max-weidth:100%;height:100% ;width:100%"
                                            @click="reviewDetail(review.review.rnum)" /></a>
                                    </div>                   
                                </div>
                                <div v-else style="height:350px;width:350px">
                                    <a><img :src="review.review.picture"
                                            style="max-height:100%; max-weidth:100%;height:100% ;width:100%"
                                            @click="reviewDetail(review.review.rnum)" /></a>
                                </div>

                                <v-list-item-title>
                                    <v-divider></v-divider>
                                    <h5> {{review.review.title}}</h5>
                                    <v-divider></v-divider>
                                </v-list-item-title>


                            </v-list-item-content>

                        </v-list-item>



                     
                            <div class="card-bottom">
                              <v-btn v-if="canReivewDelete(review.review.user.id)"
                                @click="reviewDelete(review.review.rnum)">>
                                리뷰 삭제
                            </v-btn>
                            <span  @click="reviewDetail(review.review.rnum)">
                                 <i class="far fa-thumbs-up" ></i>{{review.like}}
                                <i class="far fa-thumbs-down" ></i>{{review.dislike}}
                                <i class="far fa-comment-dots"
                                   ></i>{{review.comments}}
                                <i class="far fa-eye"></i>{{review.review.views}}
                            </span>
                            </div>
                             <!-- <v-btn outlined @click="reviewDetail(review.review.rnum)" small>
                                리뷰 보기
                            </v-btn> -->
                          


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
        mdiDelete,
        mdiCalendarEdit
    } from '@mdi/js';
    import VueStar from 'vue-star'
    export default {

        data() {
            return {
                x: 0,

                visible: [],
                commentsOfreviews: [],
                icons: {
                    arrowDownDropCircle: mdiArrowDownDropCircle,
                    arrowUpDropCircle: mdiArrowUpDropCircle,
                    Deletes: mdiDelete,
                    mdiCalendarEdit
                },
                newComment: '',

            }
        },
        computed: {
            allReviews() {
                console.log('??...')
                return this.$store.state.reviewsOfstore
            },
            defaultImage() {
                return this.$store.state.reveiwDefault
            }
        },
        watch: {

        },
        methods: {
            reviewDetail(num) {
                this.$store.dispatch('commentsOfreview', num)

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
        created() {
            console.log("리뷰 가져오기 !!")
            console.log(this.allReviews)
        },
        mounted() {
            // this.reviewsScroll();
            for (let n = 0; n < this.allReviews.length; n++) {
                this.visible.push(false)
                this.allReviews[n].review.wdate = this.allReviews[n].review.wdate.substring(0, 10);
            }
        },
        components: {
            // VueStar,
        },
    }
</script>

<style scoped>
    #reviews {
        border-radius: 10px;
    }

    #profile {
        border-radius: 100px;
        -moz-border-radius: 100px;
        -khtml-border-radius: 100px;
        -webkit-border-radius: 100px;
        width: 40px;
        height: 40px;
        margin-right: 5px;

    }

    #rtitle {
        text-align: left;
    }


    p {
        text-align: right;
        color: gray;
    }

    h5 {
        margin-top: 15px;
    }

    .v-card>:last-child:not(.v-btn):not(.v-chip) {
        text-align: center !important;
        margin-bottom: 15px !important;
    }
    svg {
        margin-right: 3px !important;
        margin-left: 3px;
    }
</style>