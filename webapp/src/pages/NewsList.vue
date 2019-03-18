<template>
  <el-container>
    <el-header>

      <site-head></site-head>
    </el-header>
    <el-main>
      <el-row>
        <el-col :span="24"
                style="height:30px;">
          <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>活动管理</el-breadcrumb-item>
            <el-breadcrumb-item>活动列表</el-breadcrumb-item>
            <el-breadcrumb-item>活动详情</el-breadcrumb-item>
          </el-breadcrumb>
        </el-col>
      </el-row>

      <el-row style="border-bottom:2px solid #409EFF;">
        <el-col :xs="12"
                :sm="12"
                :md="16"
                :lg="18"
                :xl="20"
                style="text-align:left;height:40px;line-height:40px;font-size:14xp;font-weight:bold;">
          <i class="el-icon-tickets"></i>
          新闻列表
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

      <el-table :data="dataList"
                style="width: 100%">
        <el-table-column label="标题"
                         min-width="250">
          <template slot-scope="scope">
            <a @click="showNews(scope.row.id)">{{ scope.row.title }}</a>
          </template>
        </el-table-column>
        <el-table-column label="作者"
                         width="180">
          <template slot-scope="scope">
            <span style="margin-left: 10px">{{ scope.row.author }}</span>
          </template>
        </el-table-column>
        <el-table-column label="发布日期"
                         width="180">
          <template slot-scope="scope">
            <span style="margin-left: 10px">{{ scope.row.createTime }}</span>
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
    </el-main>
  </el-container>
</template>
<script>
import SiteHead from '@/components/SiteHead'
import * as newsApi from '@/api/newsapi'

export default {
  name: 'NewsList',
  components: { SiteHead },
  data () {
    return {
      dataList: [],
      condition: { categoryId: null, title: null, pageSize: 10, pageIndex: 1 },
      total: 0
    }
  },
  created () {
    if (this.$route.params && this.$route.params.categoryId) {
      this.condition.categoryId = this.$route.params.categoryId
    }
    this.getData()
  },
  methods: {
    getData () {
      newsApi.query(this.condition).then((response) => {
        this.dataList = response.data
        this.total = response.total
      })
    },
    showNews (id) {
      top.open('http://localhost:8010/#/news/' + id)
    }
  }
}
</script>
