import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from 'views/Login.vue'
import MessagesList from 'pages/MessageList.vue'
import Profile from 'views/Profile.vue'

Vue.use(VueRouter);

const routes = [
    {path: '/', component: MessagesList},
    {path: '/login', component: Login},
    {path: '/profile', component: Profile},





    {path: '*', component: MessagesList}/*,
    {
        path: '/register',
        name: 'register',
        meta: {layout: 'empty'},
        component: () => import('../views/Register')
    },
    {
        path: '/categories',
        name: 'categories',
        meta: {layout: 'main'},
        component: () => import('../views/Categories')
    },
    {
        path: '/detail',
        name: 'detail',
        meta: {layout: 'main'},
        component: () => import('../views/DetailRecord')
    },
    {
        path: '/history',
        name: 'history',
        meta: {layout: 'main'},
        component: () => import('../views/History')
    },
    {
        path: '/',
        name: 'home',
        meta: {layout: 'main'},
        component: () => import('../views/Home')
    },
    {
        path: '/planning',
        name: 'planning',
        meta: {layout: 'main'},
        component: () => import('../views/Planning')
    },
    {
        path: '/profile',
        name: 'profile',
        meta: {layout: 'main'},
        component: () => import('../views/Profile')
    },
    {
        path: '/record',
        name: 'record',
        meta: {layout: 'main'},
        component: () => import('../views/Record')
    }*/
]

export default new VueRouter({
    mode: 'history',
    /*
        base: process.env.BASE_URL,
    */
    routes
})
