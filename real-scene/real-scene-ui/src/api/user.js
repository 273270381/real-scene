import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/account/login',
    method: 'post',
    params: {
      "username": data.username,
      "password": data.password,
      "letOut": data.letOut
    }
  })
}

export function getInfo() {
  return request({
    url: '/account/info',
    method: 'get'
  })
}

export function logout(token) {
  return request({
    url: '/logout',
    method: 'post'
  })
}

export function updatePwd(params) {
  return request({
    url: '/account/updatePwd',
    method: 'post',
    params
  })
}

export function resetPwd(params) {
  return request({
    url: '/account/resetPwd',
    method: 'post',
    params
  })
}
