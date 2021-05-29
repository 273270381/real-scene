import Vue from 'vue'

import 'normalize.css/normalize.css' // A modern alternative to CSS resets

import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

import ECharts from 'vue-echarts/components/ECharts.vue'
import '@/styles/index.scss' // global css

import App from './App'
import store from './store'
import router from './router'
import VueLazyload from 'vue-lazyload' //lanjiazai

import i18n from './lang' // Internationalization
import '@/icons' // icon
import '@/permission' // permission control
import resetMessage from './utils/resetMessage';

import progressive from 'progressive-image/dist/vue'
import progressive_preview from'./index-vue'


Vue.use(progressive_preview, {
  removePreview: true,
  scale: false
})

/**
 * If you don't want to use mock-server
 * you want to use MockJs for mock api
 * you can execute: mockXHR()
 *
 * Currently MockJs will be used in the production environment,
 * please remove it before going online! ! !
 */

// set ElementUI lang to EN
Vue.use(ElementUI, {   i18n: (key, value) => i18n.t(key, value) })
Vue.use(VueLazyload,{
 /* error:'./static/error.png',//获取错误时显示的图片*/
  loading:'../assets/404_images/404.png'//图片加载中显示的图片
})


Vue.use(progressive, {
  removePreview: true,
  scale: true,
  lazyClass: 'lazy',

})

Vue.component('v-chart', ECharts)
Vue.config.productionTip = false


new Vue({
  el: '#app',
 /* data: {

    imgs: [
      {
        src: require('./assets/img/bg10.jpg'),
        srcset: require('./assets/img/bg10.jpg'),
        preview: require('./assets/img/bg11.jpg')
      }

    ]
  },*/
  router,
  store,
  i18n,
  render: h => h(App)
})
