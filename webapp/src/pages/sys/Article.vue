<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>系统管理</el-breadcrumb-item>
      <el-breadcrumb-item>文章管理</el-breadcrumb-item>
    </el-breadcrumb>
    <el-row>
      <el-col :span="24"
              class="title">
        <li class="el-icon-tickets"></li>
        文章管理
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
        <el-input placeholder="请输入文章标题"
                  size="small"
                  v-model="condition.title"
                  class="input-with-select">
          <el-button slot="append"
                     icon="el-icon-search"
                     @click="query()"></el-button>
        </el-input>
      </el-col>
    </el-row>

    <el-table :data="userList"
              style="width: 100%">

      <el-table-column label="标题"
                       min-width="250">
        <span style="margin-left: 10px">{{ scope.row.title }}</span>
      </el-table-column>
      <el-table-column label="类别"
                       min-width="180">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.categoryPath }}</span>
        </template>
      </el-table-column>
      <el-table-column label="作者"
                       min-width="180">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.author }}</span>
        </template>
      </el-table-column>
      <el-table-column label="添加日期"
                       min-width="180">
        <template slot-scope="scope">
          <i class="el-icon-time"></i>
          <span style="margin-left: 10px">{{ scope.row.createTime }}</span>
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
    <el-dialog title="文章信息"
               width="500px"
               :visible.sync="dialogFormVisible">
      <el-form ref="form"
               size="small"
               label-width="100px"
               :rules="rules"
               :model="currentData">
        <el-form-item label="标题"
                      prop="title">
          <el-input v-model="currentData.title"
                    suffix-icon="el-icon-star-off"
                    autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="类别"
                      prop="logonName">

        </el-form-item>
        <el-form-item label="作者">
          <el-input v-model="currentData.author"
                    suffix-icon="el-icon-phone"
                    autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="内容">
          <el-input v-model="currentData.email"
                    suffix-icon="el-icon-message"
                    autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label-width="0px">
          <el-button @click="reset()">取 消</el-button>
          <el-button type="primary"
                     @click="saveData()">确 定</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import * as articleApi from '@/api/article/articleapi'

export default {
  name: 'Article',
  data () {
    return {
      dialogFormVisible: false,
      userNameSuffixIcon: 'el-input__icon el-icon-view',
      saveModel: false,
      formLabelWidth: '100px',
      userList: [],
      total: 0,
      condition: { title: null, pageIndex: 1, pageSize: 20 },
      currentData: { displayName: null, logonName: null, password: null, mobile: null, email: null },
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
      this.currentData = { displayName: null, logonName: null, password: null, mobile: null, email: null, createTime: new Date(), creator: 'system' }
      this.dialogFormVisible = true
      this.saveModel = false
    },
    handleEdit (index, row) {
      this.currentData = row
      this.saveModel = true
      this.dialogFormVisible = true
    },
    handleDelete (index, row) {
      this.$confirm('确定要删除该用户吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        articleApi.deleteById(row.id).then(response => {
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
      articleApi.query(this.condition).then(response => {
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
    saveData () {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          if (this.saveModel) {
            articleApi.update(this.currentData).then(response => {
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
          }
          else {
            articleApi.add(this.currentData).then(response => {
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
    }
  }
}
</script>
