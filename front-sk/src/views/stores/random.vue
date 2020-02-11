<template>
  <div>
      랜덤 음식점!!!!!!!!!!!!!!!!!!!!!!!
      <button v-on:click="getRandom">시작하기</button>
      <template v-for="item in randomList">
          
        <h3 v-bind:key="item.num">{{item.sname}}</h3>
        {{item.address}}
          
      </template>

  </div>
</template>

<script>
import StoreApi from '../../apis/StoreApi'

export default {
    data(){
        return{
            randomList:[],
        }
    },
    methods:{
        getRandom(){
            //  randomList에 통째로 넣는다
            StoreApi.requestRandom("이태원", res =>{
                this.randomList = res;
            }, error =>{
                alert('랜덤 음식점 리스트 가져오기 실패')
            })
        }
    }
    
}
</script>

<style>

</style>