package com.fekpal.service.impl;

import com.fekpal.common.base.BaseServiceImpl;
import com.fekpal.dao.mapper.PersonMapper;
import com.fekpal.dao.model.Person;
import com.fekpal.dao.model.User;
import com.fekpal.api.PersonService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by APone on 2017/9/5.
 * PersonService实现类
 */
@Service
public class PersonServiceImpl extends BaseServiceImpl<PersonMapper,Person> implements PersonService {

    @Override
    public Person selectByNickName(String nickName) {
        return null;
    }

    @Override
    public Person selectByUserId(int id) {
        return null;
    }

    @Override
    public int insertInfo(User user, Person person) {
        return 0;
    }

    @Override
    public boolean isExitNickName(String nickName) {
        return false;
    }

    @Override
    public void addLikeClub(int personId, int clubId) {

    }

    @Override
    public List<Integer> loadAllLikeByPersonId(int id) {
        return null;
    }

    @Override
    public List<Person> loadAllPerson(int start, int count) {
        return null;
    }
}
