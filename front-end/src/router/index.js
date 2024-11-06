import { createRouter, createWebHistory } from 'vue-router'
import DashBoard from "@/views/home/DashBoard.vue";
import Login from "@/components/member/Login.vue";
import Maintain from "@/views/view/Maintain.vue";
import Import from "@/views/view/Import.vue";
import Approval from "@/views/view/Approval.vue";
import Search from "@/views/view/Search.vue";
import Container from "@/views/Container.vue";
import From from "@/views/From.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'dashboard',
      component: Container,
      redirect: '/dashboard',
      children:[
        {
          path: '/dashboard',
          component: DashBoard
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
      path: '/member',
      name: 'member',
      component: From
    }
  ]
})

export default router
