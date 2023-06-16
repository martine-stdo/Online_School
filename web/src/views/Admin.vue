<template>
  <div style="width: 100%;height: 100%;display: flex;flex-direction: column;">

    <span style="font-size: 26px; height: 40px; font-weight: 500;">课程管理</span>

    <el-row type="flex" align="center" justify="space-between" style="width: 100%;margin-top: 20px;">
      <el-form :inline="true">
        <el-form-item style="margin-left: 20px;">
          <el-button type="primary">查询</el-button>
        </el-form-item>
      </el-form>
      <div>
        <el-button type="primary">创建课程</el-button>
        <el-button type="danger" @click="deletes">删除课程</el-button>
      </div>
    </el-row>

    <el-table ref="studentTable" :data="tableData" border style="width: 100%">
      <el-table-column type="selection" width="55"/>
      <el-table-column prop="courseId" label="课程编号" width="155"/>
      <el-table-column prop="courseName" label="课程名名" width="250"/>
      <el-table-column prop="courseDescription" label="描述" width="250"/>
      <el-table-column prop="courseStartTime" label="开始时间" width="250"/>
      <el-table-column prop="courseEndTime" label="结束时间" width="250"/>
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
  name: "Admin",
  created() {
    this.queryCourses(this.currentPage, this.pageSize);
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
    queryCourses(currentPage, pageSize) {
      this.$axios
      .post("/queryCourse", { pageNum: currentPage, pageSize: pageSize })
      .then((res)=>{
        this.tableData = res.data.courses;
      })
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