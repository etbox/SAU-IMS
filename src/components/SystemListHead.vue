<template>
  <div class="list-head">
    <div class="list-head-left">
      <input type="text">
      <div class="list-title">历史发布</div>
    </div>
    <div class="list-head-right">
      <div class="top-right">
        <img src="@/images/search_logo.png" alt="搜索">
        <img src="@/images/refresh_logo.png" alt="刷新">
      </div>
      <div class="bottom-right">
        <img src="@/images/add_logo.png" alt="新增" v-show="isAdd">
        <img src="@/images/delete_logo.png" alt="删除" v-show="isDelete">
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    isAdd: Boolean,
    isDelete: Boolean
  }
};
</script>

<style lang="scss" scoped>
$deep: #5db1f8;
$light: #91caf9;
$gray: #e4e8ec;

.list-head {
  flex: 0 0 100px;
  background-color: $gray;
  display: flex;
  box-shadow: 0 2px 2px rgba(0, 0, 0, 0.2);
  z-index: 6;
}
.list-head-left {
  flex: 1;
  display: flex;
  flex-direction: column;
  justify-content: space-around;
}
.list-head-right {
  flex: 0 100px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  img {
    width: 40px;
    height: 40px;
    padding: 4px;
  }
  img:hover {
    background-color: $light;
    cursor: pointer;
  }
}

input {
  display: inline-block;
  background-color: #dbdcdd;
  font-size: 16px;
  height: 40px;
  // width: 250px;
  margin: 0 10px;

  border-radius: 5px;
  border: 0.01px solid $deep;
  text-indent: 5px;
  outline: none;
  transition: box-shadow 0.2s;
}
input:hover {
  box-shadow: 0 3px 4px rgba(0, 0, 0, 0.3);
}

input:focus {
  box-shadow: 0 3px 4px rgba(0, 0, 0, 0.3);
  transition: box-shadow 0.2s;
  border: 0.5px solid $deep;
}
</style>