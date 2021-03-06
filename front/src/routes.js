import Login from './views/user/Login.vue'

/** user **/
import FindPW from './views/user/FindPW.vue'


import PageNotFound from './views/PageNotFound.vue'
import Components from './views/Components.vue'

/**User Page **/
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
import storeSearchN from './views/stores/storeSearchN';


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

import test from './views/test.vue';

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
        children: [
            // main page 첫 화면
            {
                path: 'popular',
                name: 'popular',
                component: bestReviewList,
            },



            // user 탭
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
                        path: '/findpw',
                        name: 'findPW',
                        component: searchPassword
                    },
                    {
                        path: '/signupconfirm',
                        name: 'signupconfirm',
                        component: signUpConfirm
                    },
                    {
                        path: '/userpage/:id',
                        name: 'userpage',
                        component: UserPage,
                        children: [{
                                path: 'fans',
                                name: 'fans',
                                component: Fans
                            },
                            {
                                path: 'stars',
                                name: 'stars',
                                component: Stars
                            },
                            {
                                path: 'bookmarks',
                                name: 'bookmarks',
                                component: UserReviews
                            }
                        ]
                    },
                    {
                        path: 'edit',
                        name: 'useredit',
                        component: UserEdit
                    },
                    {
                        path: '/updatepw',
                        name: 'updatepw',
                        component: updateNewPw
                    }


                ]
            },

            {
                path: 'food',
                name: 'food',
                component: sideMenuFood,
                children: [{
                        path: '/storesearch',
                        name: 'storeSearch',
                        component: storeSearch
                    },
                    {
                        path: '/storesearchN',
                        name: 'storeSearchN',
                        component: storeSearchN,
                    },
                    {
                        path: '/random',
                        name: 'random',
                        component: random
                    },
                    {
                        path: '/storedetail/:id',
                        name: 'storeDetail',
                        component: storeDetail,
                        children: [{
                                path: 'rev',
                                name: 'storeReviews',
                                component: storeReviews
                            },
                            {
                                path: 'comments',
                                name: 'comments',
                                component: comments
                            },

                        ]
                    }

                ]
            },


            // review router children
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


            // feed router children
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
    // {
    //     path: '/userpage/edit',
    //     name: "UserEdit",
    //     component: UserEdit
    // },
    // {
    //     path: '/signUpConfirm',
    //     name: 'signUpConfirm',
    //     component: signUpConfirm,
    // },
    // {
    //     path: '/searchPassword',
    //     name: 'searchPassword',
    //     component: searchPassword,
    // },
    {
        path: '/userSearch',
        name: 'userSearch',
        component: userSearch,
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
    // {
    //     path: '/updateNewPw',
    //     name: 'updateNewPw',
    //     component: updateNewPw,
    // },

    // {
    //     path: '/sideMenuLogin',
    //     name: 'sideMenuLogin',
    //     component: sideMenuLogin,
    // },
    // {
    //     path: '/sideMenuReview',
    //     name: 'sideMenuReview',
    //     component: sideMenuReview,
    // },
    // {
    //     path: '/sideMenuFood',
    //     name: 'sideMenuFood',
    //     component: sideMenuFood,
    // },
    // {
    //     path: '/sideMenuUserPage',
    //     name: 'sideMenuUserPage',
    //     component: sideMenuUserPage,
    // },
    {
        path: '/access_token/:access_token/userid/:userid/jwt/:jwt',
        name: 'access_token',
        component: test,
    },

]