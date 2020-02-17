<template>

    <div>
        <br>
        <h2 style="color:orange">👀 Best Rate</h2>
        <b-container class="bv-example-row">
                <b-row >
                    <div v-if="bestReviews.length==0">
                            <h2> 해당 리뷰가 존재하지 않습니다. </h2>
                    </div>
                        <div v-for="(bestReview, i) in bestReviews" v-bind:key="i+bestReview">
                            <v-hover v-slot:default="{ hover }">
                            <v-card :loading="loading_card" class="mx-auto my-12 mr-2" max-width="350" height = "600"
                                @click="storeDetail(bestReview.id)"
                                >
                                <v-img height="250" :src="bestReview.src">
                                    <!--      -->
                                <v-expand-transition> 
                                    <div
                                        v-if="hover"
                                        class="d-flex transition-fast-in-fast-out orange darken-2 v-card--reveal display-3 white--text"
                                        style="height: 100%;">
                                        {{bestReview.title}}
                                    </div>
                                </v-expand-transition>
                                </v-img>
                                <v-card-title>{{bestReview.title}}</v-card-title>

                                <v-card-text>
                                    <v-row align="center" class="mx-0">
                                        <v-rating
                                            :value="4.5"
                                            color="amber"
                                            dense="dense"
                                            half-increments="half-increments"
                                            readonly="readonly"
                                            size="14"></v-rating>

                                        <div class="grey--text ml-4">4.5 (413)</div>
                                    </v-row>

                                    <div>{{bestReview.black_text_content}}</div>
                                </v-card-text>

                                <v-divider class="mx-4"></v-divider>

                                <v-card-title>{{bestReview.address}}</v-card-title>

                                <v-card-text>

                                    <v-chip-group active-class="deep-purple accent-4 white--text" column="column">
                                        <div v-for="(tags, i) in bestReview.tag" v-bind:key="i+tags">
                                            <v-chip @click="btnClick">{{tags}}</v-chip>
                                        </div>
                                    </v-chip-group>

                                </v-card-text>

                                <v-card-actions>
                                    <v-btn color="deep-purple accent-4" text="text" @click="reserve">
                                        Reserve
                                    </v-btn>
                                </v-card-actions>
                            </v-card>
                          </v-hover>
                        </div>
                </b-row>

            </b-container>
    </div>
</template>

<script>
    import ReviewApi from '../../apis/ReviewApi.js';
    export default {
        created(){

            ReviewApi.requestfetchScoreList().then(
                response =>{
                   var rateList = new Array();
                    console.log('Review');
                    console.log(response);
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
                            item['id'] = response.data.object[i].rnum;
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
                            let HashSplit = HashString.split(' ');
                            let HashList = [];
                            for (let z in HashSplit) 
                                HashList.push(HashSplit[z]);
                            item['tag'] = HashList;
                            JSON.stringify(item);
                            rateList.push(item);
                        }
                        
                        this.bestReviews = rateList;

                }
            )
        },
        data() {
            return {
                loading_card: false,
                selection_card: 1,
                bestReviews: [
                   
                ],
            }

        },
        methods: {
            reserve() {
                this.loading = true
                
                setTimeout(() => (this.loading = false), 2000)
            },
            storeDetail(num) {
                this.$store.dispatch('storeHashtags', num)

            }
            
        },
        watch :{
            selection_card : function(v){
                console.log(v);
              
            }
        }

    }
</script>

<style scoped>
.container > div {
    padding-top: -20px;
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