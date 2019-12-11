import Vue from 'vue'
//import Vuetify from "vuetify/lib"
import vuetify from "./plugins/vuetify"
import VueResource from 'vue-resource'
import App from './pages/App.vue'
//import 'vuetify/dist/vuetify.min.css'

//Vue.use(Vuetify)
Vue.use(VueResource)

new Vue({
  vuetify,
    render: h => h(App)
}).$mount('#app')

