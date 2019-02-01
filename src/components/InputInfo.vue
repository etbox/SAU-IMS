<template>
  <div class="container" v-if="placeholder !== '验证码'">
    <div class="panel-input">
      <input :type="type" class="input" :placeholder="placeholder" v-model="value">
    </div>
    <div class="panel-info">
      <div v-if="isTrue||isFalse">
        <img src="@/images/true_logo.svg" alt="格式正确" v-if="isTrue">
        <img src="@/images/false_logo.svg" alt="信息有误" v-else>
        <div class="information">{{msg}}</div>
      </div>
    </div>
  </div>
  <!-- 验证码部分特别布局 -->
  <div class="container captcha" v-else>
    <div class="panel-input">
      <div class="captcha-extra">
        <slot>
          <img :src="captchaImg" alt="点击刷新验证码" v-if="flag" @click="refresh">
        </slot>
      </div>
      <input
        type="text"
        name="captcha"
        id="captcha"
        class="captcha-basic"
        :placeholder="placeholder"
        @focus.once="showCaptcha"
        v-model="value"
      >
    </div>
    <div class="panel-info">
      <div v-if="isTrue||isFalse">
        <img src="@/images/true_logo.svg" alt="格式正确" v-if="isTrue">
        <img src="@/images/false_logo.svg" alt="信息有误" v-else>
        <div class="information">{{msg}}</div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "InputInfo",
  props: {
    type: String,
    isTrue: Boolean,
    isFalse: Boolean,
    msg: String,
    placeholder: String
  },
  methods: {
    showCaptcha() {
      this.flag = true;
    },
    refresh() {
      this.captchaImg = `//kanlon.ink/login/captcha?r=${Math.random() * 10000}`;
    }
  },
  data() {
    return {
      flag: false,
      captchaImg: `//kanlon.ink/login/captcha?r=${Math.random() * 10000}`,
      value: ``
    };
  }
};
</script>

<style lang="scss" scoped>
$red: #f6363b;
$gray: #e4e8ec;
* {
  margin: 0;
  padding: 0;
}
.container {
  display: flex;
  padding: 8px 0;
}

.panel-input {
  // background-color: pink;
  flex: 3;
}
input {
  display: inline-block;
  background-color: $gray;
  font-size: 16px;
  height: 40px;
  width: 250px;

  border-radius: 5px;
  border: 0.01px solid $gray;
  text-indent: 5px;
  outline: none;
  transition: box-shadow 0.2s;

  margin: {
    right: 10px;
    top: 3px;
  }
  float: right;
}
input:hover {
  box-shadow: 0 3px 4px rgba(0, 0, 0, 0.3);
}

input:focus {
  box-shadow: 0 3px 4px rgba(0, 0, 0, 0.3);
  transition: box-shadow 0.2s;
}

.captcha-basic {
  width: 120px;
}
.captcha-extra {
  display: inline-block;
  // background-color: pink;
  height: 40px;
  width: 120px;

  float: right;
  margin: {
    right: 10px;
    top: 3px;
  }

  img {
    cursor: pointer;
  }
}

.panel-info {
  $length: 20px;
  // background-color: skyblue;
  flex: 2;
  margin-top: 8px;
  img {
    float: left;
    display: inline-block;
    width: $length;
    height: $length;
    margin: {
      left: 10px;
      right: 10px;
      top: 5px;
    }
  }
}
.information {
  float: left;
  display: inline-block;
  color: $red;
  line-height: 30px;
}
</style>
