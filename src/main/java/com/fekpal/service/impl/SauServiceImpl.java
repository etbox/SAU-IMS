package com.fekpal.service.impl;

import com.fekpal.common.base.BaseServiceImpl;
import com.fekpal.dao.mapper.SauMapper;
import com.fekpal.dao.mapper.UserMapper;
import com.fekpal.common.base.ExampleWrapper;
import com.fekpal.dao.model.Sau;
import com.fekpal.dao.model.User;
import com.fekpal.api.SauService;
import com.fekpal.service.model.domain.SauReg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by APone on 2017/9/5.
 * SauService实现类
 */
@Service
public class SauServiceImpl extends BaseServiceImpl<SauMapper, Sau> implements SauService {

    @Override
    public Sau selectByUserId(int id) {
        ExampleWrapper<Sau> example = new ExampleWrapper<>();
        example.eq("user_id", id);
        return mapper.selectFirstByExample(example);
    }

    @Override
    public List<Sau> loadAllSau(int offset, int limit) {
        ExampleWrapper<Sau> example = new ExampleWrapper<>();
        return mapper.selectByExample(example, offset, limit);
    }
}


