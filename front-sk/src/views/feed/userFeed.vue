<template>
  <div id="app">
   <userSearch></userSearch>  
  <v-container v-if = "tweet.length !=0">
  
    <tweets
      v-for="(tweet, $index) in tweet"
      :key="$index+tweet"
      :tweet="tweet"
    />
  </v-container>

  <v-container v-else>
   <kinesis-container>
                    <kinesis-element :strength="10">
                        <h1 style="color : orange">Following 한 사람이 없습니다.</h1>
                    </kinesis-element>
                    <kinesis-element :strength="20">
                        <h1 style="color : orange">Following 부터 해주세요.</h1>
                    </kinesis-element>
                </kinesis-container>
                 <iframe src="https://giphy.com/embed/TI4Hsj7mNI27nn9I1P" width="200" height="200" frameBorder="0" class="giphy-embed" allowFullScreen></iframe><p><a href=""></a></p>
  </v-container>   

    <!-- <div v-for="(item, $index) in list" :key="$index">{{item}}</div> -->
    <infinite-loading spinner="bubbles" :identifier="infiniteId" @infinite="infiniteHandler">
    </infinite-loading>
 
  </div>
</template>

<script> 
import Vue from 'vue'
import tweets from '../../components/common/tweets.vue';
import InfiniteLoading from "vue-infinite-loading";
import userSearch from "../../components/common/userSearch.vue";
const api = "//hn.algolia.com/api/v1/search_by_date?tags=story";
import axios from "axios";
import ReviewApi from '../../apis/ReviewApi'
import {KinesisContainer, KinesisElement} from 'vue-kinesis';
  
    Vue.component('kinesis-container', KinesisContainer);
    Vue.component('kinesis-element', KinesisElement);
export default {
  components: {
    tweets,
    InfiniteLoading,
    userSearch,
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
  watch : {
    feedList : function(v){

ReviewApi.requestFeedList(this.userid, res=>{
               let jary = [];
               if(this.$store.state.feedUserList.length !=0){
                  for(let i=0; i<res.length; i++){
                      if(this.$store.state.feedUserList==res[i].review.user.nickname){
                          jary.push(res[i]);
                      }
                  }
                
                this.tweet = jary;
               }
            }, error=>{
              alert('피드 리스트 가져오기 실패')
            })
    }
  },
  computed :{
    userid(){
        return this.$store.state.userid
      },
    feedList(){
      return this.$store.state.feedUserList;
    }
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
  max-width: 800px;
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
iframe {
  pointer-events: none;
}
</style>