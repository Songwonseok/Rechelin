<template>
    <div>
        팔로우 페이지
    <p v-for="(p, index) in UserFans" :key="index">
        {{p}}
        </p>
    </div>
</template>

<script>

export default {

    computed : {
       UserFans() {
           console.log(this.$route)
           return this.$route.query.fans 
       },
    },
    created () {
        console.log('asdfsd')
    }


}
</script>