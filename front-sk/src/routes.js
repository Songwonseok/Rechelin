import Login from './views/user/Login.vue'
import Join from './views/user/Join.vue'
import FindPW from './views/user/FindPW.vue'
import JoinDone from './views/user/JoinDone.vue'
import PageNotFound from './views/PageNotFound.vue'
import Components from './views/Components.vue'
import Userpage from './views/user/page/Userpage'


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
        path: '/userpage/:id',
        name: 'infopage',
        component: Userpage,
        
    },
    {
        path: '/userpage/edit',
        name: 'Editpage',
        component: Userpage
    },
    {
        path: '/components',
        name: 'Components',
        component: Components
    }
]