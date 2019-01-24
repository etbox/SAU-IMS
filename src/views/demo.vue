<template>
  <div class="root">
    <system-nav></system-nav>
    <div class="div2">
      <SystemListHead></SystemListHead>
      <div class="list-body">
        <SystemListItem v-for="i in 15"></SystemListItem>
      </div>
    </div>
    <div class="div3"></div>
  </div>
</template>

<script>
import SystemNav from "@/components/SystemNav.vue";
import SystemList from "@/components/SystemList.vue";
import SystemContent from "@/components/SystemContent.vue";
import SystemListHead from "@/components/SystemListHead.vue";
import SystemListItem from "@/components/SystemListItem.vue";

export default {
  components: {
    SystemNav,
    SystemList,
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
$deep: #5db1f8;
$light: #91caf9;
$gray: #e4e8ec;

.root {
  display: flex;
  height: 100%;
}

.div2 {
  // background-color: khaki;
  flex: 1.5;
  display: flex;
  flex-direction: column;
  box-shadow: 3px 0 3px rgba(0, 0, 0, 0.3);
  z-index: 5;
}
.list-body {
  overflow-y: auto;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
}

.div3 {
  background-color: lightgreen;
  flex: 3;
  display: flex;
}
</style>