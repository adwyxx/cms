import * as Request from '../index'
import config from '@/assets/config'

// 查询客户信息
export function queryRole (condition) {
  return Request.post(config.api.system.roleQuery, condition)
}
// 添加客户信息
export function addRole (data) {
  return Request.post(config.api.system.roleAdd, data)
}
// 修改客户信息
export function updateRole (data) {
  return Request.post(config.api.system.roleUpdate, data)
}
// 删除客户信息
export function deleteRole (id) {
  return Request.get(config.api.system.roleDelete + id)
}
// 验证客户名
export function checkRoleName (name) {
  return Request.get(config.api.system.roleCheckName + name)
}
// 验证客户名
export function getProviliges (id) {
  return Request.get(config.api.system.roleGetProviliges + id)
}
