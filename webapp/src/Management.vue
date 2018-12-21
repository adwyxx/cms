<template>
  <el-container id="admin"
                direction="horizontal">
    <el-aside>
      <div class="logo">新闻管理系统</div>
      <el-menu text-color="#0098fc"
               active-text-color="#0098fc"
               background-color="#0f2448">
        <el-menu-item index="0"
                      style="color:#0098fc;">
          <i class="el-icon-star-off"
             style="color:#0098fc;"></i>
          <router-link :to="{name:'Welcome'}"
                       slot="title"
                       tag="span">桌面</router-link>
        </el-menu-item>
      </el-menu>
      <el-menu class="el-menu-vertical"
               @open="handleOpen"
               @close="handleClose"
               text-color="#fff"
               active-text-color="#0098fc">
        <el-submenu v-for="(menu,index) in proviliges"
                    :index="index+''"
                    :key="menu.id">
          <template slot="title">
            <i class="el-icon-setting"></i>
            <span>{{menu.name}}</span>
          </template>
          <el-menu-item-group>
            <el-menu-item v-for="(item,itme_index) in menu.children"
                          :index="itme_index+''"
                          :key="item.id">
              <i class="el-icon-menu"></i>
              <router-link :to="item.route">{{ item.name }}</router-link>
            </el-menu-item>
          </el-menu-item-group>
        </el-submenu>
      </el-menu>
    </el-aside>
    <el-container id="MainContainer"
                  direction="vertical">
      <el-header>Header</el-header>
      <el-main>
        <router-view />
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {
  name: 'Management',
  data () {
    return {
      proviliges: []
    }
  },
  created () {
    this.getData()
  },
  methods: {
    getData () {
      var provis = [{
        id: 1,
        name: '系统管理',
        route: '',
        class: 'el-icon-setting',
        children: [
          { id: 11, name: '用户管理', route: '/management/user', class: 'el-icon-setting' },
          { id: 12, name: '角色管理', route: '/management/role', class: 'el-icon-setting' }
        ]
      }, {
        id: 2,
        name: '文章管理',
        route: '',
        class: 'el-icon-setting',
        children: [
          { id: 21, name: '文章类别管理', route: '/management/articlecategory', class: 'el-icon-setting' },
          { id: 22, name: '文章管理', route: '/management/articles', class: 'el-icon-setting' }
        ]
      }]
      this.proviliges = provis
    },
    handleOpen (key, keyPath) {
      console.log(key, keyPath)
    },
    handleClose (key, keyPath) {
      console.log(key, keyPath)
    }
  }
}
</script>

<style>
body {
  margin: 0;
}
#admin {
  height: 100%;
}
#MainContainer {
  height: 100%;
  position: relative;
}
.el-container {
  height: 100%;
}
.el-aside {
  background-color: #20375c;
  text-align: left;
  width: 200px !important;
  height: 100%;
  position: fixed;
  color: #c0ccda !important;
  top: 0;
  bottom: 0;
  left: 0;
  z-index: 10;
  padding-top: 20px;
  padding-bottom: 30px;
  box-sizing: border-box;
}
.logo {
  background-color: #20375c;
  text-align: center;
  width: 200px !important;
  font-size: 18px;
  font-weight: bold;
  line-height: 48px !important;
  color: #fff;
}
.el-menu {
  background-color: #20375c;
  border: 0px;
  font-size: 14px;
  font-weight: bold;
  text-align: left;
  line-height: 48px !important;
  color: #fff;
}
.el-submenu {
  text-align: left !important;
}
.el-submenu .el-menu-item {
  height: 40px;
  line-height: 40px;
  text-align: left;
  padding-left: 20px;
  width: 200px;
  color: #fff;
}
.el-submenu .el-menu-item a {
  color: #fff;
  text-decoration: none;
}
.el-submenu .el-menu-item a:hover {
  color: #0098fc;
  text-decoration: none;
}
.el-aside .el-menu-item:hover,
.el-aside .el-submenu__title:hover {
  background-color: #0f2448 !important;
}

.el-menu-item.is-active,
.el-menu-item.is-active a {
  color: #0098fc;
  background-color: #0f2448 !important;
}
.el-submenu {
  font-size: 13px;
  font-weight: bold;
  text-align: center;
  color: #fff;
}
.el-menu-item-group {
  background-color: #0f2448 !important;
}
.el-menu-item-group__title {
  display: none;
  height: 0px;
}
.el-main {
  background-color: #fff;
  text-align: center;
  height: 100%;
  min-height: 500px;
}
.el-header {
  background-color: #b3c0d1;
  text-align: center;
  height: 60px;
  line-height: 60px;
}
</style>
