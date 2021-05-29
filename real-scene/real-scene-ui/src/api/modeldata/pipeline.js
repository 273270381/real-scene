import request from '@/utils/request'

export function getList(params) {
  return request({
    url: '/pipeline/list',
    method: 'get',
    params
  })
}


export function exportXls(params) {
  return request({
    url: '/pipeline/export',
    method: 'get',
    params
  })
}

export function importXls(params) {
  return request({
    url: '/pipeline/importData',
    method: 'post',
    headers:{
      'Content-type': 'multipart/form-data; charset=utf-8'
    },
    data:params
  })
}


export function save(params) {
  return request({
    url: '/pipeline',
    method: 'post',
    params
  })
}

export function remove(recordIds) {
  return request({
    url: '/pipeline/' + recordIds,
    method: 'delete',
  })
}

export function getPipeLineDetail(record_id){
  return request({
    url: '/pipeline/detail/' +record_id ,
    method: 'get'
  })
}

export function importTemplate(){
  return request({
    url: '/pipeline/importTemplate',
    method: 'get',
  })
}

export function queryDictFillType(params){
  return request({
    url: '/pipeline/getDictFillType/' + params,
    method: 'get',
  })
}

export function queryDictMaterialType(params){
  return request({
    url: '/pipeline/getDictMaterialType/' + params,
    method: 'get',
  })
}

export function queryCompanyList(){
  return request({
    url: '/pipeline/company',
    method: 'get',
  })
}

export function queryDictTypeList(params){
  return request({
    url: '/pipeline/getDictType/' + params,
    method: 'get',
  })
}

