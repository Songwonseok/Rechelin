<template>
    <div style="text-align: center;">
        <div>
            <h1>유저 페이지</h1>
            <img :src="UserInfo.picture" :alt="UserInfo.name" style="width:300px; height:300px;">
            <h3>name: {{UserInfo.name}}</h3>
            <h3>email: {{UserInfo.email}}</h3>
            <h3>phone: {{UserInfo.phone}}</h3>
        
        </div>
        <router-link :to="{name: 'UserEdit', params: {
            info: UserInfo
        }}" exact>수정</router-link>
    </div>
</template>

<script>
import {mapState} from 'vuex'

export default {
    computed: {
        ...mapState(['allUsers']),
        UserInfo(){
            return this.allUsers[0]
        }
    }
}


</script>