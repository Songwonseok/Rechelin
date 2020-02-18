<template>
  <div id="app">
    <!-- <div class="view">
      {{user_type}}
      <button
        :class="[ user_type === 'all' ? 'selected' : '']"
        @click="update_source('all')"
      >All Posts</button>
      <button
        :class="[ user_type === 'friend' ? 'selected' : '']"
        @click="update_source('friend')"
      >Friends' Posts</button>
    </div> -->
    <!-- <div v-if="((tweet.user_type === user_type) || (user_type === 'all'))"> -->
    <!-- eslint-disable vue/no-use-v-if-with-v-for,vue/no-confusing-v-for-v-if -->
    <tweets
      v-for="(tweet, $index) in tweet"
      :key="$index+tweet"
      :tweet="tweet"
     
    />

    <div v-for="(item, $index) in list" :key="$index">{{item}}</div>
    <infinite-loading spinner="bubbles" :identifier="infiniteId" @infinite="infiniteHandler">
      <div slot="spinner">Loading...</div>
      <div slot="error" slot-scope="{ trigger }">
        Error message, click
        <a href="javascript:;" @click="trigger">here</a>
        to retry
      </div>
    </infinite-loading>
  </div>
  <!-- <div>

      <div>
    <h4>Top and Bottom</h4>
    <b-card-group deck>
    <b-card img-src="https://placekitten.com/300/300" img-alt="Card image" img-left class="mb-3">
      <b-card-text>
        Some quick example text to build on the card and make up the bulk of the card's content.
      </b-card-text>
    <br>
</b-card>
    <b-card img-src="https://placekitten.com/300/300" img-alt="Card image" img-left class="mb-3">
      <b-card-text>
        Some quick example text to build on the card and make up the bulk of the card's content.
      </b-card-text>
    
    </b-card>

    </b-card-group>
</div>
  </div>-->
</template>

<script> 
import tweets from '../../components/common/tweets.vue';
import InfiniteLoading from "vue-infinite-loading";
const api = "//hn.algolia.com/api/v1/search_by_date?tags=story";
import axios from "axios";
import ReviewApi from '../../apis/ReviewApi'

export default {
  components: {
    tweets,
    InfiniteLoading
  },
  data() {
    return {
      user_type: "all",
      page: 1,
      list: [],
      newsType: "story",
      infiniteId: +new Date(),
      tweet: []
    };
  },
  computed :{
    userid(){
        return this.$store.state.userid
      },
  },
  methods: {
    update_source: function(user_type) {
      // eslint-disable-next-line no-console console.log(user_type);
      this.user_type = user_type;
    },
    infiniteHandler($state) {
      // axios
      //   .get(api, {
      //     params: {
      //       page: this.page,
      //       tags: this.newsType
      //     }
      //   })
      //   .then(({ data }) => {
      //     if (data.hits.length) {
      //       this.page += 1;
      //       // this     .list     .push(...data.hits);
            
      //       ReviewApi.requestFeedList(this.userid, res=>{
      //         this.tweet = res;
      //         console.log(this.tweet)
      //       }, error=>{
      //         alert('피드 리스트 가져오기 실패')
      //       })
            
      //       this.list.unshift(...data.hits.reverse());
      //       $state.loaded();
      //     } else {
      //       $state.complete();
      //       this.tweet = [];
      //     }
      //   });
       ReviewApi.requestFeedList(this.userid, res=>{
              this.tweet = res;
              console.log(this.tweet)
               $state.loaded();
            }, error=>{
              alert('피드 리스트 가져오기 실패')
            })
             $state.complete();
    }
  }
};
</script>
<style lang="scss" scoped="scoped">
@import url('https://fonts.googleapis.com/css?family=Muli:400,800');
body {
  height: 100%;
  background: #e6ecf1;
  font-family: 'Muli', sans-serif;
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
  background-color: #ff7f00;
  border-radius: 8px;
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
    border-radius: 8px;
    background-color: darken(#ff7f00, 10%);
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
  box-shadow: 0 2px 3px rgba(10,10,10,.1), 0 0 0 1px rgba(10,10,10,.1);
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