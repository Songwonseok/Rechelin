<template>
    <div id="app">
        <v-app id="inspire_store">
            <!--To do bar-->
            <v-card class="pa-4" flat >

                <v-toolbar dense floating>
                    <v-text-field hide-details prepend-icon="search" @keyup.enter="storeListSearch" single-line
                        v-model="ssearch"></v-text-field>
                    <v-btn color="warning" icon @click="getMylocation">
                        <v-icon color="warning">my_location</v-icon>
                    </v-btn>
 
                </v-toolbar>
                <gmap-map :center="{lat: myLocation.latitude, lng: myLocation.longitude}" :zoom="16" style="height: 300px">
                    <gmap-marker :position="{lat: myLocation.latitude, lng: myLocation.longitude}" 
                    :clickable="true" :draggable="true" :icon="{ url: require('../../assets/images/recent.png')}"
                        @click="center=m.position">
                    </gmap-marker>
                </gmap-map>
            </v-card>
            <hr>
            <div v-if="this.$store.state.isClickBtnStore == true">
                <b-container class="bv-example-row">
                    <b-row>
                        <div v-for="(store, i) in this.$store.state.storeList_paging" v-bind:key="i+store">
                            <v-hover v-slot:default="{ hover }">
                                <v-card class="mx-auto mr-10 mb-2" max-width="500" width = "300" height="300" @click="storeDetail(store.num)">
                                    <v-img
                                        class="white--text align-end"
                                        height="200px"
                                        :src="`https://maps.googleapis.com/maps/api/place/photo?maxwidth=600&photoreference=${store.img}&sensor=true&key=AIzaSyDC4sonH281FHJ-YyPmeXLRdBYuqcjUkGE`">
                                        <v-expand-transition>
                                            <div v-if="hover"
                                                class="d-flex transition-fast-in-fast-out orange darken-2 v-card--reveal display-3 white--text"
                                                style="height: 100%;">
                                                <v-card-title>{{store.sname}}</v-card-title>
                                            </div>
                                        </v-expand-transition>
                                    </v-img>
                                    <v-card-subtitle class="pb-0">{{store.sname}}</v-card-subtitle>
                                    <v-card-text class="text--primary">
                                        <div>{{store.address}}</div>
                                    </v-card-text>
                                    <v-card-actions>
                                        <v-btn color="orange" text="text">
                                            Share
                                        </v-btn>
                                        <v-btn color="orange" text="text">
                                            Explore
                                        </v-btn>
                                    </v-card-actions>
                                </v-card>
                            </v-hover>
                        </div>
                    </b-row>
                </b-container>
                <div class="text-center">
                    <div v-if="this.$store.state.pageStatus">
                        <v-pagination v-model=page :length=this.len @input="next"></v-pagination>
                        <!-- 현재 Main 상단바에서 HashTag 검색 시에만 pagination을 처리하려 합니다 ㅠㅠ router로 page 2개 만들면 될것같긴한데 다른 우선순위 부터 진행하고 하겠습니다. -->
                    </div>

                    <div v-else>

                    </div>


                </div>
            </div>
            <div v-else>
                <kinesis-container>
                    <kinesis-element :strength="10">
                        <h1 style="color : orange">상단 메뉴에서 검색 해주세요!</h1>
                    </kinesis-element>
                    <kinesis-element :strength="20">
                        <h1 style="color : orange">불러온 데이터가 없습니다. </h1>
                    </kinesis-element>
                </kinesis-container>
                 <iframe src="https://giphy.com/embed/TI4Hsj7mNI27nn9I1P" width="200" height="200" frameBorder="0" class="giphy-embed" allowFullScreen></iframe><p><a href=""></a></p>
            </div>
        </v-app>
    </div>
</template>
<script>
    import Vue from 'vue'
    import Loading from 'vue-loading-overlay';
import 'vue-loading-overlay/dist/vue-loading.css'
    import {KinesisContainer, KinesisElement} from 'vue-kinesis'
    import StoreApi from '../../apis/StoreApi';
    import * as VueGoogleMaps from 'vue2-google-maps';

    Vue.use(VueGoogleMaps, {
        load: {
            key: 'AIzaSyDC4sonH281FHJ-YyPmeXLRdBYuqcjUkGE',
            v: 'OPTIONAL VERSION NUMBER',
            libraries: 'places', //// If you need to use place input
        }
    });

    Vue.component('kinesis-container', KinesisContainer)
    Vue.component('kinesis-element', KinesisElement)
    export default {
        created() {
            this.lenCalc();
            this.page = this.len;

            // 현재 위치
        },
        components : {
            // Loading,
        },
        watch: {
            storeListwatch: function (v) {
                // this.data = this.$store.state.storeList;
                // let list = [...this.data];
                // list = this.$store.state.storeList;
                // this.data = [...list];
                // this.$store.state.storeList = this.data;
                //redering
                console.log("watch");
                // for(let i=0; i<6; i++){
                //     this.storeRender.push(this.$store.state.storeList[i]);
                // } 
                console.log(this.storeRender);
            },
            fetchSearchStore: function (v) {
                console.log("fetchSearchStore");
                let list = [...this.$store.state.storeList_paging];
                // list = this.$store.state.storeList
                // .filter(post => {
                //     return post
                //         .toLowerCase()
                //         .includes(this.search.toLowerCase())
                // })
                this.$store.state.storeList_paging = [];
                console.log(this.$store.state.storeList);
                for (let i = 0; i < this.$store.state.storeList.length; i++) {
                    if (this.$store.state.storeList[i].sname.includes(this.ssearch)) {
                        console.log('isExistValue');
                        this.$store.state.storeList_paging.push(
                            this.$store.state.storeList[i]);
                    }
                }
                list = [...this.$store.state.storeList_paging];
                console.log(list);
                this.$store.state.storeList_paging = [...list];



            }
        },
        computed: {
            storeListwatch() {
                console.log('computed');
                return this.$store.state.storeList
            },
            fetchSearchStore() {
                return this.enterKey;
            }
        },
        data() {
            return {
                data: [],
                len: 1, //page 가 몇개 생성 될것인지 체크
                search: '',
                enterKey: 0, //검색했을때 rendering 하기 위해서 computed 와 watch 를 조절하기 위해 사용
                pageSataus: false,
                myLocation : {
                    latitude: 37.5663,
                    longitude: 126.9779,
                },
              
                isLoading_store : false,
            }
        },
        methods: {

            next() {
                this.$store.state.storeList_paging = [];
                for (let i = (this.page * 6) - 6; i < this.page * 6; i++) {
                    this.$store.state.storeList_paging.push(this.$store.state.storeList[i]);
                }
                this.$store.state.page = this.page;
            },
            lenCalc() {
                if (this.$store.state.storeList.length % 6 != 0)
                    this.len = Math.ceil(this.$store.state.storeList.length / 6);
                else
                    this.len = Math.ceil(this.$store.state.storeList.length / 6) - 1;

                if (this.len == 0) //데이터를 6개씩 렌더링 하는데, 6개를 못채워주면 this.len이 0으로 렌더링됨
                    this.len = 1;
            },
            storeListSearch() {
                //store에 검색했을때 
                this.isLoading_store = true;
                let searchList = new Array();
                StoreApi.requestStoreList().then(response=>{
                    console.log('storeListSearch');
                    console.log(response);
                    for(let i =0; i<response.data.object.length; i++){
                        let item = {};
                        item['num'] = response.data.object[i].store.num;
                        item['sname'] = response.data.object[i].store.sname;
                        item['address'] = response.data.object[i].store.address;
                        item['img'] = response.data.object[i].store.img;
                        item['lat'] = response.data.object[i].store.lat;
                        item['lng'] = response.data.object[i].store.lng;
                        JSON.stringify(item);
                        searchList.push(item);

                    }
                    if (searchList.length == 0)
                        this.$store.state.isClickBtnStore = false;
                    else
                        this.$store.state.isClickBtnStore = true;
                    this.$store.state.storeList = searchList;
                    this.enterKey += 1; //computed에서 지켜보고있음 
                    this.$store.state.pageStatus = false;
                }) //end for first for loop

            },
            storeDetail(num) {
                console.log('storeDetail');
                console.log(num);
                this.$store.dispatch('storeHashtags', num)

            },
            getMylocation() {
                navigator.geolocation.getCurrentPosition(pos => {
                    this.myLocation = pos.coords
                    console.log(this.myLocation.latitude, this.myLocation)
                    return pos
                })
                    this.isLoading_store = false;
              }
                 
          },
        
        
    }
</script>
<style scoped="scoped">
    .inspire_store {
        top: 200px;
    }

    .v-card--reveal {
        align-items: center;
        bottom: 0;
        justify-content: center;
        opacity: 0.5;
        position: absolute;
        width: 100%;
        z-index: 2 !important;
    }
</style>