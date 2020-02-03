import Login from './views/user/Login.vue'
import Join from './views/user/Join.vue'
import FindPW from './views/user/FindPW.vue'
import JoinDone from './views/user/JoinDone.vue'
import PageNotFound from './views/PageNotFound.vue'
import Components from './views/Components.vue'
import UserPage from './views/userpage/UserPage.vue'
import UserEdit from './views/userpage/UserEdit.vue'
import Fans from './views/follow/Fans'
import Stars from './views/follow/Stars'
import UserReviews from './views/userpage/UserReviews'
import Main from './views/Main'
import signUpForm from './views/user/signUpForm.vue';
import signUpConfirm from './views/user/signUpConfirm.vue';
import searchPassword from './views/user/searchPw.vue';
import userSearch from './components/common/userSearch.vue';
import storeDetail from './views/stores/storeDetail'

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
    { // 유저페이지
        path: '/userpage/:id',
        name: 'UserPage',
        component: UserPage,
        children: [
            {
                path: 'fans',
                name: 'Fans',
                component: Fans
            },
            {
                path: 'stars',
                name: 'Stars',
                component: Stars
            },
            {
                path: 'reviews',
                name: 'UserReviews',
                component: UserReviews
            }

        ]
    },
    {
        path: '/userpage/edit',
        name: "UserEdit",
        component: UserEdit
    },
    {
        path: '/components',
        name: 'Components',
        component: Components
    },
    {
        path: '/main',
        name: 'Main',
        component: Main
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
    },
    {
        path : '/userSearch',
        name : 'userSearch',
        component : userSearch,
    },
    {
        path: '/storeDetail/:id',
        name: 'storeDetail',
        component: storeDetail
    }
    
]
