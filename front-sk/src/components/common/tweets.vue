<template>
  <div>
       <div class="tweet">
      <div class="box">
        <article class="media">
          <div class="media-left">
            <figure class="image">
              <!-- TODO : 사진 누르면 프로필 넘어가도록-->
               <v-list-item router-link :to="{name: 'userpage', params : {id: 15}}">
                <v-list-item-content>
                  <template v-if="tweet.user.profile == null">
                    <img src="../../assets/images/ssafy.jpg" />
                  </template>
                  <template v-else>
                    <img :src="tweet.user.profile">
                  </template>
                </v-list-item-content>
              </v-list-item>
              
              
            </figure>
              <p><small>@{{tweet.user.nickname}}</small></p>
              <p>총점 : {{tweet.total}}</p> 
              <p>맛 : {{tweet.taste}}</p>
              <p>가격 : {{tweet.price}}</p>
              <p>친절함 : {{tweet.kindness}}</p>
               
          </div>
          <div class="media-content">
            <div class="content">
              <p><strong>{{tweet.title}}</strong></p>
              <p>장점: {{tweet.str}}</p>
              <p>단점: {{tweet.weak}}</p>
            </div>
            <!-- 해시 태그 리스트 -->
            
          </div>
        </article>
      </div>
    </div>
  </div>
</template>
<script>
export default {
    data(){
        return {
          tagList:[]
        }
    },
     props: {
        tweet: Object
     },
}
</script>
<style lang="scss" scoped>
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