<template>
  <el-container style="width: 100%;height: 100%;">
    <el-header style="height: 70px;background-color: black;
    display: flex;justify-content: space-between;align-items: center;">
      <img src="../assets/images/logo.png" style="object-fit: contain; width: 40px;height: 40px">
      <div style="width: 8%;height: 100%;display: flex;justify-content: space-around;align-items: center;">
        <img src="../assets/images/zz.jpg" style="object-fit: contain;width: 40px;height: 40px;border-radius:50%;"/>
        <el-dropdown>
            <span class="el-dropdown-link" style="color: white;">
              {{ user.nickname }}<i class="el-icon-arrow-down el-icon--right"></i>
            </span>
          <el-dropdown-menu>
            <el-dropdown-item>退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>
    </el-header>
    <el-container>
      <el-aside width="15vw" style="display: flex;flex-direction: column;align-items: stretch;border-right: 1px solid #d9d9d9;">
        <el-menu router :default-active="$route.path" style="border:0;">
          <el-menu-item index="1" route="/main">
            <i class="el-icon-goods"/>
            <span slot="title">主页</span>
          </el-menu-item>
          <el-menu-item index="2" route="/courses">
            <i class="el-icon-data-analysis"/>
            <span slot="title">我的课程</span>
          </el-menu-item>
          <el-menu-item index="3" route="/admin">
            <i class="el-icon-data-analysis"/>
            <span slot="title">我的班级</span>
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
      isAdmin: false,
      user: {},
    }
  },
  methods: {}
}
</script>

<style scoped>

</style>