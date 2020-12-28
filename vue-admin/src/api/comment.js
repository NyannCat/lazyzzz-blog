import request from "@/utils/request"

export function pageBy(params) {
  return request({
    url: '/comment',
    method: 'get',
    params
  })
}

export function reply(data) {
  return request({
    url: '/comment',
    method: 'post',
    data
  })
}

export function deleteArticleById(params) {
  return request({
    url: '/comment',
    method: 'delete',
    params
  })
}
