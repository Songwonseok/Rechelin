<template>
  <v-container elevation="20">
    <v-row class="tweet">
          <v-col 
          class="media-left" 
          md="4" 
          sm="12" 
          lg="3">
            <figure class="image">
              <!-- 사진 누르면 프로필 넘어가도록-->
              <v-list-item
                router-link
                :to="{name: 'userpage', params : {id: tweet.review.user.id}}"
              >
                <v-list-item-content>
                  
                  <v-img
                    v-if="tweet.review.user.profile == null"
                    src="https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_960_720.png"
                    max-width="100"
                    max-height="100"
                  ></v-img>

                    <v-img
                    v-else
                    :src="tweet.review.user.profile"
                    max-width="100"
                    max-height="100"
                    ></v-img>
                </v-list-item-content>
              </v-list-item>
            </figure>

            <router-link
              :to="{name: 'userpage', 
              params: {id: tweet.review.user.id}
              }"
              style="color: black !important; font-size: 12px;"
            >
            <v-chip 
              class="ma-2 text-left" 
             
              x-small 
              outlined>
                작성자
              </v-chip>
              {{tweet.review.user.nickname}}
            </router-link>

            <v-divider></v-divider>
            <v-row 
            class="media-content" 
            justify="center">
              <!--TODO : 별로 표시하기 -->

              <v-chip 
              id="score" 
              class="ma-2 text-center" 
              color="#ff7f00" 
              small
              outlined>
              <v-icon small>{{mdiSigma}}</v-icon>
                 총점
              </v-chip>

              <star-rating 
              read-only	
              star-size="23" 
              @rating-selected="rating = $event" 
              :rating="tweet.review.total"
              ></star-rating>

              <v-chip 
              id="score" 
              class="ma-2" 
              color="#ff7f00" 
              small
              outlined>                
              <v-icon small>{{mdiEmoticonTongueOutline}}</v-icon>
                맛
              </v-chip> 
              
              <star-rating 
              read-only	
              star-size="20" 
              @rating-selected="rating = $event" 
              :rating="tweet.review.taste"
              ></star-rating>
      
      
              <v-chip 
              class="ma-2" 
              color="#ff7f00" 
              outlined
              small>
              <v-icon small>{{mdiCurrencyUsd}}</v-icon>
              가격
            </v-chip>
                 
            <star-rating 
            read-only	
            star-size="20" 
            @rating-selected="rating = $event" 
            :rating="tweet.review.price"
            ></star-rating>
            
            <v-chip 
            class="ma-2" 
            color="#ff7f00" 
            small
            outlined>
            <v-icon small>{{mdiEmoticonOutline}}</v-icon>
            친절도
          </v-chip> 
          
          <star-rating 
          read-only	
          star-size="20" 
          @rating-selected="rating = $event" 
          :rating="tweet.review.kindness"
          ></star-rating>
        
         <v-chip 
            class="ma-2" 
            small
            color="black"
            @click="goReviewDetail(tweet.review.store.num, tweet.review.rnum)" 
            outlined>
              <i 
              class="fas fa-edit fa-2x small" 
              ></i> 
              댓글({{tweet.comments}})
            </v-chip>

            
    
            
        </v-row>
      </v-col>
        
      <v-col 
      class="media-content" 
      sm="12"
      md="8" 
      lg="9">

        <v-row
        justify="center"
        >

        <v-container>
          <v-chip 
          class="ma-2" 
          color="#ff7f00" 
          outlined 
          large 
          @click="goReviewDetail(tweet.review.store.num, tweet.review.rnum)"
          style="font-family: 'Nanum Myeongjo' !important; margin-top: 5px;"
          >
            <i 
            class="fas fa-store-alt fa-2x" 
            style="margin-right : 10px" 
            ></i>
              {{tweet.review.store.sname}}   
          </v-chip>

        
        </v-container>
         <br>
       
          <v-img
            v-if="tweet.review.picture"
            :src="tweet.review.picture"
            :lazy-src="tweet.review.picture"
            aspect-ratio="1"
            max-height="300"
            max-width="350"
            style="border-radius: 5px; max-width: 70%;"
            @click="goReviewDetail(tweet.review.store.num, tweet.review.rnum)"
          >
          </v-img>
          <v-img
            v-else
            src="https://cdn.pixabay.com/photo/2015/04/20/13/30/kitchen-731351_1280.jpg"
            max-height="300"
            max-width="350"
            width="100%"
            style="border-radius: 5px;"
            @click="goReviewDetail(tweet.review.store.num, tweet.review.rnum)"
          ></v-img>
        </v-row>

          <!-- 제목 -->
          <p
          class="subtitle-1 text-center"
          style="font-family: 'Nanum Gothic' !important; margin-top: 5%;"
          @click="goReviewDetail(tweet.review.store.num, tweet.review.rnum)" 
          >{{tweet.review.title}}</p>

          <!-- 주소 -->
          <p
          class="font-weight-light text-right body-2" 
          style="font-family: 'Nanum Gothic'; "
          @click="storeDetail(tweet.review.store.num)"
          >  
            <i 
            class="fas fa-map-marker-alt fa-2x small"
            ></i>
            {{tweet.review.store.address}} 
          </p>
          
        <v-chip 
          class="ma-2" 
          color="warning" 
          small
          outlined
          
          >
          <v-icon small>{{mdiPound}}</v-icon>     
          HashTags
          </v-chip>
          <br>
            <span 
            v-for="tag in tagList.slice(0, 5)" 
            :key="tag.id"
            style="font-family: 'Do Hyeon';"
            >
            #{{tag}}
            
            </span>
        
        <p class="overline text-right">
            작성시간 : {{this.tweet.review.wdate}} 
          <v-chip
            id="like-bookmark"
            class="ma-2" 
            color="#ff7f00"
            outlined
            @click="reviewLike(tweet.review.rnum, tweet.like)"
            >
              <v-icon >{{ mdiThumbUp }}</v-icon>
              <!-- {{tweet.like}} -->
            </v-chip>
            <v-chip 
            id="like-bookmark"
            class="ma-2" 
            color="#ff7f00"
            @click="reivewBookmark(tweet.review.rnum)"
            outlined>
            <v-icon>{{mdiBookmark}}</v-icon>
             
              <!-- 싫어요 : {{tweet.dislike}} -->
            </v-chip>
          </p>
         
        </v-col> 

           
       
          
              <!-- </router-link> -->
              <!-- title 누르면 review detail로 넘어가게 -->
              <!-- storedetail/62/comments -->
             <!-- <router-link :to="{name: 'comments', params: {
                id: tweet.review.rnum}}"
              style="color: #ff7f00 !important"> -->
           
          


              <!-- </router-link> -->
              <!-- TODO : 장점과 단점을 분리 -->

        <!-- 장점과 단점의 길이가 너무 길어져서 일단 미관을 위해 주석처리를 해두었습니다. -->
        <!-- <v-row>
          <v-col>
            <v-chip 
            class="ma-2" 
            color="warning" 
            outlined
            small
            >
            <v-icon small>{{mdiEmoticonHappyOutline}}</v-icon>
              장점
            </v-chip>
            <p class="text-center"> {{tweet.review.str}}</p>
          </v-col>   

          <v-col>
            <v-chip 
            class="ma-2" 
            color="warning" 
            small
            outlined>
            <v-icon small>{{mdiEmoticonSadOutline}}</v-icon>
            단점
          </v-chip>
          <p class="text-center">{{tweet.review.weak}}</p>
        </v-col>
        
       </v-row>   -->

        <!-- 해시 태그 리스트 -->
        <!-- card로 해시태그 분리하기 -->
      
              
          
          <!--댓글 수 -->
      


              <!-- {{tweet.review.hashtag}} -->
          

    </v-row>
  </v-container>
</template>
<script>
import {
  mdiDelete,
  mdiEmoticonSadOutline,
  mdiPound,
  mdiSigma,
  mdiCurrencyUsd,
  mdiEmoticonTongueOutline,
  mdiEmoticonOutline,
  mdiLeadPencil,
  mdiEmoticonHappyOutline,
  mdiStar,
  mdiBookmark,
   mdiThumbUp 
} from "@mdi/js";
import StarRating from 'vue-star-rating'
export default {
  created() {
    console.log(this.tweet);
    this.tweet.review.wdate = this.tweet.review.wdate.substring(0, 10);
    this.tagList = this.tweet.review.hashtag.split(",");
  },
  data() {
    return {
      tagList: [],
      date: "",

      //icons
      mdiSigma,
      mdiEmoticonTongueOutline,
      mdiCurrencyUsd,
      mdiEmoticonSadOutline,
      mdiEmoticonHappyOutline,
      mdiEmoticonOutline,
      mdiPound,
      mdiBookmark,
       mdiThumbUp ,
    };
  },
  components : {
    StarRating
  },
  props: {
    tweet: Object
  },
  methods:{

    storeDetail : function(num) {
      console.log('store')
      this.$store.dispatch('storeHashtags', num)

    },
    goReviewDetail(num, rnum) {
      // comments를 방문하기 위해 detail을 1로 만들어 주어야 storeDetail.vue에서 이를 확인하고 comments로 이동시켜줌
      this.$store.state.detail = rnum;
      this.$store.dispatch('storeHashtags', num)
    },
    reviewLike(num, status) {
      let payload = {
          num: num,
          status: status
        }
      this.$store.dispatch('reviewLike', payload)
    },
    reivewBookmark(num) {
      this.$store.dispatch('reviewBookmark', num)
    },
  }
};
</script>
<style lang="scss" scoped>
@import url('https://fonts.googleapis.com/css?family=Nanum+Gothic&display=swap');
@import url('https://fonts.googleapis.com/css?family=Nanum+Myeongjo:700&display=swap');
@import url('https://fonts.googleapis.com/css?family=Do+Hyeon&display=swap');

strong {
  font-weight: 800;
}
p {
  line-height: 1.2;
  + p {
    margin-top: 5px;
  }
}

// #app {
//   max-width: 600px;
//   margin: 30px auto;
// }
.view {
  margin-bottom: 20px;
  button + button {
    margin-left: 6px;
  }
}
.tweet {
  background-color: #fff;
  box-shadow: 0 2px 3px rgba(10, 10, 10, 0.1), 0 0 0 1px rgba(10, 10, 10, 0.1);
  padding: 30px 30px 0px 30px;
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
.subtitle-1:hover{
  color: #ff7f00;
}

</style>