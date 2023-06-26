import request from '@/utils/request'

export function getList(params) {
  return request({
    url: '/user/page',
    method: 'get',
    params
  })
}

export function findOne(id) {
  return request({
    url: `/user/${id}`,
    method: 'get'
  })
}

export function updateOne(data) {
  return request({
    url: `/user`,
    method: 'post',
    data
  })
}

export function deleteOne(id) {
  return request({
    url: `/user/${id}`,
    method: 'delete'
  })
}

export function deleteBatch(data) {
  return request({
    url: `/user/del/batch`,
    method: 'post',
    data
  })
}

export function exportURL() {
  return `${process.env.VUE_APP_BASE_API}/user/export`
}
export function importURL() {
  return `${process.env.VUE_APP_BASE_API}/user/import`
}
