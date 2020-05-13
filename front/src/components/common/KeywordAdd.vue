<template>
    <div class="keyword-add">
        <input v-model="keyword"  type="text"
               placeholder="키워드를 입력해주세요." />
        <button>
            <i class="fas fa-plus"></i>
        </button>
    </div>
</template>

<script>
    export default {
        name: "keywordAdd",
        data: () => {
            return {
                keyword:"",
            }
        }
    }
</script>
