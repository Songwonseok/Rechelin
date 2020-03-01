<template>
    <v-container>
        <p 
        class="display-1" 
        style="color:#ff7f00; font-family: 'Sacramento' !important;">
            <!-- <span 
            class="iconify" 
            data-icon="ant-design:like-outlined" 
            data-inline="false">
            </span> -->
             Best Like </p>

                <v-container>
                <v-row
                justify="start">
                
                    <v-container fluid
                    v-if="bestReviews.length==0">
                           <p 
                            class="title"> 
                            해당 리뷰가 존재하지 않습니다. </p>
                    </v-container>
                  
                  <v-flex
                  v-for="(bestReview, i) in bestReviews" 
                  :key="i+10" xs class="ml-5 mr-5" >
                    <!-- 클릭했을때 상세 페이지로 이동 
                                    router-link="router-link" :to="{name: 'storeDetail', params : {id : bestReview.rnum}}"
                            -->
                    <v-card 
                    id="review-image" 
                    :loading="loading_card" 
                    class="mx-auto mr-auto"  
                    max-width="350" width="350" 
                   
                    max-height="500" height="500"
                    @click="storeDetail(bestReview.id)">

                        <v-img  
                        height="280" 
                        :src="bestReview.src" xs4>

                        </v-img>

                        <v-card-title 
                        style="font-family: 'Noto Serif KR'"
                        >{{bestReview.title}}
                        </v-card-title>

                        <v-card-text>
                            

                            <v-container>{{bestReview.black_text_content.slice(0, 30)}}...</v-container>
                            <v-chip-group 
                            active-class="deep-purple accent-4 white--text" 
                            column="column">
                                <div v-for="(tags, i) in bestReview.tag.slice(0, 3)" v-bind:key="i+20">
                                    <v-chip style="font-family: 'Do Hyeon'">#{{tags}}</v-chip>
                                </div>
                            </v-chip-group>
                        </v-card-text>
                       
                    </v-card>
                </v-flex>
               
                </v-row>

            </v-container>
    </v-container>
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
              
            },
           
           
        },
    

    }
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css?family=Do+Hyeon|Nanum+Gothic:700|Nanum+Myeongjo:700|Sacramento&display=swap');
@import url('https://fonts.googleapis.com/css?family=Noto+Serif+KR&display=swap');
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
    -webkit-transform:scale(0.9);
    -moz-transform:scale(0.9);
    -ms-transform:scale(0.9); 
    -o-transform:scale(0.9);  
    transform:scale(0.9);
    -webkit-transition:.3s;
    -moz-transition:.3s;
    -ms-transition:.3s;
    -o-transition:.3s;
    transition:.3s;
    }
    #review-image:hover {
    -webkit-transform:scale(0.95);
    -moz-transform:scale(0.95);
    -ms-transform:scale(0.95);   
    -o-transform:scale(0.95);
    transform:scale(0.95);
    overflow: hidden !important;
    }
</style>