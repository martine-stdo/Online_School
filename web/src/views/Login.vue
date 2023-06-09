<template>
  <div style="width: 100%;height: 100%;display: flex;">
    <div style="width: 50%;height: 100%;"
         :style="{
          backgroundImage:'url(' + require('../assets/images/admin-login.png')+')',
          backgroundSize:'contain'}">
    </div>

    <div style="width: 50%;height: 100%;display: flex;justify-content: center;align-items: center;">
      <div style="width: 35%;height: 50%;padding-left: 20px;
      display: flex;flex-direction: column;justify-content: flex-start;">
        <div style="width: 100%;height: 80px;display: flex;flex-direction: column;justify-content: space-between;">
          <span style="color: #409eff;font-size: 24px;">欢迎使用Q通学</span>
          <span style="color: #409eff;font-size: 24px;">登录</span>
        </div>
        <div
            style="margin-top: 20px;width: 100%;height: 25%;display: flex;flex-direction: column;justify-content: space-between;">
          <el-input type="text" v-model="user.username" prefix-icon="el-icon-user" placeholder="请输入内容"></el-input>

          <el-input type="password" v-model="user.password" prefix-icon="el-icon-lock"
                    placeholder="请输入内容"></el-input>
        </div>

        <el-checkbox v-model="checked" style="margin-top: 20px;">记住密码</el-checkbox>

        <el-button @click="login"
                   type="primary" round style="margin-top: 20px;background-color: #409eff;border: 0;">登录
        </el-button>
      </div>

    </div>
  </div>


</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      checked: false,
      user: {
        username: '',
        password: ''
      }
    }
  },
  methods: {
    login() {
      this.$axios.post('/api/login', this.user).then(result => {
        console.log("login result: ", JSON.stringify(result))
        let user = result.data.user;
        let token = result.data.token;
        localStorage.setItem('user', JSON.stringify(user));
        localStorage.setItem('token', token);
        this.$router.push('/index');
       });
    }
  }
}
</script>

<style scoped>

</style>