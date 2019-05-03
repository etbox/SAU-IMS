import axios from 'axios';
/**
 * 登录时或更新用户信息时调用
 * 取回的用户信息保存在 vuex 中
 *
 * @export
 */
export default function getUserInfo(store) {
  axios
    .get('/member/center/info')
    .then((res) => {
      const data = res.data.data;
      store.dispatch('modifyRealName', data.realName);
    })
    .catch((err) => console.log(err));
}
