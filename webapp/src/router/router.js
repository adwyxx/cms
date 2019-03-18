import Vue from 'vue'
import Router from 'vue-router'
import News from '@/pages/News'
import Login from '@/pages/Login'
import NewsList from '@/pages/NewsList'

Vue.use(Router)

const routes = [
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
  }
]
const router = new Router({
  model: 'history',
  routes: routes
})

export default router
