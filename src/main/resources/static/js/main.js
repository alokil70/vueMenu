import Vue from 'vue'
import vuetify from "plugins/vuetify"
import '@babel/polyfill'
import 'api/resource'
import App from 'App.vue'
import store from 'store/index'
import VueResource from 'vue-resource'
import router from 'router/index'

Vue.use(VueResource);

new Vue({
//  el: '#app',
  router,
  store,
  vuetify,
    render: h => h(App)
}).$mount('#app');
