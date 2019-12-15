import Vue from 'vue'
import vuetify from "plugins/vuetify"
import VueResource from 'vue-resource'
import App from 'pages/App.vue'
import router from 'router/index'

Vue.use(VueResource)

new Vue({
  router,
  vuetify,
    render: h => h(App)
}).$mount('#app')
