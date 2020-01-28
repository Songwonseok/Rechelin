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
        path: '/user/join/done',
        name: 'JoinDone',
        component: JoinDone
    },
    {
        path: '/components',
        name: 'Components',
        component: Components
    }
]