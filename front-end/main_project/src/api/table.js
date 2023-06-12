import request from '@/utils/request'

export function getList() {
  return request({
    url: '/user',
    method: 'get'
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

export function DeleteOne(id) {
  return request({
    url: `/user/${id}`,
    method: 'delete'
  })
}
