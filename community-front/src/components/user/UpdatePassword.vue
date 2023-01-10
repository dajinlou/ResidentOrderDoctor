<template>
  <el-row type="flex" class="row-bg" justify="center">
    <el-form :model="loginForm" :rules="rules" ref="loginForm" label-width="100px" class="demo-loginForm">
      <el-form-item label="用户名" prop="username">
        <el-input v-model="loginForm.username" disabled></el-input>
      </el-form-item>
      <el-form-item label="原密码" prop="password">
        <el-input v-model="loginForm.password" type="password"></el-input>
      </el-form-item>
      <el-form-item label="新密码" prop="newPassword">
        <el-input v-model="loginForm.newPassword" type="password"></el-input>
      </el-form-item>
      <!--      <el-form-item label="验证码" prop="code">
              <el-input v-model="loginForm.name"></el-input>
            </el-form-item>-->
      <el-form-item>
        <el-button type="primary" @click="submitForm('loginForm')">修改</el-button>
        <el-button @click="resetForm('loginForm')">重置</el-button>
      </el-form-item>
    </el-form>
  </el-row>
</template>

<script>
import axios from "axios";

export default {
  name: "UpdatePassword",
  data() {
    return {
      loginForm: {
        username:'',
        password:'',
        newPassword:'',
        doctorName:'',
        adminName:''
      },
      rules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
        ],
        password: [
          { required: true, message: '请输入旧密码', trigger: 'blur' },
        ],
        newPassword: [
          { required: true, message: '请输入新密码', trigger: 'blur' },
        ]

      }
    };
  },
  created() {
    this.loginForm.username=this.$route.params.username;
  },
  methods: {
    goLogin(){
      //跳转页面
      this.$router.push({
        name: 'Login'
      });
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {

          //修改前准备
          if (this.$store.state.login.role == 3) {
            //角色=居民
            this.$store.state.login.passwordUrl='/user/updateByUsername';
          } else if (this.$store.state.login.role == 6) {
            //角色=医生
            this.loginForm.doctorName=this.loginForm.username;
            this.$store.state.login.passwordUrl='/doctor/updateByDoctorName';
          } else {
            //角色=管理员
            this.loginForm.adminName = this.loginForm.username;
            this.$store.state.login.passwordUrl='/admin/updateByAdminName';
          }
          //判断是否有资格修改密码
          axios({
            method: "post",
            url: this.$store.state.login.loginUrl,
            data: this.loginForm
          }).then(res=>{
            if(res.data=='success'){
              this.$message({
                message: '恭喜你，修改成功，！',
                type: 'success'
              });

              //发送修改密码请求
              axios({
                method:'post',
                url:this.$store.state.login.passwordUrl+'?username='+this.loginForm.username+'&newPassword='+this.loginForm.newPassword
              }).then(resp=>{
                if(resp.data=='success'){
                   setTimeout(this.goLogin(),5000);
                  this.$message({
                    message: '密码已修改，请重新登录',
                    type: 'warning'
                  });
                }else {
                  this.$message.error("修改失败!")
                }

              })
            }else {
              this.$message.error("原密码错误!")
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
.el-form{
  margin-top:122px;
}
.el-row{
  /*background: url("../../assets/imgs/login2.jpg");*/
  height: 100vh;
}
</style>
