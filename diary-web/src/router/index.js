import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
import DiaryView from '../views/DiaryView.vue'
import LoginView from "../views/LoginView.vue";
import NewDiaryView from "@/views/NewDiaryView.vue";
import EditDiaryView from "@/views/EditDiaryView.vue";
import RegisterView from "@/views/RegisterView.vue";
Vue.use(VueRouter)

const routes = [
  {
    path: '/home',
    name: 'home',
    component: HomeView
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },{
    path: '/',
    name: 'diary',
    component: DiaryView
  },{
    path: '/login',
    name: 'login',
    component: LoginView
  },{
    path: '/new',
    name: 'newDiary',
    component: NewDiaryView
  },{
    path: '/edit',
    name: 'editDiary',
    component: EditDiaryView
  },{
    path: '/register',
    name: 'register',
    component: RegisterView
  }
]

const router = new VueRouter({
  routes
})

export default router
