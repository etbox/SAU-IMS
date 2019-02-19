import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/Home.vue'
import store from './store'
// import Cookies from 'js-cookie'

Vue.use(Router)

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [{
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/about',
      name: 'about',
      // route level code-splitting
      // this generates a separate chunk (about.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import( /* webpackChunkName: "about" */ './views/About.vue')
    },
    {
      path: '/club/:orgId',
      name: 'clubdetails',
      component: () => import('./views/ClubDetails.vue')
    },
    {
      path: '/forgotpw',
      name: 'forgotpw',
      component: () => import('./views/ForgotPW.vue')
    },
    {
      path: '/signup',
      name: 'signup',
      component: () => import('./views/SignUp.vue')
    },
    {
      path: '/clubsignup',
      name: 'clubsignup',
      component: () => import('./views/ClubSignUp.vue')
    },
    {
      path: '/system',
      redirect: '/system/news',
      name: 'system',
      component: () => import('./views/System.vue'),
      beforeEnter: (to, from, next) => {
        if (store.getters["checkLogin"] < 0) {
          // if (Cookies.get('priority') < 0) {
          alert('您尚未登录！');
          next('/');
        } else {
          next();
        }
      },
      children: [{
          path: 'news',
          component: () => import('./views/SystemMain.vue')
        },
        {
          path: 'messages',
          component: () => import('./views/SystemMain.vue')
        }
      ]
    },
  ]
})