

import LoginForm from './views/user/SignUpForm.vue';
import Login from './views/user/Login.vue';
import Join from './views/user/Join.vue';
import LoginComplete from './views/user/LoginComplete.vue';
import LoginFail from './views/user/LoginFail.vue';
import testForm from './views/user/testForm.vue';
import Components from './views/Components.vue';


export default [


    {
        path : '/',
        name : 'Login',
        component : Login
    },
    {
        path : '/user/join',
        name : 'Join',
        component : Join
    },
    {
        path : '/components',
        name : 'Components',
        component : Components
    },
    {
        path : '/SignUpForm',
        name : 'SignUpForm',
        component : LoginForm
    },
    {
        path : '/LoginComplete',
        name : 'LoginComplete',
        component : LoginComplete
    },
    {
        path : '/LoginFail',
        name : 'LoginFail',
        component : LoginFail
    },
    {
        path : '/testForm',
        name : 'testForm',
        component : testForm,
    },
]
