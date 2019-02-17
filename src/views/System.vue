<template>
  <div class="root">
    <system-nav></system-nav>
    <router-view></router-view>
  </div>
</template>

<script>
import SystemNav from "@/components/SystemNav.vue";
import SystemContent from "@/components/SystemContent.vue";
import SystemListHead from "@/components/SystemListHead.vue";
import SystemListItem from "@/components/SystemListItem.vue";

export default {
  components: {
    SystemNav,
    SystemContent,
    SystemListHead,
    SystemListItem
  },
  data() {
    return {};
  }
};
</script>

<style lang="scss" scoped>
.root {
  display: flex;
  height: 100%;
}
</style>