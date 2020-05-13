<template>
    <button @click="toggleSwitch" :class="{active:isOn}" class="switch-component"></button>
</template>

<script>
    export default {
        name: "switch",
        data: function () {
            return {
                isOn:false,
            }
        },
        methods: {
            toggleSwitch(){
                this.isOn = !this.isOn
            },
        }
    }
</script>
