import request from '@/utils/request'

export function getData() {
  return request({
    url: '/echarts/bar',
    method: 'get'
  })
}


export function getMapData() {
  return request({
    url: '/echarts/map',
    method: 'get'
  })
}

