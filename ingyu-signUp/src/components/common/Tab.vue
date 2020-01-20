<template>
    <div class="tab-component" :class="{active : isActive}">
        {{tabTitle}}
        <span v-if="count" class="count">
            {{count}}
        </span>
    </div>
</template>

<script>
    export default {
        name: "tab",
        props : ['tabTitle', 'isActive', 'count'],
    }
</script>
