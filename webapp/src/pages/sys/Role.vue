<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>系统管理</el-breadcrumb-item>
      <el-breadcrumb-item>角色管理</el-breadcrumb-item>
    </el-breadcrumb>
    <el-row>
      <el-col :span="24"
              class="title">
        <li class="el-icon-tickets"></li>
        角色管理
      </el-col>
    </el-row>
    <el-row>
      <el-col :xs="12"
              :sm="12"
              :md="16"
              :lg="18"
              :xl="20"
              style="text-align:left;">
        <el-button type="primary"
                   size="small"
                   icon="el-icon-plus"
                   @click="handleAdd()">新增</el-button>
      </el-col>
      <el-col :xs="12"
              :sm="12"
              :md="8"
              :lg="6"
              :xl="4"
              style="text-align:right;">
        <el-input placeholder="角色名称"
                  size="small"
                  v-model="condition.logonName"
                  class="input-with-select">
          <el-button slot="append"
                     icon="el-icon-search"
                     @click="query()"></el-button>
        </el-input>
      </el-col>
    </el-row>

    <el-table :data="userList"
              style="width: 100%">
      <el-table-column label="角色名称"
                       min-width="180">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.roleName }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作"
                       min-width="100">
        <template slot-scope="scope">
          <el-button size="mini"
                     type="primary"
                     icon="el-icon-edit"
                     circle
                     @click="handleEdit(scope.$index, scope.row)"></el-button>
          <el-button size="mini"
                     type="danger"
                     icon="el-icon-delete"
                     circle
                     @click="handleDelete(scope.$index, scope.row)"></el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination background
                   layout="prev, pager, next"
                   @current-change="loadData()"
                   :current-page="condition.pageIndex"
                   :page-size="condition.pageSize"
                   :total="total">
    </el-pagination>
    <el-dialog title="角色信息"
               width="500px"
               :visible.sync="dialogFormVisible">
      <el-form ref="form"
               size="small"
               label-width="100px"
               :rules="rules"
               :model="curretnUser">
        <el-form-item label="角色名称"
                      prop="displayName">
          <el-input v-model="curretnUser.displayName"
                    suffix-icon="el-icon-star-off"
                    autocomplete="off"
                    @blur="checkName()"></el-input>
        </el-form-item>
        <el-form-item label-width="0px">
          <el-button @click="reset()">取 消</el-button>
          <el-button type="primary"
                     @click="save()">确 定</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'Role',
  data () {
    return {
      dialogFormVisible: false,
      roleNameSuffixIcon: 'el-input__icon el-icon-view',
      saveModel: false,
      formLabelWidth: '100px',
      userList: [],
      total: 0,
      condition: { logonName: null, displayName: null, pageIndex: 1, pageSize: 20 },
      curretnUser: { displayName: null, logonName: null, password: null, mobile: null, email: null },
      rules: {
        displayName: [
          { required: true, message: '请输入姓名', trigger: 'change' }
        ],
        logonName: [
          { required: true, message: '请输入用户名', trigger: 'change' }
        ]
      }
    }
  },
  created () {
    this.loadData()
  },
  methods: {
    handleAdd () {
      this.userNameSuffixIcon = 'el-input__icon el-icon-view'
      this.curretnUser = { displayName: null, logonName: null, password: null, mobile: null, email: null, createTime: new Date(), creator: 'system' }
      this.dialogFormVisible = true
      this.saveModel = false
    },
    handleEdit (index, row) {
      this.curretnUser = row
      this.saveModel = true
      this.dialogFormVisible = true
    },
    handleDelete (index, row) {
      this.$confirm('确定要删除该用户吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$get('/role/delete/' + row.id).then(response => {
          this.dialogFormVisible = false
          this.$message({
            type: 'success',
            duration: 1000,
            message: '删除成功!'
          })
          this.loadData()
        }, (error) => {
          // this.$thows(error)
          this.$message({
            showClose: true,
            message: '哎呀呀，删除数据报错了！..(｡•ˇ‸ˇ•｡)…',
            type: 'error'
          })
          console.log(error)
        })
      }).catch(() => { })
    },
    loadData () {
      this.$post('/role/query', this.condition).then(response => {
        this.userList = response.data
        this.total = response.total
      }, error => {
        console.log(error)
      })
    },
    query () {
      this.condition.pageIndex = 1
      this.loadData()
    },
    save () {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          var url = '/role/add'
          if (this.saveModel) {
            url = '/role/update'
          }
          this.$post(url, this.curretnUser).then(response => {
            this.dialogFormVisible = false
            if (this.saveModel) {
              this.loadData()
            } else {
              this.condition.logonName = null
              this.condition.displayName = null
              this.query()
            }
          }, (error) => {
            // this.$thows(error)
            console.log(error)
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    reset () {
      this.$refs['form'].resetFields()
      this.dialogFormVisible = false
    },
    checkName () {
      if (this.curretnUser.logonName !== null && this.curretnUser.logonName !== '') {
        this.roleNameSuffixIcon = 'el-input__icon el-icon-loading'
        this.$get('/role/checkName/' + this.curretnUser.logonName).then(response => {
          if (response === true) {
            this.roleNameSuffixIcon = 'el-input__icon el-icon-warning'
            this.curretnUser.logonName = ''
            this.$message({
              showClose: true,
              message: '该用户名已被占用，请更改用户名',
              type: 'warning'
            })
          } else {
            this.roleNameSuffixIcon = 'el-input__icon el-icon-success'
          }
        }, (error) => {
          this.roleNameSuffixIcon = 'el-icon-error bg-error'
          // this.$thows(error)
          this.$message({
            showClose: true,
            message: '哎呀呀，报错了！..(｡•ˇ‸ˇ•｡)…',
            type: 'error'
          })
          console.log(error)
        })
      }
    }
  }
}
</script>
