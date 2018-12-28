<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>系统管理</el-breadcrumb-item>
      <el-breadcrumb-item>用户管理</el-breadcrumb-item>
    </el-breadcrumb>
    <el-row>
      <el-col :span="24"
              class="title">
        <li class="el-icon-tickets"></li>
        用户管理
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
                   icon="el-icon-plus">新增</el-button>
      </el-col>
      <el-col :xs="12"
              :sm="12"
              :md="8"
              :lg="6"
              :xl="4"
              style="text-align:right;">
        <el-input placeholder="请输入用户名"
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
      <el-table-column label="最近登陆日期"
                       min-width="180">
        <template slot-scope="scope">
          <i class="el-icon-time"></i>
          <span style="margin-left: 10px">{{ scope.row.date }}</span>
        </template>
      </el-table-column>
      <el-table-column label="姓名"
                       min-width="180">
        <template slot-scope="scope">
          <el-popover trigger="hover"
                      placement="top">
            <p>姓名: {{ scope.row.displayName }}</p>
            <p>用户名: {{ scope.row.logonName }}</p>
            <p>电话: {{ scope.row.mobile }}</p>
            <p>邮箱: {{ scope.row.email }}</p>
            <div slot="reference"
                 class="name-wrapper">
              <el-tag size="medium">{{ scope.row.displayName }}</el-tag>
            </div>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column label="用户名"
                       min-width="180">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.logonName }}</span>
        </template>
      </el-table-column>
      <el-table-column label="电话"
                       min-width="180">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.mobile }}</span>
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
  </div>
</template>

<style>
</style>

<script>
export default {
  name: 'User',
  data () {
    return {
      userList: [],
      total: 0,
      condition: { logonName: null, displayName: null, pageIndex: 1, pageSize: 20 }
    }
  },
  created () {
    this.loadData()
  },
  methods: {
    handleAdd () {
    },
    handleEdit (index, row) {
    },
    handleDelete (index, row) {
    },
    loadData () {
      this.$post('/users/query', this.condition).then(response => {
        this.userList = response.data
        this.total = response.total
      }, error => {
        console.log(error)
      })
    },
    query () {
      this.condition.pageIndex = 1
      this.loadData()
    }
  }
}
</script>
