import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default {

  state: {
    name: '', //登录人姓名
    id: '', //账号
    role: '',  //角色 居民：3   医生：6   管理员： 9
    navUrl: '', //导航栏
    directory: '', //导航栏的组件所在目录
    loginUrl: '', //登录判断请求的地址
    nameUrl: '',   //查询登录人的地址
    registerUrl: '',  //注册地址
    passwordUrl: '', //修改密码的地址
    updateOrderUrl: '', //更新预约地址Url
    imageUrl: '',     //照片地址
    stageFlag: true,   //后台欢迎标语
    urlList: [],      //医生照片地址列表
    nameList:[],      //医生们的名字列表
    imgFlag: false      //是否需要重新获取照片
  },
  mutations: {
    setUrlList(state, urls) {
      state.urlList = urls;
    },
    setNameList(state, names){
      state.nameList=names;
    }
  },
  actions: {},
  modules: {}


}
