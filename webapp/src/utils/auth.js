'use strict'
import Vue from 'vue'
import http from './http'
import VueCookie from 'vue-cookies'

Vue.use(VueCookie)

export default {
  isAuthorized () {
    return true
  },
  getUser () {
    return {
      id: '',
      name: ''
    }
  },
  getToken () {
    
  }
}
