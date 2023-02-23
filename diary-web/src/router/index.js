import Vue from 'vue'
import VueRouter from 'vue-router'
import DiaryView from '../views/DiaryView.vue'
import LoginView from "../views/LoginView.vue";
import NewDiaryView from "@/views/NewDiaryView.vue";
import EditDiaryView from "@/views/EditDiaryView.vue";
import RegisterView from "@/views/RegisterView.vue";
Vue.use(VueRouter)

const routes = [
  {
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
