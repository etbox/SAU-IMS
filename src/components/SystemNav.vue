<template>
  <div class="root">
    <div class="nav-list">
      <ul>
        <router-link :to="'/system/'+item.module" v-for="(item, index) in items" :key="index">
          <li class="nav-item" :class="{'nav-current':item.module === options.module}">{{item.name}}</li>
        </router-link>
      </ul>
    </div>
  </div>
</template>

<script>
// import Cookies from "js-cookie";

export default {
  props: {
    options: Object
  },
  data() {
    return {
      items: [
        {
          name: "最新公告",
          priority: -1,
          module: "news"
        },
        {
          name: "社团信息",
          priority: -1,
          module: "orgs"
        },
        {
          name: "消息发布",
          priority: 0,
          module: "messages"
        },
        {
          name: "注册审核",
          priority: 0,
          module: "audit"
        },
        {
          name: "年度审核",
          priority: 0,
          module: "annual"
        }
      ]
    };
  },
  computed: {
    filter() {
      return this.items.filter(item => {
        return this.$store.getters["checkLogin"] > item.priority;
        // return Cookies.get("priority") > item.priority;
      });
    }
  }
};
</script>

<style lang="scss" scoped>
$deep: #5db1f8;
$light: #91caf9;
.root {
  // background-color: skyblue;
  background-image: url("../images/left.png");
  flex: 1;
  display: flex;
  box-shadow: 3px 0 3px rgba(0, 0, 0, 0.3);
  z-index: 7;
}
.nav-list {
  width: 100%;
  padding-top: 100px;
}
ul {
  list-style: none;
  margin: 0;
  padding: 0;
  cursor: pointer;
}
.nav-item {
  height: 50px;
  margin-bottom: 10px;
  display: flex;
  align-items: center;
  justify-content: space-around;
}
.nav-item:hover {
  background-color: $light;
}
.nav-current {
  background-color: $deep;
}

a {
  color: black;
}
</style>
