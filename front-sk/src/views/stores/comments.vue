<template>
    <div>
        <div class="card card--big">
            
  <div class="card__image" >
      <div >사진들어갓 곳</div>
      <img v-if="reviewInfo.picture" alt="">
  </div>
  <h2 class="card__title"></h2><span class="card__subtitle">{{reviewInfo.wdate}} by {{reviewInfo.user.nickname}} </span>
  <p class="card__text">{{reviewInfo.weak}}</p>
  <div class="card__action-bar">
    <h6>총점 ; {{reviewInfo.total}}</h6>
    <h6>맛 ; {{reviewInfo.taste}}</h6>
    <h6>가격 ; {{reviewInfo.price}}</h6>
    <h6>친절도 ; {{reviewInfo.kindness}}</h6>
    <h6>조회수 : {{reviewInfo.views}}</h6>
    
  </div>
</div>
         <div class="card">
         <v-text-field
            v-model="newComment"
            :rules="nameRules"
            :counter="30"
            label="comments"
            required
            @keyup="submitComment"
          ></v-text-field>
          <v-btn text small @click="submitComment">댓글등록</v-btn>
           <b-list-group>
            <b-list-group-item>Cras justo odio</b-list-group-item>
            <b-list-group-item>댓글댓글</b-list-group-item>
           
            </b-list-group>

          <!-- <b-list-group v-for="(comment, index) in comments" :key="index">
            <b-list-group-item>{{comment}}

            <v-btn text icon v-if="samePerson" @click="commnetDelete(comment.num)">
              <v-icon>{{mdiDelete}}</v-icon>
            </v-btn>
            </b-list-group-item>
           
            </b-list-group> -->
        </div>
        



    </div>
</template>

<script>
 import {
        mdiDelete
    } from '@mdi/js';
export default {
    data() {
        return {
            newComment: '',

            //icons
            mdiDelete,
        }
    },
    computed: {
        reviewInfo() {
            return this.$store.state.reviewInfo
        },
        comments() {
            return this.$store.state.commentsOfreview
        },
        samePerson(num) {
            if (sessionStorage.getItem('userEmail')===num) {
                return true
            }
            return false
        }
    },
    methods: {
        submitComment() {
            this.$store.dispatch('createComment', this.newComment).then((result)=> {
                this.$forceUpdate()
            })
        },
        commentDelete(num){
            this.$store.dispatch('commentDelete', num).then((result)=> {
                this.$forceUpdate()
            })
            
        }
    }
    
}
</script>  

<style scoped>
.card {
  padding: 16px;
  margin: 24px;
  border-radius: 2px;
  background-color: #fff;
  color: rgba(0,0,0,0.87);
  box-shadow: 
    0 2px 2px 0 rgba(0, 0, 0, 0.14), 
    0 1px 5px 0 rgba(0, 0, 0, 0.12), 
    0 3px 1px -2px rgba(0, 0, 0, 0.2);
}
</style>