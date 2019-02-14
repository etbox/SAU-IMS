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
        <InputInfo
          v-bind="{isTrue:false, isFalse:notFilledEmail,msg: '请输入正确的邮箱', type:'text', placeholder:'邮箱 | 用户名'}"
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
        <InputInfo
          v-bind="{isTrue:false, isFalse:false, type:'password', placeholder:'密码'}"
          v-model="password"
        />
        <InputInfo
          v-bind="{isTrue:isCorrect, isFalse:isDiffrent,msg:'两次输入的密码不一致', type:'password', placeholder:'确认密码'}"
          v-model="repassword"
          @blur="checkPassword"
        />
        <button class="button button-primary button-rounded button-signup" @click="signUp">注册</button>
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
            `/reg/person/captcha`,
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
            if (res.data.code !== 0) {
              alert(res.data.msg);
            }
          })
          .catch(function(error) {
            console.log(error);
          });
      }
    },
    checkPassword(arg) {
      // console.log(arg);
      // arg.isTrue = true;
      // 组件内不能修改props的值，同时修改的值也不会同步到组件外层，即调用组件方不知道组件内部当前的状态是什么。

      this.isCorrect = false;
      if (this.repassword) {
        if (this.password !== this.repassword) {
          this.isDiffrent = true;
        } else {
          this.isCorrect = true;
          this.isDiffrent = false;
        }
      }
    },
    signUp() {
      let params = {
          userName: this.userName,
          password: this.password,
          captcha: this.captcha
        },
        url = `/reg/person`,
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
  data() {
    return {
      userName: ``, //表单数据
      password: ``,
      captcha: ``,
      repassword: ``,
      // 控制数据
      isCorrect: false,
      isDiffrent: false,
      notFilledEmail: false
    };
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
  margin-top: 10px;
}
</style>