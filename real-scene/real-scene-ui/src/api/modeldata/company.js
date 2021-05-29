import request from '@/utils/request'

export function getList(params) {
  return request({
    url: '/company/list',
    method: 'get',
    params
  })
}

export function save(params) {
  return request({
    url: '/company',
    method: 'post',
    params
  })
}

export function remove(id) {
  return request({
    url: '/company',
    method: 'delete',
    params: {
      id: id
    }
  })
}

/**
 * 是否已经存在企业信息
 * @param param
 */
export function isHaveInfo(param) {
  return request({
    url: 'company/isHave',
    method: 'get',
    params: param
  })
}
