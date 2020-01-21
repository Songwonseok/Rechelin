<template>
    <div>
        <h1>회원정보 수정</h1>
        <p>{{ user }}</p>
    </div>
</template>

<script>

export default {
    data () {
        return {
            thisUser: null
        }
    },
    computed: {
        user() {
            return this.$route.params.user
        }
    },
    created() {
        console.log(this.$route)
    }
}
</script>