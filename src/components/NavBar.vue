<template>
  <header class="header">
    <div class="relative">
      <a class="logo" href="/">
        <img src="@/images/sau.svg" alt="校社联管理系统" class="logo-img">
        <span>校社联 · 信息管理系统</span>
      </a>
      <div class="nav">
        <button class="button button-primary button-rounded" @click="showLogin">登录</button>
        <button class="button button-primary button-rounded">注册</button>
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
  height: $length;
  min-width: 1080px;
  padding: {
    left: $padding;
    right: $padding;
  }
  z-index: 10;
  position: relative;
}

.relative {
  position: relative;
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
.button-primary-flat {
  background-color: #327aa7;
  border-color: #327aa7;
  color: #fff;
}
.button-primary:visited,
.button-primary-flat:visited {
  color: #fff;
}
.button-primary:hover,
.button-primary:focus,
.button-primary-flat:hover,
.button-primary-flat:focus {
  background-color: #32617e;
  border-color: #32617e;
  color: #fff;
}
.button-primary:active,
.button-primary.active,
.button-primary.is-active,
.button-primary-flat:active,
.button-primary-flat.active,
.button-primary-flat.is-active {
  background-color: #2798eb;
  border-color: #2798eb;
  color: #0880d7;
}

.button-rounded {
  border-radius: 10px;
}
</style>
