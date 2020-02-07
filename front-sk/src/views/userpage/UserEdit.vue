<template>
    <div style="text-align:center;">

        <h1 style="margin-top: 20px; margin-bottom: 20px;">회원정보 수정 페이지</h1>

        <div>
            <template v-if="profile==null">
            <img class="avatar" src="../../assets/images/ssafy.jpg" :alt="nickname" style="height: 300px; width:300px;" />
          </template>
          <template v-else>
            <img class="avatar" :src="profile" :alt="nickname" style="height: 300px; width:300px;"/>
          </template>

            <input type="file" @change="load" accept=".jpg, .jpeg, .png, .gif">


        </div>

        <div style="margin-top: 10px;">
            닉네임 변경 : <input v-model="nickname" type="text" id="nickname" @keyup.enter="edit"
                >
        </div>

        <div style="margin-top: 10px;">
            비밀번호 변경: <input v-model="pw1" type="password" id="pw1" @keyup.enter="edit">
        </div>
        <div v-if="error.pw1">{{error.pw1}}</div>

        <div style="margin-top: 10px;">
            비밀번호 확인: <input v-model="pw2" type="password" id="pw2" @keyup.enter="edit">
            <div v-if="error.confirmPW">{{error.confirmPW}}</div>
        </div>

        <div style="margin-top: 10px;">
            핸드폰 번호 변경: <input v-model="phone" type="text" id="phone" >
        </div>
        <v-btn class="ma-2" tile outlined color="success" @click="edit">
            <v-icon left>{{ svgPath }}</v-icon>
            EDIT
        </v-btn>
        <!-- TODO : 취소 버튼 눌르면 이전 페이지로 이동 -->


    </div>
</template>

<script>
    import PV from 'password-validator'
    import UserApi from '../../apis/UserApi'
    import router from '../../routes.js'
    import {
        mdiPencil
    } from '@mdi/js'

    export default {
        data() {
            return {
                email: this.$store.state.userEmail,
                nickname: '',
                pw1: '',
                pw2: '',
                phone: '',
                profile: '',
                passwordSchema: new PV(),
                error: {
                    pw1: false,
                    confirmPW: false
                },

                isSubmit: true,
                svgPath: mdiPencil

            }
        },
        watch: {
            nickname: function (v) {
                this.checkForm();
            },
            pw1: function (v) {
                this.checkForm();
            },
            pw2: function (v) {
                this.checkForm();
            },
            phone: function (v) {
                this.checkForm();
            }
        },
        computed: {
            // EditUser() {
            //     return this.$route.params.info
            // }
            // 해당 회원이 자기 자신인지 아닌지 확인하는 함수 만들기 : button에 v-if!만들기
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
                    let {
                        email,
                        nickname,
                        pw1,
                        phone,
                        image
                    } = this;
                    let data = {
                        "email": email,
                        "nickname": nickname,
                        "pw": pw1,
                        "phone": phone,
                        "profile": image
                    }
                    this.isSubmit = false;
                    UserApi.requestEdit(data, res => {

                        alert('회원 정보 수정에 성공');
                        this.$router.push({
                            // name: `UserPage`
                        })
                    }, error => {

                        console.log("회원 정보 수정 실패 !!!");
                        alert('회원 정보 수정 실패 !!')
                        // 로그인 실패시 버튼 비활성화
                        this.isSubmit = false;
                    })
                    // axios 보낸는 장소
                }
            },
            // 프로필 이미지 변경
            load(e) {
                var files = e.target.files || e.dataTransfer.files;
                console.log(e.target.files[0])
                if (!files.length) return;
                this.createImage(files[0]);
                console.log(files[0].mozFullPath)
            },
            createImage(file) {
                let image = new Image();
                let reader = new FileReader();

                reader.onload = (e) => {

                   // this.EditUser.picture = e.target.result;
                    // 파일 경로
                    console.log(e.target.result)
                    this.image = e.target.result;
                }
                reader.readAsDataURL(file);
            },
    
        getUser(){
            
            UserApi.requestEmail(this.email,res=>{
                this.nickname = res.object.nickname
                this.phone = res.object.phone
                this.profile = res.object.profile
                console.log('***유저정보 가져오기 성공')              
            },error=>{  
                console.log('유저정보 가져오기 실패')
            })
        }

        },
        created() {
            this.passwordSchema // 비밀번호 형식 체크
                .is().min(8)
                .is().max(100)
                .has().digits()
                .has().letters();
            this.getUser();
        }
    }
</script>