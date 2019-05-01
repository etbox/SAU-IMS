<template>
  <div class="home">
    <Banner/>
    <IndexClubList name="社团"/>
    <Copyright/>
  </div>
</template>

<script>
// @ is an alias to /src
import Banner from "@/components/Index/Banner.vue";
import IndexClubList from "@/components/Index/IndexClubList.vue";
import Copyright from "@/components/Copyright.vue";

export default {
  name: "home",
  components: {
    Banner,
    IndexClubList,
    Copyright
  }
};
</script>

<style lang="scss" scoped>
.home {
  height: 100%;
}
</style>
