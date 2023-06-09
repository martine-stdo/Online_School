<template>
  <div style="width: 100%;height: 100%;display: flex;flex-direction: column;">

    <span style="font-size: 26px; height: 40px; font-weight: 500;">学员管理</span>

    <el-row type="flex" align="center" justify="space-between" style="width: 100%;margin-top: 20px;">
      <el-form :inline="true">
        <el-form-item label="关键词" style="font-weight: bold;">
          <el-input v-model="keyword" placeholder="输入关键字进行模糊匹配" style="width: 250px;"></el-input>
        </el-form-item>
        <el-form-item label="模糊匹配" style="font-weight: bold;margin-left: 20px;">
          <el-switch :width="50" :value="true" active-color="#13ce66" inactive-color="#D5D5D5FF"/>
        </el-form-item>
        <el-form-item style="margin-left: 20px;">
          <el-button type="primary">查询</el-button>
        </el-form-item>
      </el-form>
      <div>
        <el-button type="primary">创建用户</el-button>
        <el-button type="danger" @click="deletes">删除用户</el-button>
      </div>
    </el-row>

    <el-table ref="studentTable" :data="tableData" border style="width: 100%">
      <el-table-column type="selection" width="55"/>
      <el-table-column prop="empno" label="员工编号" width="155"/>
      <el-table-column prop="ename" label="员工姓名" width="250"/>
      <el-table-column prop="job" label="员工职位" width="250"/>
      <el-table-column prop="mgr" label="上级职位" width="250"/>
      <el-table-column prop="sal" label="员工薪资" width="250"/>
      <el-table-column prop="comm" label="岗位奖金" width="250"/>
      <el-table-column prop="department.dname" label="所属部门" width="250"/>
    </el-table>
    <div style="width: 100%; display: flex;justify-content: center;">

      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="currentPage"
                     :page-sizes="[5, 10, 15, 20]" :page-size="pageSize" :total="total"
                     layout="total, sizes, prev, pager, next, jumper"/>
    </div>

  </div>
</template>

<script>
export default {
  name: "Users",
  created() {
    this.queryEmployees(this.currentPage, this.pageSize);
  },
  data() {
    return {
      currentPage: 1,
      pageSize: 5,
      total: 0,
      keyword: '',
      tableData: []
    }
  },
  methods: {
    handleSizeChange(pageSize) {
      console.log("pageSize:", pageSize)
      this.queryEmployees(this.currentPage, pageSize)
    },
    handleCurrentChange(pageNum) {
      console.log("pageNum:", pageNum)
      this.queryEmployees(pageNum, this.pageSize)
    },
    queryEmployees(currentPage, pageSize) {
      this.$axios.get('/api/queryEmployees/' + currentPage + '/' + pageSize).then(result => {
        this.tableData = result.data.employees;
        this.total = result.data.total;
      });
    },
    deletes() {
      let rows = this.$refs['studentTable'].selection;
      let ids = '';
      for (let i = 0; i < rows.length; i++) {
        ids += ',' + rows[i].empno;
      }
      ids = ids.substring(1);   // 1,2,3,4,5,6....
      this.$axios.get('/api/deletes?empnos=' + ids).then(result => {
        this.$message(result);
        this.queryEmployees(1, this.pageSize);
      });

    }

  }
}
</script>

<style scoped>

</style>