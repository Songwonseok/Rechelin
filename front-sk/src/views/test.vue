<template>
  <div>
      {{access_token}} <br>
      {{userid}}
  </div>
</template>

<script>
export default {
    data(){
        return {
            access_token:'',
            userid:''
        }
    },
    mounted(){
        this.userid = this.$route.params.userid
        this.access_token = this.$route.params.access_token
        
        sessionStorage.setItem("userToken", this.access_token);
        sessionStorage.setItem("userid", this.userid)

        this.$router.push({ name: 'main' })
    }

}
</script>

<style>

</style>