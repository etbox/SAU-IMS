<template>
  <div class="root">
    <div class="nav-list">
      <ul>
        <router-link :to="'/system/'+item.module" v-for="(item, index) in filter" :key="index">
          <li
            class="nav-item"
            :class="{'nav-current':item.module === options.module}"
            @click="getUserInfo"
          >{{item.name}}</li>
        </router-link>
      </ul>
    </div>
  </div>
</template>

<script>
import getUserInfo from "../util/getUserInfo.js";

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
      });
    }
  },
  methods: {
    getUserInfo() {
      return getUserInfo();
    }
  }
};
</script>

<style lang="scss" scoped>
$deep: #5db1f8;
$light: #91caf9;
.root {
  background-image: url("../images/left.png");
  // 宽度由 flex 决定
  flex: 1;
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
