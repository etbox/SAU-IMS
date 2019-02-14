<template>
  <div class="root">
    <div class="container">
      <div class="alternation">
        <router-link to="/signup">
          <button class="button button-rounded">普通注册</button>
        </router-link>
        <router-link to="/clubsignup">
          <button class="button button-rounded">社团注册</button>
        </router-link>
      </div>
      <Panel class="panel">
        <InputInfo v-bind="{isTrue:false, isFalse:false, type:'text', placeholder:'用户名'}"/>
        <InputInfo v-bind="{isTrue:false, isFalse:false, type:'password', placeholder:'密码'}"/>
        <InputInfo v-bind="{isTrue:false, isFalse:false, type:'password', placeholder:'确认密码'}"/>
      </Panel>
      <div>基本信息</div>
      <Panel class="panel">
        <InputInfo v-bind="{isTrue:false, isFalse:false, type:'text', placeholder:'真实姓名'}"/>
        <InputInfo
          v-bind="{isTrue:false, isFalse:notFilledEmail,msg: '请输入正确的邮箱', type:'email', placeholder:'邮箱'}"
          v-model="email"
        />
        <InputInfo v-bind="{isTrue:false, isFalse:false, type:'text', placeholder:'验证码'}">
          <button
            class="button button-primary button-rounded button-captcha"
            @click="sendCaptcha"
          >发送验证码</button>
        </InputInfo>
        <InputInfo v-bind="{isTrue:false, isFalse:false, type:'tel', placeholder:'手机号码'}"/>
      </Panel>
      <div>注册信息</div>
      <Panel class="panel">
        <InputInfo v-bind="{isTrue:false, isFalse:false, type:'text', placeholder:'社团名称'}"/>

        <select name="select">
          <option value="value1">Value 1</option>
          <option value="value2" selected>Value 2</option>
          <option value="value3">Value 3</option>
        </select>

        <InputInfo v-bind="{isTrue:false, isFalse:false, type:'text', placeholder:'社团描述'}"/>

        <InputInfo v-bind="{isTrue:false, isFalse:false, type:'file', placeholder:'选择文件'}"/>
        <button class="button button-primary button-rounded button-signup">注册</button>
      </Panel>
    </div>
  </div>
</template>

<script>
import InputInfo from "@/components/InputInfo.vue";
import Panel from "@/components/Panel.vue";
import axios from "axios";

export default {
  components: { InputInfo, Panel },
  data() {
    return {
      // 表单数据
      userName: ``,
      password: ``,
      realName: ``,
      email: ``,
      captcha: ``,
      phone: ``,
      clubName: ``,
      clubType: ``,
      description: ``,
      // file: new File(),
      // 控制数据
      notFilledEmail: false
    };
  },
  methods: {
    sendCaptcha() {
      if (
        this.email.search(
          /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/
        ) === -1
      ) {
        this.notFilledEmail = true;
      } else {
        this.notFilledEmail = false;
        axios
          .post(
            `/reg/club/captcha`,
            {
              email: this.email
            },
            {
              headers: {
                "Content-Type": "application/json;charset=UTF-8"
              }
            }
          )
          .then(res => {
            console.log(res.data);
            if (res.data.code !== 0) {
              alert(res.data.msg);
            }
          })
          .catch(function(error) {
            console.log(error);
          });
      }
    }
  }
};
</script>

<style lang="scss" scoped>
$blue: #3fb2fa;
$gray: #e4e8ec;

.root {
  display: flex;
  justify-content: space-around;
}
button {
  background-color: $blue;
  color: white;
}
.button-captcha {
  width: 100%;
}
.button-signup {
  width: 250px;
  margin-top: 10px;
}
.alternation {
  margin-top: 30px;
  display: flex;
  justify-content: space-around;
}
.panel {
  margin: 10px 0;
}
</style>