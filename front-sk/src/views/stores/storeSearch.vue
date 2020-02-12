<template>
    <div id="app">
        <v-app id="inspire">
            <v-layout row="row">
                <v-flex xs12="xs12" sm6="sm6" offset-sm3="offset-sm3">
                    <v-card>
                        <v-card-media
                            src="https://vuetifyjs.com/static/doc-images/cards/sunshine.jpg"
                            height="200px"></v-card-media>
                        <v-card-title primary-title="primary-title">
                            <div>
                                <div class="headline">Top western road trips</div>
                                <span class="grey--text">1,000 miles of wonder</span>
                            </div>
                        </v-card-title>
                        <v-card-actions>
                            <v-btn flat="flat">Share</v-btn>
                            <v-btn flat="flat" color="purple">Explore</v-btn>
                            <v-spacer></v-spacer>
                            <v-btn icon="icon" @click.native="show = !show">
                                 <v-btn flat="flat" color="purple">{{ show ? 'keyboard_arrow_down' : 'keyboard_arrow_up' }}</v-btn>
                            </v-btn>
                        </v-card-actions>
                        <v-slide-y-transition>
                            <v-card-text v-show="show">
                                I'm a thing. But, like most politicians, he promised more than he could deliver.
                                You won't have time for sleeping, soldier, not with all the bed making you'll be
                                doing. Then we'll go with that data file! Hey, you add a one and two zeros to
                                that or we walk! You're going to do his laundry? I've got to find a way to
                                escape.
                            </v-card-text>
                        </v-slide-y-transition>
                    </v-card>
                </v-flex>
            </v-layout>
        </v-app>
    </div>
</template>

<script>
    export default {
      
        data: () => ({show: false})
    }
</script>

<style></style>