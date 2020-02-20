<template>

    <div>
        <br>
        <h2 style="color:orange"><span class="iconify" data-icon="ant-design:like-outlined" data-inline="false"></span> Best Like </h2>
       <b-container class="bv-example-row">
                <b-row >
                    <div v-if="bestReviews.length==0">
                            <h2> 해당 리뷰가 존재하지 않습니다. </h2>
                    </div>
                  <b-col v-for="(bestReview, i) in bestReviews" :key="i+10">
                    <!-- 클릭했을때 상세 페이지로 이동 
                                    router-link="router-link" :to="{name: 'storeDetail', params : {id : bestReview.rnum}}"
                            -->
                    <v-card id="review-image" :loading="loading_card" class="mx-auto my-12 mr-2"  max-width="350" width="350" height="450"
                        @click="storeDetail(bestReview.id)">

                        <v-img  height="250" :src="bestReview.src">


                        </v-img>
                        <v-card-title>{{bestReview.title}}</v-card-title>

                        <v-card-text>
                            

                            <div>{{bestReview.black_text_content.slice(0, 30)}}...</div>
                            <v-chip-group active-class="deep-purple accent-4 white--text" column="column">
                                <div v-for="(tags, i) in bestReview.tag.slice(0, 4)" v-bind:key="i+20">
                                    <v-chip>{{tags}}</v-chip>
                                </div>
                            </v-chip-group>
                        </v-card-text>
                       
                    </v-card>


                </b-col>
                </b-row>

            </b-container>
    </div>
</template>

<script>
    import ReviewApi from '../../apis/ReviewApi.js';
    export default {
        created() {
                ReviewApi
                    .requestfetchLikeList()
                    .then(response => {
                        console.log('!!!!!!!!!!!!!!!!!'+response.data.object.length)
                        console.log(response);
                        var LikeList = new Array();

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
                            LikeList.push(item);
                        }
                       
                        this.bestReviews = LikeList;

                    })
            },
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
                    },
                    
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
 #review-image {
        -webkit-transform:scale(1);
    -moz-transform:scale(1);
    -ms-transform:scale(1); 
    -o-transform:scale(1);  
    transform:scale(1);
    -webkit-transition:.3s;
    -moz-transition:.3s;
    -ms-transition:.3s;
    -o-transition:.3s;
    transition:.3s;
    }
    #review-image:hover {
    -webkit-transform:scale(0.9);
    -moz-transform:scale(0.9);
    -ms-transform:scale(0.9);   
    -o-transform:scale(0.9);
    transform:scale(0.9);
    overflow: hidden !important;
    }
</style>