import Head from '@/components/Head'

function plugin (Vue) {
  if (plugin.installed) {
    return
  }
  Vue.component('Head', Head)
}

export default plugin
