<template>
  <div>
    <div class="tweet" style="border-radius: 10px; margin-bottom: 20px;">
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
                <v-icon>{{mdiLeadPencil}}</v-icon>작성자
              </v-chip>
              {{tweet.review.user.nickname}}
            </router-link>

            <div class="media-content">
              <!--TODO : 별로 표시하기 -->
              <p>총점 : {{tweet.review.total}}</p>
              <p>맛 : {{tweet.review.taste}}</p>
              <p>가격 : {{tweet.review.price}}</p>
              <p>친절함 : {{tweet.review.kindness}}</p>
            </div>
          </div>
          <div class="media-content">
            <div class="content">
              <!-- title 누르면 review detail로 넘어가게 -->
             <router-link :to="{name: 'reviewDetail', params: {
                id: tweet.review.rnum}}"
              style="color: #ff7f00 !important">
              <p>
                <strong>{{tweet.review.title}}</strong>
              </p>
              </router-link>
              <!-- TODO : 장점과 단점을 분리 -->
              <v-card>
                <v-card-text>
                  <p>장점: {{tweet.review.str}}</p>
                </v-card-text>
              </v-card>
              <v-card>
                <v-card-text>
                  <p>단점: {{tweet.review.weak}}</p>
                </v-card-text>
              </v-card>
            </div>

            <!-- 해시 태그 리스트 -->
            <!-- card로 해시태그 분리하기 -->
            <div class="content">
              <span v-for="tag in tagList" v-bind:key="tag.id">
              <v-chip>
                {{tag}}
              </v-chip>
              </span>
              <!-- {{tweet.review.hashtag}} -->
            </div>
          </div>
          <div class="content">{{this.tweet.review.wdate}}</div>

          <!--댓글 수 -->
          <div class="content">
            <span>댓글수 : {{tweet.comments}}</span><br>
            <!--좋아요 싫어요 수 -->
            <span>좋아요 : {{tweet.like}}</span>
            <span>싫어요 : {{tweet.dislike}}</span>
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
  props: {
    tweet: Object
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