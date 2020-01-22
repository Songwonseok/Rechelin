<template>
    <div style="text-align:center;">

        <h1 style="margin-top: 20px; margin-bottom: 20px;">회원정보 수정 페이지</h1>
    
        <div>
        <img :src="EditUser.picture" :alt="EditUser.name" style="height: 300px; width:300px;">
        </div>

        <div style="margin-top: 10px;">
        닉네임 변경 : <input v-model="nickname"
        type="text" id="nickname" 
        @keyup.enter="edit" :placeholder="EditUser.name">   
        </div>

        <div style="margin-top: 10px;">
        비밀번호 변경: <input v-model="pw1" 
        type="password" id="pw1" @keyup.enter="edit">
        </div>
        <div v-if="error.pw1">{{error.pw1}}</div>

        <div style="margin-top: 10px;">
        비밀번호 확인: <input v-model="pw2"
        type="password" id="pw2" @keyup.enter="edit">
        <div v-if="error.confirmPW">{{error.confirmPW}}</div>
        </div>

        <div style="margin-top: 10px;">
        핸드폰 번호 변경: <input v-model="phone" 
        type="text" id="phone" :placeholder="EditUser.phone">
        </div>
        <button type="submit" style="margin-top: 10px;" @click="edit" >변경</button>
        
        

    </div>
</template>

<script>
import PV from 'password-validator'
import UserApi from '../../apis/UserApi'
import router from '../../routes.js'

export default {
    data() {
        return {
            nickname: '',
            pw1: '',
            pw2: '',
            phone: '',
            passwordSchema: new PV(),
            error : {
                pw1: false,
                confirmPW: false
            },
            isSubmit: false,

        }
    },
    watch: {
        nickname: function(v) {
            this.checkForm();
        },
        pw1 : function(v) {
            this.checkForm();
        },
        pw2: function(v) {
            this.checkForm();
        },
        phone: function(v) {
            this.checkForm();
        }
    },
    computed: {
        EditUser() {
            return this.$route.params.info
        }
    },
    methods: {
        checkForm() {
            // 기존에 있는 닉네임인지
            // 기존에 등록된 휴대폰 번호인지 확인

            if (this.pw1.length > 0 && !this.passwordSchema.validate(this.pw1)) {
                this.error.pw1 = '영문, 숫자 포함 8자리 이상이어야 합니다'
            } else this.error.PW = false;
            if (this.pw2.length > 0 && this.pw1 != this.pw2) {
                this.error.confirmPW = '비밀번호가 일치하지 않습니다'
            } else this.error.confirmPW = false;
            let Editing = true;
            Object.values(this.error).map(err => {
                if (err) {
                    Editing = false;
                }
            })
            this.isSubmit = Editing;

        },
        edit() {
            if (this.isSubmit) {
                let {nickname, pw1, phone} = this;
                let data = {
                    nickname, pw1, phone
                }
                this.isSubmit = false;
                UserApi.requestEdit(data, res => {
                    console.log('회원 정보 수정에 성공');
                    console.log(this.EditUser.email);
                    this.$router.push(`userpage/${this.EditUser.email}`)
                })
            }
        }
        
    },
    created() {
        console.log(this.$route);
        this.passwordSchema // 비밀번호 형식 체크
            .is().min(8)
            .is().max(100)
            .has().digits()
            .has().letters();
    }
}

</script>