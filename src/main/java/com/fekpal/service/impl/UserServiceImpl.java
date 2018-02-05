package com.fekpal.service.impl;

import com.fekpal.common.base.BaseServiceImpl;
import com.fekpal.dao.mapper.UserMapper;
import com.fekpal.common.base.ExampleWrapper;
import com.fekpal.dao.model.User;
import com.fekpal.api.UserService;
import com.fekpal.common.utils.MD5Utils;
import com.fekpal.web.session.SessionLocal;
import com.fekpal.web.session.SessionContent;
import com.fekpal.web.session.SessionNullException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by APone on 2017/9/5.
 * UserService用户服务实现类
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    UserMapper userMapper;

    HttpSession session;

    /**
     * 用户身份信息进行用户登录
     *
     * @param user    用户身份记录 传入参数：用户名userName, 密码password
     * @param captcha 登录验证信息 传入参数：验证码code, 当前时间currentTime
     * @return 是否登录成功
     */
    @Override
    public boolean userLogin(User user, SessionContent.Captcha captcha) {
        try {
            //验证登录验证码是否正确
            SessionLocal sessionLocal = SessionLocal.local(session);
            if (!sessionLocal.isValidLoginCaptcha(captcha)) {
                return false;
            }

            //开始获取用户的存在
            User userIdentity = this.selectByUserName(user.getUserName());
            if (userIdentity == null) {
                return false;
            }
            String password = MD5Utils.md5(user.getPassword() + user.getUserKey());
            if (user.getPassword().equals(password)) {
                sessionLocal.createUserIdentity(userIdentity);
                return true;
            }
        } catch (SessionNullException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 用户是否仍然处于登录状态
     *
     * @return 是否处于登录状态
     */
    @Override
    public boolean isLogin() {
        try {
            SessionLocal sessionLocal = SessionLocal.local(session);
            return sessionLocal.isExitUserIdentity();
        } catch (SessionNullException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 用户登出系统
     *
     * @return 是否登出成功
     */
    @Override
    public boolean userLogout() {
        session.invalidate();
        return false;
    }

    /**
     * 根据用户名获取用户身份记录
     *
     * @param userName String 用户名
     * @return 用户身份记录
     */
    @Override
    public User selectByUserName(String userName) {
        ExampleWrapper<User> example = new ExampleWrapper<>();
        example.eq("user_name", userName);
        return mapper.selectFirstByExample(example);
    }

    /**
     * 根据用户的密码和用户名获得用户身份记录
     *
     * @param userName String 用户名
     * @param password String 密码
     * @return 用户身份记录
     */
    @Override
    public User selectByUserNameAndPwd(String userName, String password) {
        ExampleWrapper<User> example = new ExampleWrapper<>();
        example.eq("user_name", userName).and().eq("password", password);
        return mapper.selectFirstByExample(example);
    }

    /**
     * 根据用户的邮箱获得用户身份记录
     *
     * @param email String 邮箱
     * @return 用户身份记录
     */
    @Override
    public User selectByEmail(String email) {
        ExampleWrapper<User> example = new ExampleWrapper<>();
        example.eq("email", email);
        return mapper.selectFirstByExample(example);
    }

    /**
     * 是否存在相同账号，以相同用户名为标准
     *
     * @param userName String 用户名
     * @return 是否存在
     */
    @Override
    public boolean isExitAccount(String userName) {
        ExampleWrapper<User> example = new ExampleWrapper<>();
        example.eq("user_name", userName);
        int row = mapper.countByExample(example);
        return row >= 1;
    }

    /**
     * 是否存在不同用户具有相同邮箱
     *
     * @param email String 邮箱
     * @return 是否存在
     */
    @Override
    public boolean isExitEmail(String email) {
        ExampleWrapper<User> example = new ExampleWrapper<>();
        example.eq("email", email);
        int row = mapper.countByExample(example);
        return row >= 1;
    }

    /**
     * 加载所有用户身份记录，分页获取
     *
     * @param offset 跳过读数
     * @param limit  读取数
     * @return 用户身份记录列表
     */
    @Override
    public List<User> loadAllUser(Integer offset, Integer limit) {
        ExampleWrapper<User> example = new ExampleWrapper<>();
        return mapper.selectByExample(example, offset, limit);
    }
}
