import Vue from 'vue'
import Management from './Management'
import router from './router/sysrouter'
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
  el: '#admin', // 这里参考admin.html和admin.vue的根节点id，保持三者一致
  router,
  components: {
    Management
  },
  template: '<Management/>'
})
