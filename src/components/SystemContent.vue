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
        >{{`${new Date(detailContent.releaseTime).toLocaleDateString()}`}}</div>
        <div
          class="send-time"
          v-else-if="detailContent.sendTime"
        >{{`${new Date(detailContent.sendTime).toLocaleDateString()}`}}</div>
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
  <div class="root" v-else-if="contentType === 'orgs'">
    <div class="content-head">
      <div class="sender-avatar">
        <img src="@/images/avatar.png" alt="发送人头像" v-if="detailContent.orgId">
      </div>
      <div class="message-info">
        <div class="sender-name">{{detailContent.adminName}}</div>
        <div class="send-time">{{`${new Date(detailContent.foundTime).toLocaleDateString()}`}}</div>
      </div>
    </div>
    <div class="content-body">
      <div class="message-content">
        <div class="body-left">
          <div class="info-top">
            <div class="admin-name">会长：{{detailContent.adminName}}</div>
            <div class="admin-email">邮箱：{{detailContent.email}}</div>
            <div class="admin-phone">手机：{{detailContent.phone}}</div>
          </div>
          <div class="info-bottom">
            <div class="description-head">社团简介：</div>
            <div class="description-body">{{detailContent.description}}</div>
          </div>
        </div>
        <div class="body-right">
          <v-chart class="echarts" :options="options1"></v-chart>
          <v-chart class="echarts" :options="options2"></v-chart>
        </div>
      </div>
    </div>
  </div>
  <div class="root" v-else-if="contentType === 'audit'">
    <div class="content-head">
      <div class="sender-avatar">
        <img src="@/images/avatar.png" alt="发送人头像" v-if="detailContent.auditId">
      </div>
      <div class="message-info">
        <div class="sender-name">{{detailContent.userName}}</div>
        <div class="send-time">{{`${new Date(detailContent.registerTime).toLocaleDateString()}`}}</div>
      </div>
    </div>
    <div class="content-body">
      <div class="message-content">
        <p>性别：{{detailContent.gender}}</p>
        <p>邮箱：{{detailContent.email}}</p>
        <p>学号：{{detailContent.studentId}}</p>
        <p>学院：{{detailContent.departmentName}}</p>
        <p>专业：{{detailContent.majorName}}</p>
        <p>宿舍：{{detailContent.address}}</p>
        <p>生日：{{(new Date(detailContent.birthday)).toLocaleDateString()}}</p>
        <p>手机：{{detailContent.phone}}</p>
        <button @click="accept" style="margin-right: 20px">通过</button>
        <button @click="refuse">驳回</button>
      </div>
    </div>
  </div>
  <div class="root" v-else-if="contentType === 'annual'">
    <div class="content-head">
      <div class="sender-avatar">
        <img src="@/images/avatar.png" alt="发送人头像" v-if="detailContent.auditMsgId">
      </div>
      <div class="message-info">
        <div class="sender-name">{{detailContent.adminName}}</div>
        <div class="send-time">{{`${new Date(detailContent.submitTime).toLocaleDateString()}`}}</div>
      </div>
    </div>
    <div class="content-body">
      <div class="message-content">
        <p>注册人：{{detailContent.adminName}}</p>
        <p>注册内容：{{detailContent.description}}</p>
      </div>
    </div>
  </div>
</template>

<script>
import ECharts from "vue-echarts";
import "echarts/lib/chart/pie";

export default {
  components: {
    "v-chart": ECharts
  },
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
      },
      options1: {
        color: ["#37a2fe", "#8dcaea", "#327aa7"],
        tooltip: {
          trigger: "item",
          formatter: "{a} <br/>{b}: {c} ({d}%)"
        },
        legend: {
          orient: "vertical",
          x: "left",
          data: ["男", "女"]
        },
        series: [
          {
            name: "男女比例",
            type: "pie",
            radius: ["50%", "70%"],
            avoidLabelOverlap: false,
            label: {
              normal: {
                show: false,
                position: "center"
              },
              emphasis: {
                show: true,
                textStyle: {
                  fontSize: "30",
                  fontWeight: "bold"
                }
              }
            },
            labelLine: {
              normal: {
                show: false
              }
            },
            data: [
              {
                value: 335,
                name: "男"
              },
              {
                value: 310,
                name: "女"
              }
            ]
          }
        ]
      },
      options2: {
        color: ["#37a2fe", "#8dcaea", "#327aa7"],
        tooltip: {
          trigger: "item",
          formatter: "{a} <br/>{b}: {c} ({d}%)"
        },
        legend: {
          orient: "vertical",
          x: "left",
          data: ["大一", "大二", "大三"]
        },
        series: [
          {
            name: "年级比例",
            type: "pie",
            radius: ["50%", "70%"],
            avoidLabelOverlap: false,
            label: {
              normal: {
                show: false,
                position: "center"
              },
              emphasis: {
                show: true,
                textStyle: {
                  fontSize: "30",
                  fontWeight: "bold"
                }
              }
            },
            labelLine: {
              normal: {
                show: false
              }
            },
            data: [
              {
                value: 335,
                name: "大一"
              },
              {
                value: 310,
                name: "大二"
              },
              {
                value: 310,
                name: "大三"
              }
            ]
          }
        ]
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
    },
    accept() {
      this.$emit("accept", this.detailContent.auditId);
    },
    refuse() {
      this.$emit("refuse", this.detailContent.auditId);
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

.echarts {
  height: 200px;
  width: 200px;
  margin: 0 auto;
}
</style>