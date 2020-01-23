<template>
    <div>

    <v-layout row>
    <v-flex xs12 sm6 offset-sm3 >
    <v-card v-for="(store, index) in UserStores" :key="index"  style="margin-bottom: 30px;">
        <img :src="store.picture" :alt="index" height="200px" style="margin-top:10px;">
        <v-card-title primary-title style="text-align: center;">
        <div style="text-align: center;">
            <div class="headline">{{store.name}}</div>
            <span class="grey--text">{{store.address}}</span>
            <p class="grey--text">{{store.phone}}</p>
            <span class="pink" v-for="hash in store.hashtag" :key="hash" style="padding-right:10px;"># {{hash}}</span>
        </div>
        </v-card-title>
        <v-card-actions>
        <v-btn flat>Share</v-btn>
        <v-btn flat color="purple">Explore</v-btn>
        <v-spacer></v-spacer>
        </v-card-actions>
        <v-slide-y-transition>
        <v-card-text v-show="show">
            I'm a thing. But, like most politicians, he promised more than he could deliver. You won't have time for sleeping, soldier, not with all the bed making you'll be doing. Then we'll go with that data file! Hey, you add a one and two zeros to that or we walk! You're going to do his laundry? I've got to find a way to escape.
        </v-card-text>
        </v-slide-y-transition>
    </v-card>
    </v-flex>
</v-layout>
    

    </div>
</template>

<script>

export default {
    computed : {
        UserStores () {
            return this.$route.query.info
        }
    },
    created() {
        console.log(this.$route)
    }
}

</script>