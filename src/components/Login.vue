<template>
  <div class="overlayer" @click.self="vanish">
    <div class="login-container">
      <div class="login-head">
        <span>登录</span>
      </div>
      <div class="login-body">
        <!-- 上方已布好局 -->
        <div class="panel-input">
          <input type="text" name="username" id="username" placeholder="邮箱 | 用户名">
          <input type="password" name="password" id="password" placeholder="密码">
          <div class="captcha-container">
            <input type="text" name="captcha" id="captcha" placeholder="验证码">
            <img src="@/images/yzm.jpg" alt="验证码" class="captcha_img">
          </div>
          <div class="option-container">
            <input type="checkbox" name="saveName" id="saveName">
            <span>记住账号</span>
            <router-link to="/forgot" class="forgotPW">忘记密码</router-link>
          </div>
          <button class="button button-primary button-rounded button-login">登录</button>
        </div>
        <!-- 左右分界线 -->
        <div class="panel-info"></div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Login",
  methods: {
    vanish() {
      this.$parent.isLogining = false;
    }
  }
};
</script>

<style lang="scss" scoped>
@import url("../assets/button.css");
$blue: #3fb2fa;
.overlayer {
  background-color: rgba(0, 0, 0, 0.5);
  position: fixed;
  width: 100%;
  height: 100%;
  top: 0;
  left: 0;
  z-index: 2;
  display: flex;
  justify-content: space-around;
}
.login-container {
  background-color: #fff;
  width: 580px;
  min-height: 250px;
  height: 250px;
  max-height: 350px;
  margin-top: 150px;
  display: flex;
  flex-direction: column;
}
.login-head {
  background-color: $blue;
  color: #fff;
  font-size: 24px;
  flex: 0 50px;
  display: flex;
  align-items: center;
  justify-content: space-around;
}

// 以下是登录面板

.login-body {
  display: flex;
  height: 100%;
}
.panel-input {
  background-color: pink;
  flex: 3;
  // display: flex;
  // flex-direction: column;
}

.panel-info {
  background-color: orange;
  flex: 2;
}
</style>
