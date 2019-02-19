<template>
  <div class="root">
    <system-nav v-bind="{options}"></system-nav>
    <router-view v-bind="{options}"></router-view>
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
    return {
      options: {
        module: "news",
        isAdd: false,
        isDelete: true,
        identity: ""
      },
      identity: ["/memeber", "/club", "/sau"]
    };
  },
  created() {
    this.options.identity = this.identity[this.$store.getters.checkLogin];
  },
  watch: {
    $route(to, from) {
      this.options.module = to.path.split("/")[2];

      switch (this.options.module) {
        case "news":
        case "orgs":
        case "audit":
          this.options.isAdd = false;
          break;
        case "messages":
        case "annual":
          this.options.isAdd = true;
          break;
      }

      switch (this.options.module) {
        case "news":
        case "audit":
        case "messages":
        case "annual":
          this.options.isDelete = true;
          break;
        case "orgs":
          this.options.isDelete = false;
          break;
      }
    }
  }
};
</script>

<style lang="scss" scoped>
.root {
  display: flex;
  height: 100%;
}
</style>