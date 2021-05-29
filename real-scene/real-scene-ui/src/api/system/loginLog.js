import request from '@/utils/request'

export function getList(params) {
  return request({
    url: '/loginLog/list',
    method: 'get',
    params
  })
}

export function clear() {
  return request({
    url: '/loginLog',
    method: 'delete'
  })
}
export function getLogInfo(id) {
  return request({
    url:'/loginLog/getLogInfo',
    method: 'post',
    params: {
      userid:id
    }
  })
}
