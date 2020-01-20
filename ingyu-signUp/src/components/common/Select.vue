<template>
    <select class="select-component">
        <option :value="null" disabled selected>선택해주세요</option>
        <option v-for="target in options" :value="target" :key="target.value">
            {{target.title}}
        </option>
    </select>
</template>

<script>
    export default {
        name: "select",
        props : ['options'],
    }
</script>
