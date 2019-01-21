<template>
  <div id="app">
    <NavBar/>
    <Login v-show="isLogining"/>
    <!-- <div id="nav">
      <router-link to="/">Home</router-link>|
      <router-link to="/about">About</router-link>
    </div>-->
    <router-view/>
  </div>
</template>

<script>
import NavBar from "@/components/NavBar.vue";
import Login from "@/components/Login.vue";

export default {
  name: "home",
  components: {
    NavBar,
    Login
  },
  data() {
    return {
      isLogining: true // 登录框显示flag,默认不显示
    };
  }
};
</script>

<style lang="scss">
$blue: #3fb2fa;
body {
  height: 100vh;
  margin: 0;
  padding: 0;
  background-color: #e4e8ec;
}

a {
  text-decoration: none;
}

#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>