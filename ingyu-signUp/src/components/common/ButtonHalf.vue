<template>
    <div class="wrap btns-half" v-if="buttons">
        <button v-for="(button,index) in buttons" :key="index"
                @click="button.onClick" class="half"
                :class="{highlight : button.highlight}">
            {{button.title}}
        </button>
    </div>
</template>

<script>
    export default {
        name: "buttonHalf",
        props : ['buttons','title'],
    }
</script>
