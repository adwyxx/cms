const config = {
  host: 'http://localhost:8080'
}

config.api = {
  // 系统管理
  system: {
    // 用户管理
    userCheckLogonName: config.host + '/sys/users/checkLogonName/',
    userQuery: config.host + '/sys/users/query',
    userAdd: config.host + '/sys/users/add',
    userUpdate: config.host + '/sys/users/update',
    userDelete: config.host + '/sys/users/delete/',
    // 角色管理
    roleQuery: config.host + '/sys/role/query',
    roleAdd: config.host + '/sys/role/add',
    roleUpdate: config.host + '/sys/role/update',
    roleDelete: config.host + '/sys/role/delete/',
    roleCheckName: config.host + '/sys/role/checkName/',
    roleGetProviliges: config.host + '/sys/role/getproviliges/',
    // 文章管理
    articleQuery: config.host + '/sys/article/query',
    articleAdd: config.host + '/sys/article/add',
    articleUpdate: config.host + '/sys/article/update',
    articleDelete: config.host + '/sys/article/deleteById/',
    articleGetTopArticles: config.host + '/sys/article/getTopArticles/',
    articleGetTopArticlesByCategory:
      config.host + '/sys/article/getTopArticlesByCategory/',
    articleGetAllCategories: config.host + '/sys/article/getAllCategories',
    // 文件上传下载
    fileUpload: config.host + '/file/upload',
    fileDownload: config.host + '/file/download/' // /file/download/{id}
  },
  auth: {
    login: config.host + '/auth/login',
    userInfo: config.host + '/auth/userinfo'
  }
}

export default config
