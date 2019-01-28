<template>
  <div class="overlayer" @click.self="vanish">
    <div class="container">
      <Panel v-bind="{title:'登录'}" class="panel">
        <InputInfo
          v-bind="{isTrue:false, isFalse:false, msg:'请填写邮箱或账号名', type:'text', placeholder:'邮箱 | 用户名'}"
        />
        <InputInfo
          v-bind="{isTrue:false, isFalse:false, msg:'', type:'password', placeholder:'密码'}"
        />
        <InputInfo
          v-bind="{isTrue:false, isFalse:false, msg:'验证码错误', type:'text', placeholder:'验证码'}"
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
import qs from "qs";

// axios.defaults.headers.post["Content-Type"] = "application/json;charse=UTF-8";
let params = {
    userName: "person1@126.com",
    password: "12345",
    captcha: "8QCG8"
  },
  str =
    '{\n"userName":"person1@126.com",\n"password":"12345",\n"captcha":"8QCG8"\n}';

export default {
  name: "Login",
  methods: {
    vanish() {
      this.$parent.isLogining = false;
    },
    login() {
      axios
        .post(
          "//kanlon.ink/login",
          {
            userName: "person1@126.com",
            password: "12345",
            captcha: "8QCG8"
          },
          // str,
          // qs.stringify(params),
          {
            headers: {
              "Content-Type": "application/json;charset=UTF-8"
              // "Access-Control-Allow-Origin": "http://localhost:8080"
            },
            transformRequest: [
              function(data) {
                data = qs.stringify(data);
                return data;
              }
            ]
            // withCredentials: true
            // proxy: {
            //   host: "localhost",
            //   port: 8080
            // }
          }
        )
        // axios({
        //   method: "post",
        //   url: "//kanlon.ink/login",
        //   headers: {
        //     "Content-type": "application/json"
        //   },
        //   data: param,
        //   transformRequest: [
        //     function() {
        //       return JSON.stringify(param);
        //     }
        //   ]
        // })
        .then(res => {
          console.log(res);
        })
        .catch(function(error) {
          console.log(error);
        });
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
