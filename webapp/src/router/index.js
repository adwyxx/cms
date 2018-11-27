import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Login from '@/pages/Login'
import Main from '@/pages/sys/Main'
import Auth from '@/utils/auth'

Vue.use(Router)

const routes = [
  {
    path: '/',
    name: 'HelloWorld',
    component: HelloWorld,
    meta: {
      title: 'Hellow',
      requireAuth: false
    }
  },
  {
    path: '/login',
    name: 'Login',
    component: Login,
    meta: {
      title: 'Login',
      requireAuth: false
    }
  },
  {
    path: '/management',
    name: 'Management',
    component: Main,
    meta: {
      title: 'CMS Management',
      requireAuth: true
    }
  }
]
const router = new Router({
  model: 'history',
  routes: routes
})

// 进入下一个路由前处理逻辑
router.beforeEach((to, from, next) => {
  // 叛段是否需要登录验证
  if (to.meta.requireAuth) {
    // 判断是否已经登录，如果登录则直接跳转到下个路由地址，否则跳转到登录页面
    if (Auth.isAuthorized()) {
      next()
    } else {
      next({
        path: '/login',
        query: { url: to.path }
      })
    }
  } else {
    next()
  }
})

export default router
