<template>
  <div class="item-container">
    <router-link :to="'/club/'+orgId">
      <div class="img-container">
        <img :src="view" :alt="orgName">
      </div>
      <div class="item-details">
        <div class="club-name">{{orgName}}</div>
        <div class="description">{{description}}</div>
        <div class="icon-numbers">
          <img src="@/images/member.png" alt="成员人数">
          <span>{{members+'人'}}</span>
          <img src="@/images/heard_logo.png" alt="喜欢数">
          <span>{{likeClick+'人'}}</span>
        </div>
      </div>
    </router-link>
  </div>
</template>

<script>
export default {
  name: "IndexItem",
  props: {
    orgName: String,
    orgId: Number,
    description: String,
    view: String,
    logo: String,
    members: Number,
    likeClick: Number,
    isClick: Number
  },
  data() {
    return {
      // view: require("@/images/摄影协会.png"),
      // orgName: "摄影协会",
      // description: "定格光影，留住光阴",
      // members: 65,
      // likeClick: 32
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
