<template>
  <div id="userInfo">
    <el-form ref="form" :model="userInfo" label-width="80px">
      <el-form-item label="账号">
        <el-col :span="3">
          <el-input v-model="userInfo.username"></el-input>
        </el-col>
        <!-- 姓名 -->
        <el-col class="line" :span="4">&nbsp;&nbsp;</el-col>
        <el-col class="line" :span="1">姓名</el-col>
        <el-col class="line" :span="3">
          <el-input v-model="userInfo.name"></el-input>
        </el-col>
        <!-- 性别 -->
        <el-col class="line" :span="4">&nbsp;&nbsp;</el-col>
        <el-col class="line" :span="1">性别</el-col>
        <el-col class="line" :span="3">
          <el-select v-model="userInfo.sex" placeholder="请选择性别">
            <el-option label="男" value="男"></el-option>
            <el-option label="女" value="女"></el-option>
          </el-select>
        </el-col>
      </el-form-item>
      <!--      <el-form-item label="姓名">
              <el-col :span="3">
                <el-input v-model="userInfo.name"></el-input>
              </el-col>
            </el-form-item>
            <el-form-item label="性别" style="width: 20%">
              <el-select v-model="userInfo.sex" placeholder="请选择性别">
                <el-option label="男" value="男"></el-option>
                <el-option label="女" value="女"></el-option>
              </el-select>
            </el-form-item>-->
      <el-form-item label="出生日期">
        <el-col :span="4">
          <el-date-picker type="date" value-format="yyyy-MM-dd" placeholder="出生日期" v-model="userInfo.birthday"
                          style="width: 100%;"></el-date-picker>
        </el-col>
      </el-form-item>
      <el-form-item label="电话号码">
        <el-col :span="4">
          <el-input v-model="userInfo.num"></el-input>
        </el-col>
      </el-form-item>
      <el-form-item label="家庭住址">
        <el-col :span="12">
          <el-input v-model="userInfo.address"></el-input>
        </el-col>
      </el-form-item>
      <el-form-item label="个人说明">
        <el-col :span="15">
          <el-input type="textarea" v-model="userInfo.description"></el-input>
        </el-col>
      </el-form-item>
      <el-form-item label="上传头像">
        <!--上传头像-->
          <el-upload
            action="#"
            list-type="picture-card"
            :auto-upload="false"
          >
            <i slot="default" class="el-icon-plus" @click="initUpload"></i>
            <div v-if="flag" slot="file" slot-scope="{file}">
              <img
                class="el-upload-list__item-thumbnail"
                :src="file.url" alt=""
              >
              <span class="el-upload-list__item-actions">
              <span
                class="el-upload-list__item-preview"
                @click="handlePictureCardPreview(file)"
              >
                 <i class="el-icon-zoom-in"></i>
              </span>
              <span
                v-if="!disabled"
                class="el-upload-list__item-delete"
                @click="handleUpload(file)"
              >
          <i class="el-icon-upload"></i>
        </span>
              <span
                v-if="!disabled"
                class="el-upload-list__item-delete"
                @click="handleRemove(file)"
              >
          <i class="el-icon-delete"></i>
        </span>
            </span>
            </div>
          </el-upload>
        <el-dialog :visible.sync="dialogVisible">
          <img width="100%" :src="dialogImageUrl" alt="">
        </el-dialog>

      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">修改</el-button>
        <el-button>取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import axios from "axios";
import store from "../../store";

export default {
  name: "UserInfo",
  data() {
    return {
      userInfo: {
        username: '',
        name: '',
        sex: '',
        num: '',
        address: '',
        description: ''
      },
      dialogImageUrl: '',
      dialogVisible: false,
      disabled: false,
      flag: false,
      image: {
        url: '',
        img: '',
        username: '',
        position: '',
        name:''
      }
    }
  },
  mounted() {
    this.getUserInfo();
    this.getImage();
  },
  methods: {
    //上传
    upload(file) {
      const reader = new FileReader();
      reader.readAsDataURL(file.raw);
      let _this = this;
      reader.onload = function () {
        _this.image.img = reader.result;
        _this.image.url = file.name;
        _this.image.position = _this.$store.state.login.role;
        _this.image.username = _this.$store.state.login.id;
        _this.image.name = _this.$store.state.login.name;
        axios({
          method: "post",
          url: "/upload/uploadImage",
          data: _this.image
        }).then(resp => {
          let strings = resp.data.toString().split("!");
          if (strings[0] == "success") {
            _this.$store.state.login.imgFlag=true;
            _this.$router.push({path:"/userInfo"});
            _this.$message({
              type: 'success',
              message: '上传成功!'
            });
          }

        })
      }
    },
    initUpload() {
      // console.log("flag="+this.flag)
      // console.log("初始化................")
      this.flag = true;
      // console.log("flag="+this.flag)
    },
    handleRemove(file) {
      this.flag = false;
    },
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    },
    handleUpload(file) {
      this.upload(file);
    },
    //获取用户信息
    getUserInfo() {
      axios({
        method: 'get',
        url: '/userInfo/selectByUsername?username=' + this.$store.state.login.id
      }).then(resp => {
        this.userInfo = resp.data;
        this.$store.state.login.imgFlag=true;
      })
    },
    //获取头像信息
    getImage() {
      this.$axios({
        method: "get",
        url: "/upload/getImage?username=" + this.$store.state.login.id + "&position=" + this.$store.state.login.role
      }).then(resp => {
        if (resp.data != null) {
          this.imgUrl = require('../../assets/upload/' + resp.data.path);
          this.$store.state.login.imageUrl = this.imgUrl;
        }

      })
    },
    //修改用户信息
    onSubmit() {
      this.$confirm('此操作将修改该信息, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        axios({
          method: 'post',
          url: '/userInfo/updateByUsername',
          data: this.userInfo
        }).then(resp => {
          if (resp.data == 'success') {
            this.$message({
              type: 'success',
              message: '修改成功!'
            });
          }
          //刷新页面
          this.getUserInfo();
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消修改'
        });
      });
    }
  }
}
</script>

<style scoped>

#userInfo {
  height: 90vh;
  /*background: url("../../assets/imgs/userInfoBackground.jpg");*/
}
</style>
