<template>
  <el-container id="Main"
                direction="horizontal">
    <el-aside>
      <div class="logo">新闻管理系统</div>
      <el-menu class="el-menu-vertical"
               @open="handleOpen"
               @close="handleClose"
               text-color="#fff"
               active-text-color="#0098fc">
        <el-submenu v-for="menu in proviliges"
                    :index="menu.id"
                    :key="menu.id">
          <template slot="title">
            <i class="el-icon-setting"></i>
            <span>{{menu.name}}</span>
          </template>
          <el-menu-item-group>
            <el-menu-item v-for="item in menu.children"
                          :index="menu.id+'-'+item.id"
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
        <router-view name="management" />
      </el-main>
    </el-container>
  </el-container>
</template>
<style>
body {
  margin: 0;
}
#Main {
  position: relative;
  height: 100%;
}
#MainContainer {
  position: relative;
  height: 100%;
}
.el-container {
  height: 100%;
}
.el-aside {
  background-color: #20375c;
  text-align: left;
  width: 200px !important;
  position: fixed;
  height: 100%;
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
  position: relative;
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

<script>
export default {
  name: 'Main',
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
