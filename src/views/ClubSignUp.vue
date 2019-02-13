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
          v-bind="{isTrue:false, isFalse:true, msg:'请填写邮箱或账号名', type:'text', placeholder:'邮箱 | 用户名'}"
        />
        <InputInfo
          v-bind="{isTrue:false, isFalse:true, msg:'', type:'password', placeholder:'密码'}"
        />
        <InputInfo
          v-bind="{isTrue:false, isFalse:true, msg:'', type:'password', placeholder:'确认密码'}"
        />
      </Panel>
      <div>基本信息</div>
      <Panel class="panel">
        <InputInfo v-bind="{isTrue:false, isFalse:true, msg:'', type:'text', placeholder:'真实姓名'}"/>
        <InputInfo v-bind="{isTrue:false, isFalse:true, msg:'', type:'email', placeholder:'邮箱'}"/>
        <InputInfo v-bind="{isTrue:false, isFalse:true, msg:'', type:'text', placeholder:'验证码'}">
          <button class="button button-primary button-rounded button-captcha">发送验证码</button>
        </InputInfo>
        <InputInfo v-bind="{isTrue:false, isFalse:true, msg:'', type:'tel', placeholder:'手机号码'}"/>
      </Panel>
      <div>注册信息</div>
      <Panel class="panel">
        <InputInfo v-bind="{isTrue:false, isFalse:true, msg:'', type:'text', placeholder:'社团名称'}"/>

        <select name="select">
          <option value="value1">Value 1</option>
          <option value="value2" selected>Value 2</option>
          <option value="value3">Value 3</option>
        </select>

        <InputInfo v-bind="{isTrue:false, isFalse:true, msg:'', type:'text', placeholder:'社团描述'}"/>

        <InputInfo v-bind="{isTrue:false, isFalse:true, msg:'', type:'file', placeholder:'选择文件'}"/>
        <button class="button button-primary button-rounded button-signup">注册</button>
      </Panel>
    </div>
  </div>
</template>

<script>
import InputInfo from "@/components/InputInfo.vue";
import Panel from "@/components/Panel.vue";

export default {
  components: { InputInfo, Panel }
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