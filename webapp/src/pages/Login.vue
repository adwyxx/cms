<template>
  <el-container>
    <el-header>
      <span>新闻管理系统</span>
    </el-header>
    <el-main>
      <el-card shadow="always"
               class="box-card">
        <div slot="header"
             class="clearfix">
          <span>系统登录</span>
        </div>
        <el-form>
          <el-form-item label="用户名">
            <el-input type="input"
                      placeholder="请输入用户名"
                      v-model="username"></el-input>
          </el-form-item>
          <el-form-item label="密码">
            <el-input type="password"
                      placeholder="请输入密码"
                      v-model="password"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary"
                       @click="login">登陆</el-button>
          </el-form-item>
        </el-form>
      </el-card>
    </el-main>
  </el-container>
</template>

<style>
.box-card {
  width: 480px;
  margin-left: auto;
  margin-right: auto;
  margin-top: 50px;
}
.el-container {
  height: 100%;
}
.el-header {
  background-color: #409eff;
  height: 100px !important;
  font-size: 32px;
  font-weight: bold;
  color: #ffffff;
  line-height: 100px;
}
.el-footer {
  background-color: #909399;
  height: 40px !important;
}
.el-button {
  width: 100%;
}
body {
  background-color: #f2f6fc;
}
</style>

<script>
import * as authApi from '@/api/authapi'
import Auth from '@/assets/auth'
import basicConfig from '@/assets/basic-config'

export default {
  data () {
    return { username: '', password: '' }
  },
  methods: {
    login () {
      if (this.username !== '' && this.password !== '') {
        authApi.login(this.username, this.password).then(response => {
          Auth.setToken(response)
          if (this.$route.query && this.$route.query.redirect) {
            top.location.href = basicConfig.host + '/admin.html#' + this.$route.query.redirect
          } else {
            top.location.href = basicConfig.host + '/admin.html#/management'
          }
          // this.$router.push({ path: '/management' })
        })
      }
    }
  }
}
</script>
