<template>
  <div class="panel-container">
    <div class="panel-head">
      <span v-if="title">{{title}}</span>
    </div>
    <div class="panel-body">
      <slot></slot>
    </div>
  </div>
</template>

<script>
export default {
  name: "Panel",
  props: {
    title: String
  }
};
</script>

<style lang="scss" scoped>
$blue: #3fb2fa;
$gray: #e4e8ec;

.panel-container {
  background-color: #fff;
  border-radius: 10px;
  border: 0.01px solid $gray;
  box-shadow: 2px 2px 5px rgba(128, 128, 128, 0.7);
  width: 580px;
  margin-top: 100px;
  display: flex;
  flex-direction: column;
}
.panel-head {
  border-radius: 10px 10px 0 0;
  border: 0.01px solid $gray;
  background-color: $blue;
  color: #fff;
  font-size: 24px;
  flex: 0 50px;
  display: flex;
  align-items: center;
  justify-content: space-around;
}
.panel-body {
  padding: 20px 0;
}
</style>

