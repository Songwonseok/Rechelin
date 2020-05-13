<template>
    <div class="keyword-del">
        <p class="title">{{keywordTitle}}</p>
        <button>
            <i class="fas fa-times"></i>
        </button>
    </div>
</template>

<script>
    export default {
        name: "keywordDel",
        props : ['keywordTitle'],
    }
</script>
