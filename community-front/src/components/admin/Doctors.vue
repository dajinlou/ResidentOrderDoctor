<template>
  <div>
    <!--  表格-->
    <el-table
      :data="tableData.filter(data => !search || data.doctorName.toLowerCase().includes(search.toLowerCase()))"
      style="width: 100%">
      <el-table-column
        type="index"
        width="50">
      </el-table-column>
      <el-table-column
        prop="doctorName"
        label="账号"
        width="180">
      </el-table-column>
      <el-table-column
        prop="name"
        label="医生姓名">
      </el-table-column>
      <el-table-column
        prop="password"
        label="密码">
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="primary"
            @click="handleEdit(scope.$index, scope.row)">修改
          </el-button>
        </template>
      </el-table-column>
      <el-table-column
        align="right">
        <template slot="header" slot-scope="scope">
          <el-input
            v-model="search"
            size="mini"
            placeholder="输入关键字搜索"/>
        </template>
      </el-table-column>
    </el-table>

    <!--对话框-->
    <el-dialog
      title="预约信息"
      :visible.sync="dialogVisible"
      width="40%"
      :before-close="handleClose">
      <el-form ref="form" :model="doctor" label-width="80px">
        <el-form-item label="账号">
          <el-input v-model="doctor.doctorName" disabled></el-input>
        </el-form-item>
        <el-form-item label="医生姓名">
          <el-input v-model="doctor.name"></el-input>
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model="doctor.password"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="updateDoctor">确 定</el-button>
  </span>
    </el-dialog>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "Doctors",
  mounted() {
    this.getDoctors();
  },
  data() {
    return {
      dialogVisible: false,
      tableData: [],
      search: '',
      doctor: {
        doctorName: '',
        name: '',
        password: ''
      }
    }
  },
  methods: {
    //更新用户
    updateDoctor() {
      this.dialogVisible = false;
      axios({
        method: 'post',
        url: '/doctor/updateDoctor',
        data: this.doctor
      }).then(resp => {
        if (resp.data == 'success') {
          this.$message({
            type: 'success',
            message: '修改成功'
          });
          //重新获取
         this.getDoctors();
        }
      })
    },
    //获取所有用户信息
    getDoctors() {
      axios({
        method: 'get',
        url: '/doctor/selectAll'
      }).then(resp => {
        this.tableData = resp.data;
      })
    },
    handleEdit(index, row) {
      this.dialogVisible = true
      //赋值order
      this.doctor.doctorName = row.doctorName;
      this.doctor.name = row.name;
      this.doctor.password = row.password;
      // console.log(index, row);
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

</style>
