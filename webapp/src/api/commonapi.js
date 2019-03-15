import config from '@/assets/config'
import * as Request from './index'

// 登录
export function login (name, pass) {
  let formData = new FormData()
  formData.append('username', name)
  formData.append('password', pass)
  return Request.post(config.api.common.login, formData)
}
