<template>
  <div class="root">
    <div class="club-head">
      <div class="head-left">
        <div class="club-avatar">
          <!-- <img src="@/images/avatar.png" alt="社团头像"> -->
          <img :src="details.logo" alt="社团头像">
        </div>
        <div class="club-title">
          <div class="club-name">{{details.orgName}}</div>
          <div class="description">{{details.headIntroduce}}</div>
        </div>
      </div>
      <div class="head-right">
        <!-- <div class="found-time">2002年2月22日成立</div> -->
        <div
          class="found-time"
        >{{new Date(details.foundTime).getFullYear()}}年{{new Date(details.foundTime).getMonth()+1}}月{{new Date(details.foundTime).getDate()}}日成立</div>
      </div>
    </div>
    <div class="club-body">
      <div class="body-left">
        <div class="info-top">
          <div class="admin-name">会长：{{details.adminName}}</div>
          <div class="admin-email">邮箱：{{details.email}}</div>
          <div class="admin-phone">手机：{{details.phone}}</div>
        </div>
        <div class="info-bottom">
          <div class="description-head">社团简介：</div>
          <div class="description-body">{{details.description}}</div>
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
import axios from "axios";

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
      },
      details: {
        orgId: 12,
        orgName: "",
        orgType: "",
        adminName: "",
        phone: "",
        email: "",
        foundTime: 1513195751000,
        headIntroduce: "",
        description: "",
        joinState: 0,
        likeClick: 2,
        members: 2,
        logo: "default_logo.jpg",
        view: "default_overview.png",
        firstGradeNum: 0,
        secondGradeNum: 0,
        threeGradeNum: 0,
        fourGradeNum: 2,
        graduatedNum: 0,
        manNum: 2,
        womanNum: 0,
        date: 0
      }
    };
  },
  created() {
    const orgId = this.$route.params.orgId;
    axios
      .get(`//kanlon.ink/index/club/${orgId}`)
      .then(res => {
        // console.log(res);
        const resData = res.data.data;
        // console.log(resData);
        for (const key in resData) {
          if (resData.hasOwnProperty(key)) {
            const element = resData[key];
            this.details[key] = element;
          }
        }
      })
      .catch(function(error) {
        console.log(error);
      });
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