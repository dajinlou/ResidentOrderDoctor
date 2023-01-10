import Vue from 'vue'
import Vuex from 'vuex'
import menus from './modules/menus';
import login from "./modules/login";
Vue.use(Vuex)

export default new Vuex.Store({

  state: {},
  mutations: {},
  actions: {},
  modules: {
    menus,
    login
  }


})
