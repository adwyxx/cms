<template>
  <el-container>
    <el-header>

      <site-head></site-head>
    </el-header>
    <el-main>
      <el-row>
        <el-col :span="24">
          <el-carousel indicator-position="outside"
                       height="500px">
            <el-carousel-item v-for="item in carouselItems"
                              :key="item.id">
              <img :src="item.imageUrl"
                   width="100%"
                   height="500" />
            </el-carousel-item>
          </el-carousel>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12"
                style="padding-right:5px;">
          <el-card class="box-card">
            <div slot="header"
                 class="clearfix">
              <i class="el-icon-tickets"></i>
              <span>新闻</span>
            </div>
            <el-table :data="dataList"
                      style="width: 100%">
              <el-table-column min-width="250">
                <template slot-scope="scope">
                  <a @click="showNews(scope.row.id)">{{ scope.row.title }}</a>
                </template>
              </el-table-column>
              <el-table-column width="180">
                <template slot-scope="scope">
                  <span style="margin-left: 10px">{{ scope.row.createTime }}</span>
                </template>
              </el-table-column>
            </el-table>
          </el-card>
        </el-col>
        <el-col :span="12"
                style="padding-left:5px;">
          <el-card class="box-card">
            <div slot="header"
                 class="clearfix">
              <i class="el-icon-bell"></i>
              <span>公告</span>
            </div>
            <el-table :data="dataList"
                      style="width: 100%">
              <el-table-column min-width="250">
                <template slot-scope="scope">
                  <a @click="showNews(scope.row.id)">{{ scope.row.title }}</a>
                </template>
              </el-table-column>
              <el-table-column width="180">
                <template slot-scope="scope">
                  <span style="margin-left: 10px">{{ scope.row.createTime }}</span>
                </template>
              </el-table-column>
            </el-table>
          </el-card>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
        </el-col>
      </el-row>
    </el-main>
    <el-footer>
      <site-foot></site-foot>
    </el-footer>
  </el-container>
</template>
<script>
import DateTimeFormater from '@/utils/dateTimeFormater'
import SiteHead from '@/components/SiteHead'
import SiteFoot from '@/components/SiteFoot'
import * as newsApi from '@/api/newsapi'

export default {
  name: 'Index',
  components: { SiteHead, SiteFoot },
  data () {
    return {
      carouselItems: [],
      news: { title: null, content: null, author: null, createTime: null }
    }
  },
  created () {
    this.getCarouselItems()
    this.getNews()
  },
  methods: {
    getNews () {
    },
    getCarouselItems () {
      newsApi.carousel(5).then(response => {
        this.carouselItems = response
      })
    },
    showNews (id) {
      top.open(this.newsUrl + id)
    }
  },
  filters: {
    foramteDateTime: DateTimeFormater.foramteDateTime
  }
}
</script>
<style>
.clearfix:before,
.clearfix:after {
  display: table;
  content: '';
}
.clearfix:after {
  clear: both;
}
.box-card {
  text-align: left;
  min-height: 500px;
}
.el-card__header {
  border-bottom: 2px solid #409eff;
  font-size: 14px;
  font-weight: bold;
}
</style>
