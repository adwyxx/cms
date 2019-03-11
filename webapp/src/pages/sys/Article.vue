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
        <span style="margin-left: 10px">{{ scope.row.title }}</span>
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
               width="500px"
               :visible.sync="dialogFormVisible">
      <el-form ref="form"
               size="small"
               label-width="100px"
               :rules="rules"
               :model="currentData">
        <el-form-item label="标题"
                      prop="title">
          <el-input v-model="currentData.title"
                    suffix-icon="el-icon-star-off"
                    autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="类别"
                      prop="categoryId">

        </el-form-item>
        <el-form-item label="作者">
          <el-input v-model="currentData.author"
                    suffix-icon="el-icon-phone"
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
        <el-form-item label-width="0px">
          <el-button @click="reset()">取 消</el-button>
          <el-button type="primary"
                     @click="saveData()">确 定</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import * as articleApi from '@/api/article/articleapi'
import { quillEditor } from 'vue-quill-editor' // 调用编辑器
import { quillRedefine } from 'vue-quill-editor-upload'
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'

export default {
  name: 'Article',
  components: { quillEditor, quillRedefine },
  data () {
    return {
      dialogFormVisible: false,
      userNameSuffixIcon: 'el-input__icon el-icon-view',
      saveModel: false,
      formLabelWidth: '100px',
      dataList: [],
      total: 0,
      condition: { title: null, pageIndex: 1, pageSize: 20 },
      currentData: { id: null, title: null, author: null, categoryPath: null, categoryId: null, content: null, createTime: null, creator: null },
      editorOption: {},
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
      }
    }
  },
  created () {
    this.editorOption = quillRedefine(
      {
        // 图片上传的设置
        uplpadConfig: {
          action: '', // 必填参数 图片上传地址
          // 必选参数  res是一个函数，函数接收的response为上传成功时服务器返回的数据
          // 你必须把返回的数据中所包含的图片地址 return 回去
          res: (respnse) => {
            return respnse.info
          },
          methods: 'POST', // 可选参数 图片上传方式  默认为post
          // token: sessionStorage.token, // 可选参数 如果需要token验证，假设你的token有存放在sessionStorage
          name: 'img', // 可选参数 文件的参数名 默认为img
          size: 500, // 可选参数   图片限制大小，单位为Kb, 1M = 1024Kb
          accept: 'image/png, image/gif, image/jpeg, image/bmp, image/x-icon', // 可选参数 可上传的图片格式
          // input点击事件  formData是提交的表单实体
          change: (formData) => {
          },
          // 设置请求头 xhr: 异步请求， formData: 表单对象
          header: (xhr, formData) => {
            // xhr.setRequestHeader('myHeader','myValue');
            // formData.append('token', '1234')
          },
          // 可选参数 接收一个函数 开始上传数据时会触发
          start: () => {
          },
          // 可选参数 接收一个函数 上传数据完成（成功或者失败）时会触发
          end: () => {
          },
          // 可选参数 接收一个函数 上传数据成功时会触发
          success: () => {
          },
          // 可选参数 接收一个函数 上传数据中断时会触发
          error: () => {
          }
        },
        // 以下所有设置都和vue-quill-editor本身所对应
        placeholder: '', // 可选参数 富文本框内的提示语
        theme: '', // 可选参数 富文本编辑器的风格
        toolOptions: [], // 可选参数  选择工具栏的需要哪些功能  默认是全部
        handlers: {} // 可选参数 重定义的事件，比如link等事件
      }
    )
    this.loadData()
  },
  methods: {
    handleAdd () {
      this.userNameSuffixIcon = 'el-input__icon el-icon-view'
      this.currentData = { id: null, title: null, author: null, categoryPath: null, categoryId: null, content: null, createTime: new Date(), creator: 'system' }
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
    onEditorChange () { }
  },
  computed: {
    editor () {
      return this.$refs.contentEditor.quill
    }
  }
}

// 图片上传实现 https://segmentfault.com/a/1190000012620431
</script>
