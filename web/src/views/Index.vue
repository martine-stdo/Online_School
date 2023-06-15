<template>
  <el-container style="width: 100%;height: 100%;">
    <el-header style="height: 70px;background-color: black;
    display: flex;justify-content: space-between;align-items: center;">
      <img src="../assets/images/logo.png" style="object-fit: contain; width: 40px;height: 40px">
      <div style="width: 8%;height: 100%;display: flex;justify-content: space-around;align-items: center;">
        <!--        <el-avatar size="medium" src="/assets/images/zz.jpg" />-->
        <img src="../assets/images/zz.jpg" style="object-fit: contain;width: 40px;height: 40px;border-radius:50%;"/>
        <el-dropdown>
            <span class="el-dropdown-link" style="color: white;">
              {{ user.nickname }}<i class="el-icon-arrow-down el-icon--right"></i>
            </span>
          <el-dropdown-menu>
            <el-dropdown-item>个人中心</el-dropdown-item>
            <el-dropdown-item>退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>
    </el-header>
    <el-container>
      <el-aside width="150px" style="display: flex;flex-direction: column;align-items: center;border-right: 1px solid #d9d9d9;">
        <el-menu router :default-active="$route.path" style="border:0;">
          <el-menu-item index="1" route="/students">
            <i class="el-icon-goods"/>
            <span slot="title">学员管理</span>
          </el-menu-item>
          <el-menu-item index="2" route="/coming-soon">
            <i class="el-icon-data-analysis"/>
            <span slot="title">教师管理</span>
          </el-menu-item>
          <el-menu-item index="3" route="/coming-soon">
            <i class="el-icon-data-analysis"/>
            <span slot="title">班级管理</span>
          </el-menu-item>
          <el-menu-item index="4" route="/coming-soon">
            <i class="el-icon-loading"/>
            <span slot="title">课程管理</span>
          </el-menu-item>
        </el-menu>
      </el-aside>
      <el-main>

        <router-view/>

      </el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {
  name: "index",
  created() {
    // this.user = JSON.parse(localStorage.getItem("user"));
  },
  data() {
    return {
      openRouter: true,
      user: {},
    }
  },
  methods: {}
}
</script>

<style scoped>

</style>