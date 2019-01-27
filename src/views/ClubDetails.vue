<template>
  <div class="root">
    <div class="club-head">
      <div class="head-left">
        <div class="club-avatar">
          <img src="@/images/avatar.png" alt="社团头像">
        </div>
        <div class="club-title">
          <div class="club-name">摄影协会</div>
          <div class="description">定格光影，留住光阴</div>
        </div>
      </div>
      <div class="head-right">
        <div class="found-time">2002年2月22日成立</div>
      </div>
    </div>
    <div class="club-body">
      <div class="body-left">
        <div class="info-top">
          <div class="admin-name">会长：买买买</div>
          <div class="admin-email">邮箱：789@jkl.com</div>
          <div class="admin-phone">手机：12312341234</div>
        </div>
        <div class="info-bottom">
          <div class="description-head">社团简介：</div>
          <div
            class="description-body"
          >摄影协会是由摄影爱好者组成的非营利性社团，包容接纳擅长各门类摄影、持各种摄影观念的人员，让不同的思想得以碰撞交融。让摄影爱好者能够共同学习，互相交流，在学习和交流中提高艺术修养和摄影技术。</div>
        </div>
      </div>
      <div class="body-right">
        <v-chart class="echarts" :options="options1"></v-chart>
        <v-chart class="echarts" :options="options2"></v-chart>
      </div>
    </div>
    <Copyright/>
  </div>
</template>

<script>
import Copyright from "@/components/Copyright.vue";
import ECharts from "vue-echarts";
import "echarts/lib/chart/pie";

export default {
  components: {
    Copyright,
    "v-chart": ECharts
  },
  data() {
    return {
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
  }
};
</script>

<style lang="scss" scoped>
$gray: #e4e8ec;
.root {
  display: flex;
  flex-direction: column;
  height: 100%;
}

.club-head {
  // background-color: pink;
  background-color: white;
  width: 980px;
  margin: 10px auto;
  border: 1px solid rgba(0, 0, 0, 0.1);
  border-radius: 20px;

  flex: 1 0;
  display: flex;
  justify-content: space-between;
}

.head-left {
  display: flex;
}
.club-avatar {
  display: flex;
  align-items: center;
  margin: 0 50px;
  img {
    width: 100px;
    height: 100px;
  }
}
.club-title {
  display: flex;
  flex-direction: column;
}
.club-name {
  font-size: 20px;
  margin: {
    top: 50px;
    bottom: 5px;
  }
}

.head-right {
  display: flex;
  align-items: center;
}
.found-time {
  margin-right: 50px;
}

// 下半部分
.club-body {
  // background-color: skyblue;
  background-color: white;
  width: 980px;
  margin: 10px auto;
  border: 1px solid rgba(0, 0, 0, 0.1);
  border-radius: 20px;

  flex: 3 0;
  display: flex;
}
.body-left {
  flex: 2 0;
  display: flex;
  flex-direction: column;
}

.info-top {
  flex: 1 0;
  display: flex;
  justify-content: space-around;
  align-items: center;
}
.info-bottom {
  flex: 3 0;
  display: flex;
  flex-direction: column;

  text-align: left;
  padding: 0 50px;
}
.description-head {
  margin-bottom: 5px;
}
.description-body {
  text-indent: 2rem;
}

.body-right {
  flex: 1 0;
  display: flex;
  flex-direction: column;
}
.echarts {
  width: 100%;
  height: 100%;
}
</style>