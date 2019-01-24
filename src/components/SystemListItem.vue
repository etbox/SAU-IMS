<template>
  <div class="list-item">
    <div class="read-flag new-flag"></div>
    <div class="list-item-left">
      <div class="message-title">校社联管理系统做好啦</div>
      <div class="sender-name">by 校社联</div>
    </div>
    <div class="list-item-right">
      <input type="checkbox" name id>
      <div class="send-time">2017/8/24</div>
    </div>
  </div>
</template>

<script>
export default {};
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
}
.sender-name {
  font-size: 14px;
}
.list-item-right {
  flex: 0 100px;
}
</style>