<template>
  <div>
    <div class="tweet" style="border-radius: 10px; margin-bottom: 20px; width : 800px">
      <div class="box">
        <article class="media">
          <div class="media-left">
            <figure class="image">
              <!-- 사진 누르면 프로필 넘어가도록-->
              <v-list-item
                router-link
                :to="{name: 'userpage', params : {id: tweet.review.user.id}}"
              >
                <v-list-item-content>
                  <template v-if="tweet.review.user.profile == null">
                    <img
                      src="https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_960_720.png"
                    />
                  </template>
                  <template v-else>
                    <img :src="tweet.review.user.profile" />
                  </template>
                </v-list-item-content>
              </v-list-item>
            </figure>
            <router-link
              :to="{name: 'userpage', params: {
                id: tweet.review.user.id
            }}"
              style="color: #ff7f00 !important"
            >
              <v-chip class="ma-2" color="warning" outlined>
                작성자
              </v-chip>
              {{tweet.review.user.nickname}}
            </router-link>
            <div class="media-content">
              <!--TODO : 별로 표시하기 -->
              <p>총점 :  <star-rating read-only	star-size="30" @rating-selected="rating = $event" :rating="tweet.review.total"></star-rating></p>
              <p>맛 : <star-rating read-only	star-size="30" @rating-selected="rating = $event" :rating="tweet.review.taste"></star-rating></p>
              <p>가격 : <star-rating read-only	star-size="30" @rating-selected="rating = $event" :rating="tweet.review.price"></star-rating></p>
              <p>친절함 : <star-rating read-only	star-size="30" @rating-selected="rating = $event" :rating="tweet.review.kindness"></star-rating></p>
            </div>
          </div>
          <div class="media-content" style="margin-top : 80px; margin-left : 30px">
            <div class="content">
              <!-- <router-link 
                :to="{name: 'comments', params: {
                id: tweet.review.store.num}}"
              style="color: #ff7f00 !important"> -->
              <strong  @click="storeDetail(tweet.review.store.num)">
                <v-chip class="ma-2" color="warning" outlined>
                <i class="far fa-clock fa-2x" style="margin-right : 10px"></i>
                {{this.tweet.review.wdate}}  
                </v-chip>
                <br>
                <v-chip class="ma-2" color="warning" outlined>
                  <i class="fas fa-store-alt fa-2x" style="margin-right : 10px" ></i>
                {{tweet.review.store.sname}}   
                </v-chip>
                <br>
                <v-chip class="ma-2" color="warning" outlined>
                 <i class="fas fa-map-marker-alt fa-2x" style="margin-right : 10px"></i>
                {{tweet.review.store.address}}
                </v-chip>
              </strong>
              <!-- </router-link> -->
              <!-- title 누르면 review detail로 넘어가게 -->
              <!-- storedetail/62/comments -->
             <!-- <router-link :to="{name: 'comments', params: {
                id: tweet.review.rnum}}"
              style="color: #ff7f00 !important"> -->
             <br>
           <v-chip class="ma-2" color="warning" outlined>
            
             <span class="iconify" data-icon="subway:title"  style="margin-right : 10px" data-inline="false"></span>
                {{tweet.review.title}}
                  
            </v-chip>

              <!-- </router-link> -->
              <!-- TODO : 장점과 단점을 분리 -->
             <br>
               <v-chip class="ma-2" color="warning" outlined>
                  <p style="margin-top: 10px;">장점: {{tweet.review.str}}</p>
                 </v-chip>
              
            <br>
                <v-chip class="ma-2" color="warning" outlined>
                  <p style="margin-top: 10px;">단점: {{tweet.review.weak}}</p>
                </v-chip>
         
            </div>
            <br>
            <!-- 해시 태그 리스트 -->
            <!-- card로 해시태그 분리하기 -->
            <div class="content">
              
            <v-chip class="ma-2" color="warning" outlined>
                  <string>      
                  HashTags
                  </string> 
            </v-chip><br>

              <v-chip class="ma-2" color="warning" outlined v-for="tag in tagList" v-bind:key="tag.id">
              
               {{tag}}
              </v-chip>
             
          <!--댓글 수 -->
          <div class="content" style="margin-top : 30px;" >


            <v-chip class="ma-2" color="warning" outlined>
                <i class="fas fa-edit fa-2x" style="margin-right : 10px"></i> 댓글 수 : 
                {{tweet.comments}}
            </v-chip>
            
            <!--좋아요 싫어요 수 -->
            <v-chip class="ma-2" color="warning" outlined>
                <i class="far fa-thumbs-up fa-2x" style="margin-right : 10px"></i> 좋아요 : 
                {{tweet.like}}
            </v-chip>
            
            <v-chip class="ma-2" color="warning" outlined>
                <i class="far fa-thumbs-down fa-2x" style="margin-right : 10px"></i> 싫어요 : 
                {{tweet.dislike}}
            </v-chip>
          
          </div>
              <!-- {{tweet.review.hashtag}} -->
            </div>
          </div>
          
        </article>
      </div>
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
  mdiStar
} from "@mdi/js";
import StarRating from 'vue-star-rating'
export default {
  created() {
    this.tweet.review.wdate = this.tweet.review.wdate.substring(0, 10);
    this.tagList = this.tweet.review.hashtag.split(",");
  },
  data() {
    return {
      tagList: [],
      date: ""
    };
  },
  components : {
    StarRating
  },
  props: {
    tweet: Object
  },
  methods:{
    // gotoReview(){
    //   console.log('들어온다아아ㅏ아아')
    //   this.$store.dispatch('commentsOfreview', this.tweet.review.rnum)
    // },
    storeDetail : function(num) {
      console.log('store')
      this.$store.dispatch('storeHashtags', num)

    }
  }
};
</script>
<style lang="scss" scoped>
@import url("https://fonts.googleapis.com/css?family=Muli:400,800");
body {
  height: 100%;
  background: #e6ecf1;
  font-family: "Muli", sans-serif;
}
strong {
  font-weight: 800;
}
p {
  line-height: 1.2;
  + p {
    margin-top: 5px;
  }
}
button {
  background-color: #43c594;
  border: 0;
  color: #fff;
  padding: 15px 20px;
  font-size: 18px;
  text-transform: uppercase;
  cursor: pointer;
  &:focus {
    outline: none;
  }
  &.selected {
    background-color: darken(#43c594, 15%);
  }
}
#app {
  max-width: 600px;
  margin: 30px auto;
}
.view {
  margin-bottom: 20px;
  button + button {
    margin-left: 6px;
  }
}
.tweet {
  background-color: #fff;
  color: #373737;
  box-shadow: 0 2px 3px rgba(10, 10, 10, 0.1), 0 0 0 1px rgba(10, 10, 10, 0.1);
  padding: 30px;
  + .tweet {
    margin-top: 20px;
  }
  .media {
    display: flex;
  }
  img {
    width: 64px;
    margin-right: 15px;
  }
  small {
    font-size: 14px;
    color: #657786;
  }
}
</style>