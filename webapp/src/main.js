// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router/router'
import ElementUI from 'element-ui' /* 引入全局element-ui */
import 'element-ui/lib/theme-chalk/index.css' /* 引入全局element-ui样式 */

Vue.config.productionTip = false
Vue.use(ElementUI)
// 给vue设置原型属性，支持axios post,get请求
/* eslint-disable no-new */
new Vue({
  el: '#app', // 这里参考admin.html和admin.vue的根节点id，保持三者一致
  router,
  components: {
    App
  },
  template: '<App/>'
})
