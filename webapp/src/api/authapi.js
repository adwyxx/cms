import config from '@/assets/config'
import * as Request from './index'

// 登录
export function login (name, pass) {
  let formData = new FormData()
  formData.append('username', name)
  formData.append('password', pass)
  return Request.post(config.api.auth.login, formData)
}
// 获取用户恓
export function userInfo () {
  return Request.get(config.api.auth.userInfo)
}
