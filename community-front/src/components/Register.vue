<template>
<div id="register">
  <el-row type="flex" class="row-bg" justify="center">
    <el-form :model="registerForm" :rules="rules" ref="registerForm" label-width="100px" class="demo-registerForm">
      <el-form-item label="用户名" prop="username">
        <el-input v-model="registerForm.username"></el-input>
      </el-form-item>
      <el-form-item label="姓名" prop="name">
        <el-input v-model="registerForm.name"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input v-model="registerForm.password" type="password"></el-input>
      </el-form-item>

      <el-form-item label="角色" prop="role">
        <el-radio-group v-model="registerForm.role">
          <el-radio :label="3">居民</el-radio>
          <el-radio :label="6">医生</el-radio>
          <el-radio :label="9">管理员</el-radio>
        </el-radio-group>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="submitForm('registerForm')">注册</el-button>
        <el-button @click="resetForm('registerForm')">重置</el-button>
      </el-form-item>
    </el-form>

  </el-row>
</div>
</template>

<script>
import axios from "axios";

export default {
  name: "Register",
  data() {
    return {
      registerForm: {
        username: '',
        doctorName: '',
        adminName:'',
        password: '',
        role: '',
        name:''
      },
      rules: {
        username: [
          {required: true, message: '请输入用户名', trigger: 'blur'},
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
        ],
        name: [
          {required: true, message: '请输入姓名', trigger: 'blur'},
        ],
        role: [
          {required: true, message: '请选择角色', trigger: 'blur'},
        ]

      }

    }
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          //跳转准备
          if (this.registerForm.role == 3) {
            //角色=居民
            this.$store.state.login.registerUrl = '/user/addUser';
            this.$store.state.login.nameUrl='/user/selectByUsername';
          } else if (this.registerForm.role == 6) {
            //角色=医生
            this.registerForm.doctorName=this.registerForm.username;
            this.$store.state.login.registerUrl = '/doctor/addDoctor';
            this.$store.state.login.nameUrl='/doctor/selectByDoctorName';
          } else {
            //角色=管理员
            this.registerForm.adminName = this.registerForm.username;
            this.$store.state.login.registerUrl = '/admin/addAdmin';
            this.$store.state.login.nameUrl='/admin/selectByAdminName';
          }
          //检测用户名是否被注册
          axios({
            method:'get',
            url:this.$store.state.login.nameUrl + '?username='+this.registerForm.username
          }).then(res=>{
            if(res.data=='success'){
              //发送注册请求
              this.$axios({
                method: "post",
                url: this.$store.state.login.registerUrl,
                data: this.registerForm
              }).then(resp => {
                if (resp.data == 'success') {
                  //登录成功
                  this.$message({
                    message: '恭喜你，注册成功！',
                    type: 'success'
                  });
                  //跳转页面
                  this.$router.push({
                    name: 'Login',
                    params: {
                      username: this.registerForm.username,
                      role: this.registerForm.role
                    }
                  });
                } else {
                  //登录失败
                  this.$message.error('用户名、密码或角色错误！');
                }
              })
            }else {
              this.$message.error("用户名已被注册!")
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
#register{
  margin-top: 100px;
}
</style>
