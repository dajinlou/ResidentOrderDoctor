import Vue from 'vue'
import Router from 'vue-router'
import Home from "../components/Home";
import Index from "../components/Index";
import UpdatePassword from "../components/user/UpdatePassword";
import store from "../store";
import axios from "axios";

Vue.use(Router)


const routes = [
  {
    name:'Home',
    path: '/home',
    component: Home,
    children: [
    /*  {
        path: '/index',
        name: 'Index',
        component: Index
      }, */{
        path: '/updatePassword',
        name: 'UpdatePassword',
        component: UpdatePassword
      }
    ]
  }, {
    path: '/index',
    name: 'Index',
    component: Index
  }, {
    path: '/',
    name: 'Login',
    component: () => import('../components/Login')
  },{
    path:'/register',
    name:'Register',
    component: ()=>import('../components/Register')
  }
]

const router = new Router({
  mode: 'history',
  routes
})

//路由之前
router.beforeEach((to, from, next) => {
  if (to.name == "Login" || to.name=="Register") { //跳入登录页面时不用获取东西
    next()
  } else {
    //防止重复获取导航
    let hasRoute = store.state.menus.hasRoute;
    if (!hasRoute) {
      axios({
        method: 'get',
        url: store.state.login.navUrl
      }).then(resp => {

        //获取导航栏
        store.commit("setMenuList", resp.data);

        // 动态绑定路由
        let newRoutes = router.options.routes;
        //提前获取directory，否则可能会引起异步（在第100行代码）
        let directory = store.state.login.directory;
        let list = Array.from(resp.data);
        list.forEach(menu => {
          //转成路由
          let route = menuToRoute(menu,directory);
          //把路由添加到路由管理中
          if (route) {
            newRoutes[0].children.push(route)
          }

        })
        router.addRoutes(newRoutes)

        hasRoute = true;
        store.commit("changeRouteStatus", hasRoute)
      })
    }
    next()
  }
})


//导航转换成路由
const menuToRoute = (menu,directory) => {
  let route = {
    name: menu.component,
    path: menu.path,
    meta: {
      icon: menu.icon,
      title: menu.title,
      title2: menu.title2
    }
  }
//'+directory+'
  route.component = () => import('../components/'+directory+'/' + menu.component + '.vue')

  return route;
}


export default router
