<template>
  <div class="choices">
    <section
      class="list-item"
      v-if="options.module === 'news' || options.module === 'messages'"
      @click="showMsgDetails"
    >
      <div
        class="read-flag"
        :class="{'new-flag':!(item.readFlag)}"
        v-if="options.module === 'news'"
      ></div>
      <div class="list-item-left">
        <!-- 由于异步传输, undefined 没有 slice 方法 -->
        <div class="message-title">{{`${String(item.messageTitle).slice(0,8)}...`}}</div>
        <div class="sender-name" v-if="options.module === 'news'">{{item.releaseName}}</div>
      </div>
      <div class="list-item-right">
        <input type="checkbox" :value="item.messageId" @input="check">
        <div
          class="send-time"
          v-if="options.module === 'news'"
        >{{`${new Date(item.releaseTime).getFullYear()}/${new Date(item.releaseTime).getMonth()+1}/${new Date(item.releaseTime).getDate()}`}}</div>
        <div
          class="send-time"
          v-else-if="options.module === 'messages'"
        >{{`${new Date(item.sendTime).getFullYear()}/${new Date(item.sendTime).getMonth()+1}/${new Date(item.sendTime).getDate()}`}}</div>
      </div>
    </section>

    <section class="list-item" v-if="options.module === 'orgs'" @click="showOrgsDetails">
      <div class="list-item-left">
        <div class="message-title">{{`${item.orgName}`}}</div>
        <!-- <div class="sender-name">{{item.orgName}}</div> -->
      </div>
      <div class="list-item-right">
        <input type="checkbox" :value="item.messageId" @input="check">
        <!-- <div class="send-time"></div> -->
      </div>
    </section>

    <section class="list-item" v-if="options.module === 'audit'" @click="showAuditDetails">
      <div class="list-item-left">
        <div class="message-title">{{`${item.auditTitle}`}}</div>
        <div class="sender-name">{{item.userName}}</div>
      </div>
      <div class="list-item-right">
        <input type="checkbox" :value="item.messageId" @input="check">
        <div class="send-time">{{(new Date(item.registerTime)).toLocaleDateString()}}</div>
      </div>
    </section>

    <section class="list-item" v-if="options.module === 'annual'" @click="showAnnualDetails">
      <div class="list-item-left">
        <div class="message-title">{{`${item.registerTitle}`}}</div>
        <div class="sender-name">{{item.registerName}}</div>
      </div>
      <div class="list-item-right">
        <input type="checkbox" :value="item.messageId" @input="check">
        <div class="send-time">{{(new Date(item.registerTime)).toLocaleDateString()}}</div>
      </div>
    </section>
  </div>
</template>

<script>
export default {
  props: {
    options: Object,
    item: Object
  },
  data() {
    return {};
  },
  methods: {
    check(event) {
      // console.log(event.target.value);
      const value = event.target.value;
      if (this.$store.state.mCheck.checkedId.has(value)) {
        this.$store.dispatch("deleteChecked", value);
      } else {
        this.$store.dispatch("addChecked", value);
      }
      console.log(
        `删除队列：${[...this.$store.state.mCheck.checkedId].join()}`
      );
    },
    showMsgDetails() {
      this.$emit("show-msg-details", this.item.messageId);
    },
    showAuditDetails() {
      this.$emit("show-audit-details", this.item.auditMsgId);
    },
    showAnnualDetails() {
      this.$emit("show-annual-details", this.item.auditMsgId);
    },
    showOrgsDetails() {
      this.$emit("show-orgs-details", this.item.orgId);
    }
  }
};
</script>

<style lang="scss" scoped>
$deep: #5db1f8;
$light: #91caf9;

.list-item {
  cursor: pointer;
  background-color: white;
  flex: 0 0 50px;
  margin-top: 10px;
  display: flex;
  transition: all 0.3s ease-in-out;
  padding: 5px 0;
}
.list-item:hover {
  background-color: $light;
}

.read-flag {
  flex: 0 10px;
}
.new-flag {
  background-color: $deep;
}

.list-item-left {
  flex: 1;
}
.message-title {
  font-size: 18px;
  float: left;
  padding-left: 1rem;
}
.sender-name {
  font-size: 14px;
  float: left;
  padding-left: 1rem;
  clear: left;
}

.list-item-right {
  flex: 0 100px;
}
.send-time {
  font-size: 14px;
  padding-top: 0.2rem;
}
</style>