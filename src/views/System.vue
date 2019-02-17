<template>
  <div class="root">
    <system-nav></system-nav>
    <div class="div2">
      <SystemListHead></SystemListHead>
      <div class="list-body">
        <SystemListItem v-for="i in 15" :key="i"></SystemListItem>
      </div>
    </div>
    <SystemContent></SystemContent>
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

// .div3 {
//   // background-color: lightgreen;
//   background-color: $gray;
//   flex: 4;
//   display: flex;
//   flex-direction: column;
// }
// .content-head {
//   background-color: white;
//   flex: 0 0 100px;
//   display: flex;
//   align-items: center;
// }
// .sender-avatar {
//   flex: 0 0 100px;
//   img {
//     width: 60px;
//     height: 60px;
//     margin: 20px;
//   }
// }
// .message-info {
//   flex: 1;
// }
// .sender-name {
//   font-size: 20px;
//   float: left;
//   clear: left;
// }
// .send-time {
//   float: left;
//   clear: left;
// }
// .right-part {
//   flex: 0 1 150px;
// }
// .delete {
//   float: left;
//   cursor: pointer;
//   color: $deep;
//   img {
//     height: 20px;
//     width: 20px;
//   }
// }

// .content-body {
//   flex: 1;
//   display: flex;
//   flex-direction: column;
// }
// .message-title {
//   margin-top: 30px;
//   font-size: 18px;
// }
</style>