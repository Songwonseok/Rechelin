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
                                <div id="rtitle" >
                                    <span v-if="review.review.user.profile ===null || review.review.user.profile === '' ">
                                       <a><img id ="profile" src="../../assets/images/ssafy.jpg" 
                                        @click = "$router.push(
                                            {name: 'userpage', 
                                            params: { id: review.review.user.id
                                            }})"/></a> 
                                     </span>
                                    <span v-else>
                                        <a><img :src="review.review.user.profile" id ="profile" 
                                         @click = "$router.push(
                                            {name: 'userpage', 
                                            params: { id: review.review.user.id
                                            }})"/> </a>  
                                    </span>
                                    

                                  <a style="color:black; font-size:14pt;"><span  @click = "$router.push(
                                            {name: 'userpage', 
                                            params: { id: review.review.user.id
                                            }})">
                                      
                                      {{review.review.user.nickname}}</span></a>

                                      <span style="float:right">
                                        
                                    </span>
                                </div>
                                    <br> <br>

                                

                                    <div v-if="review.review.picture === null ||review.review.picture ===''"  style="height:350px;width:350px;">
                                        <a><img src="../../assets/images/default.jpg" 
                                        style="max-height:100%; max-weidth:100%; height:100% ;width:100%" 
                                        @click="reviewDetail(review.review.rnum)"/></a>
                                    </div>
                                    <div v-else style="height:350px;width:350px">
                                        <a><img :src="review.review.picture"  
                                        style="max-height:100%; max-weidth:100%;height:100% ;width:100%" 
                                        @click="reviewDetail(review.review.rnum)"/></a>
                                    </div>

                                     <v-list-item-title> 
                                      <span style="font-size:20pt;float:left;">
                                          {{review.review.title}}
                                          </span> 
                                       <span style="float:right; margin-top:5px;">
                                    <i class="far fa-thumbs-up" style="margin-left:10px !important"></i>{{review.like}}
                                    <i class="far fa-thumbs-down" style="margin-left:10px !important"></i>{{review.dislike}}
                                    <i class="far fa-comment-dots" style="margin-left:10px !important"></i>{{review.comments}}
                                    <i class="far fa-eye" style="margin-left:10px !important"></i>{{review.review.views}}
                                </span>
                                   </v-list-item-title>
                                   
                                   
                                
                                
                               
                                <!-- 좋아요 : , 싫어요 : , 조회수 : , 댓글 수 : 
                                총점 {{review.total}}.0
                                &nbsp;
                                조회수  {{review.views}}
                               {{review.wdate}}    -->
                                

                                
                            </v-list-item-content>

                        </v-list-item>

                        

                            <!-- <v-btn class="btn">
                                <vue-star animate="animated bounceIn" color="#F7D358">
                                    <i slot="icon" class="fas fa-thumbs-up fa-lg"></i>
                                </vue-star>
                            </v-btn>
                             
                             <v-btn class="btn">
                                <vue-star animate="animated bounceIn" color="#F7D358">
                                    <i slot="icon" class="fas fa-thumbs-down fa-lg"></i>
                                </vue-star>
                            </v-btn> -->
                            <v-card-actions class ="btnTap">
                            <v-btn class="btn" @click="reviewDetail(review.review.rnum)" >
                                리뷰 보기
                            </v-btn>
                            <v-btn class="btn">
                                <vue-star animate="animated bounceIn" color="#F7FE2E">
                                    
                                    <i slot="icon" class="fas fa-bookmark fa-2x"></i>
                                </vue-star>
                            </v-btn>

                            <v-btn class="btn" v-if="canReivewDelete(review.review.user.id)"
                             @click="reviewDelete(review.review.rnum)">>
                                리뷰 삭제 
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

        },
        mounted() {
            // this.reviewsScroll();
            for (let n = 0; n < this.allReviews.length; n++) {
                this.visible.push(false)
            }
        },
        components: {
            // VueStar,
        },
    }
</script>

<style scoped>

.btnTap {
    display: flow-root !important;
}
#profile{

    border-radius: 100px;
    -moz-border-radius: 100px;
    -khtml-border-radius: 100px;
    -webkit-border-radius: 100px;
     width: 40px;
     height:40px;
     margin-right:5px;

}
#rtitle{
    text-align: left;
}

.btnTap{
 text-align: right  !important;
}
.btnTap .btn {
    background-color: #FF7F00  !important;
    padding: 500px;
    
}


</style>