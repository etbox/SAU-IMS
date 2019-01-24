<template>
  <div class="root">
    <div class="container">
      <Panel v-bind="{title:'忘记密码'}" class="panel">
        <InputInfo
          v-bind="{isTrue:false, isFalse:true, msg:'请填写邮箱或账号名', type:'text', placeholder:'邮箱 | 用户名'}"
        />
        <InputInfo v-bind="{isTrue:false, isFalse:true, msg:'', type:'text', placeholder:'验证码'}">
          <button class="button button-primary button-rounded button-captcha">发送验证码</button>
        </InputInfo>
        <InputInfo
          v-bind="{isTrue:false, isFalse:true, msg:'', type:'password', placeholder:'密码'}"
        />
        <InputInfo
          v-bind="{isTrue:false, isFalse:true, msg:'', type:'password', placeholder:'确认密码'}"
        />
        <button class="button button-primary button-rounded button-fgpw">确认</button>
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