import Vue from 'vue'
import Router from 'vue-router'
import Auth from '@/utils/auth'
import Login from '@/pages/Login'
import User from '@/pages/sys/User'
import Welcome from '@/pages/sys/Welcome'

Vue.use(Router)

const routes = [{
  path: '',
  alias: 'welcom',
  name: 'Welcome',
  component: Welcome,
  meta: {
    title: 'Welcome',
    requireAuth: true
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
const sysrouter = new Router({
  model: 'history',
  routes: routes
})

// 进入下一个路由前处理逻辑
sysrouter.beforeEach((to, from, next) => {
  // 叛段是否需要登录验证
  if (to.meta.requireAuth) {
    // 判断是否已经登录，如果登录则直接跳转到下个路由地址，否则跳转到登录页面
    if (Auth.isAuthorized()) {
      next()
    } else {
      next({
        path: '/login',
        query: {
          url: to.path
        }
      })
    }
  } else {
    next()
  }
})

export default sysrouter
