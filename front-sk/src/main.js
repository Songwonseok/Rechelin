import Vue from 'vue'
import App from './App.vue'
import Router from 'vue-router'
import routes from './routes'
import store from './vuex/store'
import vuetify from '@/plugins/vuetify'

import "./style/tailwind.css";
import { extend, localize } from "vee-validate";
import * as rules from "vee-validate/dist/rules";
import en from "vee-validate/dist/locale/en.json";
import { registerVeeValidation } from '@/register-vee-validation'

registerVeeValidation()

import { BootstrapVue, IconsPlugin } from 'bootstrap-vue';
// app.js
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
// app.js
import "./vee-validate";
//aws 
import Amplify, * as AmplifyModules from 'aws-amplify'
import { AmplifyPlugin } from 'aws-amplify-vue'
import awsconfig from './aws-exports'

import VueLoading from 'vuejs-loading-plugin'
import 'material-design-icons-iconfont/dist/material-design-icons.css'
Amplify.configure(awsconfig)
import VueStar from 'vue-star'
Vue.component('VueStar', VueStar)
import VueSimpleAlert from "vue-simple-alert";

Vue.use(VueSimpleAlert, { reverseButtons: true });

//강제 업데이트


// infinite scroll
import InfiniteLoading from 'vue-infinite-loading';

Vue.use(InfiniteLoading, { /* options */ });

//

import VueKinesis from 'vue-kinesis'
Vue.use(VueKinesis)

Vue.use(VueLoading)
Vue.use(VueLoading, {
    dark: true, // default false
    text: 'Ladataan', // default 'Loading'
    loading: true, // default false
    customLoader: null, // replaces the spinner and text with your own
    background: 'rgb(255,255,255)', // set custom background
    classes: ['myclass'] // array, object or string
  })
Object.keys(rules).forEach(rule => {
    extend(rule, rules[rule]);
  });
  
localize("en", en);
  
Vue.config.productionTip = false

Vue.use(Router)
// Install BootstrapVue
Vue.use(BootstrapVue)
// Optionally install the BootstrapVue icon components plugin
Vue.use(IconsPlugin)
 
const router = new Router({
    mode:'history',
    base: process.env.BASE_URL,
    routes,
});

new Vue({
    router,
    store,
    vuetify,
    render: h => h(App),
}).$mount('#app');

export default router

