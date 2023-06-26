import { login, getInfo } from '@/api/user'
import { resetRouter } from '@/router'
import router from '@/router'

const getDefaultState = () => {
  return {
    name: '',
    avatar: ''
  }
}

const state = getDefaultState()

const mutations = {
  SET_NAME: (state, name) => {
    state.name = name
  },
  SET_AVATAR: (state, avatar) => {
    state.avatar = avatar
  },
  LOG_OUT: () => {
    localStorage.removeItem('user')
    localStorage.removeItem('menus')
    resetRouter()
  }
}

const actions = {
  // user login
  login({ commit }, userInfo) {
    const { username, password } = userInfo
    return new Promise((resolve, reject) => {
      login({ username: username.trim(), password: password }).then(response => {
        const { data } = response
        localStorage.setItem('user', JSON.stringify(data))
        localStorage.setItem('menus', JSON.stringify(data.menus))
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },
  getInfo({ commit, state }, username) {
    return new Promise((resolve, reject) => {
      getInfo(username).then(response => {
        const { data } = response

        if (!data) {
          return reject('Verification failed, please Login again.')
        }

        const { username, avatarUrl } = data

        commit('SET_NAME', username)
        commit('SET_AVATAR', avatarUrl)
        resolve(data)
      }).catch(error => {
        reject(error)
      })
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}

