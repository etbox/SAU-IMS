<template>
  <div class="root" v-if="contentType === 'show'">
    <div class="content-head">
      <div class="sender-avatar">
        <img src="@/images/avatar.png" alt="发送人头像" v-if="detailContent.messageId">
      </div>
      <div class="message-info">
        <div class="sender-name">{{detailContent.releaseName}}</div>
        <div
          class="send-time"
          v-if="detailContent.releaseTime"
        >{{`${new Date(detailContent.releaseTime).getFullYear()}年${new Date(detailContent.releaseTime).getMonth()+1}月${new Date(detailContent.releaseTime).getDate()}日`}}</div>
        <div
          class="send-time"
          v-else-if="detailContent.sendTime"
        >{{`${new Date(detailContent.sendTime).getFullYear()}年${new Date(detailContent.sendTime).getMonth()+1}月${new Date(detailContent.sendTime).getDate()}日`}}</div>
      </div>
      <div class="right-part">
        <!-- <div class="delete" v-show="detailContent.releaseTime">
          <img src="@/images/delete_logo.png" alt="删除" @click="clear">
          删除
        </div>-->
      </div>
    </div>
    <div class="content-body">
      <div class="message-title">{{detailContent.messageTitle}}</div>
      <div class="message-content">
        <pre>{{detailContent.messageContent}}</pre>
      </div>
    </div>
  </div>
  <div class="root" v-else-if="contentType === 'edit'">
    <div class="content-head">
      <div class="buttons">
        <div class="reset">
          <img src="@/images/delete.svg" alt="清空">
          清空
        </div>
        <div class="send" @click="send">
          <img src="@/images/send_logo.svg" alt="发送">
          发送
        </div>
      </div>
    </div>
    <div class="content-body">
      <input type="text" placeholder="收件人">
      <input type="text" placeholder="标题" v-model="msgBody.messageTitle">
      <textarea name id cols="30" rows="10" placeholder="内容" v-model="msgBody.messageContent"></textarea>
    </div>
  </div>
  <div class="root" v-else-if="contentType === 'audit'">
    <div class="content-head">123</div>
  </div>
</template>

<script>
export default {
  props: {
    detailContent: Object,
    contentType: String
  },
  data() {
    return {
      msgBody: {
        messageTitle: "精英社的活动6,全体通知",
        messageContent: "在星期天有一个精英社的活动6，全体通知",
        sendTime: 1523266240332,
        publishedObject: ""
      }
    };
  },
  methods: {
    send() {
      this.msgBody.sendTime = new Date();
      this.$emit("send", this.msgBody);
    },
    clear() {
      this.$emit("clear");
    }
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

// TODO: 没写edit的css
// .buttons {
//   // align-self: flex-end;
// }
.reset,
.send {
  float: left;
}
.reset:hover,
.send:hover {
  background-color: $light;
  cursor: pointer;
}
</style>