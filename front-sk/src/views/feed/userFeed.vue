<template>
  <div id="app">
    <div class="view">
      {{user_type}}
      <button
        :class="[ user_type === 'all' ? 'selected' : '']"
        @click="update_source('all')"
      >All Posts</button>
      <button
        :class="[ user_type === 'friend' ? 'selected' : '']"
        @click="update_source('friend')"
      >Friends' Posts</button>
    </div>
    <div></div>
    <!-- <div v-if="((tweet.user_type === user_type) || (user_type === 'all'))"> -->
    <!-- eslint-disable vue/no-use-v-if-with-v-for,vue/no-confusing-v-for-v-if -->
    <tweets
      v-for="(tweet, $index) in tweet"
      v-bind:key="$index+tweet"
      :tweet="tweet"
      v-if="((tweet.user_type === user_type) || (user_type === 'all'))"
    />
    <!-- </div> -->
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
  methods: {
    update_source: function(user_type) {
      // eslint-disable-next-line no-console console.log(user_type);
      this.user_type = user_type;
    },
    infiniteHandler($state) {
      axios
        .get(api, {
          params: {
            page: this.page,
            tags: this.newsType
          }
        })
        .then(({ data }) => {
          if (data.hits.length) {
            this.page += 1;
            // this     .list     .push(...data.hits);
            this.tweet.push(
              {
                id: 1,
                name: "James",
                handle: "@james",
                img:
                  "https://www.shareicon.net/data/128x128/2016/09/01/822711_user_512x512.png",
                tweet:
                  "Puppy kitty ipsum dolor sit good dog tigger good boy furry teeth purr lazy cat" +
                  " run fast fish critters string.",
                user_type: "public"
              },
              {
                id: 2,
                name: "Jessica",
                handle: "@jessica",
                img:
                  "https://www.shareicon.net/data/128x128/2016/09/01/822739_user_512x512.png",
                tweet:
                  "Wag Tail tuxedo run bedding head chew food purr drool kisses carrier chirp toy" +
                  " ID tag slobbery smooshy.",
                user_type: "public"
              },
              {
                id: 3,
                name: "Heather",
                handle: "@heather",
                img:
                  "https://www.shareicon.net/data/128x128/2016/09/01/822761_user_512x512.png",
                tweet: "Rover maine coon cat speak harness whiskers mouse.",
                user_type: "public"
              },
              {
                id: 4,
                name: "Arthur",
                handle: "@arthur",
                img:
                  "https://www.shareicon.net/data/128x128/2016/09/01/822745_user_512x512.png",
                tweet:
                  "Toys stay finch polydactyl stay barky bark pet supplies food Buddy chirp Spike" +
                  " nap stick dog house throw.Tail collar leash Rover meow catch Scooby snacks.",
                user_type: "friend"
              },
              {
                id: 5,
                name: "Francesca",
                handle: "@francesca",
                img:
                  "https://www.shareicon.net/data/128x128/2016/09/01/822715_user_512x512.png",
                tweet:
                  "Kitty bark string shake litter box toys polydactyl yawn polydactyl scratcher w" +
                  "ater dog stay cage nest slobber chirp water.",
                user_type: "friend"
              },
              {
                id: 6,
                name: "Tina",
                handle: "@tina",
                img:
                  "https://www.shareicon.net/data/128x128/2016/09/01/822726_user_512x512.png",
                tweet:
                  "Birds fur collar fluffy collar parakeet barky dog house run sit Buddy purr. Bi" +
                  "rd wag tail small animals groom vitamins Tigger.",
                user_type: "friend"
              },
              {
                id: 1,
                name: "James",
                handle: "@james",
                img:
                  "https://www.shareicon.net/data/128x128/2016/09/01/822711_user_512x512.png",
                tweet:
                  "Puppy kitty ipsum dolor sit good dog tigger good boy furry teeth purr lazy cat" +
                  " run fast fish critters string.",
                user_type: "public"
              },
              {
                id: 2,
                name: "Jessica",
                handle: "@jessica",
                img:
                  "https://www.shareicon.net/data/128x128/2016/09/01/822739_user_512x512.png",
                tweet:
                  "Wag Tail tuxedo run bedding head chew food purr drool kisses carrier chirp toy" +
                  " ID tag slobbery smooshy.",
                user_type: "public"
              },
              {
                id: 3,
                name: "Heather",
                handle: "@heather",
                img:
                  "https://www.shareicon.net/data/128x128/2016/09/01/822761_user_512x512.png",
                tweet: "Rover maine coon cat speak harness whiskers mouse.",
                user_type: "public"
              },
              {
                id: 4,
                name: "Arthur",
                handle: "@arthur",
                img:
                  "https://www.shareicon.net/data/128x128/2016/09/01/822745_user_512x512.png",
                tweet:
                  "Toys stay finch polydactyl stay barky bark pet supplies food Buddy chirp Spike" +
                  " nap stick dog house throw.Tail collar leash Rover meow catch Scooby snacks.",
                user_type: "friend"
              },
              {
                id: 5,
                name: "Francesca",
                handle: "@francesca",
                img:
                  "https://www.shareicon.net/data/128x128/2016/09/01/822715_user_512x512.png",
                tweet:
                  "Kitty bark string shake litter box toys polydactyl yawn polydactyl scratcher w" +
                  "ater dog stay cage nest slobber chirp water.",
                user_type: "friend"
              },
              {
                id: 6,
                name: "Tina",
                handle: "@tina",
                img:
                  "https://www.shareicon.net/data/128x128/2016/09/01/822726_user_512x512.png",
                tweet:
                  "Birds fur collar fluffy collar parakeet barky dog house run sit Buddy purr. Bi" +
                  "rd wag tail small animals groom vitamins Tigger.",
                user_type: "friend"
              },
              {
                id: 1,
                name: "James",
                handle: "@james",
                img:
                  "https://www.shareicon.net/data/128x128/2016/09/01/822711_user_512x512.png",
                tweet:
                  "Puppy kitty ipsum dolor sit good dog tigger good boy furry teeth purr lazy cat" +
                  " run fast fish critters string.",
                user_type: "public"
              },
              {
                id: 2,
                name: "Jessica",
                handle: "@jessica",
                img:
                  "https://www.shareicon.net/data/128x128/2016/09/01/822739_user_512x512.png",
                tweet:
                  "Wag Tail tuxedo run bedding head chew food purr drool kisses carrier chirp toy" +
                  " ID tag slobbery smooshy.",
                user_type: "public"
              },
              {
                id: 3,
                name: "Heather",
                handle: "@heather",
                img:
                  "https://www.shareicon.net/data/128x128/2016/09/01/822761_user_512x512.png",
                tweet: "Rover maine coon cat speak harness whiskers mouse.",
                user_type: "public"
              },
              {
                id: 4,
                name: "Arthur",
                handle: "@arthur",
                img:
                  "https://www.shareicon.net/data/128x128/2016/09/01/822745_user_512x512.png",
                tweet:
                  "Toys stay finch polydactyl stay barky bark pet supplies food Buddy chirp Spike" +
                  " nap stick dog house throw.Tail collar leash Rover meow catch Scooby snacks.",
                user_type: "friend"
              },
              {
                id: 5,
                name: "Francesca",
                handle: "@francesca",
                img:
                  "https://www.shareicon.net/data/128x128/2016/09/01/822715_user_512x512.png",
                tweet:
                  "Kitty bark string shake litter box toys polydactyl yawn polydactyl scratcher w" +
                  "ater dog stay cage nest slobber chirp water.",
                user_type: "friend"
              },
              {
                id: 6,
                name: "Tina",
                handle: "@tina",
                img:
                  "https://www.shareicon.net/data/128x128/2016/09/01/822726_user_512x512.png",
                tweet:
                  "Birds fur collar fluffy collar parakeet barky dog house run sit Buddy purr. Bi" +
                  "rd wag tail small animals groom vitamins Tigger.",
                user_type: "friend"
              }
            );
            this.list.unshift(...data.hits.reverse());
            $state.loaded();
          } else {
            $state.complete();
            this.tweet = [];
          }
        });
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