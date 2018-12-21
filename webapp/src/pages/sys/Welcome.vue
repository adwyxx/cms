<template>
  <div class="welcome">欢迎使用新闻管理系统</div>
</template>
<style>
.welcome {
  text-align: center;
  width: 100%;
  padding: 10px;
}
</style>

<script>
export default {
  name: 'Welcome',
  data () {
    return {
      totalArticles: 0,
      totalPageView: 0
    }
  }
}
</script>
