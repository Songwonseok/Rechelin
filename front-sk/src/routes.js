import Login from './views/user/Login.vue'
import Join from './views/user/Join.vue'
import FindPW from './views/user/FindPW.vue'
import JoinDone from './views/user/JoinDone.vue'
import PageNotFound from './views/PageNotFound.vue'
import Components from './views/Components.vue'
import signUpForm from './views/user/signUpForm.vue';
import signUpConfirm from './views/user/signUpConfirm.vue';
import searchPassword from './views/user/searchPw.vue';

export default [
    // ERROR PAGE
    // 404 PAGE
   

    {
        path: '*',
        redirect: '/404'
    },
    {
        path: '/404',
        component: PageNotFound
    },
    {
        path: '/',
        name: 'Login',
        component: Login
    },
    {
        path: '/user/join',
        name: 'Join',
        component: Join
    },
    {
        path: '/user/findpw',
        name: 'FindPW',
        component: FindPW
    },
    {
        path: '/user/join/done',
        name: 'JoinDone',
        component: JoinDone
    },
    {
        path: '/components',
        name: 'Components',
        component: Components
    },
    {
        path : '/signUpForm',
        name : 'signUpForm',
        component : signUpForm,
    },
    {
        path : '/signUpConfirm',
        name : 'signUpConfirm',
        component : signUpConfirm,
    },
    {
        path : '/searchPassword',
        name : 'searchPassword',
        component : searchPassword,
        }
    
]