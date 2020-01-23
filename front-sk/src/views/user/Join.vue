
<!--
    가입하기는 기본적인 폼만 제공됩니다
    기능명세에 따라 개발을 진행하세요.
    Sub PJT I에서는 UX, 디자인 등을 포함하여 백엔드를 제외하여 개발합니다.
 -->
<template>
    <div class="user" id="join">
         <div class="wrapC">

        <h1>가입하기</h1>
        <div >
            <div class="input-with-label">
                <input v-model="nickName" 
                       @keyup.enter="join"
                       id="nickname"
                       placeholder="닉네임을 입력하세요." type="text"/>
                <label for="nickname">닉네임</label>
            </div>

            <div class="input-with-label">
                <input v-model="email" v-bind:class="{error : error.email, complete:!error.email&&email.length!==0}"
                       @keyup.enter="join"
                       id="email" placeholder="이메일을 입력하세요."
                       type="text"/>
                <label for="email">이메일</label>
                <div class="error-text" v-if="error.email">
                    {{error.email}}
                </div>

            </div>

            <div class="input-with-label">
                <input v-model="password"
                        @keyup.enter="join"
                       id="password" :type="passwordType"
                       placeholder="비밀번호를 입력하세요."/>
                <label for="password">비밀번호</label>
                <div class="error-text" v-if="error.password">
                    {{error.password}}

                </div>
            </div>

            <div class="input-with-label">
                <input v-model="passwordConfirm" :type="passwordConfirmType"
                        id="password-confirm"
                       placeholder="비밀번호를 다시한번 입력하세요."/>
                <label for="password-confirm">비밀번호 확인</label>
                <div class="error-text" v-if="error.password">
                    {{error.passwordConfirm}}

                </div>
            </div>
        </div>

        <label >
            <input v-model="isTerm" type="checkbox" id="term"/>
            <span>약관을 동의합니다.</span>
        </label>

        <span @click="termPopup=true">약관보기</span>

        <button class="btn btn--back btn--join" v-on:click="join" :disabled="!isSubmit"
                :class="{disabled : !isSubmit}">
            가입하기
        </button>

        </div>

    </div>
</template>

<script>
    import '../../assets/css/style.scss'
    import '../../assets/css/user.scss'
    import PV from 'password-validator'
    import * as EmailValidator from 'email-validator';
    import UserApi from '../../apis/UserApi'
    import router from '../../routes.js'
    export default {
        created(){
            this.passwordSchema // 비밀번호 형식 체크
                .is().min(8)
                .is().max(100)
                .has().digits()
                .has().letters();
        },
        watch: {
            // nickName: function (v) {
            //     this.checkForm();
            // },
            email: function (v) {
                this.checkForm();
            },
            password: function (v) {
                this.checkForm();
            },
            passwordConfirm: function (v) {
                this.checkForm();
            },
        },
        methods: {
            checkForm(){
                // 닉네임 -- 이미사용중인 닉네임이에요

                if (this.email.length > 0 && !EmailValidator.validate(this.email))
                    this.error.email = "이메일 형식이 아닙니다."
                else this.error.email = false;

                if (this.password.length > 0 && !this.passwordSchema.validate(this.password))
                    this.error.password = '영문,숫자 포함 8 자리이상이어야 합니다.'
                else
                    this.error.password = false;
                
                if( !this.error.password ){
                    if(this.passwordConfirm.length >0 && this.password != this.passwordConfirm)
                        this.error.passwordConfirm = "비밀번호가 일치하지 않습니다."
                }
                else
                    this.error.passwordConfirm = false;


                console.log(this.error)
                let isSubmit = true;
                if(this.email.length>0 && this.password.length>0 && this.passwordConfirm.length>0) isSubmit = true;

                Object.values(this.error).map(v => {
                    if (v) {
                        // console.log(v)
                        isSubmit = false;
                    }
                })
                this.isSubmit = isSubmit;
            },
            join(){
                if(this.isSubmit){
                    let {email,nickName,password} = this;
                    let data = {
                        email,nickName,password
                    }

                    // console.log(router)
                    this.isSubmit = false;

                    UserApi.requestJoin(data,res=>{
                        //통신을 통해 전달받은 값 콘솔에 출력
                        console.log('회원가입에 성공하였습니다!!')
                        console.log(res);

                        this.$router.push("join/done/");
                    },error=>{  
                        console.log("회원가입 실패 !!!");
                        alert('회원가입 실패 !!')
                        // 로그인 실패시 버튼 비활성화
                        this.isSubmit = false;
                    })

                    console.log('회원가입 끝')
                }
            }
        },
        data: () => {
            return {
                email: '',
                password: '',
                passwordConfirm: '',
                nickName: '',
                isTerm: false, // 약관동의
                isLoading: false,
                passwordSchema: new PV(),
                error: {                                      
                    email: false,
                    password: false,
                    nickName: false,
                    passwordConfirm: false,
                    term: false
                },
                isSubmit: false,
                passwordType: "password",
                passwordConfirmType: "password",
                termPopup: false,
            }
        }

    }

</script>


