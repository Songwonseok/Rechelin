<template>
   <div class="user" id="joindone">
        <p>회원가입 완료페이지</p>
        <p>가입완료 이메일 발송하였습니다.</p>

        <button class="btn btn--back btn--login" v-on:click="login">로그인 화면으로</button>
        <button class="btn btn--back btn--mail" v-on:click="mail">메일이 도착하지 않았나요?</button>
        
   </div>
</template>

<script>
export default {
    methods:{
        login(){
            this.$router.push("/");
        },
        mail(){
            // 이메일 입력화면 -- 아직 구현안함
            this.$router.push("/");
        }
    }

}
</script>

<style>

</style>