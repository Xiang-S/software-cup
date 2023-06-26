import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/user/login',
    method: 'post',
    data
  })
}

export function getInfo(username) {
  return request({
    url: `/user/username/${username}`,
    method: 'get'
  })
}

export function saveInfo(data) {
  return request({
    url: `/user`,
    method: 'post',
    data
  })
}

export function uploadAvatarURL() {
  return `${process.env.VUE_APP_BASE_API}/file/upload`
}
