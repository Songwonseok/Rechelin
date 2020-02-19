<template>

    <div>
        <br>
        <h2 style="color:orange"><span class="iconify" data-icon="whh:crown" data-inline="false"></span> Best Views</h2>
        <b-container class="bv-example-row">
            <b-row>
                <div v-if="bestReviews.length==0">
                    <h2> 해당 리뷰가 존재하지 않습니다. </h2>
                </div>
                <b-col v-for="(bestReview, i) in bestReviews" :key="i+bestReview">
                    <v-hover v-slot:default="{ hover }">
                        <!-- 클릭했을때 상세 페이지로 이동 
                                    router-link="router-link" :to="{name: 'storeDetail', params : {id : bestReview.rnum}}"
                            -->
                        <v-card :loading="loading_card" class="mx-auto my-12 mr-2" max-width="350" height="500"
                            @click="storeDetail(bestReview.id)">

                            <v-img height="260" :src="bestReview.src">


                                <v-expand-transition>
                                    <div v-if="hover"
                                        class="d-flex transition-fast-in-fast-out orange darken-2 v-card--reveal display-3 white--text"
                                        style="height: 100%;">
                                        {{bestReview.title}}

                                    </div>
                                </v-expand-transition>
                            </v-img>
                            <v-card-title>{{bestReview.title}}</v-card-title>

                            <v-card-text>
                                <v-row align="center" class="mx-0">
                                    <!-- <v-rating
                                            :value="4.5"
                                            color="amber"
                                            dense="dense"
                                            half-increments="half-increments"
                                            readonly="readonly"
                                            size="14"></v-rating> -->

                                    <!-- <div class="grey--text ml-4">4.5 (413)</div> -->
                                </v-row>

                                <div>{{bestReview.black_text_content}}</div>
                                <p>{{bestReview.address}}</p>
                                <v-divider></v-divider>
                                <v-chip-group active-class="deep-purple accent-4 white--text" column="column">
                                    <div v-for="(tags, i) in bestReview.tag" v-bind:key="i+tags">
                                        <v-chip>{{tags}}</v-chip>
                                    </div>
                                </v-chip-group>
                            </v-card-text>
                            
                        </v-card>
                    </v-hover>
                </b-col>
            </b-row>

        </b-container>

        <best-like-list></best-like-list>
        <best-rate-list></best-rate-list>
    </div>
</template>

<script>
    import bestLikeList from './bestLikeList.vue';
    import bestRateList from './bestRateList.vue';
    import ReviewApi from '../../apis/ReviewApi.js';
     
    export default {
        created() {
            ReviewApi
                .requestfetcViewsrList()
                .then(response => {
                    console.log('bestReviewList');
                    console.log(response);
                    var viewsList = new Array();

                    for (let i = 0; i < response.data.object.length; i++) {
                        var item = {};
                        if (response.data.object[i].picture)
                            item['src'] = response
                            .data
                            .object[i]
                            .picture;
                        else
                            item['src'] = "https://cdn.pixabay.com/photo/2016/12/26/17/28/food-1932466_1280.jpg"
                        item['title'] = response
                            .data
                            .object[i]
                            .title;
                        item['id'] = response.data.object[i].store.num;
                        let content = response
                            .data
                            .object[i]
                            .str + response
                            .data
                            .object[i]
                            .weak;
                        item['black_text_content'] = content;
                        item['address'] = response
                            .data
                            .object[i]
                            .store
                            .address;
                        let HashString = response
                            .data
                            .object[i]
                            .hashtag;
                        let HashSplit = HashString.split(',');
                        let HashList = [];
                        for (let z in HashSplit)
                            HashList.push(HashSplit[z]);
                        item['tag'] = HashList;
                        JSON.stringify(item);
                        viewsList.push(item);
                    }

                    this.bestReviews = viewsList;

                })
                
        },
        components: {
            bestLikeList,
            bestRateList
        },
        data() {
            return {
                loading_card: false,
                selection_card: 1,
                bestReviews: []
            }

        },
        methods: {
            reserve() {
                this.loading = true

                setTimeout(() => (this.loading = false), 2000)
            },
            storeDetail(num) {
                console.log('storeDetail');
                console.log(num);
                this.$store.dispatch('storeHashtags', num)

            }

        },
        watch: {
            selection_card: function (v) {
                console.log(v);

            }
        }

    }
</script>

<style scoped>
    b-row {

    }
    .container>div {
       
        padding-top: 3px;
    }

    .v-card--reveal {
        align-items: center;
        bottom: 0;
        justify-content: center;
        opacity: 0.5;
        position: absolute;
        width: 100%;
    }


</style>