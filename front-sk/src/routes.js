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


/**store search **/
import storeDetail from './views/stores/storeDetail'
import storeReviews from './views/stores/storeReviews'
import storeSearch from './views/stores/storeSearch.vue'

import comments from './views/stores/comments'
import random from './views/stores/random.vue'
import reviewPage from './views/review/reviewPage.vue';
import reviewDetail from './views/review/reviewDetail.vue';
import updateNewPw from './views/user/updateNewPw.vue';
import userFeed from './views/feed/userFeed.vue';


/*side menu*/
import sideMenuLogin from './views/sideMenu/sideMenuLogin.vue';
import sideMenuReview from './views/sideMenu/sideMenuReview.vue';
import sideMenuFood from './views/sideMenu/sideMenuFood.vue';
import sideMenuUserPage from './views/sideMenu/sideMenuUserPage.vue';
import bestReviewList from './views/review/bestReviewList.vue';
import sideMenuFeed from './views/sideMenu/sideMenuFeed.vue';


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
        name: 'main',
        component: Main,
        children: [{
                path: 'popular',
                name: 'popular',
                component: bestReviewList,
            },
            {
                path: 'user',
                name: 'user',
                component: sideMenuLogin,
                children: [{
                        path: '/login',
                        name: 'login',
                        component: Login
                    },
                    {
                        path: '/signup',
                        name: 'signup',
                        component: signUpForm
                    },
                    {
                        path: '/findPW',
                        name: 'findPW',
                        component: searchPassword
                    },
                    {
                        path: '/signupConfirm',
                        name: 'signupconfirm',
                        component: signUpConfirm
                    }
                ]
            },
            {
                path: 'userDetail',
                name: 'userDetail',
                component: sideMenuUserPage,
                children: [{ // 유저페이지
                    path: '/userpage/:id',
                    name: 'userpage',
                    component: UserPage,
                    children: [{
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
                        },
                        {
                            path: 'edit',
                            name: "UserEdit",
                            component: UserEdit
                        },

                    ]
                }]
            },
            {
                path: 'food',
                name: 'food',
                component: sideMenuFood,
                children: [{
                        path: 'storeSearch',
                        name: 'storeSearch',
                        component: storeSearch
                    },
                    {
                        path: 'random',
                        name: 'random',
                        component: random
                    }

                ]
            },
            {
                path: 'review',
                name: 'review',
                component: sideMenuReview,
                children: [{
                    path: 'create',
                    name: 'createReview',
                    component: reviewPage
                }]
            },
            {
                path: 'feed',
                name: 'feed',
                component: sideMenuFeed,
                children: [{
                    path: 'userFeed',
                    name: 'userFeed',
                    component: userFeed
                }]
            },
        ]

    },


    {
        path: '/components',
        name: 'Components',
        component: Components
    },
    {
        path: '/main',
        name: 'Main',
        component: Main,

    },
    {
        path: '/signUpForm',
        name: 'signUpForm',
        component: signUpForm,
    },
    {
        path: '/signUpConfirm',
        name: 'signUpConfirm',
        component: signUpConfirm,
    },
    {
        path: '/searchPassword',
        name: 'searchPassword',
        component: searchPassword,
    },
    {
        path: '/userSearch',
        name: 'userSearch',
        component: userSearch,
    },
    { // 음식점 상세페이지
        path: '/storeDetail/:id',
        name: 'storeDetail',
        component: storeDetail,
        children: [{
                path: 'reviews',
                name: 'reviewsOfstore',
                component: storeReviews,

            },
            {
                path: '/comments',
                name: 'comments',
                component: comments
            },
        ]
    },

    {
        path: '/reviewPage',
        name: 'reviewPage',
        component: reviewPage,
    },
    {
        path: '/reviewDetail/:id',
        name: 'reviewDetail',
        component: reviewDetail
    },
    {
        path: '/updateNewPw',
        name: 'updateNewPw',
        component: updateNewPw,
    },
    {
        path: '/userFeed',
        name: 'userFeed',
        component: userFeed,
    },
    {
        path: '/sideMenuLogin',
        name: 'sideMenuLogin',
        component: sideMenuLogin,
    },
    {
        path: '/sideMenuReview',
        name: 'sideMenuReview',
        component: sideMenuReview,
    },
    {
        path: '/sideMenuFood',
        name: 'sideMenuFood',
        component: sideMenuFood,
    },
    {
        path: '/sideMenuUserPage',
        name: 'sideMenuUserPage',
        component: sideMenuUserPage,
    }


]