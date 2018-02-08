package com.fekpal.service.impl;

import com.fekpal.common.base.BaseServiceImpl;
import com.fekpal.dao.mapper.PersonMapper;
import com.fekpal.dao.model.Person;
import com.fekpal.dao.model.User;
import com.fekpal.api.PersonService;
import com.fekpal.service.model.domain.PersonReg;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by APone on 2017/9/5.
 * PersonService实现类
 */
@Service
public class PersonServiceImpl extends BaseServiceImpl<PersonMapper,Person> implements PersonService {



    @Override
    public Person selectByUserId(int id) {
        return null;
    }

    @Override
    public Person selectByNickname(String name) {
        return null;
    }

    @Override
    public List<Person> queryByNickname(String name, int offset, int limit) {
        return null;
    }


    @Override
    public boolean isExitNickname(String name) {
        return false;
    }

    @Override
    public List<Person> loadAllPerson(int start, int count) {
        return null;
    }
}
