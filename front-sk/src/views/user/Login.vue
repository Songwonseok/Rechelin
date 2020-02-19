<template>
    <div class="user" id="app">
        
	<div id="wrapper">
        
		<div id="table">
			<h1 class="active" style="color: #ff7f00; text-align:left;">Login</h1>
		</div>
		<div id="signin">
	
				<div class="form-group">
					<label for="username">Email</label>
					<input type="text"  v-model="email" id="username"  required @keyup.enter="login" autofocus>
                    <div class="error-text" v-if="error.email" style="color:#ff7f00;">
                {{error.email}}
            </div>
				</div>
				<div class="form-group">
					<label for="pass">Password</label>
					<input type="password" id="pass" @keyup.enter="login" v-model="password">
					<span id="showpwd" class="fa fa-eye-slash"></span>
                     <div class="error-text" v-if="error.password" style="color:#ff7f00;">
                {{error.password}}
                </div>
				</div>
	
				
                    <v-btn block @click="login" color="#ff7f00" :disabled="!isSubmit" :class="{disabled : !isSubmit}">SignIn</v-btn>
                    <hr>
                    <div style="text-align: left">
                    <h4 >Social Login</h4>
                     <NaverLogin :component="component" />
                    </div>
                    <hr>
		<router-link :to="{name: 'findPW'}" id="froget-pass">Forget Password?</router-link>
        <router-link :to="{name: 'signup'}" id="froget-pass">회원가입 안하셨나요?</router-link>
		
    </div>
		
	</div>
    </div>
</template>

<script>
    /*eslint-disable*/
    import Loading from 'vue-loading-overlay';
    import 'vue-loading-overlay/dist/vue-loading.css';
    import PV from 'password-validator'
    import * as EmailValidator from 'email-validator';
    import KakaoLogin from '../../components/user/snsLogin/Kakao.vue'
    import GoogleLogin from '../../components/user/snsLogin/Google.vue'
    import NaverLogin from '../../components/user/snsLogin/Naver.vue'
    import UserApi from '../../apis/UserApi'
    // import JoinPage from './Join.vue';
    import ImgurApi from '../../apis/ImgurApi'

    export default {
        components: {
            KakaoLogin,
            GoogleLogin,
            // JoinPage,
            NaverLogin,
            Loading
        },
        created() {

            this.component = this;


            this.passwordSchema
                .is().min(8)
                .is().max(100)
                .has().digits()
                .has().letters();

            //this.getProfile();
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
            goSignUp() {
                this.$router.push({
                    name: 'signup'
                })
            },
            goFindPW() {
                this.$router.push({
                    name: 'findPW'
                })
            },
            checkForm() {
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
            },
            login() {

                if (this.isSubmit) {
                    let {
                        email,
                        password
                    } = this;
                    let data = {
                        email,
                        password
                    }

                    //요청 후에는 버튼 비활성화
                    this.isSubmit = false;
                    UserApi.requestLogin(data, res => {
                        //통신을 통해 전달받은 값 콘솔에 출력
                        console.log(res.data);
                        if (res.status === true) {

                            console.log('login form 안 ' + res.object)
                            console.log(res.object);
                          
                            this.$alert("로그인 성공하셨습니다.","success","success");
                           
                            
                            this.$router.push({
                                name: "popular"
                            });
                            
                        } else{
                            this.$alert('아이디 혹은 비밀번호가 틀렸습니다',"warning","waring")
                            this.$router.push({
                                name: 'login'
                            });
                            
                        //요청이 끝나면 버튼 활성화
                        this.isSubmit = true;


                        }

                    }, error => {
                         this.$alert('아이디 혹은 비밀번호가 틀렸습니다',"warning","warning")
                            this.$router.push({
                                name: 'login'
                            });
                        this.isSubmit = true;
                    })
                }



            },
            getProfile() {
                // Axios로 사진 가져오기
                // console.log('프로필 가져오기!!!')

                let email = "ssafy@naver.com";
                let data = {
                    email
                }
                UserApi.requestProfile(email, res => {
                    // console.log(res)
                    this.profile = res.object.profile
                    // console.log(this.profile);
                    // console.log('프로필 가져오기 성공')

                }, error => {
                    // console.log('프로필 가져오기 실패')
                })
            },
            upload() {
                console.log('이미지 업로드 @@')
                // console.log(this.selectedImage)


                ImgurApi.uploadProfile(this.selectedImage, res => {
                    // img url - res.link에 저장
                    // 2) Imgur에 저장된 사진 링크를 가져오기

                    // this.imageUrl = res.data.link
                    this.imageUrl = "https://i.imgur.com/91WnlBF.png" // ######TEST 용
                    // console.log(this.imageUrl)
                    this.email = "ssafy@naver.com" // ######TEST 용


                    // 3) 사진링크를 User의 profile 링크로 수정하기
                    UserApi.requestUpload(this.email, this.imageUrl, res => {
                        // status로 판단
                        console.log(res)
                        if (res.status == true)
                            console.log('프로필 업로드 성공!')
                    }, error => {
                        alert('프로필 업로드 실패')
                    })

                }, error => {
                    alert('Imgur 업로드 실패!')
                })

            },
            getProfileForm(event) {
                this.selectedImage = event.target.files[0];
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
                profile: '',
                imageUrl: '',
                selectedImage: '',
              
            }
        }
    }
</script>

<style scoped>
    @keyframes signIn {
        0% {
            opacity: 0;
            -webkit-transform: scale3d(0.3, 0.3, 0.3);
            -ms-transform: scale3d(0.3, 0.3, 0.3);
            transform: scale3d(0.3, 0.3, 0.3);
        }

        20% {
            -webkit-transform: scale3d(1.1, 1.1, 1.1);
            -ms-transform: scale3d(1.1, 1.1, 1.1);
            transform: scale3d(1.1, 1.1, 1.1);
        }

        40% {
            -webkit-transform: scale3d(0.9, 0.9, 0.9);
            -ms-transform: scale3d(0.9, 0.9, 0.9);
            transform: scale3d(0.9, 0.9, 0.9);
        }

        60% {
            opacity: 1;
            -webkit-transform: scale3d(1.03, 1.03, 1.03);
            -ms-transform: scale3d(1.03, 1.03, 1.03);
            transform: scale3d(1.03, 1.03, 1.03);
        }

        80% {
            -webkit-transform: scale3d(0.97, 0.97, 0.97);
            -ms-transform: scale3d(0.97, 0.97, 0.97);
            transform: scale3d(0.97, 0.97, 0.97);
        }

        100% {
            opacity: 1;
            -webkit-transform: scale3d(1, 1, 1);
            -ms-transform: scale3d(1, 1, 1);
            transform: scale3d(1, 1, 1);
        }

    }

    .user {

        -webkit-animation: signIn 0.75s ease-in-out 0.5s;
        animation: signIn 0.75s ease-in-out 0.5s;
        -webkit-animation-fill-mode: both;
        animation-fill-mode: both;
        opacity: 0;
    }
    #wrapper {
	width: 430px;
	background: url(https://cdn.pixabay.com/photo/2012/02/29/12/17/break-18987__340.jpg);
	margin: 25px auto;
	padding: 64px;
	background-size: cover;
	position: relative;
	z-index: 1;
	box-shadow: 0px 15px 20px 0px rgba(128, 128, 128, 0.76);
}

#wrapper:before {
	content: "";
	position: absolute;
	top: 0;
	left: 0;
	right: 0;
	bottom: 0;
	
	z-index: -1;
}

#table {
	margin-bottom: 6em;
}

#table a {
	text-transform: uppercase;
	margin-right: 40px;
	padding: 11px 4px;
	color: #bbb;
	cursor: pointer
}

#table a.active {
	border-bottom: 1.5px solid #ff7f00;
	color: #fff;
}

label {
	display: block;
    font-size: 1.3rem;
}

form {
	margin-bottom: 3.3em;
}

.form-group {
	position: relative;
	margin-bottom: 16px;
}

.form-group label {
	display: block;
	margin-bottom: 6px;
	font-size: 14px;
	margin-left: 14px;
	color: #bbb;
}

input {
	width: 100%;
	background: rgba(0, 0, 0, 0.42);
	outline: none;
	padding: 10px 14px;
	color: #fff;
	border: none;
	border-radius: 36px;
	font-family: 'Ubuntu', sans-serif;
	font-size: 16px;
	transition: background 0.5s ease-in-out;
}
span#showpwd {
    position: absolute;
    top: 32px;
    right: 16px;
    cursor: pointer;
}
input:focus {
	background: rgba(0, 0, 0, 0.6);
}


#checkbox {
	color: #fff;
	cursor: pointer;
	font-size: 16px
}

@-webkit-keyframes scalecheck {
	0% {
		transform: scale(0);
	}
	90% {
		transform: scale(1.4);
	}
	100% {
		transform: scale(1);
	}
}

.hr {
	height: 1.4px;
	background: rgba(128, 128, 128, 0.51);
	border-radius: 17px;
	margin-bottom: 33px;
}

#froget-pass {
	text-align: center;
	color: #bbb;
	margin: 0;
	display: block;
}
#froget-pass:hover {
    font-size: 130%;
    color: #ff7f00;
}

@media screen and (max-width :490px) {
	body {
		display: table;
		width: 100%;
	}
	#wrapper {
		width: auto;
		height: 100vh;
		margin: 0;
		display: table-cell;
		vertical-align: middle;
	}
}

@media screen and (max-width :490px) {
	#wrapper {
		padding: 28px;
	}
}
</style>