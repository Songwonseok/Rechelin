<template>
  <div>
      <div class="input-with-label">
                <input v-model="email" v-bind:class="{error : error.email, complete:!error.email&&email.length!==0}"
                       @keyup.enter="login"
                       id="email" placeholder="이메일을 입력하세요."
                       type="text" style="
            background-color: white;
            color: black;
            border: 2px solid #008Cy
            BA;"/>
                <label for="email">이메일</label>
                <div class="error-text" v-if="error.email">
                    {{error.email}}
                </div>
            </div>
            
  </div>
</template>

<script>
export default {

}
</script>

<style>

</style>