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
      label="居民姓名"
      width="180">
    </el-table-column>
    <el-table-column
      prop="height"
      label="身高(cm)">
    </el-table-column>
    <el-table-column
      prop="weight"
      label="体重(kg)">
    </el-table-column>
    <el-table-column
      prop="heartRate"
      label="心率(次/分)">
    </el-table-column>
    <el-table-column
      prop="bloodPressure"
      label="血压(mmHg)">
    </el-table-column>
    <el-table-column
      prop="description"
      label="症状描述">
    </el-table-column>
    <el-table-column
      prop="allergyHistory"
      label="药物过敏史">
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
</div>
</template>

<script>
import axios from "axios";

export default {
  name: "HealthInfos",
  mounted() {
    this.getHealthInfos();
  },
  data(){
    return{
      tableData:[],
      search:'',
    }
  },
  methods:{
    //获取居民们的健康信息
    getHealthInfos() {
      axios({
        method:'get',
        url:'/healthInfo/selectAll'
      }).then(resp=>{
        this.tableData=resp.data
      })
    },
    //变色
    tableRowClassName({row, rowIndex}) {
      if (rowIndex%2 == 0) {
        return 'warning-row';
      } else {
        return 'success-row';
      }
    }
  }
}
</script>

<style>
.el-table .warning-row {
  background: oldlace;
}

.el-table .success-row {
  background: #f0f9eb;
}

</style>
