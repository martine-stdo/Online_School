<template>
  <div class="bg">
    <div class="bgOverlay"></div>
    <el-row style="width: 100%">
      <el-col
        type="flex"
        justify="space-around"
        :xs="24"
        :sm="{ span: 10, offset: 10 }"
        :md="{ span: 8, offset: 12 }"
      >
        <div class="login-panel">
          <div class="content">
            <div style="width: 100%; text-align: center; margin-top: 40px">
              <h1>登录</h1>
              <el-input
                v-model="user.username"
                placeholder="用户名"
                class="input"
              >
                <i slot="prefix" class="el-input__icon el-icon-user"></i>
              </el-input>
              <el-input
                v-model="user.password"
                placeholder="密码"
                :show-password="true"
                class="input"
              >
                <i slot="prefix" class="el-input__icon el-icon-key"></i>
              </el-input>

              <div class="login-items">
                <el-checkbox class="checkbox">记住密码</el-checkbox>
                <div>
                  <i slot="prefix" class="el-input__icon el-icon-lock"></i>
                  <el-button type="text" style="color: gray"
                    >忘记密码?</el-button
                  >
                </div>
              </div>
              <div class="btn_link">
                <el-button type="primary" class="btn_submit" @click="login">
                  <strong style="font-size: large">
                    &nbsp;&nbsp;&nbsp;&nbsp;登录&nbsp;&nbsp;&nbsp;&nbsp;
                  </strong>
                </el-button>
                <el-button type="" class="btn_submit"
                  >没有账号？注册一个&nbsp;
                  <i class="el-icon-arrow-right"></i>
                </el-button>
              </div>
            </div>
          </div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      checked: false,
      user: {
        username: "",
        password: "",
      },
    };
  },
  methods: {
    login() {
      this.$axios.post("/user/token", this.user).then((result) => {
        let token = result.data.token;
        localStorage.setItem("token", token);
        this.$router.push("/index");
      },
      (err)=>{
        this.$notify.error({
            title: "登录错误",
            message: "密码或账号错误！",
          });
      });
    },
  },
};
</script>

<style scoped>
.bg {
  display: flex;
  box-sizing: border-box;
  padding: 10px;
  align-items: center;
  position: relative;

  /* Take full size */
  height: 100vh;
  width: 100%;

  /* Background */
  background: url("https://api.aqcoder.cn/random") center center / cover
    no-repeat;
}
.bgOverlay {
  opacity: 1;
  position: absolute;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  transition: opacity 0.3s linear 0s;
  background-image: radial-gradient(
      rgba(0, 0, 0, 0) 0%,
      rgba(0, 0, 0, 0.5) 100%
    ),
    radial-gradient(rgba(255, 255, 255, 0) 33%, rgba(255, 255, 255, 0.3) 166%);
}
.login-panel {
  /* 阴影/低阴影 */
  box-shadow: 0px 3px 8px 0px rgba(0, 0, 0, 0.2);
  padding: 5px;
  border-radius: 4px;
  padding-bottom: 15px;
  background-color: rgba(255, 255, 255, 0.6);
  z-index: 10;
  backdrop-filter: blur(10px);
  box-shadow: rgba(0, 0, 0, 0.3) 0px -2px 0px inset,
    rgba(0, 0, 0, 0.4) 0px 1px 1px;
}
.input {
  width: calc(100% - 50px);
  margin-top: 10px;
}
.center {
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  /* height: 100%; */
}

.box {
  width: 400px;
  border: none;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.2);
  border-radius: 6px 6px 6px 6px;
  overflow: hidden;
}

.content {
  width: 100%;
  /* height: 100%; */
  /* background-color: white; */
  position: relative;
}

.login-items {
  display: flex;
  text-align: left;
  margin: 10px 25px;
  justify-content: space-between;
  align-items: center;
}

.checkbox {
  text-align: left;
}
.btn_submit {
  box-shadow: rgba(0, 0, 0, 0.15) 0px -2px 0px inset,
    rgba(0, 0, 0, 0.4) 0px 1px 1px;
  border: 0;
}
.btn_link {
  width: 100%;
  display: flex;
  justify-content: space-between;
  box-sizing: border-box;
  padding: 0 25px;
  bottom: 20px;
}
.docked-at-corner {
  position: relative;
}
.docked-at-corner__docker {
  position: absolute;
  right: 0;
  top: 0;
  transform: translate(30%, 50%);

  /* Center the content */
  align-items: center;
  display: flex;
  justify-content: center;
  box-shadow: rgba(0, 0, 0, 0.15) 0px -3px 0px inset,
    rgba(0, 0, 0, 0.4) 0px 1px 1px;
  border: 0;
  box-shadow: 0px 3px 8px 0px rgba(0, 0, 0, 0.2);
}
</style>