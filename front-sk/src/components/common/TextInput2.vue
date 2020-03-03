<template>
  <ValidationProvider
    class="relative appearance-none w-full TextInput"
    tag="div"
    :vid="vid"
    :rules="rules"
    :name="name || label"
    v-slot="{ errors, required, ariaInput, ariaMsg }"
  >
    <input
      :id="name"
      :type="type"
      :placeholder="placeholder"
      ref="input"
      v-model="innerValue"
      v-bind="ariaInput"
      style="text-align: center; width:350px;"
    >
  <div id="nickButton">
    <v-btn color="warning" dark v-if="propsdata"  v-on:click="nameFunction" style="width:350px;">중복확인</v-btn>
    <!--v-if="propsdata" -->
    <v-btn color="warning" v-else :disabled="propsdata" dark style="width:350px;">{{CheckBold}}중복 확인 되셨습니다.</v-btn>
</div>
    <p></p>
    <label
      class="absolute block inset-0 w-full px-2 py-2 leading-normal"
      @click="$refs.input.focus()"
      :for="name"
      :class="{ 'text-gray-700': !errors[0], 'text-red-600': errors[0] }"
    >
      <!-- <span>{{ label || name }}</span>
      <span>{{ required ? ' *' : '' }}</span> -->
    </label>
    <span
      class="block text-red-600 text-xs absolute bottom-0 left-0"
      v-bind="ariaMsg"
      v-if="errors[0]"
      style ="color:red"
    >{{ errors[0] }}</span>
  </ValidationProvider>
</template>

<script>
  import {
   mdiCheckBold
  } from '@mdi/js'
import { ValidationProvider } from "vee-validate";
import UserApi from '../../apis/UserApi';
export default {
 
  name: "TextInput2",
  components: {
    ValidationProvider
  },
  
  props: {
    propsdata : Boolean,
    vid: {
      type: String,
      default: undefined
    },
    name: {
      type: String,
      default: ""
    },
    label: {
      type: String,
      default: ""
    },
    rules: {
      type: [Object, String],
      default: ""
    },
    placeholder: {
      type: String,
      default: ""
    },
    type: {
      type: String,
      default: "text",
      validator(value) {
        return [
          "url",
          "text",
          "password",
          "tel",
          "search",
          "number",
          "email"
        ].includes(value);
      }
    },
    value: {
      type: null,
      default: ""
    }
  },
  data: () => ({
    innerValue: "",
    icons: {
      CheckBold : mdiCheckBold
    },
    propsdata_input : true,
  }),
  computed: {
    hasValue() {
      return !!this.innerValue;
    },
    inputComputed(){
      return this.innerValue;
    }
  },
  watch: {
    innerValue(value) {
      this.$emit("input", value);
    },
    value(val) {  
      if (val !== this.innerValue) {
        this.innerValue = val;
      }
    },
  },
  created() {
    if (this.value) {
      this.innerValue = this.value;
    }
  },
  methods:{
    nameFunction(){
      // let data ={innerValue},
      let data = this.innerValue;
      
      UserApi.requestName(data, res=>{
          this.$emit('statusName', res.status); 
      })
      
    } 
  }

};

</script>


<style lang="scss" scoped>
#nickButton {
  margin-top: 15px;
}
.TextInput {
  padding-bottom: 18px;
  input {
    z-index: 99999;
    padding-top: 1.4rem;
    background-color: rgba(2, 2, 2, 0.07);
    border: 1px solid rgba(0, 0, 0, 0.02);
    border-radius: 8px;

    &.has-value,
    &:focus {
      outline: none;
    }
  }

  label {
    margin-top: 1rem;
    user-select: none;
  }

  input.has-value ~ label,
  input:focus ~ label {
    font-size: 0.6rem;
    margin-top: 0;
    transition: all 0.2s ease-in-out;
  }
}
.TextInput input[data-v-29d5bae9] {
    z-index: 99999;
    padding-top: 1.4rem;
    width: 100%;
}
.TextInput[data-v-29d5bae9] {
    padding-bottom: 18px;
    margin-bottom: 1rem;
}
.btn-secondary {
    width: auto;
    width: 100%;
    color: #3480b6;
    background-color: #6c757d;
    border-color: #6c757d;
    width: 100%;
    margin-bottom: 10px;
    text-align: center;
}
.spa {
    background-color: aliceblue;
    border: none;
    color: white;
    padding: 7px 50px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    margin: 4px 2px;
    cursor: pointer;
    color: cornflowerblue;
    height: 40px;
    margin-top: 10px;
    width : 20%;
}
.spa2 {
         background-color: aliceblue;
    border: none;
    color: white;
    padding: 7px 50px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    margin: 4px 2px;
    cursor: pointer;
    color: cornflowerblue;
    height: 40px;
    margin-top: 10px;
    width : 20%;
}
.pl-3, .px-3 {
    padding-left: 1rem !important;
    width: 80%;
}
.TextInput[data-v-10f94b99] {
    padding-bottom: 18px;
    margin-bottom: 15px;
}
</style>
