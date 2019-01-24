<template>
  <header class="header">
    <div class="relative">
      <router-link class="logo" to="/">
        <img src="@/images/sau.svg" alt="校社联管理系统" class="logo-img" @click="vanish">
        <span @click="vanish">校社联 · 信息管理系统</span>
      </router-link>
      <div class="nav">
        <button class="button button-primary button-rounded" @click="showLogin">登录</button>
        <router-link to="/signup">
          <button class="button button-primary button-rounded" @click="vanish">注册</button>
        </router-link>
        <router-link to="/demo">
          <button class="button button-primary button-rounded" @click="vanish">系统</button>
        </router-link>
      </div>
    </div>
  </header>
</template>

<script>
export default {
  name: "NavBar",
  methods: {
    showLogin() {
      this.$parent.isLogining = !this.$parent.isLogining;
    },
    vanish() {
      this.$parent.isLogining = false;
    }
  }
};
</script>

<style lang="scss" scoped>
@import url("../assets/button.css");
$length: 50px;
.header {
  $padding: 150px;
  background-color: #3ea4fb;
  // flex: 0 $length;
  min-width: 1080px;
  padding: {
    left: $padding;
    right: $padding;
  }
  z-index: 10;
  position: relative;
  box-shadow: 0 3px 3px rgba(0, 0, 0, 0.3);
}

.relative {
  position: relative;
  height: $length;
}

$navpadding: 30px;

.logo {
  display: inline-block;
  font-size: 1.5rem;
  line-height: $length;
  color: white;
  font-weight: 500;
  position: absolute;
  left: $navpadding;
}
.logo-img {
  vertical-align: middle;
  margin-right: 6px;
  width: $length;
  height: $length;
}

.nav {
  position: absolute;
  right: $navpadding;
  top: 5px;
  line-height: 40px;
}

.button {
  line-height: 30px;
  height: 30px;
  padding: 0 20px;
  margin: 0 15px;
}

.button-primary,
.button-primary:active {
  background-color: #367ba5;
}

.button-primary:hover {
  background-color: #326171;
}

.button-rounded {
  border-radius: 10px;
}
</style>
