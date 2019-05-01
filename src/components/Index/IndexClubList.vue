<template>
  <div class="list-container">
    <div class="list-head">
      <div class="posistioned">
        <img src="@/images/xiangyun2.png" alt="祥云" class>
        <span class="list-title">{{name}}</span>
        <img src="@/images/xiangyun2.png" alt="祥云" class="flipx">
      </div>
    </div>
    <div class="list-body">
      <IndexClubItem
        v-for="item in items"
        :key="item.orgId"
        :orgId="item.orgId"
        :view="item.view"
        :orgName="item.orgName"
        :description="item.description"
        :members="item.members"
        :likeClick="item.likeClick"
      />
    </div>
  </div>
</template>

<script>
import IndexClubItem from "./IndexClubItem.vue";
import axios from "axios";

export default {
  name: "IndexClubList",
  props: {
    name: String
  },
  components: {
    IndexClubItem
  },
  data() {
    return {
      items: []
    };
  },
  created() {
    axios
      .get("/index/club", {
        offset: 1,
        limit: 10
      })
      .then(res => {
        // console.log(res);
        const arr = res.data.data;
        this.items = arr;
      })
      .catch(function(error) {
        console.log(error);
      });
  }
};
</script>

<style lang="scss" scoped>
$blue: #3fb2fa;
.list-container {
  // border: 0.1px solid blue;
  display: flex;
  flex-direction: column;
  // flex-direction: row;
  box-sizing: border-box;
  width: 980px;
  height: 520px;
  margin: 0 auto;
}

.list-head {
  // border: 0.1px solid red;
  flex: 0 45px;
}
.posistioned {
  float: left;
  span {
    color: $blue;
    margin: 0 12px;
    position: relative;
    top: -8px;
  }
}

.list-body {
  // border: 0.1px solid green;
  flex: 1;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
  // justify-content: flex-start;
}

.flipx {
  -moz-transform: scaleX(-1);
  -webkit-transform: scaleX(-1);
  -o-transform: scaleX(-1);
  transform: scaleX(-1);
}
</style>

