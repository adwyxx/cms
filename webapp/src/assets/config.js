const config = {
  host: 'http://localhost:8080'
}

config.api = {
  // 系统管理
  system: {
    // 用户
    userCheckLogonName: config.host + '/users/checkLogonName/',
    userQuery: config.host + '/users/query',
    userAdd: config.host + '/users/add',
    userUpdate: config.host + '/users/update',
    userDelete: config.host + '/users/delete/',
    // 角色
    roleQuery: config.host + '/role/query',
    roleAdd: config.host + '/role/add',
    roleUpdate: config.host + '/role/update',
    roleDelete: config.host + '/role/delete/',
    roleCheckName: config.host + '/role/checkName/',
    roleGetProviliges: config.host + '/role/getproviliges/',
    // 文章
    articleQuery: config.host + '/article/query',
    articleAdd: config.host + '/article/add',
    articleUpdate: config.host + '/article/update',
    articleDelete: config.host + '/article/delete/'
  }
}

export default config
