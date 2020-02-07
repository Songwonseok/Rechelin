<template>
  <v-card max-width="500" class="mx-auto">
    <v-toolbar color="indigo" dark>
      <v-toolbar-title>Following</v-toolbar-title>

      <v-spacer></v-spacer>


    </v-toolbar>
    <v-list>
      <v-list-item v-for="(s, index) in Stars" :key="index">

        <v-list-item-content>
          <v-list-item-title v-text="s.nickname"></v-list-item-title>
        </v-list-item-content>
      </v-list-item>
    </v-list>
  </v-card>
</template>

<script>
  export default {
    computed: {
      Stars() {
        return this.$route.params.stars
      }
    },
    created() {
    }
  }
</script>