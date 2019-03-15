import Vue from 'vue'
import Router from 'vue-router'
import News from '@/pages/News'

Vue.use(Router)

const routes = [
  {
    path: '/news/:id',
    name: 'News',
    component: News
  }
]
const router = new Router({
  model: 'history',
  routes: routes
})

export default router
