<template>
  <el-container>
    <!--侧边栏-->
    <el-aside width="200px">
      <SideMenu></SideMenu>
    </el-aside>


    <el-container>
      <!--头部-->
      <el-header>
        <strong>居民预约医生服务平台</strong>
        <div class="header-avatar">
          <img v-if="imageUrl!=null&&imageUrl!=''" width="46px" height="46px" :src="imageUrl"/>
          <!--          <el-avatar
                      v-if="true"
                      size="medium"
                      :src="require('../upload/20220428181439ad189962efe0305.jpg')">
                    </el-avatar>-->
          <el-avatar
            v-else
            icon="el-icon-user-solid"></el-avatar>
          <el-dropdown>
           <span class="el-dropdown-link">
                 {{ name }}<i class="el-icon-arrow-down el-icon--right"></i>
           </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>
                <span @click="goUpdatePassword">修改密码</span>
              </el-dropdown-item>
              <el-dropdown-item>
                <span @click="logOut">退出登录</span>
              </el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>

      </el-header>
      <el-main>
        <div v-if="flag" id="backstage">
          <strong> 欢迎您！登录居民预约医生服务平台 </strong>
        </div>
        <div style="margin: -19px -20px">
          <router-view/>
        </div>
      </el-main>
    </el-container>

  </el-container>
</template>

<script>
import SideMenu from "./inc/SideMenu";

export default {
  name: "Home",
  components: {
    SideMenu
  },
  data() {
    return {
      imgUrl: ''
      // imageUrl: ''
    }
  },//data
  created() {
    this.getImage();
    if (this.imgFlag || this.name == '') {
      this.getInfo();
    }
  },
  computed: {
    name: {
      get() {
        return this.$store.state.login.name;
      }
    },
    imgFlag: {
      get() {
        //console.log("Home_computed_imgFlag:====="+this.$store.state.login.imgFlag)
        return this.$store.state.login.imgFlag;
      }
    },
    flag: {
      get() {
        return this.$store.state.login.stageFlag;
      }
    },
    imageUrl: {
      get() {
        return this.$store.state.login.imageUrl;
      }
    }
  },
  methods: {
    //退出登录
    logOut() {
      this.$store.state.login.imageUrl = '';
      this.$store.state.login.name='';
      this.$router.push({
        name: 'Login'
      })
    },
    //跳转到修改密码页面
    goUpdatePassword() {
      this.$store.state.login.stageFlag = false;
      this.$router.push({
        name: 'UpdatePassword',
        params: {
          username: this.$store.state.login.id
        }
      })
    },
    //获取个人信息
    getInfo() {
      this.$axios({
        method: "post",
        url: this.$store.state.login.nameUrl + '?username=' + this.$store.state.login.id
      }).then(resp => {
        //this.name = resp.data.name;
        this.$store.state.login.name = resp.data.name;
      })
    },
    //获取头像信息
    getImage() {
      this.$axios({
        method: "get",
        url: "/upload/getImage?username=" + this.$store.state.login.id + "&position=" + this.$store.state.login.role
      }).then(resp => {
        if (resp.data != null) {
          this.imgUrl = require('../assets/upload/' + resp.data.path);
          this.$store.state.login.imageUrl = this.imgUrl;
        }

      })
    }
  }
}
</script>

<style scoped>

#backstage {
  margin-top: 100px;
  margin-left: 381px;
}

.el-container {
  padding: 0;
  margin: 0;
  height: 100vh;
}

.el-avatar {
  margin-right: 10px;
}

.header-avatar {
  padding: 0;
  float: right;
  width: 210px;
  display: flex;
  justify-content: flex-end;
  align-items: center;
}

.header-avatar img {
  margin-right: 10px;
  border-radius: 50%;
}

.el-dropdown-link {
  cursor: pointer;
}

.el-header {
  background-color: #3ce23e;
  color: #333;
  text-align: center;
  line-height: 60px;
}

.el-aside {
  background-color: #D3DCE6;
  color: #333;
  text-align: center;
  line-height: 200px;
}

.el-main {
  background-color: #E9EEF3;
  color: #333;
  /*text-align: center;*/
  line-height: 160px;
}

body > .el-container {
  margin-bottom: 40px;
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
  line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
  line-height: 320px;
}

a {
  text-decoration: none;
}

.router-link-active {
  text-decoration: none;
}

</style>
