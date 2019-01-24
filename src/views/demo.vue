<template>
  <div class="root">
    <system-nav></system-nav>
    <div class="div2">
      <SystemListHead></SystemListHead>
      <div class="list-body">
        <SystemListItem v-for="i in 15"></SystemListItem>
      </div>
    </div>
    <div class="div3">
      <div class="content-head">
        <div class="sender-avatar">
          <img src="@/images/avatar.png" alt="发送人头像">
        </div>
        <div class="message-info">
          <div class="sender-name">校社联</div>
          <div class="send-time">2017年8月25日</div>
        </div>
        <div class="right-part">
          <div class="delete">
            <img src="@/images/delete_logo.png" alt="删除">
            删除
          </div>
        </div>
      </div>
      <div class="content-body">
        <div class="message-title">校社联管理系统建好啦</div>
        <div class="message-content">
          <pre>
    各位社长、成员、同学们：
    你们好！现在我们校社联管理系统已经建好，请各位填好自己的相关信息、并加入各自的社团。谢谢合作！
          </pre>
        </div>
      </div>
    </div>
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
  // background-color: lightgreen;
  background-color: $gray;
  flex: 4;
  display: flex;
  flex-direction: column;
}
.content-head {
  background-color: white;
  flex: 0 0 100px;
  display: flex;
  align-items: center;
}
.sender-avatar {
  flex: 0 0 100px;
  img {
    width: 60px;
    height: 60px;
    margin: 20px;
  }
}
.message-info {
  flex: 1;
}
.sender-name {
  font-size: 20px;
  float: left;
  clear: left;
}
.send-time {
  float: left;
  clear: left;
}
.right-part {
  flex: 0 1 150px;
}
.delete {
  float: left;
  cursor: pointer;
  color: $deep;
  img {
    height: 20px;
    width: 20px;
  }
}

.content-body {
  flex: 1;
  display: flex;
  flex-direction: column;
}
.message-title {
  margin-top: 30px;
  font-size: 18px;
}
</style>