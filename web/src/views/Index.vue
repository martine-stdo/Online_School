<template>
  <el-container style="width: 100%; height: 100%">
    <el-header style="
        height: 70px;
        background-color: black;
        display: flex;
        justify-content: space-between;
        align-items: center;
      ">
      <div style="
          display: flex;
          justify-content: space-between;
          align-items: center;
        ">
        <img src="../assets/images/logo.png" style="object-fit: contain; width: 40px; height: 40px" />
        <span style="color: white; padding-left: 15px">
          <h1>课程信息管理系统</h1>
        </span>
      </div>
      <div style="
          height: 100%;
          display: flex;
          justify-content: space-around;
          align-items: center;
        ">s
        <el-avatar :src="`${this.user.avator}`">
        </el-avatar>
        <div style="width: 15px"></div>
        <el-dropdown @command="handleCommand">
          <span class="el-dropdown-link" style="color: white">
            你好，{{ user.username }}<i class="el-icon-arrow-down el-icon--right"></i>
          </span>
          <el-dropdown-menu>
            <el-dropdown-item command="exit">退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>
    </el-header>
    <el-container>
      <el-aside width="15vw" style="
          display: flex;
          flex-direction: column;
          align-items: stretch;
          border-right: 1px solid #d9d9d9;
        ">
        <el-menu router :default-active="$route.path" style="border: 0">
          <el-menu-item index="1" route="/main">
            <i class="el-icon-goods" />
            <span slot="title">主页</span>
          </el-menu-item>
          <el-menu-item index="3" route="/admin" v-show="isAdmin">
            <i class="el-icon-data-analysis" />
            <span slot="title">课程管理</span>
          </el-menu-item>
        </el-menu>
      </el-aside>
      <el-main>
        <router-view />
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {
  name: "index",
  created() {
    // 发送 GET 请求获取用户信息
    this.$axios.get('/user/info',)
      .then(response => {
        if (response.code === 200) {
          this.user = response.data; // 保存用户信息到组件的 user 对象
          localStorage.setItem("user", JSON.stringify(this.user));
        } else {
          console.log('获取用户信息失败');
        }
      })
      .catch(error => {
        console.log('获取用户信息失败', error);
      });
    setTimeout(() => {
      this.user = JSON.parse(localStorage.getItem("user"));
      this.isAdmin = this.user.admin;
    },50);

  },
  data() {
    return {
      openRouter: true,
      isAdmin: false,
      user: {
        username: "管理员"
      },
    };
  },
  methods: {
    extractColorByName(name) {
      var temp = [];
      temp.push("#");
      for (let index = 0; index < name.length; index++) {
        temp.push(parseInt(name[index].charCodeAt(0), 10).toString(16));
      }
      return temp.slice(0, 5).join("").slice(0, 4);
    },
    handleCommand(command) {
      if (command === "exit") {
        localStorage.clear();
        this.$router.push("/");
      }
    },
  }
};
</script>

<style scoped></style>