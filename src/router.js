import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/Home.vue'

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
      name: 'system',
      component: () => import('./views/System.vue')
    },
    {
      path: '/demo',
      name: 'demo',
      component: () => import('./views/demo.vue')
    }
  ]
})