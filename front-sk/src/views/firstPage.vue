<template>
    <div>
        <!-- 네브바 -->
        <div id="mainTop">
            <div id="mainWithImage" style="text-align: center !important;">

               

                

                  

                        

                      
                <v-app id="inspire">

                    <v-carousel
                        cycle="cycle"
                        show-arrows-on-hover="show-arrows-on-hover"
                        hide-delimiters="hide-delimiters"
                        v-ripple="true"
                        class="text-center">
                        <v-carousel-item v-for="(item,i) in items" :key="i" :src="item.src"></v-carousel-item>
                    </v-carousel>
                   


                        
                </v-app>
                <hr></div>
            </div>
            <!-- 음식창과 검색창 -->

            <!-- <v-row align="start" no-gutters style="height: 130px;" class="mainSearch ">
            <v-col> </v-col> <v-col> -->
            <!-- <div class="search"> -->
            <!-- <input type="text" class="searchTerm" placeholder="What are you looking
            for?"> -->
            <!--User가 최근 검색한 history를 보여줌 -->
            <!-- <div v-if="inputStatus"> <div class="input-container"> <b-form-input
            class="searchTerm" type="text" v-on:keyup.enter="changeInput" v-model="search"
            list="this.$store.state.recentUser"></b-form-input> <datalist
            id="this.$store.state.recentUser"> <option v-for="user in
            this.$store.state.recentUser" v-bind:key="user">{{user}}</option> </datalist>
            </div> </div> -->
            <!--User 정보 전체를 보여줌 -->
            <!-- <div v-else> <div class="input-container"> <b-form-input class="searchTerm"
            type="text" v-on:keyup.enter="changeInput2" v-model="search"
            list="this.$store.state.searchUser.nickname" /> <datalist
            id="this.$store.state.searchUser.nickname"> <option v-for="user in
            this.$store.state.searchUser.nickname" v-bind:key="user">{{user}}</option>
            </datalist> </div> </div> -->
            <!-- <button type="submit" class="searchButton"> </button> -->
            <!-- </div> -->
            <!-- </v-col> <v-col> </v-col> -->
            <!-- </v-row> -->

            <!-- 사진이랑 유저 랭킹 -->
            <v-container class="grey lighten-5" style="
    margin-top: 400px;
">
                <v-row no-gutters="no-gutters">
                    <v-col cols="9">
                        <v-row no-gutters="no-gutters">
                            <template v-for="(store, index) in Stores">
                                <v-col :key="index">
                                    <v-hover v-slot:default="{ hover }">
                                        <v-card :loading="loading" class="mx-auto my-12 storeRank" max-width="374">

                                            <!-- <v-img height="200" :src="store.picture"> <v-expand-transition> <div
                                            v-if="hover" class="d-flex transition-fast-in-fast-out v-card--reveal
                                            white--text" style="height: 100%;"> 식당 상세 정보 </div> </v-expand-transition>
                                            </v-img> -->

                                            <!-- <v-card-title>{{store.name}}</v-card-title> <v-card-text> <v-row
                                            align="center" class="mx-0"> <v-rating :value="4.5" color="amber" dense
                                            half-increments readonly size="14"></v-rating> <div class="grey--text ml-4">4.5
                                            (413)</div> </v-row> <div class="my-4 subtitle-1 black--text"> {{store.address}}
                                            </div> <v-chip-group column> <v-chip v-for="(h, i) in store.hashtag"
                                            :key="i">#{{h}}</v-chip> </v-chip-group> </v-card-text> -->

                                        </v-card>
                                    </v-hover>
                                </v-col>
                                <v-responsive v-if="n === 2" :key="`width-${index}`" width="100%"></v-responsive>
                            </template>
                        </v-row>
                    </v-col>

                    <!-- <v-col cols="3"> <v-row align="center"> <v-card class="mx-auto"
                    max-width="400" tile> <v-list flat=true avatar=true rounded=true> <v-subheader>
                    <v-icon>{{icons.Crown}}</v-icon>이달의 리뷰왕<v-icon>{{icons.Crown}}</v-icon>
                    </v-subheader> <v-list-item-group v-model="item" color="primary"> <v-list-item
                    v-for="(item, i) in items" :key="i"> <v-list-item-avatar v-if="item.avatar">
                    <v-img :src="item.avatar"></v-img> </v-list-item-avatar> <v-list-item-content>
                    <v-list-item-title> {{i+1}}등: {{item.title}}</v-list-item-title>
                    <v-list-item-subtitle></v-list-item-subtitle> </v-list-item-content>
                    </v-list-item> </v-list-item-group> </v-list> </v-card> </v-row> <div
                    class="text-center"> <br><br> <span style="font-size:30px"> 리뷰 작성하기</span><br>
                    <v-btn router-link to="/reviewPage" class="mx-2" id="btn" style="font-size: 2em;
                    color:black" fab dark large color="purple"> <i class="fas fa-pencil-alt"></i>
                    </v-btn> </div> </v-col> -->

                </v-row>
            </v-container>

        </div>
    </template>

    <script>
        import {mdiMagnify, mdiAccountCircle, mdiForum, mdiCrown} from '@mdi/js'
        import {mapState} from 'vuex'
        //import userSearch from '../../src/components/common/userSearch';
        import UserApi from '../../src/apis/UserApi.js';
       
        export default {
            created() {
                this
                    .$store
                    .dispatch('LOADING_USERDATA');
            },
            
            data() {
                return {
                    search: '',
                    inputStatus: true,
                    history: {
                        email: '',
                        nickname: '',
                        moveUser: ''
                    },
                    icons: {
                        Magnify: mdiMagnify,
                        AccountCircle: mdiAccountCircle,
                        Forum: mdiForum,
                        Crown: mdiCrown
                    },
                    loading: false,
                    selection: 1,

                    newSearch: [],
                    openSearch: false,
                    value: [
                        'apple', 'orange'
                    ],
                    items: [
                        {
                            src: 'https://cdn.pixabay.com/photo/2017/05/07/08/56/pancakes-2291908_1280.jpg'
                        }, {
                            src: 'https://cdn.pixabay.com/photo/2016/03/05/23/02/barbecue-1239434_1280.jpg'
                        }, {
                            src: 'https://cdn.pixabay.com/photo/2016/12/26/17/28/food-1932466_1280.jpg'
                        }, {
                            src: 'https://cdn.pixabay.com/photo/2017/03/30/15/47/churros-2188871_1280.jpg'
                        }
                    ],
                    hashtags: {
                        locations: [
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0
                        ],
                        withWho: [
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0
                        ],
                        ages: [
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0
                        ],
                        moods: [
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0
                        ],
                        facility: [
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0
                        ]
                    },
                    tabs: null
                }
            },

            computed: {
                ...mapState(['tempStores']),
                Stores() {
                    return this.tempStores
                }
            },
            methods: {
                reserve() {
                    this.loading = true

                    setTimeout(() => (this.loading = false), 2000)
                },
                changeInput() { //if-else
                    if (this.inputStatus == true) 
                        this.inputStatus = false;
                    else 
                        this.inputStatus = true;
                    }
                ,
                changeInput2() { //enter 누를 때, user 정보가 db에 저장된다.
                    let find = false;

                    for (var j = 0; j < this.$store.state.searchUser.nickname.length; j++) {
                        if (this.search == this.$store.state.searchUser.nickname[j]) {
                            this.moveUser = this
                                .$store
                                .state
                                .searchUser
                                .email[j];
                            //검색한 닉네임의 email
                            find = true;
                            break;
                        }

                    }
                    if (find == false) 
                        this.moveUser = 'notEmail'
                        //this.history.email = sessionStorage.getItem("userEmail");
                    this.history.email = "ssafy@naver.com"
                    this.history.nickname = this.search;

                    UserApi.searchUserHistory(this.history, res => {

                        if (this.inputStatus == true) 
                            this.inputStatus = false;
                        else {
                            this.inputStatus = true;
                            this.search = "";
                            this
                                .$store
                                .dispatch('LOADING_RECENTUSERDATA', this.history.email);
                        }
                    }, error => {
                        console.log("userSearch.vue, server 와 통신 실패");
                    })
                    // DB에 저장하고 email로 유저페이지 이동한다. this.$router.push({ name: 'userPage', params: {
                    // id: this.moveUser }}) 다시 inpustState를 변화시키므로써, User가 최근 검색 history를 보여준다.

                },
                open() {
                    this.openSearch = !this.openSearch
                },
                insertTags(cate, n, name) {

                    if (cate === "locations") {

                        if (!this.hashtags.locations[n]) {
                            this
                                .hashtags
                                .locations[n] = !this
                                .hashtags
                                .locations[n]
                            let list = [...this.newSearch]
                            list.push(name)
                            this.newSearch = [...list]
                            console.log(this.newSearch)
                        } else {
                            var pos = this
                                .newSearch
                                .indexOf(name)
                            this
                                .hashtags
                                .locations[n] = !this
                                .hashtags
                                .locations[n]
                            let list = [...this.newSearch]
                            list.splice(pos, 1)
                            this.newSearch = [...list]
                        }

                    } else if (cate === "withWho") {

                        if (!this.hashtags.withWho[n]) {
                            this
                                .hashtags
                                .withWho[n] = !this
                                .hashtags
                                .withWho[n]
                            let list = [...this.newSearch]
                            list.push(name)
                            this.newSearch = [...list]
                            console.log(this.newSearch)
                        } else {
                            let pos = this
                                .newSearch
                                .indexOf(name)
                            this
                                .hashtags
                                .withWho[n] = !this
                                .hashtags
                                .withWho[n]
                            let list = [...this.newSearch]
                            list.splice(pos, 1)
                            this.newSearch = [...list]
                        }
                    } else if (cate === "ages") {

                        if (!this.hashtags.ages[n]) {
                            this
                                .hashtags
                                .ages[n] = !this
                                .hashtags
                                .ages[n]
                            let list = [...this.newSearch]
                            list.push(name)
                            this.newSearch = [...list]
                            console.log(this.newSearch)
                        } else {
                            let pos = this
                                .newSearch
                                .indexOf(name)
                            this
                                .hashtags
                                .locations[n] = !this
                                .hashtags
                                .locations[n]
                            let list = [...this.newSearch]
                            list.splice(pos, 1)
                            this.newSearch = [...list]
                        }

                    } else if (cate === "moods") {
                        if (!this.hashtags.moods[n]) {
                            this
                                .hashtags
                                .moods[n] = !this
                                .hashtags
                                .moods[n]
                            let list = [...this.newSearch]
                            list.push(name)
                            this.newSearch = [...list]
                            console.log(this.newSearch)
                        } else {
                            let pos = this
                                .newSearch
                                .indexOf(name)
                            this
                                .hashtags
                                .moods[n] = !this
                                .hashtags
                                .moods[n]
                            let list = [...this.newSearch]
                            list.splice(pos, 1)
                            this.newSearch = [...list]
                        }
                    } else if (cate === "facility") {
                        if (!this.hashtags.facility[n]) {
                            this
                                .hashtags
                                .facility[n] = !this
                                .hashtags
                                .facility[n]
                            let list = [...this.newSearch]
                            list.push(name)
                            this.newSearch = [...list]
                            console.log(this.newSearch)
                        } else {
                            let pos = this
                                .newSearch
                                .indexOf(name)
                            this
                                .hashtags
                                .facility[n] = !this
                                .hashtags
                                .facility[n]
                            let list = [...this.newSearch]
                            list.splice(pos, 1)
                            this.newSearch = [...list]
                        }
                    }

                }
            }
        }
    </script>

    <style scoped="scoped">
        #mainWithImage {
            position: relative;
        }
        #inspire {
            position: absolute;
            width: 100%;
            top: 0;
            right: 0;
        }
        .btn b-form-tags-button py-0 btn-outline-secondary {
            height: 30px !important;
        }

        .form-control {
            position: absolute;
            z-index: 1;
            top: 285px;
            left: 300px;
            height: 50px;
            width: 1000px;

        }

        .mb-2 {
            height: 50px !important;
        }

        #mainWithImage {
            padding-top: 50px;
        }

        #mainTop {
            background: #ff7f00;
        }
        #find {
            -webkit-transform: scale(1.2);
            -moz-transform: scale(1.2);
            -ms-transform: scale(1.2);
            -o-transform: scale(1.2);
            transform: scale(1.2);
            -webkit-transition: 0.3s;
            -moz-transition: 0.3s;
            -ms-transition: 0.3s;
            -o-transition: 0.3s;
            transition: 0.3s;
            background: #ff7f00;

        }
        #find:hover {
            -webkit-transform: scale(1.5);
            -moz-transform: scale(1.5);
            -ms-transform: scale(1.5);
            -o-transform: scale(1.5);
            transform: scale(1.5);
            background: white;
            color: #ff7f00;
            outline-color: #ff7f00;
        }
    </style>