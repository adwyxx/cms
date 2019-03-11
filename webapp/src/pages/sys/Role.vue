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
                  v-model="condition.roleName"
                  class="input-with-select">
          <el-button slot="append"
                     icon="el-icon-search"
                     @click="query()"></el-button>
        </el-input>
      </el-col>
    </el-row>
    <!-- 角色列表 -->
    <el-table :data="roleList"
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
                     icon="el-icon-setting"
                     circle
                     @click="setProviliges(scope.$index, scope.row)"></el-button>
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
    <!-- 角色列表 End -->
    <!-- 角色信息编辑Dialog -->
    <el-dialog title="角色信息"
               width="500px"
               :visible.sync="dialogFormVisible">
      <el-form ref="form"
               size="small"
               label-width="100px"
               :rules="rules"
               :model="curretnRole">
        <el-form-item label="角色名称"
                      prop="roleName">
          <el-input v-model="curretnRole.roleName"
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
    <!-- 角色信息编辑Dialog End -->
    <!-- 角色授权Dialog -->
    <el-dialog itle="角色授权"
               width="500px"
               :visible.sync="dialogProviligeVisible">
      <el-form>
        <el-form-item>
          <el-tree ref="proviligeForm"
                   size="small"
                   :data="currentRoleProviliges"
                   show-checkbox
                   node-key="id"
                   default-expand-all="true"
                   :default-checked-keys="defaultCheckedKeys"
                   :props="defaultProps">
          </el-tree>
        </el-form-item>
        <el-form-item label-width="0px">
          <el-button @click="dialogProviligeVisible=false">取 消</el-button>
          <el-button type="primary"
                     @click="saveProviliges()">确 定</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
    <!-- 角色授权Dialog End -->
  </div>
</template>

<script>
import * as roleApi from '@/api/sys/roleapi'

export default {
  name: 'Role',
  data () {
    return {
      dialogFormVisible: false,
      dialogProviligeVisible: false,
      roleNameSuffixIcon: 'el-input__icon el-icon-view',
      saveModel: false,
      formLabelWidth: '100px',
      roleList: [],
      currentRoleProviliges: [],
      defaultCheckedKeys: [],
      defaultProps: {
        children: 'children',
        label: 'label'
      },
      total: 0,
      condition: { roleName: null, displayName: null, pageIndex: 1, pageSize: 20 },
      curretnRole: { id: -1, roleName: null, createTime: null, creator: null },
      rules: {
        roleName: [
          { required: true, message: '请输入角色名称', trigger: 'change' }
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
      this.curretnRole = { id: -1, roleName: null, createTime: new Date(), creator: 'system' }
      this.dialogFormVisible = true
      this.saveModel = false
    },
    handleEdit (index, role) {
      this.curretnRole = role
      this.saveModel = true
      this.dialogFormVisible = true
    },
    handleDelete (index, role) {
      this.$confirm('确定要删除该角色吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        roleApi.deleteRole(role.id).then(response => {
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
      roleApi.queryRole(this.condition).then(response => {
        this.roleList = response.data
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
          if (this.saveModel) {
            roleApi.addRole(this.curretnRole).then(response => {
              this.dialogFormVisible = false
              if (this.saveModel) {
                this.loadData()
              } else {
                this.condition.roleName = null
                this.condition.displayName = null
                this.query()
              }
            }, (error) => {
              // this.$thows(error)
              console.log(error)
            })
          } else {
            roleApi.updateRole(this.curretnRole).then(response => {
              this.dialogFormVisible = false
              if (this.saveModel) {
                this.loadData()
              } else {
                this.condition.roleName = null
                this.condition.displayName = null
                this.query()
              }
            }, (error) => {
              // this.$thows(error)
              console.log(error)
            })
          }
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
      if (this.curretnRole.roleName !== null && this.curretnRole.roleName !== '') {
        this.roleNameSuffixIcon = 'el-input__icon el-icon-loading'
        roleApi.checkRoleName(this.curretnRole.roleName).then(response => {
          if (response === true) {
            this.roleNameSuffixIcon = 'el-input__icon el-icon-warning'
            this.curretnRole.roleName = ''
            this.$message({
              showClose: true,
              message: '该角色名称已被占用，请更改角色名称',
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
    },
    setProviliges (index, role) {
      this.dialogProviligeVisible = true
      this.currentRoleProviliges = []
      roleApi.getProviliges(role.id).then(response => {
        this.currentRoleProviliges = response
      }, (error) => {
        // this.$thows(error)
        this.$message({
          showClose: true,
          message: '哎呀呀，报错了！..(｡•ˇ‸ˇ•｡)…',
          type: 'error'
        })
        console.log(error)
      })
    },
    saveProviliges () {

    }
  }
}
</script>
