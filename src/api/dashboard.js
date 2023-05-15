import request from '@/utils/request'

export function getData() {
  return request({
    url: '/show_books',
    method: 'get'
  })
}
