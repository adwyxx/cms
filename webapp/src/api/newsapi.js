import config from '@/assets/config'
import * as Request from './index'

// 获取所有文章类别
export function categories () {
  return Request.get(config.api.news.categories)
}
// 根据文章ID获取文章信息
export function news (id) {
  return Request.get(config.api.news.news + id)
}
// 获取所有文章最新的几条数据
export function topAllNews (topNum) {
  return Request.post(config.api.news.topallnews + topNum)
}

// 获取文章分类下最新的几条信息
export function topCategoryNews (categoryId, topNum) {
  return Request.post(
    config.api.news.topcategorynews + categoryId + '/' + topNum
  )
}

// 获取文章分类下最新的几条信息
export function query (condition) {
  return Request.post(config.api.news.query, condition)
}
