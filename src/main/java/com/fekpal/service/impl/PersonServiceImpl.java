package com.fekpal.service.impl;

import com.fekpal.common.base.BaseServiceImpl;
import com.fekpal.common.base.ExampleWrapper;
import com.fekpal.dao.mapper.PersonMapper;
import com.fekpal.dao.model.Person;
import com.fekpal.api.PersonService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by APone on 2017/9/5.
 * PersonService实现类
 */
@Service
public class PersonServiceImpl extends BaseServiceImpl<PersonMapper, Person> implements PersonService {

    @Override
    public Person selectByUserId(int id) {
        ExampleWrapper<Person> example = new ExampleWrapper<>();
        example.eq("user_id", id);
        return mapper.selectFirstByExample(example);
    }

    @Override
    public Person selectByNickname(String name) {
        ExampleWrapper<Person> example = new ExampleWrapper<>();
        example.eq("nickname", name);
        return mapper.selectFirstByExample(example);
    }

    @Override
    public List<Person> queryByNickname(String name, int offset, int limit) {
        ExampleWrapper<Person> example = new ExampleWrapper<>();
        example.like("nickname", name);
        return mapper.selectByExample(example, offset, limit);
    }


    @Override
    public boolean isExitNickname(String name) {
        ExampleWrapper<Person> example = new ExampleWrapper<>();
        example.eq("nickname", name);
        int row = mapper.countByExample(example);
        return row >= 1;
    }

    @Override
    public List<Person> loadAllPerson(int offset, int limit) {
        ExampleWrapper<Person> example = new ExampleWrapper<>();
        return mapper.selectByExample(example, offset, limit);
    }
}
