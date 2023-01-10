<template>
  <div id="login">
    <el-row type="flex" class="row-bg" justify="center">
      <el-form :model="loginForm" :rules="rules" ref="loginForm" label-width="100px" class="demo-loginForm">
        <el-form-item label="用户名" prop="username">
          <el-input v-model="loginForm.username"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="loginForm.password" type="password"></el-input>
        </el-form-item>
        <!--      <el-form-item label="验证码" prop="code">
                <el-input v-model="loginForm.name"></el-input>
              </el-form-item>-->
        <el-form-item label="角色" prop="role">
          <el-radio-group v-model="loginForm.role">
            <el-radio :label="3">居民</el-radio>
            <el-radio :label="6">医生</el-radio>
            <el-radio :label="9">管理员</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm('loginForm')">登录</el-button>
          <el-button @click="resetForm('loginForm')">重置</el-button>
        </el-form-item>
      </el-form>
    </el-row>
    <p class="register">
      <router-link to="register">没有账号？立即注册</router-link>
    </p>
  </div>
</template>

<script>

export default {
  name: "Login",
  data() {
    return {
      loginForm: {
        username: '',
        doctorName: '',
        adminName: '',
        password: '',
        role: ''
      },
      rules: {
        username: [
          {required: true, message: '请输入用户名', trigger: 'blur'},
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
        ],
        role: [
          {required: true, message: '请选择角色', trigger: 'blur'},
        ]

      }

    }
  },
  methods: {
    //获取头像信息
    getImage() {
      this.$axios({
        method: "get",
        url: "/upload/getImage?username=" + this.$store.state.login.id + "&position=" + this.$store.state.login.role
      }).then(resp => {
        if (resp.data != null) {
          this.imageUrl = require('../assets/upload/' + resp.data.path);
          this.$store.state.login.imageUrl = this.imageUrl;
        }

      })
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          //跳转准备
          this.$store.state.login.role = this.loginForm.role;
          this.$store.state.login.id = this.loginForm.username;
          if (this.loginForm.role == 3) {
            //角色=居民
            this.$store.state.login.loginUrl = '/user/checkLogin';
            this.$store.state.menus.hasRoute = false;
            this.$store.state.login.navUrl = '/nav/selectAllOfUser';
            this.$store.state.login.directory = 'user';
            this.$store.state.login.nameUrl = '/userInfo/selectByUsername';
          } else if (this.loginForm.role == 6) {
            //角色=医生
            this.loginForm.doctorName = this.loginForm.username;
            this.$store.state.login.loginUrl = '/doctor/checkLogin';
            this.$store.state.menus.hasRoute = false;
            this.$store.state.login.navUrl = '/nav/selectAllOfDoctor';
            this.$store.state.login.directory = 'doctor';
            this.$store.state.login.nameUrl = '/doctorInfo/selectByDoctorName';
          } else {
            //角色=管理员
            this.loginForm.adminName = this.loginForm.username;
            this.$store.state.login.loginUrl = '/admin/checkLogin';
            this.$store.state.menus.hasRoute = false;
            this.$store.state.login.navUrl = '/nav/selectAllOfAdmin';
            this.$store.state.login.directory = 'admin';
            this.$store.state.login.nameUrl = '/adminInfo/selectByAdminName';
          }
          //发送登录请求
          this.$axios({
            method: "post",
            url: this.$store.state.login.loginUrl,
            data: this.loginForm
          }).then(resp => {
            if (resp.data == 'success') {
              //登录成功
              this.$message({
                message: '恭喜你，登录成功！',
                type: 'success'
              });
              //跳转页面
              this.$router.push({
                name:'Home',
                params: {
                  username: this.loginForm.username,
                  role: this.loginForm.role

                }
              });
              //获取头像
              this.getImage();
            } else {
              //登录失败
              this.$message.error('用户名、密码或角色错误！');
            }
          })
        } else {
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  }
}
</script>

<style scoped>
#login {
  margin-top: -53px;
  background: url("../assets/imgs/login.jpg");
  width: 100vm;
  height: 100vh;
}

.el-form {
  margin-top: 253px;
}

.register {
  margin-left: 712px;
}
</style>
