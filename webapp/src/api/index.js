import axios from 'axios'
import { Message, Loading } from 'element-ui'
// import qs from 'qs'

let loadingInstance

/**
 * 请求拦截器
 */
axios.interceptors.request.use(
  function (config) {
    if (sessionStorage.getItem('PER_USERNAME')) {
      config.headers = {
        clientCode: 'CMSClient',
        clientVersion: 'cms_v1.0',
        userName: sessionStorage.getItem('PER_USERNAME') || '',
        certifacte: sessionStorage.getItem('PER_TOKEN') || ''
      }
    }
    return config
  },
  function (error) {
    return Promise.reject(error)
  }
)

/**
 * 响应拦截器
 */
axios.interceptors.response.use(
  function (response) {
    return response
  },
  function (error) {
    return Promise.reject(error)
  }
)

// 关闭loading
function closeLoading () {
  if (loadingInstance) {
    // this.$nextTick(() => { // 以服务的方式调用的 Loading 需要异步关闭
    loadingInstance.close()
    // })
  }
}
// 显示loading
function showLoading () {
  loadingInstance = Loading.service({
    spinner: 'el-icon-loading',
    text: '加载中...',
    background: 'rgba(0, 0, 0, 0.7)'
  })
}

// http post 请求
export function post (url, params, hideErrorMsg) {
  showLoading()
  return new Promise((resolve, reject) => {
    axios
      .post(url, params)
      .then(response => {
        closeLoading()
        response = response.data
        if (response && response.status === 200) {
          resolve(response.data)
        } else {
          console.log(response)
          if (!hideErrorMsg) {
            Message({
              message: `${response.messages}`,
              type: 'error',
              duration: 3 * 1000
            })
          }
        }
      })
      .catch(error => {
        closeLoading()
        if (!hideErrorMsg) {
          Message({
            message: error.Message,
            type: 'error',
            duration: 3 * 1000
          })
        }
        console.log(error)
        reject(error)
      })
  })
}

// http get 请求
export function get (url, params, hideErrorMsg) {
  showLoading()
  return new Promise((resolve, reject) => {
    axios
      .get(url, params)
      .then(response => {
        closeLoading()
        response = response.data
        if (response && response.status === 200) {
          resolve(response.data)
        } else {
          console.log(response)
          if (!hideErrorMsg) {
            Message({
              message: `${response.messages}`,
              type: 'error',
              duration: 3 * 1000
            })
          }
        }
      })
      .catch(error => {
        closeLoading()
        if (!hideErrorMsg) {
          Message({
            message: error.Message,
            type: 'error',
            duration: 3 * 1000
          })
        }
        console.log(error)
        reject(error)
      })
  })
}
