import './assets/css/main.css'

import { createApp } from 'vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import router from '../src/router/index.js'
import App from './App.vue';
const app = createApp(App);

app.use(router)

app.use(ElementPlus);

app.mount('#app')
