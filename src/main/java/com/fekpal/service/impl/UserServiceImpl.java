package com.fekpal.service.impl;

import com.fekpal.dao.user.UserDao;
import com.fekpal.domain.ExampleWrapper;
import com.fekpal.domain.pojo.User;
import com.fekpal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by APone on 2017/9/5.
 * UserService用户服务实现类
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserDao, User> implements UserService {

    @Override
    public boolean login(String userName, String password) {
        return false;
    }

    @Autowired
    UserDao userDao;

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
        return userDao.selectFirstByExample(example);
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
        return userDao.selectFirstByExample(example);
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
        return userDao.selectFirstByExample(example);
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
        int row = userDao.countByExample(example);
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
        int row = userDao.countByExample(example);
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
        return userDao.selectByExample(example, offset, limit);
    }
}
