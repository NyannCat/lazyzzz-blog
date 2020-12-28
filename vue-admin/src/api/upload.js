import request from "@/utils/request"

export function pageBy(params) {
  return request({
    url: '/attachment',
    method: 'get',
    params
  })
}

export function deleteBy(params) {
  return request({
    url: '/attachment',
    method: 'delete',
    params
  })
}
