<template>
  <el-menu
    default-active="2"
    class="el-menu-vertical-demo"
    background-color="#545c64"
    text-color="#fff"
    active-text-color="#ffd04b">
    <router-link to="/index">
      <el-menu-item index="0">
        <i class="el-icon-s-home"></i>
        <span slot="title">首&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;页</span>
      </el-menu-item>
    </router-link>
    <el-submenu :index="menu.menuName" v-for="menu in menuList" :key="menu.id">
      <template slot="title">
        <i :class="menu.icon"></i>
        <span>{{menu.title}}</span>
      </template>
      <router-link :to="menu.path">
        <el-menu-item :index="menu.itemName">
          <template slot="title">
            <span @click="initStage">{{menu.title2}}</span>
          </template>
        </el-menu-item>
      </router-link>
    </el-submenu>
  </el-menu>
</template>

<script>
export default {
  name: "SideMenu",
  data(){
    return{

    }
  },
  computed:{
    menuList:{
      get(){
        return this.$store.state.menus.menuList
      }
    },
    role:{
      get(){
        return this.$store.state.login.role
      }
    },
    navUrl:{
      get(){
        return this.$store.state.login.navUrl
      }
    },
    directory:{
      get(){
        return this.$store.state.login.directory
      }
    }
  },
  methods:{
    initStage(){
      this.$store.state.login.stageFlag=false;
    }
  }
}
</script>

<style scoped>

.el-menu-vertical-demo {
  height: 100%;
}

a{
  text-decoration: none;
}
.router-link-active{
  text-decoration: none;
}

.el-submenu .el-menu-item{
  min-width: 181px;
}
</style>
