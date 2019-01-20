<template>
  <header>
    <div class="relative">
      <a id="logo" href="/">
        <img src="@/images/sau_logo.svg" alt="校社联管理系统">
        <span>校社联 · 信息管理系统</span>
      </a>
      <div id="nav">
        <button>登录</button>
        <button>注册</button>
      </div>
    </div>
  </header>
</template>

<script>
export default {
  name: "NavBar"
};
</script>

<style lang="scss" scoped>
header {
  $padding: 150px;
  background-color: #3ea4fb;
  height: 50px;
  // width: 100vw;
  min-width: 1080px;
  padding: {
    left: $padding;
    right: $padding;
  }
  position: relative;
  top: 0;
  z-index: 100;
}

.relative {
  position: relative;
}

#logo {
  $length: 50px;
  display: inline-block;
  font-size: 1.5em;
  line-height: $length;
  color: white;
  font-weight: 500;
  img {
    vertical-align: middle;
    margin-right: 6px;
    width: $length;
    height: $length;
  }
  position: absolute;
  left: 0;
}

#nav {
  list-style-type: none;
  margin: 0;
  padding: 0;
  position: absolute;
  right: 30px;
  top: 10px;
  height: 40px;
  line-height: 40px;
}
</style>
