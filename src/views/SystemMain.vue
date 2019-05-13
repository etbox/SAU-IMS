<template>
  <div class="root">
    <div class="list-panal">
      <SystemListHead
        v-bind="{isAdd:options.isAdd, isDelete:options.isDelete}"
        v-on:refresh="refresh"
        v-on:search="search"
        v-on:clear="clear"
        v-on:add="add"
      />
      <div class="list-body">
        <SystemListItem
          v-for="item in items"
          :key="item.messageId"
          v-bind="{options, item}"
          v-on:show-msg-details="showMsgDetails"
          v-on:show-audit-details="showAuditDetails"
          v-on:show-annual-details="showAnnualDetails"
          v-on:show-orgs-details="showOrgsDetails"
        />
      </div>
    </div>
    <SystemContent
      v-bind="{detailContent, contentType}"
      v-on:send="send"
      v-on:clear="clear"
      @accept="accept"
      @refuse="refuse"
    />
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
    options: Object
  },
  data() {
    return {
      isAdd: false,
      isDelete: true,
      items: [],
      offset: 1,
      detailContent: {},
      contentType: "show"
    };
  },
  methods: {
    refresh() {
      const limit = 10,
        url = `${
          this.options.module === "news" ? "" : this.options.identity
        }/msg${this.options.module === "news" ? "" : "/old"}`;

      // console.log(`${url} refresh`);
      axios
        .get(url, {
          offset: this.offset,
          limit: limit
        })
        .then(response => {
          if (response.data.code) {
            alert(response.data.msg);
          } else {
            this.items = response.data.data;
          }
        })
        .catch(function(error) {
          console.log(error);
        });
    },
    search(keyword) {
      const url = `${
          this.options.module === "news" ? "" : this.options.identity
        }/msg${this.options.module === "news" ? "" : "/old"}/search`,
        limit = 10;
      let params = {
        findContent: keyword,
        offset: this.offset,
        limit
      };

      // console.log(`${url} search`);
      axios
        .get(url, { params })
        .then(response => {
          this.items = response.data.data;
        })
        .catch(error => console.log(error));
    },
    showMsgDetails(messageId) {
      this.contentType = "show";

      const url = `${
        this.options.module === "news" ? "" : this.options.identity
      }/msg${this.options.module === "news" ? "" : "/old"}/${messageId}`;

      // console.log(`${url} show details`);
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
    showAuditDetails(auditMsgId) {
      this.contentType = "audit";

      axios
        .get(`/club/audit/join/${auditMsgId}`)
        .then(response => {
          if (response.data.code) {
            alert(response.data.msg);
          } else {
            this.detailContent = response.data.data;
          }
        })
        .catch(error => console.log(error));
    },
    showAnnualDetails(auditMsgId) {
      this.contentType = "annual";

      const url =
        this.$store.getters["checkLogin"] === 1
          ? `/club/ann/${auditMsgId}`
          : `/sau/audit/ann/${auditMsgId}`;

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
    showOrgsDetails(orgId) {
      this.contentType = "orgs";

      axios
        .get(`/club/other/${orgId}`)
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
      const url = `${
          this.options.module === "news" ? "" : this.options.identity
        }/msg${this.options.module === "news" ? "" : "/old"}`,
        params = {
          _method: "delete",
          deleteMsgIds: [...this.$store.state.mCheck.checkedId].join()
        };

      console.log(`删除路径：${url} 消息id：${params.deleteMsgIds}`);
      // axios
      //   .delete(url, params)
      axios({
        method: "delete",
        url,
        data: params
      })
        .then(response => {
          if (response.data.code) {
            alert(response.data.msg);
          } else {
            console.log("已删除");
            this.refresh();
          }
        })
        .catch(error => console.log(error));

      this.$store.dispatch("clearCheckeds");
    },
    add() {
      this.contentType = "edit";
    },
    send(msgBody) {
      this.contentType = "show";
      this.detailContent = msgBody;

      const url = `${this.options.identity}/msg/new/all`;

      axios
        .post(url, msgBody)
        .then(response => {
          if (response.data.code) {
            alert(response.data.msg);
          } else {
            console.log("发送信息成功");
          }
        })
        .catch(error => console.log(error));
    },
    accept(auditId) {
      axios.put(`/club/audit/join/${auditId}`, {
        auditState: 1,
        auditResult: ""
      });
    },
    refuse(auditId) {
      axios.put(`/club/audit/join/${auditId}`, {
        auditState: 0,
        auditResult: ""
      });
    }
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
  watch: {
    "options.module"() {
      this.contentType = "show";

      // console.log("options changed!");
      // console.log(this.options.module);
      const limit = 10;
      let url = ``;

      switch (this.options.module) {
        case "news":
          url = `/msg`;
          break;
        case "orgs":
          url = `/club/other`;
          break;
        case "messages":
          url = `${this.options.identity}/msg/old`;
          break;
        case "audit":
          url = `/club/audit/join`;
          break;
        case "annual":
          url =
            this.$store.getters["checkLogin"] === 1
              ? `/club/ann`
              : `/sau/audit/ann`;
          break;
      }

      // TODO: 此处访问未完成的功能会报错
      axios
        .get(url, {
          offset: this.offset,
          limit: limit
        })
        .then(response => {
          if (response.data.code) {
            alert(response.data.msg);
          } else {
            this.items = response.data.data;
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