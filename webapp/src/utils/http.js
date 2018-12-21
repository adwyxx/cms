'use strict'
import axios from 'axios'
import qs from 'qs'
import router from '@/router/sysrouter'

// 配置全局api域名地址
axios.defaults.baseURL = 'http://localhost:8080/'

// 添加请求拦截设置
axios.interceptors.request.use(config => {
  return config
}, error => {
  return Promise.reject(error)
})

// 添加请求结果拦截设置
axios.interceptors.response.use(config => {
  return config
}, error => {
  return Promise.reject(error)
})

// 检查请求结果返回状态
const checkStatus = (response) => {
  // 如果http状态码正常，则直接返回数据
  if (response && (response.status === 200 || response.status === 304 || response.status === 400)) {
    return response
    // 如果不需要除了data之外的数据，可以直接 return response.data
  } else if (response.status === 401) {
    // 如果是401错误，跳转至登录页面
    router.push({
      path: 'Login'
    })
  }
  // 异常状态下，把错误信息返回去
  return {
    status: -404,
    msg: '网络异常,您请求的页面不存在。'
  }
}

export default {
  post: (url, data) => {
    return axios({
      method: 'POST',
      url,
      data: qs.stringify(data),
      headers: {
        'X-Requested-With': 'XMLHttpRequest',
        'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
      }
    }).then(function (response) {
      return checkStatus(response)
    })
  },
  get: (url, params) => {
    return axios({
      method: 'GET',
      url,
      params,
      headers: {
        'X-Requested-With': 'XMLHttpRequest',
        'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
      }
    }).then((response) => {
      return checkStatus(response)
    })
  }
}
