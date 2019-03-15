<template>
  <div style="width:400px;margin-left:auto;margin-right:auto;magrin-top:200px;">
    <el-card shadow="hover">
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
  </div>
</template>
<script>
import * as commonApi from '@/api/commonapi'
import { Message } from 'element-ui'

export default {
  data () {
    return { username: '', password: '' }
  },
  methods: {
    login () {
      if (this.username !== '' && this.password !== '') {
        commonApi.login(this.username, this.password).then(response => {
          if (response.status === 'SUCCESS') {
            this.$router.push({ path: '/management' })
          } else {
            Message({
              message: response.message,
              type: 'error',
              duration: 3 * 1000
            })
          }
        })
      }
    }
  }
}
</script>
