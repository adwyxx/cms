'use strict'
const DateTimeFormater = {}
// 将值转化为日期类型
DateTimeFormater.parserDate = function (date) {
  if (date instanceof Date) {
    return date
  }
  var t = Date.parse(date)
  if (!isNaN(t)) {
    return new Date(Date.parse(date.replace(/-/g, '/')))
  } else {
    return new Date()
  }
}
// 格式如日期字符串，格式为:yyyy-MM-dd HH:mm:ss
DateTimeFormater.foramteDateTime = function (dt) {
  if (dt === null || dt === undefined || dt === '') {
    return ''
  }
  var date = DateTimeFormater.parserDate(dt)
  var y = date.getFullYear()
  var m = date.getMonth() + 1
  m = m < 10 ? ('0' + m) : m
  var d = date.getDate()
  d = d < 10 ? ('0' + d) : d
  var h = date.getHours()
  var minute = date.getMinutes()
  minute = minute < 10 ? ('0' + minute) : minute
  var second = date.getSeconds()
  second = minute < 10 ? ('0' + second) : second
  return y + '-' + m + '-' + d + ' ' + h + ':' + minute + ':' + second
}
// 格式日期，格式为:yyyy-MM-dd
DateTimeFormater.foramteDate = function (dt) {
  if (dt === null || dt === undefined || dt === '') {
    return ''
  }
  var date = DateTimeFormater.parserDate(dt)
  var y = date.getFullYear()
  var m = date.getMonth() + 1
  m = m < 10 ? ('0' + m) : m
  var d = date.getDate()
  d = d < 10 ? ('0' + d) : d

  return y + '-' + m + '-' + d
}
// 格式中文日期，格式为:yyyy年M月dd日
DateTimeFormater.foramteCnDate = function (dt) {
  if (dt === null || dt === undefined || dt === '') {
    return ''
  }
  var date = DateTimeFormater.parserDate(dt)
  var y = date.getFullYear()
  var m = date.getMonth() + 1
  var d = date.getDate()
  return y + '年' + m + '月' + d + '日'
}
// 格式时间，格式为:HH:mm:ss
DateTimeFormater.foramteTime = function (dt) {
  if (dt === null || dt === undefined || dt === '') {
    return ''
  }
  var date = DateTimeFormater.parserDate(dt)
  var h = date.getHours()
  var minute = date.getMinutes()
  minute = minute < 10 ? ('0' + minute) : minute
  var second = date.getSeconds()
  second = minute < 10 ? ('0' + second) : second
  return h + ':' + minute + ':' + second
}

export default DateTimeFormater
