import Vue from 'vue'
import vuetify from "plugins/vuetify"
import '@babel/polyfill'
import 'api/resource'
import App from 'App.vue'
import store from 'store/index'
import router from 'router/index'


new Vue({
//  el: '#app',
  router,
  store,
  vuetify,
    render: h => h(App)
}).$mount('#app');
