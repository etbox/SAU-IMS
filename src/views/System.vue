<template>
  <div class="root">
    <system-nav></system-nav>
    <system-list></system-list>
    <system-content></system-content>
  </div>
</template>

<script>
import SystemNav from "@/components/SystemNav.vue";
import SystemList from "@/components/SystemList.vue";
import SystemContent from "@/components/SystemContent.vue";

export default {
  components: {
    SystemNav,
    SystemList,
    SystemContent
  }
};
</script>

<style lang="scss" scoped>
.root {
  display: flex;
  height: 100%;
}
</style>
