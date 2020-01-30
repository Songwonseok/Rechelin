<style lang="scss">
</style>
<template>
    <div class="user" style=" display: flex;" id="login" >
        <div class="wrapC" style="
    text-align: center;
">
            <h1 >로그인 해주세요! <br>오늘도 즐겁게 🤣</h1>


            <div class="input-with-label">
                <input v-model="email" v-bind:class="{error : error.email, complete:!error.email&&email.length!==0}"
                       @keyup.enter="login"
                       id="email" placeholder="이메일을 입력하세요."
                       type="text" style="
            background-color: white;
            color: black;
            border: 2px solid #008Cy
            BA;"/>
                <label for="email">이메일</label>
                <div class="error-text" v-if="error.email">
                    {{error.email}}
                </div>
            </div>

            <div class="input-with-label">
                <input v-model="password" type="password"
                       v-bind:class="{error : error.password, complete:!error.password&&password.length!==0}"
                       id="password"
                       @keyup.enter="login"
                       placeholder="비밀번호를 입력하세요." style="background-color: white; color: black; border: 2px solid rgb(0, 140, 186);"/>
                <label for="password">비밀번호</label>
                <div class="error-text" v-if="error.password">
                    {{error.password}}

                </div>
            </div>
            <button class="btn btn--back btn--login" v-on:click="login" :disabled="!isSubmit"
                    :class="{disabled : !isSubmit}">
                로그인

            </button>


            <div class="sns-login" style="
    text-align: center;">
                <div class="text">
                    <p>SNS 간편 로그인</p>
                    <div class="bar"></div>
                </div>


                <!-- 소셜 로그인 -->
                <NaverLogin :component="component" />
                <button v-on:click="NaverLogin"></button>

                <a :href=naverLoginURL>네이버로 로그인</a>
                <kakaoLogin :component="component" v-on:click="NaverLogin"/>
                <GoogleLogin :component="component"/>

            </div>
            <div class="add-option">
                <div class="text">
                    
                    <div class="bar"></div>
                </div>
                <div class="wrap">
                    <p>비밀번호를 잊으셨나요? </p>
                    <router-link v-bind:to="{name:'searchPassword'}" class="btn--text">👉비밀번호 찾기</router-link>
                </div>
                <div class="wrap">
                    <p>아직 회원이 아니신가요? </p>
                    <router-link v-bind:to="{name:'signUpForm'}" class="btn--text">👉가입하기</router-link>
                </div>
            </div>
        </div>

    </div>
</template>

<script>
     /*eslint-disable*/
    import '../../assets/css/style.scss'
    import '../../assets/css/user.scss'
    import PV from 'password-validator'
    import * as EmailValidator from 'email-validator';
    import KakaoLogin from '../../components/user/snsLogin/Kakao.vue'
    import GoogleLogin from '../../components/user/snsLogin/Google.vue'
    import NaverLogin from '../../components/user/snsLogin/Naver.vue'
    import UserApi from '../../apis/UserApi'
    import JoinPage from './Join.vue';
    
    export default {
        components: {
            KakaoLogin,
            GoogleLogin,
            JoinPage,
            NaverLogin,
        },
        created(){

            this.naverLoginURL += '&client_id=' + this.CLIENT_ID
            this.naverLoginURL += '&redirect_uri=' + this.redirectURI
            this.naverLoginURL += '&state=' + this.state

            console.log(this.naverLoginURL)
            this.component = this;


            this.passwordSchema
                .is().min(8)
                .is().max(100)
                .has().digits()
                .has().letters();


        },
        watch: {
            password: function (v) {
                this.checkForm();
            },
            email: function (v) {
                this.checkForm();
            },
        },
        methods: {
            checkForm(){
                if (this.email.length >= 0 && !EmailValidator.validate(this.email))
                    this.error.email = "이메일 형식이 아닙니다."
                else this.error.email = false;

                if (this.password.length >= 0 && !this.passwordSchema.validate(this.password))
                    this.error.password = '영문,숫자 포함 8 자리이상이어야 합니다.'
                else
                    this.error.password = false;


                let isSubmit = true;
                Object.values(this.error).map(v => {
                    if (v) isSubmit = false;
                })
                this.isSubmit = isSubmit;
            }
            ,login(){

                if (this.isSubmit) {    
                    let {email,password} = this;
                    let data = {
                        email,password
                    }

                    //요청 후에는 버튼 비활성화
                    this.isSubmit = false;
                    this.$router.push({ path: '/signUpForm' });
                    UserApi.requestLogin( data,res=>{
                        //통신을 통해 전달받은 값 콘솔에 출력
                        console.log(res);
                        if(res == "success")    
                            this.$router.push({ path: '/signUpForm' });
                        else
                            this.$router.push({ path: '/signUpForm' });
                        //요청이 끝나면 버튼 활성화
                        this.isSubmit = true;
                    },error=>{  
                        this.isSubmit = true;
                    })
                }

                    console.log('로그인 끝')

            },
            NaverLogin(){
                //////////////////////// 카카오 버튼을 누르면 네이버로 로그인 -- 기능되나 테스트만
                console.log('HELLO')
            }
        },
        data: () => {
            return {
                email: '',
                password: '',
                passwordSchema: new PV(),
                error: {
                    email: false,
                    passowrd: false,
                },
                isSubmit: false,
                component: this,
                CLIENT_ID: 'yW3gT9TqzIgQqklEfEBF',
                naverLoginURL: 'https://nid.naver.com/oauth2.0/authorize?response_type=code',
                redirectURI: 'http://localhost:8080/account/naverlogin',
                state: 123
            }
        }
    }
</script>

<style scoped>
.btn.disabled {
    /* background: #ccc; */
    /* display: inline-block; */
    padding: 3px 25px;
    font-size: 20px;
    cursor: pointer;
    text-align: center;
    text-decoration: none;
    outline: none;
    color: #fff;
    background-color: #FF9800;
    border: none;
    border-radius: 20px;
    box-shadow: 0 9px #999;
}

.user#login .text .bar {
    height: 1px;
    background-color: #238bde;
    float: right;
    margin-top: 9px;
    margin-bottom: 20px;
}
.user#login .add-option p {
    float: initial;
}

.user#login .add-option a {
    color: #000;
    float: unset;
    font-weight: 600;
}
.btn--back{
    borderradius: 3px;
    padding: 3px 25px;
    font-size: 20px;
    cursor: pointer;
    text-align: center;
    text-decoration: none;
    outline: none;
    color: #fff;
    background-color: #238bde;
    border: none;
    border-radius: 20px;
}
</style>
