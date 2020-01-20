<template>
    <div class="toast-component" :class="{error:isError}">
        <h5>
            {{toastTitle}}
        </h5>
        <button v-if="isCancel">
            취소
        </button>
    </div>
</template>

<script>
    export default {
        name: "toast",
        props : ['toastTitle', 'isError', 'isCancel'],
    }
</script>
