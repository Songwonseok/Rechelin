<template>
    <label class="checkbox-component">
        <input type="checkbox" v-model="check" :value="checkValue">
        <span class="title">{{label}}</span>
    </label>
</template>

<script>
    export default {
        name: "checkbox",
        props : ['checkValue','label'],
    }
</script>
