import * as Request from '../index'
import config from '@/assets/config'

// 查询客户信息
export function query (condition) {
  return Request.post(config.api.system.articleQuery, condition)
}
// 添加客户信息
export function add (data) {
  return Request.post(config.api.system.articleAdd, data)
}
// 修改客户信息
export function update (data) {
  return Request.post(config.api.system.articleUpdate, data)
}
// 删除客户信息
export function deleteById (id) {
  return Request.get(config.api.system.articleDelete + id)
}
