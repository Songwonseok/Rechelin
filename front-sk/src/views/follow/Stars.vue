<template>
    <div>
        팔로잉 페이지
        <p v-for="(s, index) in Stars" :key="index">
            {{s}}
        </p>
        
    </div>
</template>

<script>
export default {
    computed: {
        Stars() {
            return this.$route.query.stars
        }
    },
    created () {
        console.log(this.$route)
    }
}
</script>