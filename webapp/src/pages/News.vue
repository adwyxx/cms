<template>
  <el-container>
    <el-header>

      <site-head></site-head>
    </el-header>
    <el-main>
      <div>{{news.content}}</div>
    </el-main>
  </el-container>
</template>
<script>
import SiteHead from '@/components/SiteHead'

export default {
  name: 'News',
  components: { SiteHead },
  data () {
    return {
      news: { content: '11231212' }
    }
  },
  created () { },
  methods: {

  }
}
</script>
