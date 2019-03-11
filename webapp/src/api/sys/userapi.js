import * as Request from '../index'
import config from '@/assets/config'

// 查询客户信息
export function queryUsers (condition) {
  return Request.post(config.api.system.userQuery, condition)
}
// 添加客户信息
export function addUser (data) {
  return Request.post(config.api.system.userAdd, data)
}
// 修改客户信息
export function updateUser (data) {
  return Request.post(config.api.system.userUpdate, data)
}
// 删除客户信息
export function deleteUser (id) {
  return Request.get(config.api.system.userDelete + id)
}
// 验证客户名
export function checkLogonName (name) {
  return Request.get(config.api.system.userCheckLogonName + name)
}
