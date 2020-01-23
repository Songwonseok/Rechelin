<template>
    <div class="input-with-label">

        <input v-model="text"
               :id="inputValue" :placeholder="placeholder"
               :class="{error : errorText.length > 0, complete : text.length > 0 && errorText.length === 0}"
               @input="changeInput($event, inputValue)"
               :type="password ? type :'text'"/>
        <label :for="inputValue">{{label}}</label>

        <div class="error-text" v-if="errorText.length>0">
            {{errorText}}
        </div>

        <span @click="viewPassword" v-if="password" :class="{active : type==='text'}" class="eyes-icon">
            <i class="fas fa-eye"></i>
        </span>
    </div>
</template>

<script>

    export default {
        name: "input",
        props : ['inputValue', 'errorText', 'password', 'placeholder', 'label', 'enterInput'],
        methods: {
            viewPassword() {
                this.type = this.type==="password" ? "text" : "password";
            },
            changeInput(event,type){
                this.enterInput(event.target.value, type);
            }
        },
        data: () => {
            return {
                type:"password",
                text:"",
            }
        },
    }
</script>
