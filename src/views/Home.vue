<template>
  <div class="home">
    <img alt="Vue logo" src="../assets/logo.png">
    <HelloWorld msg="Welcome to Your Vue.js App"/>
    <footer>{{copyright}}</footer>
  </div>
</template>

<script>
// @ is an alias to /src
import HelloWorld from "@/components/HelloWorld.vue";

const copyright = "2019";

export default {
  name: "home",
  components: {
    HelloWorld
  },
  data: function() {
    return {
      copyright: `${new Date().toString().slice(11, 15)} @ 校社联管理系统`
    };
  }
};
</script>

<style lang="scss" scoped>
$height: 75px;
footer {
  color: white;
  background-color: gray;
  height: $height;
  width: 100%;
  line-height: $height;
}
</style>
