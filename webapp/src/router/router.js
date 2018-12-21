import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/pages/Login'
import User from '@/pages/sys/User'
import Welcome from '@/pages/sys/Welcome'

Vue.use(Router)

const routes = [{
  path: '/',
  name: 'Welcome',
  component: Welcome,
  meta: {
    title: 'Welcome',
    requireAuth: false
  }
}, {
  path: '/login',
  name: 'Login',
  component: Login,
  meta: {
    title: 'Login',
    requireAuth: false
  }
}, {
  path: '/management',
  name: 'Management',
  component: Welcome,
  meta: {
    title: 'Management',
    requireAuth: true
  }
}, {
  path: '/management/user',
  name: 'User',
  component: User,
  meta: {
    title: 'User',
    requireAuth: true
  }
}]
const router = new Router({
  model: 'history',
  routes: routes
})

export default router
