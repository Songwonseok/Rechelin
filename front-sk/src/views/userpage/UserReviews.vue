<template>
    <div>
        <h2>음식점 북마크</h2>
        <carousel-3d>
            <slide v-for="(bookmark, index) in UserBookmarks" :key="key-`${index}`" :index="index" >
                <img :src="bookmark.picture">
            </slide>
        </carousel-3d>
        <h2>작성한 리뷰</h2>
        <carousel-3d>
            <slide v-for="(review, index) in UserReviews" :key="key-`${index}`" :index="index" >
                <img :src="review.picture">
            </slide>
        </carousel-3d>
      </div>
</template>

<script>
import { Carousel3d, Slide } from 'vue-carousel-3d';

export default {
    components: {
        Carousel3d,
        Slide
    },
    computed : {
        UserBookmarks() {
            return this.$route.params.bookmarks
        },
        UserReviews () {
            return this.$route.params.reviews
        }
    },
    created() {
        console.log(this.$route.params.reviews)
    }
}

</script>
