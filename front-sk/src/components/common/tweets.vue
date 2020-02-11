<template>
  <div>
       <div class="tweet">
      <div class="box">
        <article class="media">
          <div class="media-left">
            <figure class="image">
              <img :src="tweet.img">
            </figure>
          </div>
          <div class="media-content">
            <div class="content">
              <p><strong>{{tweet.name}}</strong> <small>{{tweet.handle}}</small></p>
              <p>{{tweet.tweet}}</p>
            </div>
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
        //  user_type : 'all',
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