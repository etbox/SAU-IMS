<template>
  <div class="list-item" v-if="type === 'news'">
    <div class="read-flag" :class="{'new-flag':!(item.readFlag)}"></div>
    <div class="list-item-left">
      <div class="message-title">{{item.messageTitle}}</div>
      <div class="sender-name">{{item.releaseName}}</div>
    </div>
    <div class="list-item-right">
      <input type="checkbox" v-model="checkedItems" :value="item.messageId" @input="check">
      <div class="send-time">{{`${new Date(item.releaseTime).getFullYear()}/${new Date(item.releaseTime).getMonth()+1}/${new Date(item.releaseTime).getDate()}`}}</div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    type: String,
    item: Object
  },
  data() {
    return {
      checkedItems: []
    };
  },
  methods: {
    check(event) {
      console.log(event.target.value);
    }
  },
};
</script>

<style lang="scss" scoped>
$deep: #5db1f8;
$light: #91caf9;

.list-item {
  cursor: pointer;
  background-color: white;
  flex: 0 0 50px;
  margin-top: 10px;
  display: flex;
  transition: all 0.3s ease-in-out;
  padding: 5px 0;
}
.list-item:hover {
  background-color: $light;
}

.read-flag {
  flex: 0 10px;
}
.new-flag {
  background-color: $deep;
}

.list-item-left {
  flex: 1;
}
.message-title {
  font-size: 18px;
  float: left;
  padding-left: 1rem;
}
.sender-name {
  font-size: 14px;
  float: left;
  padding-left: 1rem;
}

.list-item-right {
  flex: 0 100px;
}
.send-time {
  font-size: 14px;
  padding-top: 0.2rem;
}
</style>