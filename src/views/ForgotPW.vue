<template>
  <div class="root">
    <div class="container">
      <Panel v-bind="{title:'忘记密码'}" class="panel">
        <InputInfo
          v-bind="{isTrue:isSent, isFalse:notFilledEmail, msg:'请填写正确的邮箱或账号', type:'text', placeholder:'邮箱 | 用户名'}"
          v-model="userName"
        />
        <InputInfo
          v-bind="{isTrue:false, isFalse:false, type:'text', placeholder:'验证码'}"
          v-model="captcha"
        >
          <button
            class="button button-primary button-rounded button-captcha"
            @click="sendCaptcha"
          >发送验证码</button>
        </InputInfo>
        <button class="button button-primary button-rounded button-fgpw">确认</button>
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
      //表单数据
      userName: ``,
      captcha: ``,
      // 控制数据
      notFilledEmail: false,
      isSent: false
    };
  },
  methods: {
    sendCaptcha() {
      if (
        this.userName.search(
          /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/
        ) === -1
      ) {
        this.notFilledEmail = true;
      } else {
        this.notFilledEmail = false;
        axios
          .post(
            `/resetpwd/captcha`,
            {
              email: this.userName
            },
            {
              headers: {
                "Content-Type": "application/json;charset=UTF-8"
              }
            }
          )
          .then(res => {
            console.log(res.data);
            if (res.data.code) {
              alert(res.data.msg);
            } else {
              this.isSent = true;
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
}
.button-captcha {
  width: 100%;
}
.button-fgpw {
  width: 250px;
  margin-top: 10px;
}

.panel {
  margin-top: 100px;
}
</style>