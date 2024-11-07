import { createRouter, createWebHistory } from 'vue-router'
import Maintain from "@/views/view/Maintain.vue";
import Import from "@/views/view/Import.vue";
import Approval from "@/views/view/Approval.vue";
import Search from "@/views/view/Search.vue";
import Container from "@/views/Container.vue";
import From from "@/views/From.vue";
import Home from "@/views/home/Home.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: Container,
      redirect: '/home',
      children:[
        {
          path: '/home',
          component: Home
        },{
          path: '/maintain',
          component: Maintain
        },{
          path: '/import',
          component: Import
        },{
          path: '/search',
          component: Search
        },{
          path: '/approval',
          component: Approval
        }
      ]
    },
    {
      path: '/login',
      name: 'login',
      component: From
    }
  ]
})

export default router
