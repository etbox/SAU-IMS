<template>
  <div class="home">
    <!-- <Login/> -->
    <Banner/>
    <!-- <img alt="Vue logo" src="../assets/logo.png">
    <HelloWorld msg="Welcome to Your Vue.js App"/>-->
    <ItemList name="社团"/>
    <footer>{{copyright}}</footer>
  </div>
</template>

<script>
// @ is an alias to /src
// import HelloWorld from "@/components/HelloWorld.vue";
import Banner from "@/components/Banner.vue";
import ItemList from "@/components/ItemList.vue";
// import Login from "@/components/Login.vue";

export default {
  name: "home",
  components: {
    // HelloWorld,
    Banner,
    ItemList
    // Login
  },
  data: function() {
    return {
      copyright: `${new Date().toString().slice(11, 15)} @ 校社联管理系统`
    };
  }
};
</script>

<style lang="scss" scoped>
.home {
  height: 100%;
}
footer {
  $height: 75px;
  color: white;
  background-color: gray;
  height: $height;
  width: 100%;
  line-height: $height;
}
</style>
