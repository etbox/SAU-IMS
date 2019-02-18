<template>
  <div class="root">
    <div class="list-panal">
      <SystemListHead v-bind="{isAdd, isDelete}" v-on:refresh="refresh" v-on:search="search"></SystemListHead>
      <div class="list-body">
        <SystemListItem v-for="item in items" :key="item.messageId" v-bind="{type, item}"></SystemListItem>
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
      items: [],
      offset: 1
    };
  },
  created() {
    const limit = 10,
      url = `/msg`;

    axios
      .get(url, {
        offset: this.offset,
        limit: limit
      })
      .then(response => {
        if (response.data.code) {
          alert(response.data.msg);
        } else {
          const arr = response.data.data;
          for (let i = 0; i < arr.length && this.items.length < 10; i++) {
            this.items.push(arr[i]);
          }
          // console.log(this.items);
          // this.offset++;
        }
      })
      .catch(function(error) {
        console.log(error);
      });
  },
  methods: {
    refresh() {
      const limit = 10,
        url = `/msg`;

      axios
        .get(url, {
          offset: this.offset,
          limit: limit
        })
        .then(response => {
          if (response.data.code) {
            alert(response.data.msg);
          } else {
            this.items = [];
            console.log("news refresh");

            const arr = response.data.data;
            for (let i = 0; i < arr.length; i++) {
              this.items.push(arr[i]);
            }
            // console.log(this.items);
            // this.offset++;
          }
        })
        .catch(function(error) {
          console.log(error);
        });
    },
    search(keyword) {
      const url = "/msg/search",
        limit = 10;
      let params = {
        findContent: keyword,
        offset: this.offset,
        limit
      };

      axios
        .get(url, { params })
        .then(response => {
          this.items = [];
          console.log("news search");

          const arr = response.data.data;
          for (let i = 0; i < arr.length; i++) {
            this.items.push(arr[i]);
          }
        })
        .catch(error => console.log(error));
    }
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