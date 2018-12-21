<template>
  <div>
    <i type="success"
       circle
       class="el-icon-circle-plus-outline"
       @click="handleAdd()"></i>
    <el-input placeholder="请输入用户名"
              v-model="condition.userName"
              class="input-with-select">
      <el-button slot="append"
                 icon="el-icon-search"></el-button>
    </el-input>

    <el-table :data="userList"
              style="width: 100%">
      <el-table-column label="最近登陆日期"
                       width="180">
        <template slot-scope="scope">
          <i class="el-icon-time"></i>
          <span style="margin-left: 10px">{{ scope.row.date }}</span>
        </template>
      </el-table-column>
      <el-table-column label="姓名"
                       width="180">
        <template slot-scope="scope">
          <el-popover trigger="hover"
                      placement="top">
            <p>姓名: {{ scope.row.displayName }}</p>
            <p>用户名: {{ scope.row.userName }}</p>
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
                       width="180">
        <template slot-scope="scope">
          <i class="el-icon-time"></i>
          <span style="margin-left: 10px">{{ scope.row.userName }}</span>
        </template>
      </el-table-column>
      <el-table-column label="电话"
                       width="180">
        <template slot-scope="scope">
          <i class="el-icon-time"></i>
          <span style="margin-left: 10px">{{ scope.row.mobile }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button size="mini"
                     @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
          <el-button size="mini"
                     type="danger"
                     @click="handleDelete(scope.$index, scope.row)">删除</el-button>
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
      condition: { userName: '', displayName: '', pageIndex: 1, pageSize: 20 }
    }
  },
  created () {
    // this.loadData()
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
        this.userList = response.data.data
        this.total = response.data.total
      }, error => {
        console.log(error)
      })
    }
  }
}
</script>
