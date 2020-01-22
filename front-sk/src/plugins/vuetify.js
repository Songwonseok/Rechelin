import Vue from 'vue'
import Vuetify, {
    VBtn,
    VIcon
} from 'vuetify/lib'
import 'vuetify/dist/vuetify.min.css'

Vue.use(Vuetify, {
    components: {
        VBtn,
        VIcon
    }
})
const opts = {
    icons: {
        iconfont: 'mdi' // 'mdi' || 'mdiSvg' || 'md' || 'fa' || 'fa4'
      }
}

export default new Vuetify(opts)