'use strict'
import Vue from 'vue'
// import http from '@/utils/http'
import VueCookie from 'vue-cookies'

Vue.use(VueCookie)

export default {
  isAuthorized: () => {
    return true
  },
  getUser: () => {
    return {
      id: '',
      name: ''
    }
  },
  getToken: () => {

  }
}
