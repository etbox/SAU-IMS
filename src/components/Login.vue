<template>
  <div class="overlayer" @click.self="vanish">
    <div class="container">
      <Panel v-bind="{title:'登录'}" class="panel">
        <InputInfo
          v-bind="{isTrue:false, isFalse:true, msg:'请填写邮箱或账号名', type:'text', placeholder:'邮箱 | 用户名'}"
        />
        <InputInfo
          v-bind="{isTrue:false, isFalse:true, msg:'', type:'password', placeholder:'密码'}"
        />
        <InputInfo v-bind="{isTrue:false, isFalse:true, msg:'', type:'text', placeholder:'验证码'}">
          <img src="@/images/yzm.jpg" alt="验证码" class="yzm">
        </InputInfo>

        <div class="login-options">
          <div class="rememberID">
            <input type="checkbox" name="rememberID" id="rememberID">
            <label for="rememberID">记住账号</label>
          </div>
          <div class="forgotPW">
            <router-link to="/forgotpw">
              <span @click="vanish">忘记密码</span>
            </router-link>
          </div>
        </div>

        <button class="button button-primary button-rounded button-login">登录</button>
      </Panel>
    </div>
  </div>
</template>

<script>
import InputInfo from "@/components/InputInfo.vue";
import Panel from "@/components/Panel.vue";

export default {
  name: "Login",
  methods: {
    vanish() {
      this.$parent.isLogining = false;
    }
  },
  components: { Panel, InputInfo }
};
</script>

<style lang="scss" scoped>
@import url("../assets/button.css");
$blue: #3fb2fa;
.overlayer {
  background-color: rgba(0, 0, 0, 0.5);
  position: fixed;
  width: 100%;
  height: 100%;
  top: 0;
  left: 0;
  z-index: 9;
  display: flex;
  justify-content: space-around;
}
.yzm {
  display: block;
  height: 100%;
  width: 100%;
}
.login-options {
  margin: 10px 0;
}
.rememberID {
  display: inline-block;
  position: relative;
  right: 130px;
}
.forgotPW {
  display: inline-block;
  position: relative;
  right: 20px;
}
.button-login {
  width: 250px;
}
.panel {
  margin-top: 100px;
}
</style>
