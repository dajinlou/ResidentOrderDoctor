<template>
  <div id="healthInfo">
    <el-form ref="form" :model="healthInfo" label-width="90px">
      <el-form-item label="姓名">
        <el-col :span="3">
          <el-input v-model="healthInfo.name"></el-input>
        </el-col>
      </el-form-item>
      <el-form-item label="身高">
        <el-col :span="3">
          <el-input v-model="healthInfo.height">
            <i slot="suffix">cm</i>
          </el-input>
        </el-col>
      </el-form-item>
      <el-form-item label="体重">
        <el-col :span="3">
          <el-input v-model="healthInfo.weight">
            <i slot="suffix">kg</i>
          </el-input>
        </el-col>
      </el-form-item>
      <el-form-item label="血压">
        <el-col :span="3">
          <el-input v-model="healthInfo.bloodPressure">
            <i slot="suffix">mmHg</i>
          </el-input>
        </el-col>
      </el-form-item>
      <el-form-item label="心率">
        <el-col :span="3">
          <el-input v-model="healthInfo.heartRate">
            <i slot="suffix">次/分</i>
          </el-input>
        </el-col>
      </el-form-item>
      <el-form-item label="药物过敏史">
        <el-col :span="3">
          <el-input v-model="healthInfo.allergyHistory"></el-input>
        </el-col>
      </el-form-item>

      <el-form-item label="身体状况">
        <el-col :span="15">
          <el-input type="textarea" v-model="healthInfo.description"></el-input>
        </el-col>
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
  name: "HealthInfo",
  data() {
    return {
      healthInfo: {
        name: '',
        height: '',
        weight:'',
        bloodPressure: '',
        heartRate: '',
        allergyHistory: '',
        description: ''
      }
    }
  },
  mounted() {
    this.getHealthInfo();
  },
  methods: {
    //获取用户信息
    getHealthInfo() {
      axios({
        method: 'post',
        url: '/healthInfo/selectByUsername?username=' + this.$store.state.login.id
      }).then(resp => {
        this.healthInfo = resp.data;

      })
    },
    onSubmit() {
      this.$confirm('此操作将修改该信息, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        axios({
          method: 'post',
          url: '/healthInfo/updateByUsername',
          data: this.healthInfo
        }).then(resp => {
          if (resp.data == 'success') {
            this.$message({
              type: 'success',
              message: '修改成功!'
            });
          }
          //刷新页面
          this.getHealthInfo();
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

#healthInfo {
  /*background: url("../../assets/imgs/userInfoBackground2.jpg");*/
  height: 90vh;
}
</style>
