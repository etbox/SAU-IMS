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
    HttpSession session;

    @Override
    public User selectByUserName(String userName) {
        ExampleWrapper<User> example = new ExampleWrapper<>();
        example.eq("user_name", userName);
        return mapper.selectFirstByExample(example);
    }

    @Override
    public User selectByUserNameAndPwd(String userName, String password) {
        ExampleWrapper<User> example = new ExampleWrapper<>();
        example.eq("user_name", userName).and().eq("password", password);
        return mapper.selectFirstByExample(example);
    }

    @Override
    public User selectByEmail(String email) {
        ExampleWrapper<User> example = new ExampleWrapper<>();
        example.eq("email", email);
        return mapper.selectFirstByExample(example);
    }

    @Override
    public boolean isExitAccount(String userName) {
        ExampleWrapper<User> example = new ExampleWrapper<>();
        example.eq("user_name", userName);
        int row = mapper.countByExample(example);
        return row >= 1;
    }

    @Override
    public boolean isExitEmail(String email) {
        ExampleWrapper<User> example = new ExampleWrapper<>();
        example.eq("email", email);
        int row = mapper.countByExample(example);
        return row >= 1;
    }

    @Override
    public List<User> loadAllUser(Integer offset, Integer limit) {
        ExampleWrapper<User> example = new ExampleWrapper<>();
        return mapper.selectByExample(example, offset, limit);
    }
}
