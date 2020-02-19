<template>
  <div>
    <div class="card card--big">
      <router-link :to="{name: 'userpage', params: {
                id: reviewInfo.user.id
            }}" style="color: #ff7f00 !important">
        <v-chip class="ma-2" color="warning" outlined>
          <v-icon>{{mdiLeadPencil}}</v-icon>
          작성자
        </v-chip> {{reviewInfo.user.nickname}}
      </router-link>
      <b-row>

        <b-col style="padding-bottom: 0px;">
          <div v-if="reviewInfo.picture">
            <img class="review-image" :src="reviewInfo.picture" alt="" style="width: 80%; height: 350px;">
          </div>
          <div v-else>
            <img src="../../assets/images/default.jpg" alt="" style="width: 80%; height: 350px;">
          </div>
          <!-- 좋아요 -->
          <v-divider style="margin-top: 30px;"></v-divider>
          <div style="margin-top: 10px;">

            <v-btn small fab class="mt-2 review-like" @click="reviewLike(reviewInfo.rnum, 1)" color="warning">
              <vue-star animate="animated bounceIn" color="#F7D358">
                <i slot="icon" class="fas fa-thumbs-up fa-lg"></i>
              </vue-star>
            </v-btn>

            <v-btn small fab class="mt-2 review-like" @click="reviewLike(reviewInfo.rnum, 0)" color="warning">
              <vue-star animate="animated bounceIn" color="#F7D358">

                <i slot="icon" class="fas fa-thumbs-down fa-lg"></i>
              </vue-star>
            </v-btn>


            <v-btn fab small class="mt-2 review-like" color="warning" @click="reviewBookmark(reviewInfo.rnum)">
              <vue-star animate="animated bounceIn" color="#F7FE2E">
                <i slot="icon" class="fas fa-bookmark fa-2x"></i>
              </vue-star>
            </v-btn>

            <v-divider></v-divider>

          </div>

        </b-col>
        <b-col style="padding-bottom: 0px;">
          <h2 class="card__title"></h2>
          <div>
            <v-chip class="ma-2" color="warning" outlined>
              <v-icon>{{mdiCalendarEdit}}</v-icon>
              작성시간
            </v-chip>
            <p class="card__text">{{reviewInfo.wdate}}</p>
            <v-chip class="ma-2" color="warning" outlined>
              <v-icon>{{mdiEye}}</v-icon>
              조회수
            </v-chip>
            <p class="card__text">{{reviewInfo.views}}</p>
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

          <p> #{{reviewInfo.hashtag.split(',').join('#')}}</p>
          <div v-if="samePerson(reviewInfo.user.id)">
            <v-btn icon @click="deleteReview(reviewInfo.rnum)">
              <v-icon color="warning">{{mdiDelete}}</v-icon>
            </v-btn>
          </div>
          <v-divider>공유하기</v-divider>
          <v-btn text icon fab @click="Nshare">
            <v-icon color="success" large>{{mdiAlphaNCircle}}</v-icon>
          </v-btn>
          <v-btn text icon fab @click="Fshare">
            <v-icon color="indigo" large>{{mdiFacebook }}</v-icon>
          </v-btn>
          <v-divider></v-divider>

        </b-col>
      </b-row>
      <b-row>
        <b-col lg="6" style="padding-top: 0px;">

          <v-chip id="score" class="ma-2" color="warning" outlined>
            <v-icon>{{mdiSigma}}</v-icon>
            총점
          </v-chip>
          <figure class="chart" :data-percent="String(reviewInfo.total*20)">
            <img class="all">
            <svg width="200" height="200">
              <circle class="outer" cx="95" cy="95" r="85" transform="rotate(-90, 95, 95)" />
            </svg>
          </figure>

        </b-col>
        <b-col lg="6" style="padding-top: 0px;">

          <v-chip class="ma-2" color="warning" outlined>
            <v-icon>{{ mdiEmoticonTongueOutline}}</v-icon>
            맛
          </v-chip>
          <v-rating background-color="orange lighten-3" color="orange" large readonly v-model="reviewInfo.taste">
          </v-rating>


          <v-chip class="ma-2" color="warning" outlined>
            <v-icon>{{mdiCurrencyUsd}}</v-icon>
            가격
          </v-chip>

          <v-rating background-color="orange lighten-3" color="orange" large readonly v-model="reviewInfo.price">
          </v-rating>

          <v-chip class="ma-2" color="warning" outlined>
            <v-icon>{{mdiEmoticonOutline}}</v-icon>
            친절도
          </v-chip>
          <v-rating background-color="orange lighten-3" color="orange" large readonly v-model="reviewInfo.kindness">
          </v-rating>

        </b-col>
      </b-row>

    </div>


    <div class="card">
      <v-text-field v-model="newComment" :counter="30" label="comments" required @keyup.enter="submitComment">
      </v-text-field>
      <v-btn small color="warning" @click="submitComment" style="color: #ff7f00 !important">댓글등록</v-btn>


      <b-list-group v-for="(comment, index) in comments" :key="index">
        <b-list-group-item style="text-align: left;">
          <p>
            <router-link :to="{name: 'userpage', params: {
                        id: comment.user.id
                    }}" style="color: black;"><strong>{{comment.user.nickname}}</strong></router-link>
            {{comment.content}}
          </p>

          <p style="text-align: right;"> <span class="written-time">작성시간 : {{comment.wdate}}</span>

            <v-btn icon v-if="samePerson(comment.user.id)" @click="commentDelete(comment)">
              <v-icon color="warning">{{mdiDelete}}</v-icon>
            </v-btn>
          </p>

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
    mdiLeadPencil,
    mdiThumbUp,
    mdiFacebook,
    mdiAlphaNCircle,
    mdiCalendarEdit,
    mdiEye
  } from '@mdi/js';
  import Axios from 'axios'

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
        mdiThumbUp,
        mdiFacebook,
        mdiAlphaNCircle,
        mdiCalendarEdit,
        mdiEye,

        changeLike: true,

      }
    },
    mounted() {
      for (var i = 0; i < this.comments.length; i++) {
        this.comments[i].wdate = this.comments[i].wdate.substring(0, 10);
      }

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
        this.comments.unshift(this.newReturnComment.object);

        this.newComment = '';
      }
    },
    methods: {
      submitComment() {
        if (this.newComment < 3) {
          alert('댓글은 최소 3글자 이상 작성해주세요'+this.newComment)
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

      },
      samePerson(num) {

        if (sessionStorage.getItem('userid') == num) {

          return true
        } else {
          return false
        }

      },
      Nshare() {
        var url = encodeURI(encodeURIComponent(window.location.href));
        var title = encodeURI(this.reviewInfo.title);
        var shareURL = "https://share.naver.com/web/shareView.nhn?url=" + url + "&title=" + title;
        Axios.get(shareURL)
          .then(res => {
            console.log(res)
          })
        document.location = shareURL;

      },
      Fshare() {
        var url = encodeURI(encodeURIComponent(window.location.href));
        var title = encodeURI(this.reviewInfo.title);
        var shareURL = "https://www.facebook.com/sharer/sharer.php?u=https" + url + "&title=" + title;
        Axios.get(shareURL)
          .then(res => {
            console.log(res)
          })
        document.location = shareURL;
      },
      commentDelete(comment) {
        this.$store.dispatch('commentDelete', comment.num).then((result) => {
          this.$forceUpdate()
        })
        let list = [...this.comments]
        let pos = list.indexOf(comment)
        list.splice(pos, 1)
        this.comments = [...list]


      },
      reviewLike(num, status) {
        this.changeLike = !this.changeLike
        let payload = {
          num: num,
          status: status
        }
        this.$store.dispatch('reviewLike', payload)
      },
      reviewBookmark(num) {
        this.$store.dispatch('reviewBookmark', num)
      },
      deleteReview(num) {
        this.$store.dispatch('reviewDelete', num)
      }


    },
    created() {
      this.reviewInfo.wdate = this.reviewInfo.wdate.substring(0, 10);

    }

  }
</script>

<style scoped>
  #score {
    position: absolute;
    top: 40%;
    right: 42%;
  }

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

  .review-image {
    width: 350px;
    height: 350px;
    margin-top: 50px !important;
    border-radius: 10px;
  }

  .review-like {
    margin-right: 5px;
    margin-left: 5px;
  }

  /* Import the Google Font 'Lato' */
  /* @import url(https://fonts.googleapis.com/css?family=Lato:300, 400, 700); */



  /* END Container styles */

  /* Colors for the circles and positions for the graphics */
  .all {}

  .all+svg .outer {
    stroke: #e34f26;
  }

  .taste {}

  .taste+svg .outer {
    stroke: #0d84ce;
  }

  .price {
    max-width: 90px;
    max-height: 90px;

  }

  .price+svg .outer {
    stroke: #f0e040;
  }

  .kind {}

  .kind+svg .outer {
    stroke: #83cd29 !important;
  }

  .chart svg {
    position: relative;
    top: 15%;
    left: 0;
  }

  .outer {
    fill: transparent;
    stroke: #333;
    stroke-width: 20;
    stroke-dasharray: 534;
    transition: stroke-dashoffset 1s;
    -webkit-animation-play-state: running;
    /* firefox bug fix - won't rotate at 90deg angles */
    -moz-transform: rotate(-89deg) translateX(-190px);
  }

  .chart {
    display: inline;
  }

  .chart:hover .outer {
    stroke-dashoffset: 534 !important;
    -webkit-animation-play-state: paused;
  }

  /* END Circle colors and graphic positions */

  /* Set the initial values for the animation */
  .chart[data-percent="100"] .outer {
    stroke-dashoffset: 0;
    -webkit-animation: show100 2s;
    animation: show100 2s;
  }

  .chart[data-percent="80"] .outer {
    stroke-dashoffset: 107;
    -webkit-animation: show80 2s;
    animation: show80 2s;
  }

  .chart[data-percent="60"] .outer {
    stroke-dashoffset: 214;
    -webkit-animation: show60 2s;
    animation: show60 2s;
  }

  .chart[data-percent="40"] .outer {
    stroke-dashoffset: 322;
    -webkit-animation: show40 2s;
    animation: show40 2s;
  }

  .chart[data-percent="20"] .outer {
    stroke-dashoffset: 430;
    -webkit-animation: show20 2s;
    animation: show20 2s;
  }

  .chart[data-percent="0"] .outer {
    stroke-dashoffset: 530;
    -webkit-animation: show0 2s;
    animation: show0 2s;
  }

  /* END set initial animation values */

  /* Keyframes for the initial animation */
  @-webkit-keyframes show100 {
    from {
      stroke-dashoffset: 537;
    }

    to {
      stroke-dashoffset: 0;
    }
  }

  @keyframes show100 {
    from {
      stroke-dashoffset: 537;
    }

    to {
      stroke-dashoffset: 0;
    }
  }

  @-webkit-keyframes show80 {
    from {
      stroke-dashoffset: 537;
    }

    to {
      stroke-dashoffset: 107;
    }
  }

  @keyframes show80 {
    from {
      stroke-dashoffset: 537;
    }

    to {
      stroke-dashoffset: 107;
    }
  }

  @-webkit-keyframes show60 {
    from {
      stroke-dashoffset: 537;
    }

    to {
      stroke-dashoffset: 214;
    }
  }

  @keyframes show60 {
    from {
      stroke-dashoffset: 537;
    }

    to {
      stroke-dashoffset: 214;
    }
  }

  @-webkit-keyframes show40 {
    from {
      stroke-dashoffset: 537;
    }

    to {
      stroke-dashoffset: 322;
    }
  }

  @keyframes show40 {
    from {
      stroke-dashoffset: 537;
    }

    to {
      stroke-dashoffset: 322;
    }
  }


  @-webkit-keyframes show20 {
    from {
      stroke-dashoffset: 537;
    }

    to {
      stroke-dashoffset: 430;
    }
  }

  @keyframes show20 {
    from {
      stroke-dashoffset: 537;
    }

    to {
      stroke-dashoffset: 430;
    }
  }

  /* END Keyframes for the initial animation */
  @-webkit-keyframes show0 {
    from {
      stroke-dashoffset: 537;
    }

    to {
      stroke-dashoffset: 537;
    }
  }

  @keyframes show0 {
    from {
      stroke-dashoffset: 537;
    }

    to {
      stroke-dashoffset: 537;
    }
  }
</style>