<template>
    <div id="app">
        <v-app id="inspire">
            <div v-if="this.$store.state.storeList.length >= 1">
                <b-container class="bv-example-row">
                    <b-row >

                        <div v-for="(store, i) in this.$store.state.storeList" v-bind:key="i+store">
                            <v-hover v-slot:default="{ hover }">
                                <v-card class="mx-auto mr-2 mb-2" max-width="370" height="300">
                                    <v-img
                                        class="white--text align-end"
                                        height="200px"
                                        :src="`https://maps.googleapis.com/maps/api/place/photo?maxwidth=600&photoreference=${store.img}&sensor=true&key=AIzaSyDC4sonH281FHJ-YyPmeXLRdBYuqcjUkGE`">
                                        <v-expand-transition>
                                            <div
                                                v-if="hover"
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
            </div>
        </v-app>
    </div>
</template>

<script>
    import Vue from 'vue'
    import {KinesisContainer, KinesisElement} from 'vue-kinesis'

    Vue.component('kinesis-container', KinesisContainer)
    Vue.component('kinesis-element', KinesisElement)
    export default {

        watch: {
            storeListwatch: function (v) {
                this.data = this.$store.state.storeList;
            }
        },
        computed: {
            storeListwatch() {
                return this.$store.state.storeList
            }
        },
        data() {
            return {data: []}
        }
    }
</script>

<style scoped="scoped">
    .v-card--reveal {
        align-items: center;
        bottom: 0;
        justify-content: center;
        opacity: 0.5;
        position: absolute;
        width: 100%;
    }
</style>