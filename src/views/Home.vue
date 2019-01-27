<template>
  <div class="home">
    <Banner/>
    <!-- <img alt="Vue logo" src="../assets/logo.png">
    <HelloWorld msg="Welcome to Your Vue.js App"/>-->
    <ItemList name="社团"/>
    <Copyright/>
  </div>
</template>

<script>
// @ is an alias to /src
// import HelloWorld from "@/components/HelloWorld.vue";
import Banner from "@/components/Banner.vue";
import ItemList from "@/components/ItemList.vue";
import Copyright from "@/components/Copyright.vue";

export default {
  name: "home",
  components: {
    // HelloWorld,
    Banner,
    ItemList,
    Copyright
  }
};
</script>

<style lang="scss" scoped>
.home {
  height: 100%;
}
</style>
