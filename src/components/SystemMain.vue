<template>
  <div class="root">
    <div class="list-panal">
      <SystemListHead
        v-bind="{isAdd, isDelete}"
        v-on:refresh="refresh"
        v-on:search="search"
        v-on:clear="clear"
        v-on:add="add"
      />
      <div class="list-body">
        <SystemListItem
          v-for="item in items"
          :key="item.messageId"
          v-bind="{type, item}"
          v-on:show-details="showDetails"
        />
      </div>
    </div>
    <SystemContent v-bind="{detailContent}"/>
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
  props: {
    type: String
  },
  data() {
    return {
      isAdd: false,
      isDelete: true,
      items: [],
      offset: 1,
      detailContent: {}
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
        }
      })
      .catch(error => console.log(error));
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
    },
    showDetails(messageId) {
      const url = `/msg/${messageId}`;

      axios
        .get(url)
        .then(response => {
          if (response.data.code) {
            alert(response.data.msg);
          } else {
            this.detailContent = response.data.data;
          }
        })
        .catch(error => console.log(error));
    },
    clear() {
      const url = "/msg",
        params = { _method: "delete" };
      axios
        .delete()
        .then(response => {
          console.log(response);
        })
        .catch(error => console.log(error));
      store.dispatch("clearCheckeds");
    },
    add() {}
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