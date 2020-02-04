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
Amplify.configure(awsconfig)

Vue.use(AmplifyPlugin, AmplifyModules)

// infinite scroll
var infiniteScroll =  require('vue-infinite-scroll');
Vue.use(infiniteScroll)

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

