<template>
  <el-container>
    <el-header>
      <strong id="PlaHeader">居民预约医生服务平台</strong>
      <div class="header-avatar">
        <router-link to="/home"><span id="backstage">返回后台</span></router-link>
        <img v-if="imageUrl!=null&&imageUrl!=''" width="46px" height="46px" :src="imageUrl"/>
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
      <!--轮播图-->
      <div>
        <el-carousel :interval="2000" type="card" height="450px">
          <el-carousel-item v-for="item in pictureList" :key="item.url">
            <!--       <h3 class="medium">{{ item }}</h3>-->
            <img class="bannerImg" :src="item.url" alt="加载失败">
          </el-carousel-item>
        </el-carousel>
      </div>

      <!--医生图片-->
      <span class="columnName">————————————————————————————医&nbsp;&nbsp;生&nbsp;&nbsp;栏&nbsp;&nbsp;目————————————————————————————</span>
      <div class="demo-image">
        <div class="inline" v-for="(url,index) in imgUrls" :key="url">
          <el-image
            class="docImg"
            style="width: 100px; height: 100px"
            :src="url"
            fit="fill"
            @click="handleEdit(url,names[index],index)"></el-image>
          <span class="demonstration" @click="handleEdit(url,names[index],index)">{{ names[index] }}</span>
        </div>
      </div>

      <!--      医生信息对话框1-->
      <el-dialog
        title="医生信息"
        :visible.sync="dialogVisible"
        width="30%"
        :before-close="handleClose">
        <img :src="docUrl" style="width: 120px;height: 110px;margin-bottom: -38px" alt=""/>
        <el-form ref="form" :model="doctorInfo" label-width="80px">
          <el-form-item label="账号">
            <el-col :span="12">
              <el-input v-model="doctorInfo.doctorName"></el-input>
            </el-col>
          </el-form-item>
          <el-form-item label="姓名">
            <el-col :span="12">
              <el-input v-model="doctorInfo.name"></el-input>
            </el-col>
          </el-form-item>
          <el-form-item label="性别">
            <el-col :span="8">
              <el-input v-model="doctorInfo.sex"></el-input>
            </el-col>
          </el-form-item>
          <el-form-item label="电话号码">
            <el-col :span="12">
              <el-input v-model="doctorInfo.tel"></el-input>
            </el-col>
          </el-form-item>
          <el-form-item label="所属科室">
            <el-col :span="12">
              <el-input v-model="doctorInfo.room"></el-input>
            </el-col>
          </el-form-item>
          <el-form-item label="执业经历">
            <el-col :span="12">
              <el-input v-model="doctorInfo.experience">
                <i slot="suffix">年</i>
              </el-input>
            </el-col>
          </el-form-item>
          <el-form-item label="擅长领域">
            <el-col :span="12">
              <el-input v-model="doctorInfo.major">
              </el-input>
            </el-col>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="orderBtn(doctorInfo.name,doctorInfo.room)">预约</el-button>
            <el-button @click="dialogVisible = false">取消</el-button>
          </el-form-item>
        </el-form>

      </el-dialog>

      <!--   预约对话框2-->
      <el-dialog
        title="预约信息"
        :visible.sync="dialogVisible2"
        width="40%"
        :before-close="handleClose">
        <el-form ref="form" :model="order" label-width="80px">
          <el-form-item label="医生姓名">
            <el-input v-model="order.dName" disabled></el-input>
          </el-form-item>
          <el-form-item label="居民姓名">
            <el-input v-model="order.uName"></el-input>
          </el-form-item>
          <el-form-item label="预约时间">
            <el-col :span="11">
              <el-date-picker type="date" placeholder="选择日期" value-format="yyyy-MM-dd" v-model="order.date1"
                              style="width: 100%;"></el-date-picker>
            </el-col>
            <el-col class="line" :span="2">-</el-col>
            <el-col :span="11">
              <el-time-picker value-format="HH:mm:ss" placeholder="选择时间" v-model="order.date2"
                              style="width: 100%;"></el-time-picker>
            </el-col>
          </el-form-item>
          <el-form-item label="预约地点">
            <el-input v-model="order.orderAddress" disabled></el-input>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible2 = false">取 消</el-button>
    <el-button type="primary" @click="addOrder">确 定</el-button>
  </span>
      </el-dialog>
    </el-main>
  </el-container>

</template>

<script>
import store from "../store";
import axios from "axios";

export default {
  name: "Index",
  mounted() {
    this.name = this.$store.state.login.name;
    this.imageUrl = this.$store.state.login.imageUrl;
    this.$store.state.login.stageFlag = true;
    this.$store.state.login.imgFlag = false;
    /* if(this.imgUrls.length==0){
       this.getDoctorImages();
     }*/
    //实时获取医生图片
    this.getDoctorImages();

  },
  computed: {
    imgUrls: {
      get() {
        return this.$store.state.login.urlList;
      }
    },
    names: {
      get() {
        return this.$store.state.login.nameList;
      }
    }
  },
  data() {
    return {
      pictureList: [
        {url: require('../assets/imgs/6.jpg')},
        {url: require('../assets/imgs/1.jpg')},
        {url: require('../assets/imgs/2.jpg')},
        {url: require('../assets/imgs/3.jpg')},
        {url: require('../assets/imgs/4.jpg')},
        {url: require('../assets/imgs/5.jpg')}
      ],
      urls: [],
      doctorNames: [],
      zhangHao: [],
      doctorInfo: {
        doctorName: '',
        name: '',
        sex: '',
        tel: '',
        room: '',
        experience: '',
        major: ''
      },
      order: {
        doctorName: '',
        username: '',
        dName: '',
        uName: '',
        orderTime: '',
        orderAddress: '',
        date1: '',
        date2: '',
        status: 0
      },
      name: '',
      imageUrl: '',
      dialogVisible: false,
      dialogVisible2: false,
      docUrl: ''
    }
  },
  methods: {
    //发送预约请求
    addOrder() {
      this.dialogVisible2 = false;
      this.dialogVisible = false;
      this.order.username = this.$store.state.login.id;
      this.order.orderTime = this.order.date1 + ' ' + this.order.date2;
      axios({
        method: 'post',
        url: '/order/addOrder',
        data: this.order
      }).then(resp => {
        if (resp.data == 'success') {
          this.$message({
            type: 'success',
            message: '预约请求已成功发送!'
          });
        } else {
          this.$message.error('预约失败！');
        }
      })
    },
    //点击预约
    orderBtn(name, room) {
      this.dialogVisible2 = true;
      this.order.dName = name;
      this.order.orderAddress = room;
      this.order.uName = this.$store.state.login.name;
    },
    //展开预约预约
    handleEdit(url, name, index) {
      this.docUrl = url;
      this.dialogVisible = true;
      this.getDoctorInfo(this.zhangHao[index]);
      this.order.doctorName = this.zhangHao[index];
     // console.log("Index_methods_handleEdit====" + name);
    },
    //获取医生信息
    getDoctorInfo(doctorName) {
      axios({
        method: 'get',
        url: '/doctorInfo/selectByDoctorName?username=' + doctorName
      }).then(resp => {
        this.doctorInfo = resp.data;
      })
    },
    //退出登录
    logOut() {
      this.$store.state.login.imageUrl = '';
      this.$store.state.login.name = '';
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
    //获取医生的图片信息
    getDoctorImages() {
      this.$axios({
        method: "get",
        url: "/upload/getDoctorImages"
      }).then(resp => {
        if (resp.data != null) {

          for (let i = 0; i < resp.data.length; i++) {
            this.urls[i] = require('../assets/upload/' + resp.data[i].path);
            this.doctorNames[i] = resp.data[i].name;
            this.zhangHao[i] = resp.data[i].username;
            //console.log("图片名字："+resp.data[i].name)
          }
          //console.log("图片名字：=========" + this.doctorNames);
          store.commit("setUrlList", this.urls);
          store.commit("setNameList", this.doctorNames);
          //store.commit("setMenuList", resp.data);
          //this.imageUrl=require('../assets/upload/'+resp.data.path);
        }

      })
    },
    //关闭对话框
    handleClose(done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done();
        })
        .catch(_ => {
        });
    }
  }

}
</script>
<style scoped>

#PlaHeader{
  margin-left: 200px;
}
/*对话框*/
.el-dialog__header {
  height: 82px;
}

.demonstration {
  display: block;
  margin-top: -137px;
  cursor: pointer;
}

/*悬浮医生名字时，放大*/
.demonstration:hover {
  font-size: 18px;
}

.inline {
  display: inline-block;
  margin-left: 27px;
  cursor: pointer;
}

.docImg:hover {
  width: 110px !important;
  height: 110px !important;
}

/*公告栏名字*/
.columnName {
  margin-top: -119px;
  display: flex;
  justify-content: center;
}

#backstage {
  margin-right: 20px;
  cursor: pointer;
  color: black;
}

.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
}

.bannerImg {
  width: 100%;
  height: inherit;
}

.el-header {
  background-color: #B3C0D1;
  color: #333;
  text-align: center;
  line-height: 60px;
}

.el-main {
  background-color: #E9EEF3;
  color: #333;
  text-align: center;
  line-height: 160px;
}

body > .el-container {
  margin-bottom: 40px;
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

a {
  text-decoration: none;
  font-size: 15px;
}

a:hover {
  font-size: 18px;
}

.router-link-active {
  text-decoration: none;
}
</style>
