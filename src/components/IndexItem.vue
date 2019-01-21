<template>
  <div class="item-container">
    <div class="img-container">
      <img :src="clubView" :alt="clubName">
    </div>
    <div class="item-details">
      <div class="club-name">{{clubName}}</div>
      <div class="description">{{description}}</div>
      <div class="icon-numbers">
        <img src="@/images/member.png" alt="成员人数">
        <span>{{members+'人'}}</span>
        <img src="@/images/heard_logo.png" alt="喜欢数">
        <span>{{likeNumber+'人'}}</span>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "IndexItem",
  // props: {
  //   clubId: Number,
  //   clubView: [String],
  //   clubName: String,
  //   description: String,
  //   members: Number,
  //   likeNumber: Number
  // },
  data() {
    return {
      clubView: require("@/images/摄影协会.png"),
      clubName: "摄影协会",
      description: "定格光影，留住光阴",
      members: 65,
      likeNumber: 32
    };
  }
};
</script>

<style lang="scss" scoped>
.item-container {
  border: 0.1px solid black;
  background-color: #fff;
  width: 180px;
  margin-bottom: 20px;
}

.img-container {
  height: 105px;
  img {
    transform: scale(1);
    transition: transform 0.2s;
    z-index: -10;
  }
  // img:hover {
  // transform: scale(1.05);
  // }
}
.item-container:hover {
  img {
    transform: scale(1.05);
  }
}

.item-details {
  font-size: 14px;
  div {
    margin: 10px 0;
  }
  img {
    width: 25px;
    height: 20px;
  }
  span {
    margin: 0 5px;
  }
}
</style>
