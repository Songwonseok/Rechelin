<template>
      <v-speed-dial
        v-model="fab"
        :top="true"
        direction="left"
        :open-on-hover="hover"
        transition="scale"
        :right="true"
         class="vertical-menu"
      >
        <template v-slot:activator>
          <v-btn
            v-model="fab"
            color="warning"
            dark
            fab
          >
            <v-icon v-if="fab">mdi-close</v-icon>
            <v-icon v-else>mdi-account-circle</v-icon>
          </v-btn>
        </template>
        <v-btn
          fab
          dark
          small
          color="green"
          @click="$router.push({name: 'createReview'})"
        >
          <v-icon>mdi-pencil</v-icon>
        </v-btn>
        <v-btn
          fab
          dark
          small
          color="indigo"
          @click="UserLogout"
        >
          <v-icon>mdi-logout</v-icon>
        </v-btn>
        <v-btn
          fab
          dark
          small
          color="red"
           id="popover-target-1" @click="notificationGet" :content="messages" @mouseover="notificationGet"
          
        >
          <v-icon>mdi-bell</v-icon>
        </v-btn>
        <b-popover target="popover-target-1" triggers="hover" placement="bottom">
        <template v-slot:title>팔로우 요청</template>
      
        <b-list-group-item v-for="(alarm, index) in alarms" :key="index">{{alarm.fan.nickname}}
          <div>
          <v-btn @click="followAccept(alarm.fan, index)">accept</v-btn>
          <v-btn @click="followDecline(alarm.fan, index)">decline</v-btn>
          </div>
        </b-list-group-item>
      </b-popover>
     
      </v-speed-dial>



</template>

<script>
export default {
    data() {
        return {
            fab: false,
        }
    },
    methods: {
    UserLogout() {
        this.$store.dispatch('logout')
        this.$router.push({
          name: "login"
        })
      },
      notificationGet() {
        var payload = this.$store.state.userid
        this.$store.dispatch('notificationGet', payload)
        this.alarms = this.$store.state.notifications
      },
      userpageGo() {
        this.$router.push({name: 'userpage', params: {
            id: sessionStorage.getItem('userid')
        }})
      },
      followAccept(f, index) {
        var payload = {
          fan: f,
          star: this.$store.state.userid
        }
        this.$store.dispatch('followAccept', payload)
        this.alarms.splice(index, 1)
      },
      followDecline(f, index) {
        var payload = {
          fan: f,
          star: this.$store.state.userid
        }
        this.$store.dispatch('followDecline', payload)
        this.alarms.splice(index, 1)
      },
    },
      computed: {
    activeFab () {
      switch (this.tabs) {
        case 'one': return { class: 'purple', icon: 'account_circle' }
        case 'two': return { class: 'red', icon: 'edit' }
        case 'three': return { class: 'green', icon: 'keyboard_arrow_up' }
        default: return {}
      }
    },
    
    
  }, 

}
</script>

<style scoped>
 .v-speed-dial {
  position: sticky;
  top: 90%;
  right: 1000px;
z-index: 9;
}

.v-btn--floating {
  position: sticky;
}
</style>