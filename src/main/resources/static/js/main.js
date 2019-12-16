import Vue from 'vue'
import vuetify from "plugins/vuetify"
import '@babel/polyfill'
import VueResource from 'vue-resource'
import store from 'store'
import App from 'App.vue'
import router from 'router/index'

Vue.use(VueResource)

new Vue({
  router,
  store,
  vuetify,
    render: h => h(App)
}).$mount('#app')
