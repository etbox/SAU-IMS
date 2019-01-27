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
      <IndexItem
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
import IndexItem from "./IndexItem.vue";
import axios from "axios";

let items = [];

export default {
  name: "ItemList",
  props: {
    name: String
  },
  components: {
    IndexItem
  },
  data() {
    return {
      items
    };
  },
  created() {
    axios
      .get("//kanlon.ink/index/club", {
        offset: 1,
        limit: 10
      })
      .then(res => {
        // console.log(res);
        const arr = res.data.data;
        // console.log(items);
        for (let i = 0; i < arr.length; i++) {
          items.push(arr[i]);
        }
        // console.log(items);
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

