package com.fekpal.web.session;

import com.fekpal.domain.BasePOJO;
import com.fekpal.domain.pojo.User;

/**
 * Created by APone on 2018/1/30 13:05.
 * 存储在session的常用信息类
 */
public class SessionContent {

    /**
     * 身份类
     */
    public static class UserIdentity extends BasePOJO {

        //用户id
        private int id;

        //用户名
        private String name;

        //手机
        private String phone;

        //邮箱
        private String email;

        //权限
        private int authority;

        public UserIdentity(User user) {
            this.id = user.getUserId();
            this.name = user.getUserName();
            this.email = user.getEmail();
            this.phone = user.getPhone();
            this.authority = user.getAuthority();
        }

        public UserIdentity() {
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public int getAuthority() {
            return authority;
        }

        public void setAuthority(int authority) {
            this.authority = authority;
        }
    }

    /**
     * 验证信息类
     */
    public static class Captcha extends BasePOJO {

        //验证码
        private String code;

        //哈希值
        private String hashCode;

        //有效时间
        private long activeTime;

        //创建时间
        private long createTime;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getHashCode() {
            return hashCode;
        }

        public void setHashCode(String hashCode) {
            this.hashCode = hashCode;
        }

        public long getActiveTime() {
            return activeTime;
        }

        public void setActiveTime(long activeTime) {
            this.activeTime = activeTime;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }
    }
}