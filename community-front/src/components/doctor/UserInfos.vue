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
          prop="sex"
          label="性别">
        </el-table-column>
        <el-table-column
          prop="birthday"
          label="出生日期">
        </el-table-column>
        <el-table-column
          prop="num"
          label="手机号">
        </el-table-column>
        <el-table-column
          prop="address"
          label="地址">
        </el-table-column>
        <el-table-column
          prop="description"
          label="个人说明">
        </el-table-column>

        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="primary"
              @click="handleEdit(scope.$index, scope.row)">健康详情
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
        title="健康信息"
        :visible.sync="dialogVisible"
        width="40%"
        :before-close="handleClose">
        <el-form ref="form" :model="healthInfo" label-width="90px">
          <el-form-item label="姓名">
            <el-col :span="8">
              <el-input v-model="healthInfo.name" disabled></el-input>
            </el-col>
          </el-form-item>
          <el-form-item label="身高">
            <el-col :span="8">
           <el-input v-model="healthInfo.height" disabled>
                <i slot="suffix">cm</i>
              </el-input>
            </el-col>
          </el-form-item>
          <el-form-item label="体重">
            <el-col :span="8">
              <el-input v-model="healthInfo.weight" disabled>
                <i slot="suffix">kg</i>
              </el-input>
            </el-col>
          </el-form-item>
          <el-form-item label="血压">
            <el-col :span="8">
              <el-input v-model="healthInfo.bloodPressure"  disabled>
                <i slot="suffix">mmHg</i>
              </el-input>
            </el-col>
          </el-form-item>
          <el-form-item label="心率">
            <el-col :span="8">
              <el-input v-model="healthInfo.heartRate" disabled>
                <i slot="suffix">次/分</i>
              </el-input>
            </el-col>
          </el-form-item>
          <el-form-item label="药物过敏史">
            <el-col :span="8">
              <el-input v-model="healthInfo.allergyHistory" disabled></el-input>
            </el-col>
          </el-form-item>

          <el-form-item label="身体状况">
            <el-col :span="15">
              <el-input type="textarea" v-model="healthInfo.description" disabled></el-input>
            </el-col>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">关闭</el-button>
  </span>
      </el-dialog>
    </div>
</template>

<script>
import axios from "axios";

export default {
  name: "UserInfos",
  mounted() {
    this.getUserInfos();
  },
  data() {
    return {
      tableData: [],
      dialogVisible: false,
      healthInfo: {
        name: '',
        height: '',
        weight:'',
        bloodPressure: '',
        heartRate: '',
        allergyHistory: '',
        description: ''
      },
      search:''
    }
  },
  methods: {

    //获取居民们信息
    getUserInfos() {
      axios({
        method: 'get',
        url: '/userInfo/selectAll'
      }).then(resp => {
        this.tableData = resp.data;
      })
    },
    //变色
    tableRowClassName({row, rowIndex}) {
      if (rowIndex%2 == 0) {
        return 'warning-row';
      } else {
        return 'success-row';
      }
    },
    //健康详情
    handleEdit(index, row) {
      this.dialogVisible = true
      //赋值order
      axios({
        method: 'post',
        url: '/healthInfo/selectByUsername?username=' + row.username
      }).then(resp => {
        this.healthInfo = resp.data;

      })
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

<style>

.el-table .warning-row {
  background: oldlace;
}

.el-table .success-row {
  background: #f0f9eb;
}

</style>
