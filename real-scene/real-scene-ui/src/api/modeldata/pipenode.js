import request from '@/utils/request'

export function getList(params) {
  return request({
    url: '/pipenode/list',
    method: 'get',
    params
  })
}


export function exportXls(params) {
  return request({
    url: '/pipenode/export',
    method: 'get',
    params
  })
}




export function save(params) {
  return request({
    url: '/pipenode',
    method: 'post',
    params
  })
}

export function remove(probePoints) {
  return request({
    url: '/pipenode/' + probePoints,
    method: 'delete'
  })
}

export function getPipeNodeDetail(probe_point) {
  return request({
    url: '/pipenode/detail/' + probe_point,
    method: 'get'
  })
}

export function importXls(params) {
  return request({
    url: '/pipenode/importData',
    method: 'post',
    headers:{
      'Content-type': 'multipart/form-data; charset=utf-8'
    },
    data:params
  })
}

export function importTemplate(){
  return request({
    url: '/pipenode/importTemplate',
    method: 'get',
  })
}


export function queryDictTypeList(params){
  return request({
    url: '/pipeline/getDictType/' + params,
    method: 'get',
  })
}

export function queryCompanyList(){
  return request({
    url: '/pipenode/company',
    method: 'get',
  })
}

export function queryManholeTypeList(params){
  return request({
    url: '/pipenode/manholeType/' + params,
    method: 'get',
  })
}

