'use strict'
// import http from '@/utils/http'
import VueCookie from 'vue-cookies'

const Auth = {
  isAuthorized: () => {
    if (Auth.getToken() === null) {
      return false
    }
    return true
  },
  getUser: () => {
    return {
      id: '',
      name: ''
    }
  },
  setToken: token => {
    VueCookie.set('token', token, 60 * 60 * 24)
  },
  getToken: () => {
    if (VueCookie.isKey('token')) {
      return VueCookie.get('token')
    }
    return null
  }
}

export default Auth
