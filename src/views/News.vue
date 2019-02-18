<template>
  <div class="root">
    <div class="list-panal">
      <SystemListHead v-bind="{isAdd, isDelete}"></SystemListHead>
      <div class="list-body">
        <SystemListItem v-for="item in items" :key="item.messageId" v-bind="{type, item}"></SystemListItem>
        <!-- <SystemListItem v-for="item in items" :key="item" v-bind="item"></SystemListItem> -->
      </div>
    </div>
    <SystemContent></SystemContent>
  </div>
</template>

<script>
import SystemContent from "@/components/SystemContent.vue";
import SystemListHead from "@/components/SystemListHead.vue";
import SystemListItem from "@/components/SystemListItem.vue";
import axios from "axios";

export default {
  components: {
    SystemContent,
    SystemListHead,
    SystemListItem
  },
  data() {
    return {
      isAdd: false,
      isDelete: true,
      type: "news",
      items: []
    };
  },
  created() {
    let offset = 1;
    const limit = 10,
      url = `/msg`;

    axios
      .get(url, {
        offset: offset,
        limit: limit
      })
      .then(res => {
        if (res.data.code) {
          alert(res.data.msg);
        } else {
          const arr = res.data.data;
          for (let i = 0; i < arr.length && this.items.length < 10; i++) {
            this.items.push(arr[i]);
          }
          console.log(this.items);
          // offset++;
        }
      })
      .catch(function(error) {
        console.log(error);
      });
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