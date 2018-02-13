package com.fekpal.common.session;

import com.fekpal.common.base.BaseModel;

/**
 * Created by APone on 2018/1/30 13:05.
 * 存储在session的常用信息类
 */
public class SessionContent {

    /**
     * 生成用户身份实例
     *
     * @return 用户身份封装
     */
    public static UserIdentity createUID() {
        return new UserIdentity();
    }

    /**
     * 生成验证信息实例
     *
     * @return 验证信息封装
     */
    public static Captcha createCaptcha() {
        return new Captcha();
    }

    /**
     * 身份类
     */
    public static class UserIdentity extends BaseModel {

        private static final long serialVersionUID = -2303162431945571719L;

        /**
         * 用户id
         */
        private int id;

        /**
         * 用户名
         */
        private String name;

        /**
         * 手机号码
         */
        private String phone;

        /**
         * 邮箱
         */
        private String email;

        /**
         * 权限
         */
        private int authority;

        private UserIdentity() {

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
    public static class Captcha extends BaseModel {

        private static final long serialVersionUID = -6418758502209346026L;

        /**
         * 验证码
         */
        private String code;

        /**
         * 哈希值
         */
        private String hashCode;

        /**
         * 有效时间
         */
        private long activeTime;

        /**
         * 创建时间
         */
        private long createTime;

        /**
         * 当前时间
         */
        private long currentTime;

        /**
         * 授权对象
         */
        private String authorize;

        private Captcha() {

        }

        public long getCurrentTime() {
            return currentTime;
        }

        public void setCurrentTime(long currentTime) {
            this.currentTime = currentTime;
        }

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

        public String getAuthorize() {
            return authorize;
        }

        public void setAuthorize(String authorize) {
            this.authorize = authorize;
        }
    }
}
