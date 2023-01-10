<template>
  <div>
    <!--  表格-->
    <el-table
      :data="tableData.filter(data => !search || data.username.toLowerCase().includes(search.toLowerCase()))"
      style="width: 100%"
      :row-class-name="tableRowClassName">
      <el-table-column
        type="index"
        width="50">
      </el-table-column>
      <el-table-column
        prop="username"
        label="账号"
        width="180">
      </el-table-column>
      <el-table-column
        prop="name"
        label="居民姓名">
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
      <el-form ref="form" :model="user" label-width="80px">
        <el-form-item label="账号">
          <el-input v-model="user.username" disabled></el-input>
        </el-form-item>
        <el-form-item label="居民姓名">
          <el-input v-model="user.name"></el-input>
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model="user.password"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="updateUser">确 定</el-button>
  </span>
    </el-dialog>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "Users",
  mounted() {
    this.getUsers();
  },
  data() {
    return {
      dialogVisible: false,
      tableData: [],
      search: '',
      user: {
        username: '',
        name: '',
        password: ''
      }
    }
  },
  methods: {
    //更新用户
    updateUser() {
      this.dialogVisible = false;
      axios({
        method: 'post',
        url: '/user/updateUser',
        data: this.user
      }).then(resp => {
        if (resp.data == 'success') {
          this.$message({
            type: 'success',
            message: '修改成功'
          });
          //重新获取
          this.getUsers();
        }
      })
    },

    tableRowClassName({row, rowIndex}) {
      if (rowIndex === 1) {
        return 'warning-row';
      } else if (rowIndex === 3) {
        return 'success-row';
      }
      return '';
    },
    //获取所有用户信息
    getUsers() {
      axios({
        method: 'get',
        url: '/user/selectAll'
      }).then(resp => {
        this.tableData = resp.data;
      })
    },
    handleEdit(index, row) {
      this.dialogVisible = true
      //赋值order
      this.user.username = row.username;
      this.user.name = row.name;
      this.user.password = row.password;
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
