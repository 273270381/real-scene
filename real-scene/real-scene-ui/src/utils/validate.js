/**
 * Created by PanJiaChen on 16/11/18.
 */

/**
 * @param {string} path
 * @returns {Boolean}
 */
export function isExternal(path) {
  return /^(https?:|mailto:|tel:)/.test(path)
}

/**
 * 校验用户名,必须是5-32位
 * @param {string} str
 * @returns {Boolean}
 */
export function isvalidUsername(str) {
  var reg = /^[0-9A-Za-z]{5,32}$/
  return reg.test(str)
}

// 判断是否为空
export function isEmpty(value) {
  // 正则表达式用于判斷字符串是否全部由空格或换行符组成
  var reg = /^\s*$/
  // 返回值为true表示是空字符串
  return !(value != null && value !== undefined && value !== 'undefined' && value !== 'undefined' && !reg.test(value))
}
