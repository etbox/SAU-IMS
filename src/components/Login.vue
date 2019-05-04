<template>
  <div class="overlayer" @click.self="vanish">
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
        @login="login"
      />

      <div class="login-options">
        <!-- TODO: 记住账号 -->
        <!-- <div class="rememberID">
            <input type="checkbox" name="rememberID" id="rememberID">
            <label for="rememberID">记住账号</label>
        </div>-->
        <div class="forgotPW">
          <router-link to="/forgotpw">
            <span @click="vanish">忘记密码</span>
          </router-link>
        </div>
      </div>

      <button class="button button-primary button-rounded button-login" @click="login">登录</button>
    </Panel>
  </div>
</template>

<script>
import axios from "axios";
import Cookies from "js-cookie";

import InputInfo from "@/components/InputInfo.vue";
import Panel from "@/components/Panel.vue";
import getUserInfo from "../util/getUserInfo.js";

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
        isFilled = false,
        expires = 1 / 48;

      // 检测输入框是否为空
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
            // 想了想只需要重置验证码
            this.captcha = ``;

            if (res.data.code === 2 && res.data.msg.search(/JDBC/) !== -1) {
              // TODO: 没办法自动刷新验证码
              alert("数据库正在重新连接，请重试");
            } else if (res.data.code !== 0) {
              alert(res.data.msg);
            } else {
              const priority = Number(res.data.data);

              // 在 cookie 中保存权限信息，使导航栏正确显示用户信息（因为 cookie 可以设过期时间）
              Cookies.set("priority", priority, { expires });
              // vuex 的数据在内存中，每次都要手动修改
              this.$store.dispatch("login", priority);

              // 页面跳转
              this.$router.push("system");
              this.vanish();

              // 获取用户信息并显示在顶部导航栏
              getUserInfo(this.$store);
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
  width: 100%;
  height: 100%;
  // 产生层叠上下文
  position: fixed;
  z-index: 9;
}
.panel {
  margin: 0 auto;
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
