<template>
  <div class="root">
    <div class="content-head">
      <div class="sender-avatar">
        <img src="@/images/avatar.png" alt="发送人头像" v-show="detailContent.releaseTime">
      </div>
      <div class="message-info">
        <div class="sender-name">{{detailContent.releaseName}}</div>
        <div
          class="send-time"
          v-show="detailContent.releaseTime"
        >{{`${new Date(detailContent.releaseTime).getFullYear()}年${new Date(detailContent.releaseTime).getMonth()+1}月${new Date(detailContent.releaseTime).getDate()}日`}}</div>
      </div>
      <div class="right-part">
        <div class="delete" v-show="detailContent.releaseTime">
          <img src="@/images/delete_logo.png" alt="删除">
          删除
        </div>
      </div>
    </div>
    <div class="content-body">
      <div class="message-title">{{detailContent.messageTitle}}</div>
      <div class="message-content">
        <pre>{{detailContent.messageContent}}</pre>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    detailContent: Object
  }
};
</script>

<style lang="scss" scoped>
$deep: #5db1f8;
$light: #91caf9;
$gray: #e4e8ec;
.root {
  // background-color: lightgreen;
  background-color: $gray;
  flex: 3;
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