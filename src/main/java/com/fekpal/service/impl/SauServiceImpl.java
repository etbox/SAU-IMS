package com.fekpal.service.impl;

import com.fekpal.dao.user.SauDao;
import com.fekpal.dao.user.UserDao;
import com.fekpal.domain.ExampleWrapper;
import com.fekpal.domain.pojo.Sau;
import com.fekpal.domain.pojo.User;
import com.fekpal.service.SauService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by APone on 2017/9/5.
 * SauService实现类
 */
@Service
public class SauServiceImpl extends BaseServiceImpl<SauDao, Sau> implements SauService {

    @Autowired
    private SauDao sauDao;

    @Autowired
    private UserDao userDao;

    /**
     * 根据校社联用户的用户身份id获取校社联信息
     *
     * @param id int 用户身份id
     * @return 校社联信息记录
     */
    @Override
    public Sau selectByUserId(int id) {
        ExampleWrapper<Sau> example = new ExampleWrapper<>();
        example.eq("user_id", id);
        return sauDao.selectFirstByExample(example);
    }

    /**
     * 插入新的校社联用户信息以及用户身份信息
     * 当插入数为2时才为成功，其他为错误
     *
     * @param user 用户身份信息
     * @param sau  校社联用户信息
     * @return 插入数
     */
    @Override
    public int insertInfo(User user, Sau sau) {

        int row = userDao.insert(user);
        row += sauDao.insert(sau);
        return row;
    }

    /**
     * 获取所有校社联信息，分页获取
     *
     * @param offset 跳过读数
     * @param limit  读取数
     * @return 校社联信息记录集
     */
    @Override
    public List<Sau> loadAllSau(int offset, int limit) {
        ExampleWrapper<Sau> example = new ExampleWrapper<>();
        return sauDao.selectByExample(example, offset, limit);
    }
}

