<template>
  <div>
    <el-form ref="form" :model="doctorInfo" label-width="80px">
      <el-form-item label="账号">
        <el-col :span="3">
          <el-input v-model="doctorInfo.doctorName"></el-input>
        </el-col>
      </el-form-item>
      <el-form-item label="姓名">
        <el-col :span="3">
          <el-input v-model="doctorInfo.name"></el-input>
        </el-col>
      </el-form-item>
      <el-form-item label="性别" style="width: 20%">
        <el-select v-model="doctorInfo.sex" placeholder="请选择性别">
          <el-option label="男" value="男"></el-option>
          <el-option label="女" value="女"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="电话号码">
        <el-col :span="3">
          <el-input v-model="doctorInfo.tel"></el-input>
        </el-col>
      </el-form-item>
      <el-form-item label="所属科室">
        <el-col :span="3">
          <el-input v-model="doctorInfo.room"></el-input>
        </el-col>
      </el-form-item>
      <el-form-item label="执业经历">
        <el-col :span="3">
          <el-input v-model="doctorInfo.experience">
            <i slot="suffix">年</i>
          </el-input>
        </el-col>
      </el-form-item>
      <el-form-item label="预约上限">
        <el-col :span="3">
          <el-input v-model="doctorInfo.orderCount">
            <i slot="suffix">人</i>
          </el-input>
        </el-col>
      </el-form-item>
      <el-form-item label="擅长领域">
        <el-col :span="12">
          <el-input v-model="doctorInfo.major">
          </el-input>
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

export default {
  name: "DoctorInfo",
  mounted() {
    this.getDoctorInfo();
  },
  data() {
    return {
      doctorInfo: {
        doctorName: '',
        name: '',
        sex: '',
        tel: '',
        room: '',
        experience: '',
        major: '',
        orderCount:''
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
  methods: {
    //上传头像
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
            _this.$message({
              type: 'success',
              message: '上传成功!'
            });
          }
        })
      }
    },
    initUpload() {
      /* console.log("初始化................")*/
      this.flag = true;
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
    //获取医生信息
    getDoctorInfo() {
      axios({
        method: 'get',
        url: '/doctorInfo/selectByDoctorName?username=' + this.$store.state.login.id
      }).then(resp => {
        this.$store.state.login.imgFlag=true;
        this.doctorInfo = resp.data;
      })
    },
    //修改信息
    onSubmit() {
      this.$confirm('此操作将修改该信息, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        axios({
          method: 'post',
          url: '/doctorInfo/updateByDoctorName',
          data: this.doctorInfo
        }).then(resp => {
          if (resp.data == 'success') {
            this.$message({
              type: 'success',
              message: '修改成功!'
            });
          }
          //刷新页面
          this.getDoctorInfo();
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

</style>
