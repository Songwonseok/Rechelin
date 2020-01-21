import Login from './views/user/Login.vue'
import Join from './views/user/Join.vue'
import FindPW from './views/user/FindPW.vue'
import JoinDone from './views/user/JoinDone.vue'
import PageNotFound from './views/PageNotFound.vue'
import Components from './views/Components.vue'
import Infopage from './views/user/page/Infopage'
import Editpage from './views/user/page/EditPage'

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
        path: '/infopage/:id',
        name: 'infopage',
        component: Infopage,
        
    },
    {
        path: '/infopage/edit',
        name: 'Editpage',
        component: Editpage
    },
    {
        path: '/components',
        name: 'Components',
        component: Components
    }
]