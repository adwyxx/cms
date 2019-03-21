import Vue from 'vue'
import Router from 'vue-router'
import News from '@/pages/News'
import Login from '@/pages/Login'
import Index from '@/pages/Index'
import NewsList from '@/pages/NewsList'
import About from '@/pages/About'
import Connect from '@/pages/Connect'

Vue.use(Router)

const routes = [
  {
    path: '/',
    name: 'Index',
    component: Index,
    meta: {
      title: 'Index'
    }
  },
  {
    path: '/login',
    name: 'Login',
    component: Login,
    meta: {
      title: 'Login'
    }
  },
  {
    path: '/news/:id',
    name: 'News',
    component: News
  },
  {
    path: '/newslist',
    name: 'NewsList',
    component: NewsList
  },
  {
    path: '/newslist/:categoryId',
    name: 'NewsList',
    component: NewsList
  },
  {
    path: '/about',
    name: 'About',
    component: About
  },
  {
    path: '/connect',
    name: 'Connect',
    component: Connect
  }
]
const router = new Router({
  model: 'history',
  routes: routes
})

export default router
