import Vue from "vue";
import { extend, localize } from "vee-validate";
import { required, email, min, confirmed } from "vee-validate/dist/rules";
import ar from "vee-validate/dist/locale/ar.json";

//import en from "vee-validate/dist/locale/en.json";
//import kr from "vee-validate/dist/locale/kr.json";

// Install required rule.
extend("required", required);

// Install email rule.
extend("email", email);

// Install min rule.
extend("min", min);

extend("confirmed", confirmed);
// Install English and Arabic localizations.
localize({
  en: {
    messages: {
       required : '입력해주세요'
       ,
       confirmed: "비밀번호가 일치하지 않습니다."
        
    },
    names: {
      email: "E-mail Address",
      password: "Password"
    },// }, message와 names 는 잘모르겠음 document 참조 바람.
    fields: {
      name : {
        required : '이름을 공백으로 둘 수 없습니다.'
        , max : "글자는 최대 5글자 입니다."
      },
      email :{
        required : '이메일을 공백으로 둘 수 없습니다.',
        email: "이메일 양식을 정확히 지켜주세요."
      },
      password: {
        required : '비밀번호는 공백으로 둘 수 없습니다.',
        min: "비밀번호가 너무 짧습니다. 최소 8자리 입니다.",
        regex : "특수문자, 문자, 숫자 3가지 양식이 포함되어야 합니다.",
        confirmed: "비밀번호가 일치하지 않습니다."
    },
    re_password: {
        required : '비밀번호는 공백으로 둘 수 없습니다.',
        min: "비밀번호가 너무 짧습니다. 최소 8자리 입니다.",
        confirmed: "비밀번호가 일치하지 않습니다."
      },
      
      
    }
  },
});

let LOCALE = "en";

// A simple get/set interface to manage our locale in components.
// This is not reactive, so don't create any computed properties/watchers off it.
Object.defineProperty(Vue.prototype, "locale", {
  get() {
    return LOCALE;
  },
  set(val) {
    LOCALE = val;
    localize(val);
  }
});
