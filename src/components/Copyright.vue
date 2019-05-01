<template>
  <footer>{{copyright}}</footer>
</template>

<script>
export default {
  data: function() {
    return {
      copyright: `${new Date().getFullYear()} @ 校社联管理系统`
    };
  }
};
</script>

<style lang="scss" scoped>
footer {
  $height: 75px;
  color: white;
  background-color: gray;
  width: 100%;
  line-height: $height;
}
</style>