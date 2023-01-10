import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default {

  state: {
    menuList: [],
    hasRoute: false
  },
  mutations: {
    setMenuList(state, menus) {
      state.menuList = menus;
    },

    changeRouteStatus(state, hasRoute) {
      state.hasRoute = hasRoute;
      /* sessionStorage.setItem("hasRoute",hasRoute)*/
    }

  },
  actions: {},
  modules: {}


}
