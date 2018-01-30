package com.fekpal.service.impl;

import com.fekpal.dao.user.SauDao;
import com.fekpal.domain.pojo.Sau;
import com.fekpal.service.SauService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by APone on 2017/9/5.
 * SauService实现类
 */
@Service
public class SauServiceImpl implements SauService {

    @Autowired
    private SauDao sauDao;

    @Override
    public Sau getSauByUserId(int userId) {
        return sauDao.selectByPrimaryKey(userId);
    }

    @Override
    public Sau getSauBySauId(int sauId) {
        return sauDao.selectByPrimaryKey(sauId);
    }

    @Override
    public void updateSauInfo(Sau sau) {
        sauDao.updateByPrimaryKey(sau);
    }

    @Override
    public List<Sau> loadAllSau() {
        return null;
    }

    @Override
    public Sau getSauAllInfoByUserId(int userId) {
        return null;
    }
}
