import Vue from 'vue'
import App from './App.vue'
import Router from 'vue-router'
import routes from './routes'
import store from './vuex/store'
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
    routes,
});

new Vue({
    router,
    store,
    render: h => h(App),
}).$mount('#app');


