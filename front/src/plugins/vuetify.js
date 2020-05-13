import Vue from 'vue'
import Vuetify from 'vuetify'
import { mdiAccount } from '@mdi/js'
import 'vuetify/dist/vuetify.min.css'


Vue.use(Vuetify, {
    
})
const opts = {
    icons: {
        iconfont: 'mdiSvg' // 'mdi' || 'mdiSvg' || 'md' || 'fa' || 'fa4'
      },
}

export default new Vuetify(opts)