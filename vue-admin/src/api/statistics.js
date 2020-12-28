import request from "@/utils/request"

export function infoDashboard() {
  return request({
    url: '/statistics/dashboard',
    method: 'get'
  })
}

export function logs(params) {
  return request({
    url: '/statistics/logs',
    method: 'get',
    params
  })
}

export function user() {
  return request({
    url: '/statistics/user',
    method: 'get',
  })
}
