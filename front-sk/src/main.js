import Vue from 'vue'
import App from './App.vue'
import Router from 'vue-router'
import routes from './routes'
import store from './vuex/store'

Vue.config.productionTip = false

Vue.use(Router)

const router = new Router({
    mode: 'history',
    base: process.env.BASE_URL,
    routes,
});

new Vue({
    router,
    store,
    render: h => h(App),
}).$mount('#app');


