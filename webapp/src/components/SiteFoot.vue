<template>
  <el-row>
    <el-col :span="24"
            style="font-size:12px;color:#ffffff;background: rgb(48, 49, 51);line-height:20px;">
      <p> Copyright &copy; 2019 {{copyright}} All Rights Reserved.</p>
    </el-col>
  </el-row>
</template>
<script>
export default {
  name: 'SiteFoot',
  data () {
    return {
      copyright: 'adwyxx@qq.com',
      registCode: '1100000000'
    }
  }
}
</script>
