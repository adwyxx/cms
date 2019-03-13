<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>系统管理</el-breadcrumb-item>
      <el-breadcrumb-item>文章管理</el-breadcrumb-item>
    </el-breadcrumb>
    <el-row>
      <el-col :span="24"
              class="title">
        <li class="el-icon-tickets"></li>
        文章管理
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
                   icon="el-icon-plus"
                   @click="handleAdd()">新增</el-button>
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
          <span style="margin-left: 10px">{{ scope.row.title }}</span>
        </template>
      </el-table-column>
      <el-table-column label="类别"
                       min-width="180">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.categoryPath }}</span>
        </template>
      </el-table-column>
      <el-table-column label="作者"
                       min-width="180">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.author }}</span>
        </template>
      </el-table-column>
      <el-table-column label="添加日期"
                       min-width="180">
        <template slot-scope="scope">
          <i class="el-icon-time"></i>
          <span style="margin-left: 10px">{{ scope.row.createTime }}</span>
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
    <el-dialog title="文章信息"
               width="80%"
               :visible.sync="dialogFormVisible">
      <el-form ref="form"
               size="small"
               label-width="100px"
               style="text-align:left;"
               :rules="rules"
               :model="currentData">
        <el-form-item label="标题"
                      prop="title">
          <el-input v-model="currentData.title"
                    autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="类别"
                      prop="categoryId">
          <el-select v-model="currentData.categoryId"
                     @change="categoryChange"
                     placeholder="请选择">
            <el-option-group v-for="category in categorys"
                             :key="category.id"
                             :label="category.label">
              <el-option v-for="item in category.children"
                         :key="item.id"
                         :label="item.label"
                         :value="item.id">
              </el-option>
            </el-option-group>
          </el-select>
        </el-form-item>
        <el-form-item label="作者">
          <el-input v-model="currentData.author"
                    autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="内容"
                      prop="content">
          <quill-editor v-model="currentData.content"
                        ref="contentEditor"
                        :options="editorOption"
                        @blur="onEditorBlur($event)"
                        @focus="onEditorFocus($event)"
                        @change="onEditorChange($event)">
          </quill-editor>
        </el-form-item>
        <el-form-item label-width="0px"
                      style="text-align:center;">
          <el-button @click="reset()">取 消</el-button>
          <el-button type="primary"
                     @click="saveData()">确 定</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import * as articleApi from '@/api/sys/articleapi'
import { quillEditor, Quill } from 'vue-quill-editor'
import { container, ImageExtend, QuillWatch } from 'quill-image-extend-module'
import config from '@/assets/config'
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'

Quill.register('modules/ImageExtend', ImageExtend)

export default {
  name: 'Article',
  components: { quillEditor },
  data () {
    return {
      dialogFormVisible: false,
      saveModel: false,
      formLabelWidth: '100px',
      dataList: [],
      categorys: [],
      total: 0,
      condition: { title: null, pageIndex: 1, pageSize: 20 },
      currentData: { id: null, title: null, author: null, categoryPath: null, categoryId: null, content: null, createTime: null, creator: null, hits: 0, validStatus: true, modifyTime: null, modifier: null },
      rules: {
        title: [
          { required: true, message: '请输入标题', trigger: 'change' }
        ],
        categoryId: [
          { required: true, message: '请输选择类别', trigger: 'change' }
        ],
        content: [
          { required: true, message: '请输入内容', trigger: 'change' }
        ]
      },
      editorOption: {
        modules: {
          ImageExtend: {
            // 图片参数名
            name: 'file',
            // 可选参数 图片大小，单位为M，1M = 1024kb
            size: 3,
            // 服务器地址, 如果action为空，则采用base64插入图片
            action: config.api.system.fileUpload,
            // response 为一个函数用来获取服务器返回的具体图片地址
            // 例如服务器返回{code: 200; data:{ url: 'baidu.com'}}则 return res.data.url
            response: (res) => {
              return res.data.url
            },
            // 可选参数 设置请求头部
            headers: (xhr) => {
              // xhr.setRequestHeader('Content-Type', 'multipart/form-data')
            },
            // 可选参数 自定义开始上传触发事件
            sizeError: () => { },
            // 可选参数 自定义开始上传触发事件
            start: () => { },
            // 可选参数 自定义上传结束触发的事件，无论成功或者失败
            end: () => { },
            // 可选参数 上传失败触发的事件
            error: () => { },
            // 可选参数  上传成功触发的事件
            success: () => { },
            // 可选参数 每次选择图片触发，也可用来设置头部，但比headers多了一个参数，可设置formData
            change: (xhr, formData) => {
              // xhr.setRequestHeader('myHeader','myValue')
              // formData.append('token', 'myToken')
            }
          },
          // 如果不上传图片到服务器，此处不必配置
          toolbar: {
            // container为工具栏，此次引入了全部工具栏，也可自行配置
            container: container,
            handlers: {
              // 劫持原来的图片点击按钮事件
              'image': function () {
                QuillWatch.emit(this.quill.id)
              }
            }
          }
        }
      }
    }
  },
  created () {
    this.loadData()
    this.loadCategories()
  },
  methods: {
    handleAdd () {
      this.userNameSuffixIcon = 'el-input__icon el-icon-view'
      this.currentData = { id: null, title: null, author: null, categoryPath: null, categoryId: null, content: null, createTime: new Date(), creator: 'system', hits: 0, validStatus: true, modifyTime: null, modifier: null }
      this.dialogFormVisible = true
      this.saveModel = false
    },
    handleEdit (index, row) {
      this.currentData = row
      this.saveModel = true
      this.dialogFormVisible = true
    },
    handleDelete (index, row) {
      this.$confirm('确定要删除该用户吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        articleApi.deleteById(row.id).then(response => {
          this.dialogFormVisible = false
          this.$message({
            type: 'success',
            duration: 1000,
            message: '删除成功!'
          })
          this.loadData()
        }, (error) => {
          // this.$thows(error)
          this.$message({
            showClose: true,
            message: '哎呀呀，删除数据报错了！..(｡•ˇ‸ˇ•｡)…',
            type: 'error'
          })
          console.log(error)
        })
      }).catch(() => { })
    },
    loadData () {
      articleApi.query(this.condition).then(response => {
        this.dataList = response.data
        this.total = response.total
      }, error => {
        console.log(error)
      })
    },
    query () {
      this.condition.pageIndex = 1
      this.loadData()
    },
    saveData () {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          if (this.saveModel) {
            articleApi.update(this.currentData).then(response => {
              this.dialogFormVisible = false
              if (this.saveModel) {
                this.loadData()
              } else {
                this.condition.title = null
                this.query()
              }
            }, (error) => {
              // this.$thows(error)
              console.log(error)
            })
          } else {
            articleApi.add(this.currentData).then(response => {
              this.dialogFormVisible = false
              if (this.saveModel) {
                this.loadData()
              } else {
                this.condition.title = null
                this.query()
              }
            }, (error) => {
              // this.$thows(error)
              console.log(error)
            })
          }
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    reset () {
      this.$refs['form'].resetFields()
      this.dialogFormVisible = false
    },
    // 准备编辑器
    onEditorReady (editor) {

    },
    // 失去焦点事件
    onEditorBlur () { },
    // 获得焦点事件
    onEditorFocus () { },
    // 内容改变事件
    onEditorChange () { },
    // 加载类别数据
    loadCategories () {
      articleApi.getAllCategories().then(response => {
        if (response) {
          this.categorys = response
        }
      }, (error) => {
        // this.$thows(error)
        console.log(error)
      })
    },
    // 类别选中事件
    categoryChange (value) {
      for (let i = 0, len = this.categorys.length; i < len; i++) {
        var items = this.categorys[i].children
        for (let j = 0, leng = items.length; j < leng; j++) {
          if (items[j].id === value) {
            this.currentData.categoryPath = items[j].fullPath
            return
          }
        }
      }
    }
  },
  computed: {
    editor () {
      return this.$refs.contentEditor.quill
    }
  }
}

// 图片上传实现 https://segmentfault.com/a/1190000012620431
</script>
