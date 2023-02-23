import mavonEditor from 'mavon-editor'  //引入mavon-editor 就是上面所安装的
Vue.use(mavonEditor)


import router from './router'
import Vue from 'vue';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import App from './App.vue';
import 'mavon-editor/dist/css/index.css'
// import 'font-awesome/css/font-awesome.css'

Vue.use(ElementUI);
Vue.config.productionTip = false

import store from "./store"


import {postRequest}    from "./utils/api";
import {getRequest}     from "./utils/api";
import {putRequest}     from "./utils/api";
import {deleteRequest}  from "./utils/api";
// import {downloadRequest} from "./utils/download";

Vue.prototype.postRequest   = postRequest;
Vue.prototype.getRequest    = getRequest;
Vue.prototype.putRequest    = putRequest;
Vue.prototype.deleteRequest = deleteRequest;

router.beforeEach((to,from,next)=>{
  console.log("TOKENSTR:"+window.sessionStorage.getItem('tokenStr'))
  console.log("USER:"+window.sessionStorage.getItem('user'));
  if(window.sessionStorage.getItem('tokenStr')){
    if(!window.sessionStorage.getItem('user')){
      // 判断用户信息是否存在
      return getRequest('/user/info').then(resp=>{
        if(resp){
          window.sessionStorage.setItem('user',JSON.stringify(resp));
          next('/');
        }
      })
    }
    next();
  }else{
    if(to.path == '/login' || to.path == '/register'){
      next();
    }else{
      next('/login?redirect='+to.path);
    }
  }
})
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')

