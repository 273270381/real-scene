import request from '@/utils/request'

export function getList(params) {
  return request({
    url: '/role/list',
    method: 'get',
    params
  })
}
export function getAllRoles() {
  return request({
    url: '/role/allRoles',
    method: 'get'
  })
}

export function save(params) {
  return request({
    url: '/role',
    method: 'post',
    params
  })
}

export function remove(roleIds) {
  return request({
    url: '/role/' + roleIds,
    method: 'delete'
  })
}

export function getRole(roleId) {
  return request({
    url: '/role/' + roleId,
    method: 'get'
  })
}

export  function roleTreeListByIdUser(idUser){
  return request({
    url: '/role/roleTreeListByIdUser',
    method: 'get',
    params: {
      idUser: idUser
    }
  })
}


export function savePermissons(params) {
  return request({
    url: '/role/savePermisson',
    method: 'post',
    params
  })
}
