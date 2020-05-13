<template>
  <div>
        <kinesis-container>
            
              <kinesis-element :strength="10">
                <h1 style="color : orange">검색된 결과가 없습니다.</h1>
              </kinesis-element>
              <kinesis-element :strength="20">
                <h1 style="color : orange">다시 검색해 주세요.</h1>
              </kinesis-element>
          </kinesis-container>
  </div>
</template>

<script>
import Vue from 'vue'
import { KinesisContainer, KinesisElement } from 'vue-kinesis'

Vue.component('kinesis-container', KinesisContainer)
Vue.component('kinesis-element', KinesisElement)
export default {

}
</script>

<style>

</style>