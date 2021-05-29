import request from '@/utils/request'

export function getList(params) {
  return request({
    url: '/camera/list',
    method: 'get',
    params
  })
}

export function save(params) {
  return request({
    url: '/camera',
    method: 'post',
    params
  })
}

export function remove(id) {
  return request({
    url: '/camera',
    method: 'delete',
    params: {
      id: id
    }
  })
}
