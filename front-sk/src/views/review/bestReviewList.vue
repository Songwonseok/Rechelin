<template>

    <div>
        <br>
        <h2 style="color:orange">👑 가장 많은 회원이 본 리뷰</h2>
        <b-container class="bv-example-row">
            <b-row md="1"> 
              
                <div v-for="(bestReview, i) in bestReviews" v-bind:key="i+bestReview">
                    
                    <v-card :loading="loading_card" class="mx-auto my-12" max-width="370">
                        <v-img height="250" :src="bestReview.src"></v-img>

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

                          
                            <v-chip-group
                                
                                active-class="deep-purple accent-4 white--text"
                                column="column">
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
                </div>
               
            </b-row>

        </b-container>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                loading_card: false,
                selection_card: 1,
                bestReviews: [
                    {
                        src: 'https://cdn.pixabay.com/photo/2017/05/07/08/56/pancakes-2291908_1280.jpg',
                        title: '싱그러운 과일나라',
                        black_text_content: '여기는 정말 좋당',
                        address : '강남',
                        tag: ['즐거운', '좋은', '재밌는']
                    }, {
                        src: 'https://cdn.pixabay.com/photo/2016/03/05/23/02/barbecue-1239434_1280.jpg',
                        title: '싱그러운 과일나라2',
                        black_text_content: '여기는 정말 좋당2',
                        address : '대전',
                        tag: ['행복한', '여유로운', '슬픈']
                    }, {
                        src: 'https://cdn.pixabay.com/photo/2016/12/26/17/28/food-1932466_1280.jpg',
                        title: '싱그러운 과일나라3',
                        black_text_content: '몰라아몰랑',
                        address : '청주',
                        tag: ['너는', '배고피', '멀캠자취생']
                    }
                ],
            }

        },
        methods: {
            reserve() {
                this.loading = true
                
                setTimeout(() => (this.loading = false), 2000)
            },
            
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
</style>