<template>
    <div class="textarea-wrap">
        <h4>
            {{label}}
        </h4>
        <span>{{contents.length}}/{{this.maxLength}}</span>
        <textarea v-model="contents" :placeholder="placeholder"/>
    </div>
</template>

<script>
    export default {
        name: "textarea",
        props : ['placeholder', 'label', 'maxLength'],
        data: () => {
            return {
                contents:"",
            }
        },
        watch:{
            contents : function(value){
                let length = this.maxLength;
                value = value.length > length ? value.substr(0, length) : value;

                this.contents = value;
            }
        },
    }
</script>
