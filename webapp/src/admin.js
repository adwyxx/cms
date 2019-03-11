import Vue from 'vue'
import Management from './Management'
import router from './router/sysrouter'
import ElementUI from 'element-ui' /* 引入全局element-ui */
import 'element-ui/lib/theme-chalk/index.css' /* 引入全局element-ui样式 */

Vue.config.productionTip = false
Vue.use(ElementUI)

/* eslint-disable no-new */
new Vue({
  el: '#admin', // 这里参考admin.html和admin.vue的根节点id，保持三者一致
  router,
  components: {
    Management
  },
  template: '<Management/>'
})
