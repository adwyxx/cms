// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui' /* 引入全局element-ui */
import 'element-ui/lib/theme-chalk/index.css' /* 引入全局element-ui样式 */
import http from './utils/http'

Vue.config.productionTip = false
Vue.use(ElementUI)
// 给vue设置原型属性，支持axios post,get请求
Vue.prototype.$http = http
Vue.prototype.$get = http.get
Vue.prototype.$post = http.post
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: {
    App
  },
  template: '<App/>'
})
