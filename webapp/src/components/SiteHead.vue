<template>
  <el-menu :default-active="activeIndex"
           class="el-menu-demo"
           router="true"
           mode="horizontal">
    <el-menu-item index="0"
                  :route="{path:'/'}">首页</el-menu-item>
    <el-submenu v-for="category in categories"
                :index="category.id+''"
                :key="category.id">
      <template slot="title">{{category.label}}</template>
      <el-menu-item v-for="item in category.children"
                    :index="category.id+'-'+item.id"
                    :route="{path:'/newslist/'+item.id}"
                    :key="item.id">{{item.label}}</el-menu-item>
    </el-submenu>
    <el-menu-item index="98"
                  :route="{path:'/about'}">关于我们</el-menu-item>
    <el-menu-item index="99"
                  :route="{path:'/connect'}">联系我们</el-menu-item>
  </el-menu>
</template>

<script>
import * as newsApi from '@/api/newsapi'

export default {
  name: 'SiteHead',
  props: {
  },
  created () {
    this.getAllCategories()
  },
  data () {
    return {
      activeIndex: '1',
      categories: [],
      news: null
    }
  },
  methods: {
    handleSelect (key, keyPath) {
      console.log(key, keyPath)
    },
    getAllCategories () {
      newsApi.categories().then((response) => {
        this.categories = response
      })
    }
  }
}
</script>
