<template>
    <div>
        <div style="text-align: center;">
        <h1>mypage</h1>
        <img :src="allUsers[0].picture" :alt="allUsers[0].name">
        <h2>name: {{allUsers[0].name}}</h2>
        <h2>email: {{allUsers[0].email}}</h2>
        <button router :to="{name: 'Editpage'}" exact>수정</button>
        </div>
        
    </div>
</template>

<script>
import {mapState} from 'vuex'

export default {
    data () {
        return {}
    },
    computed: {
        ...mapState(['allUsers'])
        }
    }
    
</script>