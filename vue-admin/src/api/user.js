import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/user/login',
    method: 'post',
    data
  })
}

export function register(data) {
  return request({
    url: '/install',
    method: 'post',
    data
  })
}

export function getInfo(token) {
  return request({
    url: '/user',
    method: 'get',
    params: {token}
  })
}

export function logout() {
  return request({
    url: '/user/logout',
    method: 'post'
  })
}

export function updateBasicInfo(data) {
  return request({
    url: '/user',
    method: 'put',
    data
  })
}

export function updatePassword(params) {
  return request({
    url: '/user/password',
    method: 'put',
    params
  })
}

export function isInstalled() {
  return request({
    url: '/install',
    method: 'get',
  })
}
