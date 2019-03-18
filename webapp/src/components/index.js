import SiteHead from '@/components/SiteHead'

function plugin (Vue) {
  if (plugin.installed) {
    return
  }
  Vue.component('SiteHead', SiteHead)
}

export default plugin
