<template>
  <div>
    <!--  表格-->
    <el-table
      :data="tableData.filter(data => !search || data.uName.toLowerCase().includes(search.toLowerCase())|| data.dName.toLowerCase().includes(search.toLowerCase()))"
      style="width: 100%"
      :row-class-name="tableRowClassName">
      <el-table-column
        type="index"
        width="50">
      </el-table-column>
      <el-table-column
        prop="dName"
        label="医生姓名"
        width="180">
      </el-table-column>
      <el-table-column
        prop="uName"
        label="居民姓名">
      </el-table-column>
      <el-table-column
        prop="orderTime"
        label="预约时间">
      </el-table-column>
      <el-table-column
        prop="orderAddress"
        label="预约地点">
      </el-table-column>
      <el-table-column
        prop="statusStr"
        label="预约状态">
      </el-table-column>

      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="primary"
            @click="handleEdit(scope.$index, scope.row)">编辑
          </el-button>
          <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.$index, scope.row)">删除
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
      <el-form ref="form" :model="order" label-width="80px">
        <el-form-item label="医生姓名">
          <el-input v-model="order.dName"></el-input>
        </el-form-item>
        <el-form-item label="居民姓名">
          <el-input v-model="order.uName" disabled></el-input>
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
          <el-input v-model="order.orderAddress"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="updateOrder">确 定</el-button>
  </span>
    </el-dialog>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "OrderAll",
  mounted() {
    this.getOrder();
  },
  data() {
    return {
      tableData: [],
      dialogVisible: false,
      order: {
        doctorName: '',
        username: '',
        dName: '',
        uName: '',
        orderTime: '',
        orderAddress: '',
        date1: '',
        date2: '',
        status:''
      },
      search:''
    }
  },
  methods: {
    //更新预约信息
    updateOrder() {
      this.dialogVisible = false
      this.order.orderTime = this.order.date1 + ' ' + this.order.date2;
      axios({
        method: 'post',
        url: '/order/updateOrder',
        data: this.order
      }).then(resp => {
        if (resp.data == 'success') {
          this.$message({
            type: 'success',
            message: '修改成功!'
          });
          //重新获取
          this.getOrder();
        } else {
          this.$message.error('修改失败！');
        }
      })

    },
    //获取居民预约信息
    getOrder() {
      axios({
        method: 'get',
        url: '/order/selectAll'
      }).then(resp => {
        this.tableData = resp.data;
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
    //编辑
    handleEdit(index, row) {
      this.dialogVisible = true
      //赋值order
      this.order.username = row.username;
      this.order.doctorName = row.doctorName;
      this.order.dName = row.dName;
      this.order.uName = row.uName;
      this.order.orderAddress = row.orderAddress;
      let orderTime = row.orderTime;
      let time = orderTime.split(' ');
      this.order.date1 = time[0];
      this.order.date2 = time[1];
      this.order.status=row.status;
      // console.log(index, row);
    },
    //删除
    handleDelete(index, row) {
      this.$confirm('此操作将永久删除该预约, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        axios({
          method:'post',
          url:'/order/deleteOrder?username='+row.username+'&doctorName='+row.doctorName
        }).then(resp=>{
          if(resp.data=='success'){
            //响应
            this.$message({
              type: 'success',
              message: '删除成功!'
            });
            //刷新页面
            this.getOrder();
          }else {
            this.$message.error("删除失败！")
          }
        })

      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
      console.log(index, row);
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
