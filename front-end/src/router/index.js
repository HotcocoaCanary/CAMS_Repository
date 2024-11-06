import { createRouter, createWebHistory } from 'vue-router'
import DashBoard from "@/views/home/DashBoard.vue";
import Login from "@/views/member/Login.vue";
import Register from "@/views/member/Register.vue";
import Maintain from "@/views/view/Maintain.vue";
import Import from "@/views/view/Import.vue";
import Approval from "@/views/view/Approval.vue";
import Search from "@/views/view/Search.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: DashBoard
    },
    {
      path: '/member',
      name: 'member',
      redirect: '/member/login',
      children: [
        {
          path: '/member/login',
          component: Login
        },
        {
          path: '/member/register',
          component: Register
        }
      ]
    },
    {
      path:'/view',
      name:'view',
      children:[
        {
          path: '/view/maintain',
          component: Maintain
        },{
          path: '/view/import',
          component: Import
        },{
          path: '/view/search',
          component: Search
        },{
          path: '/view/approval',
          component: Approval
        }
      ]
    }
  ]
})

export default router
