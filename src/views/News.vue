<template>
  <div class="root">
    <div class="list-panal">
      <SystemListHead></SystemListHead>
      <div class="list-body">
        <SystemListItem v-for="i in 15" :key="i"></SystemListItem>
      </div>
    </div>
    <SystemContent></SystemContent>
  </div>
</template>

<script>
import SystemContent from "@/components/SystemContent.vue";
import SystemListHead from "@/components/SystemListHead.vue";
import SystemListItem from "@/components/SystemListItem.vue";

export default {
  components: {
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
  flex: 6;
}
.list-panal {
  // background-color: khaki;
  flex: 2.5;
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
</style>