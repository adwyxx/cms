import { Loading } from 'element-ui'

export default {
  loddingInstens: null,

  // 显示加载动画
  showLoadding: text => {
    loadingInstance = Loading.service({
      spinner: 'el-icon-loading',
      text: text,
      background: 'rgba(0, 0, 0, 0.7)'
    })
  },

  // 隐藏加载动画
  hideLoadding: () => {
    if (loadingInstance) {
      // this.$nextTick(() => { // 以服务的方式调用的 Loading 需要异步关闭
      loadingInstance.close()
      // })
    }
  }
}
