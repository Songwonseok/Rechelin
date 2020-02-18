<template>
  <b-container class="bv-example-row" style="padding-top: 100px !important; padding-bottom: 100px;">
    <h3 style="margin-top: 30px; margin-bottom: 50px;">
      <v-icon large>{{mdiInformationOutline}}</v-icon>Edit User Information
    </h3>
    <b-row>
      <b-col>
        <template v-if="profile==null">
          <img
            class="avatar"
            src="../../assets/images/ssafy.jpg"
            :alt="nickname"
            style="height: 300px; width:300px;"
          />
        </template>
        <template v-else>
          <img class="avatar" :src="profile" :alt="nickname" style="height: 300px; width:300px;" />
        </template>
        <br />
        <b-form-file
          v-model="file"
          @change="load"
          ref="file-input"
          class="mb-2"
          accpt=".jpg, .jpeg, .png"
          style="width: 300px;"
        ></b-form-file>
      </b-col>
      <b-col>
        <p class="changed-info">닉네임 변경</p>

        <b-form-input
          v-model="nickname"
          placeholder="Enter your nickname"
          @keyup.enter="edit"
          style="width: 300px;"
        ></b-form-input>
        <!-- 닉네임 변경 : <input v-model="nickname" type="text" id="nickname" @keyup.enter="edit"
        >-->
        <p class="changed-info">핸드폰 번호 변경</p>

        <b-form-input
          id="phone"
          v-model="phone"
          placeholder="Enter your phone"
          @keyup.enter="edit"
          style="width: 300px;"
        ></b-form-input>

        <div style="padding-top: 30px;" ref="finalSubmit">
          <v-btn class="ma-2" color="warning" @click="edit" style="color: #ff7f00 !important; ">
            <v-icon left>{{ svgPath }}</v-icon>EDIT
          </v-btn>
        </div>
      </b-col>
      <!-- <div style="margin-top: 10px;">
            비밀번호 변경: <input v-model="pw1" type="password" id="pw1" @keyup.enter="edit">
        </div>
        <div v-if="error.pw1">{{error.pw1}}</div>

        <div style="margin-top: 10px;">
            비밀번호 확인: <input v-model="pw2" type="password" id="pw2" @keyup.enter="edit">
            <div v-if="error.confirmPW">{{error.confirmPW}}</div>
      </div>-->

      <!-- TODO : 취소 버튼 눌르면 이전 페이지로 이동 -->
    </b-row>
  </b-container>
</template>

<script>
import PV from "password-validator";
import UserApi from "../../apis/UserApi";
import ImgurApi from "../../apis/ImgurApi";
import router from "../../routes.js";
import { mdiPencil, mdiInformationOutline } from "@mdi/js";
import { jump } from "../../../public/js/animejs";

export default {
  data() {
    return {
      email: this.$store.state.userEmail,
      nickname: "",
      phone: "",
      profile: "",
      passwordSchema: new PV(),
      error: {
        pw1: false,
        confirmPW: false
      },

      isSubmit: true,
      svgPath: mdiPencil,
      mdiInformationOutline
    };
  },
  watch: {
    nickname: function(v) {
      this.checkForm();
    },
    phone: function(v) {
      this.checkForm();
    }
  },
  computed: {
    uid() {
      return sessionStorage.getItem("userid");
    }
  },
  methods: {
    gojump() {
      jump(this.$refs.finalSubmit);
    },
    checkForm() {
      // 기존에 있는 닉네임인지
      // 기존에 등록된 휴대폰 번호인지 확인

    //   if (this.pw1.length > 0 && !this.passwordSchema.validate(this.pw1)) {
    //     this.error.pw1 = "영문, 숫자 포함 8자리 이상이어야 합니다";
    //   } else this.error.pw1 = false;
    //   if (this.pw2.length > 0 && this.pw1 != this.pw2) {
    //     this.error.confirmPW = "비밀번호가 일치하지 않습니다";
    //   } else this.error.confirmPW = false;
      let Editing = true;
      Object.values(this.error).map(err => {
        if (err) {
          Editing = false;
        }
      });
      this.isSubmit = Editing;
    },
    edit() {
      if (this.isSubmit) {
        let { nickname, phone, uid, profile } = this;
        let data = {
          nickname: nickname,
          user: uid,
          phone: phone,
          profile: profile
        };
        this.isSubmit = false;
        console.log("Edit 들어가기전!!!!!!!!!!");
        UserApi.requestEdit(data, res => {    
        },
          error => {
            console.log("회원 정보 수정 실패 !!!");
            alert("회원 정보 수정 실패 !!");
            // 로그인 패시 버튼 비활성화
            this.isSubmit = false;
          }
        );
        // axios 보낸는 장소
        this.$alert("회원 정보 수정 성공", "success", "success", );
      }
    },
    // 프로필 이미지 변경
    load(e) {
      this.createImage(e.target.files[0]);
    },
    createImage(file) {
      ImgurApi.uploadProfile(
        file,
        res => {
          // img url - res.link에 저장
          // 2) Imgur에 저장된 사진 링크를 가져오기
          this.profile = res.data.link;

          // // 3) 사진링크를 User의 profile 링크로 수정하기
          // UserApi.requestUpload(
          //   this.uid, this.profile,res => {
          //     console.log("프로필 수정 성공");
          //   },
          //   error => {
          //     alert("프로필 업로드 실패");
          //   }
          // );
        },
        error => {
          alert("Imgur 업로드 실패!");
        }
      );
    },

    getUser() {
      UserApi.requestId(this.uid,
        res => {
          this.nickname = res.object.nickname;
          this.phone = res.object.phone;
          this.profile = res.object.profile;
          console.log("***유저정보 가져오기 성공");
        },
        error => {
          console.log("유저정보 가져오기 실패");
        }
      );
    }
  },
  created() {
    this.passwordSchema // 비밀번호 형식 체크
      .is()
      .min(8)
      .is()
      .max(100)
      .has()
      .digits()
      .has()
      .letters();
    this.getUser();
  },
  mounted() {
    this.gojump();
  }
};
</script>

<style scoped>
.v-btn__content {
  color: #ff7f00 !important;
}

img {
  padding-bottom: 30px;
  padding-top: 30px;
}
.changed-info {
  text-align: left;
  padding-top: 20px;
}
</style>