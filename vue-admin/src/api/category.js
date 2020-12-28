import request from "@/utils/request"

export function getCategoryList() {
  return request({
    url: '/category',
    method: 'get',
  })
}

export function createCategory(params) {
  return request({
    url: '/category',
    method: 'post',
    params
  })
}

export function updateCategory(params) {
  return request({
    url: '/category',
    method: 'put',
    params
  })
}

export function deleteCategory(params) {
  return request({
    url: '/category',
    method: 'delete',
    params
  })
}
