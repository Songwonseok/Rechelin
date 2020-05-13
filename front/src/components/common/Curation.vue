<template>
    <div class="wrap curation-component" :class="{selected:isSelect}">
        <h4 class="title">
            {{curationTitle}}
        </h4>

        <div class="btns" v-if="!isSelect">
            <button @click="selectTriggle">선택</button>
            <button>보기</button>
        </div>
        <span v-if="isSelect" class="select-text" @click="cancelSelect">
            선택됨
        </span>



    </div>
</template>

<script>
    export default {
        name: "curation",
        props : ['curationTitle'],
        data: () => {
            return {
                isSelect:false,
            }
        },
        methods: {
            selectTriggle(){
                this.isSelect = true
            },
            cancelSelect() {
                this.isSelect = false;
            }
        },
    }
</script>
