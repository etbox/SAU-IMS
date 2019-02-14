<template>
  <div class="overlayer" @click.self="vanish">
    <div class="container">
      <Panel v-bind="{title:'登录'}" class="panel">
        <InputInfo
          v-bind="{isTrue:false, isFalse:false, type:'text', placeholder:'邮箱 | 用户名'}"
          v-model="userName"
        />
        <InputInfo
          v-bind="{isTrue:false, isFalse:false, type:'password', placeholder:'密码'}"
          v-model="password"
        />
        <InputInfo
          v-bind="{isTrue:false, isFalse:false, type:'text', placeholder:'验证码'}"
          v-model="captcha"
        />

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

        <button class="button button-primary button-rounded button-login" @click="login">登录</button>
      </Panel>
    </div>
  </div>
</template>

<script>
import InputInfo from "@/components/InputInfo.vue";
import Panel from "@/components/Panel.vue";
import axios from "axios";

export default {
  name: "Login",
  methods: {
    vanish() {
      this.$parent.isLogining = false;
    },
    login() {
      let params = {
          userName: this.userName,
          password: this.password,
          captcha: this.captcha
        },
        url = `/login`,
        isFilled = false;

      console.log(params);

      for (const key in params) {
        if (params.hasOwnProperty(key)) {
          const element = params[key];
          if (!element) {
            alert("还有未填项！");
            isFilled = false;
            break;
          } else {
            isFilled = true;
          }
        }
      }

      if (isFilled) {
        axios
          .post(url, params, {
            headers: {
              "Content-Type": "application/json;charset=UTF-8"
            }
          })
          .then(res => {
            console.log(res.data);

            // 想了想只需要重置验证码
            // this.userName = ``;
            // this.password = ``;
            this.captcha = ``;

            if (res.data.code === 2 && res.data.msg.search(/JDBC/) !== -1) {
              alert("数据库正在重新连接，请重试");
            } else if (res.data.code !== 0) {
              alert(res.data.msg);
            }
          })
          .catch(function(error) {
            console.log(error);
          });
      }
    }
  },
  components: { Panel, InputInfo },
  data() {
    return {
      userName: ``,
      password: ``,
      captcha: ``
    };
  }
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
