
<template>

  <div class = "userSignUp" id="signUp" style="
    padding: 20px;background-color: papayawhip;
">
     <h1 style="
    text-align: center;
">회원가입 🐤</h1>
    <div class="input-with-label">  
       <input v-model="name" id = "name" placeholder ="아이디 작성해주세요." type="text"/>
       <label from ="name">이름 : </label>  
    </div>
      <div class="input-with-label">  
     <label from ="password">비밀번호 :</label>
     <input v-model="password" id ="password" placeholder="비밀번호를 작성해주세요." type="text"/>
     </div>

    <!--email -->
      <div class="input-with-label">
    
     <input v-model="email" id ="email" placeholder="이메일을 작성해주세요." type="text"/>
     <label from ="email">이메일 : </label>
    </div>

    <!--약관 동의 버튼 -->
  <div class = "bottom"  id="bottomFirst">
    <div class ="subBottom" id="subBottomFirst">
      <b-button id="show-btn" @click="$bvModal.show('bv-modal-example')">약관 보기</b-button>
        <b-modal id="bv-modal-example" hide-footer>
          <template v-slot:modal-title>
            이용 약관
          </template>
          <div class="d-block text-center">
            <h3>약관 동의 할꺼니? 동의 누르렴</h3>
          </div>
          <b-button class="mt-3" block @click="$bvModal.hide('bv-modal-example')">닫기</b-button>
        </b-modal>
        </div>     

        <b-form-group style="
    text-align: center;">
              <b-form-radio-group id="radio-group" name="radio-sub-component">
                <b-form-radio v-model="selected" name="some-radios" value="A">약관 동의</b-form-radio>
                <b-form-radio v-model="selected" name="some-radios2" value="B">약관 동의하지 않음</b-form-radio>
              </b-form-radio-group>
      </b-form-group>

      <div class ="btnTwo" id="btnTag">
            <b-button pill variant="outline-danger" router-link v-bind:to="{name:'Login'}">돌아가기</b-button>
            <b-button pill variant="outline-danger" router-link v-bind:to="{name:'Login'}">완료</b-button>
            
      </div>
        </div>
          </div>
      
</template>

<script>
import '../../assets/css/style.scss'
import '../../assets/css/user.scss'
import '../../assets/css/signup.scss'
export default {
    data: () => {
        return {
            name: '',
            password: '',
            email: '',
            isSubmit : false
        }
    }
}
</script>

<style scoped>
.userSignUp#signUp{
  color: #000;
}
.nameInput#nameTag {
    width: 100%;
    padding: 12px 20px;
    margin: 8px 0;
    display: inline-block;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
    /* border: 2px solid rgb(0, 140, 186); */
}
#name {
    
    border: 2px solid rgb(0, 140, 186);
    /* color: white; */
    background-color: white;
}
#password {
    border: 2px solid rgb(0, 140, 186);
    /* color: white; */
    background-color: white;

}
#email {
      border: 2px solid rgb(0, 140, 186);
    /* color: white; */
    background-color: white;

}
.input-with-label input[type="text"], .input-with-label input[type="password"] {
    width: 100%;
    float: left;
    height: 50px;
    line-height: 1em;
    padding: 0 15px 0 105px;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
    color: #000;
    border: 1px solid #000;
    border-radius: 3px;
}
.btn-secondary {
    color: #fff;
    background-color: #6c757d;
    border-color: #6c757d;
    /* padding: 20px; */
    /* width: 100px; */
    /* padding: 20px 450px 30px 50px; */
    width: 30;
    width: 100%;
    height: 10%;
}
.form-group {
    margin-bottom: 1rem;
    margin-top: 1rem;
}
.pt-0, .py-0 {
    padding-top: 0 !important;
    text-align: center;
}
.btnTwo#btnTag {
    text-align: center;
}
.rounded-pill {
    border-radius: 50rem !important;
    height: 30%;
    margin-left: 20px;
}
</style>

