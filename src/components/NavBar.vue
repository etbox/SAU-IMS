<template>
  <header>
    <div class="navBar-core">
      <router-link class="logo" to="/">
        <img src="@/images/sau.svg" alt="校社联管理系统" class="logo-img" @click="vanish">
        <span @click="vanish">校社联 · 信息管理系统</span>
      </router-link>
      <nav>
        <div class="unlogin" v-if="this.$store.getters['checkLogin'] < 0">
          <button class="button button-primary button-rounded" @click="showLogin">登录</button>
          <router-link to="/signup">
            <button class="button button-primary button-rounded" @click="vanish">注册</button>
          </router-link>
        </div>
        <div class="logined" v-else>
          <div class="user-info">
            <span>
              <button class="button button-primary button-rounded logout" @click="logout">登出</button>
            </span>
            <!-- <div class="user--avatar"> -->
            <router-link to="/system">
              <button class="button button-primary button-rounded" @click="vanish">系统</button>
            </router-link>
            <!-- </div> -->
            <div class="user--realname">{{realName}}</div>
          </div>
          <div class="submenu"></div>
        </div>
      </nav>
    </div>
  </header>
</template>

<script>
import axios from "axios";
import Cookies from "js-cookie";

import getUserInfo from "../util/getUserInfo.js";

export default {
  name: "NavBar",
  methods: {
    showLogin() {
      this.$parent.isLogining = !this.$parent.isLogining;
    },
    vanish() {
      this.$parent.isLogining = false;
    },
    logout() {
      axios
        .get("/logout")
        .then(res => {
          if (res.data.code === 2 && res.data.msg.search(/JDBC/) !== -1) {
            alert("数据库正在重新连接，请重试");
          } else if (res.data.code !== 0) {
            alert(res.data.msg);
          } else {
            Cookies.set("priority", -1);
            this.$store.dispatch("logout");

            alert("登出成功");
            this.$router.push("/");
          }
        })
        .catch(function(error) {
          console.log(error);
        });
    }
  },
  created() {
    const priority = Cookies.get("priority"),
      realName = Cookies.get("realName");

    if (priority > 0) {
      this.$store.dispatch("login", priority);
      this.$store.dispatch("modifyRealName", realName);
    }
  },
  computed: {
    realName() {
      return this.$store.getters["getRealName"];
    }
  }
};
</script>

<style lang="scss" scoped>
@import url("../assets/button.css");
$navBar-height: 50px;
header {
  background-color: #3ea4fb;
  width: 100%;
  min-width: 1080px;
  // 产生层叠上下文
  position: relative;
  z-index: 10;
  box-shadow: 0 3px 3px rgba(0, 0, 0, 0.3);
}
.navBar-core {
  // 元素撑开容器
  height: $navBar-height;
  width: 980px;
  margin: 0 auto;
}

.logo {
  font-size: 1.5rem;
  line-height: $navBar-height;
  color: white;
  font-weight: 500;
  float: left;
}
.logo-img {
  vertical-align: middle;
  margin-right: 6px;
  width: $navBar-height;
  height: $navBar-height;
}

.unlogin,
.logined {
  float: right;
  line-height: $navBar-height;
}

.button {
  line-height: 30px;
  height: 30px;
  padding: 0 20px;
  margin-left: 20px;
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

.user-info > * {
  float: right;
  // display: inline-block;
}
.user--realname {
  margin: 0 15px;
}
</style>
