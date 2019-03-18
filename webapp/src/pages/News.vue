<template>
  <el-container>
    <el-header>

      <site-head></site-head>
    </el-header>
    <el-main>
      <el-row>
        <el-col :span="24"
                style="font-size:18px;font-weight:bold;line-height:40px;">{{news.title}}</el-col>
      </el-row>
      <el-row>
        <el-col :span="4"
                style="text-align:left;">作者：{{news.author}} </el-col>
        <el-col :span="6"
                style="text-align:left;">发布时间：{{news.createTime | foramteCnDate}}</el-col>
      </el-row>
      <el-row>
        <el-col :span="24"
                style="text-align:left;">
          <p v-html="news.content"></p>
        </el-col>
      </el-row>
    </el-main>
  </el-container>
</template>
<script>
import DateTimeFormater from '@/utils/dateTimeFormater'
import SiteHead from '@/components/SiteHead'
import * as newsApi from '@/api/newsapi'

export default {
  name: 'News',
  components: { SiteHead },
  data () {
    return {
      news: { title: null, content: null, author: null, createTime: null }
    }
  },
  created () {
    this.getNews()
  },
  methods: {
    getNews () {
      if (this.$route.params && this.$route.params.id) {
        newsApi.news(this.$route.params.id).then((response) => {
          this.news = response
        })
      }
    }
  },
  filters: {
    foramteDateTime: DateTimeFormater.foramteDateTime
  }
}
</script>
