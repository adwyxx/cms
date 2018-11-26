<template>
    <el-container id="Main">
        <el-header>Header</el-header>
        <el-container>
            <el-aside width="200px">
                <el-menu
                default-active="2"
                class="el-menu-vertical-demo"
                @open="handleOpen"
                @close="handleClose"
                background-color="#545c64"
                text-color="#fff"
                active-text-color="#ffd04b">
                <el-submenu v-for="(menu,index) in proviliges" :index="index">
                    <template slot="title">
                    <i class="el-icon-location"></i>
                    <span>{{menu.name}}}</span>
                    </template>
                    <el-menu-item-group>
                    <el-menu-item v-for="(item,ii) in menu.children" :index="index+'-'+ii">
                       <router-link :to="item.route">{{ item.name }}</router-link>
                    </el-menu-item>
                </el-submenu>
                </el-menu>
            </el-aside>
            <el-container>
                <el-main>
                    <router-view/>
                </el-main>
            </el-container>
        </el-container>
        <el-footer>Footer</el-footer>
    </el-container>
</template>

<script>
export default {
    name:'Main',
    data () {
        return {
            proviliges: []
        }
    },
    created (){
        this.getData()
    },
    methods:{
        getData(){
            var provis=[
                {id:1,name:'系统管理',route:'',class:'el-icon-setting',children:[
                    {id:11,name:'用户管理',route:'/managment/users',class:'el-icon-setting'},
                    {id:12,name:'角色管理',route:'/managment/roles',class:'el-icon-setting'}
                ]},
                {id:2,name:'文章管理',route:'',class:'el-icon-setting',children:[
                    {id:21,name:'文章类别管理',route:'/managment/articlecategory',class:'el-icon-setting'},
                    {id:22,name:'文章管理',route:'/managment/articles',class:'el-icon-setting'}
                ]}]
            this.proviliges=provis
        }
    }
}
</script>
