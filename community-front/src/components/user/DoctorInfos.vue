<template>
   <div>
     <!--  表格-->
     <el-table
       :data="tableData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
       style="width: 100%"
       :row-class-name="tableRowClassName">

       <el-table-column
         type="index"
         width="50">
       </el-table-column>
       <el-table-column
         prop="name"
         label="医生姓名"
         width="180">
       </el-table-column>
       <el-table-column
         prop="sex"
         label="性别">
       </el-table-column>
       <el-table-column
         prop="tel"
         label="电话">
       </el-table-column>
       <el-table-column
         prop="experience"
         label="执业经历">
       </el-table-column>
       <el-table-column
         prop="major"
         label="擅长领域">
       </el-table-column>
       <el-table-column label="操作">
         <template slot-scope="scope">
           <el-button
             size="mini"
             type="primary"
             @click="handleEdit(scope.$index, scope.row)">预约
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
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="addOrder">确 定</el-button>
  </span>
     </el-dialog>
   </div>
</template>

<script>
import axios from "axios";

export default {
  name: "DoctorInfos",
  mounted() {
    this.getDoctors();
  },
  data(){
    return{
      tableData: [],
      search:'',
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
        status:0
      }
    }
  },
  methods:{
    //添加预约
    addOrder(){
      this.dialogVisible = false
      this.order.username=this.$store.state.login.id;
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
        }else if(resp.data=='ordered'){
          this.$message.error('已经预约过'+this.order.dName+'医生，请勿多次预约!');
        } else {
          this.$message.error('预约人数已满，预约失败！');
        }
      })
    },
    //获取医生信息
    getDoctors(){
      axios({
        method:'get',
        url:'/doctorInfo/selectAll'
      }).then(resp=>{
          this.tableData=resp.data
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
    //预约按钮
    handleEdit(index, row) {
      this.dialogVisible=true;
      this.order.dName = row.name;
      this.order.doctorName=row.doctorName;
      this.order.orderAddress = row.room;
      this.order.uName = this.$store.state.login.name;
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
