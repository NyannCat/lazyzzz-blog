import request from "@/utils/request"

export function pageBy(params) {
  return request({
    url: '/article/all',
    method: 'get',
    params
  })
}

export function submit(data) {
  return request({
    url: '/article',
    method: 'post',
    data
  })
}

export function getArticleById(params) {
  return request({
    url: '/article',
    method: 'get',
    params
  })
}

export function deleteArticleById(params) {
  return request({
    url: '/article',
    method: 'delete',
    params
  })
}
