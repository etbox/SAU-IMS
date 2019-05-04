import axios from 'axios';
import Cookies from 'js-cookie';

/**
 * 登录时或更新用户信息时调用
 * 为保证 vuex 的及时更新，在进入用户中心时也调用
 * 取回的用户信息保存在硬盘中（cookie）；为了侦测变化，还要保存在 vuex 中
 *
 * @export
 */
export default function getUserInfo(store, router) {
  axios
    .get('/member/center/info')
    .then((res) => {
      const data = res.data.data,
        resCode = res.data.code;
      if (resCode === 0 && data) {
        Cookies.set('realName', data.realName);
        store.dispatch('modifyRealName', data.realName);
      } else if (resCode === 2) {
        alert('身份已过期，请重新登录');
        store.dispatch('logout');
        router.replace('/');
      }
    })
    .catch((err) => console.log(err));
}
