<template>
    <div class="header-default">

        <button class="menu" v-if="!isBack"></button>
        <button v-if="isBack" class="back">
            <i class="fas fa-chevron-left"></i>
        </button>

        <h4 class="title">
            {{headerTitle}}
        </h4>

        <button v-if="rightText" class="right-text" :class="{disabled:isDisabled}" :disabled="isDisabled">
            {{rightText}}
        </button>

        <button class="btn-search" v-if="isSearch">
            <i class="fas fa-search"></i>
        </button>
    </div>
</template>

<script>
    export default {
        name: "header",
        props : ['headerTitle', 'isBack', 'isSearch', 'rightText', 'isDisabled'],
    }
</script>
