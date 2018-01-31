package com.fekpal.service.impl;

import com.fekpal.dao.LikeClubDao;
import com.fekpal.dao.user.PersonDao;
import com.fekpal.domain.pojo.Person;
import com.fekpal.domain.pojo.User;
import com.fekpal.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by APone on 2017/9/5.
 * PersonService实现类
 */
@Service
public class PersonServiceImpl extends BaseServiceImpl<PersonDao,Person> implements PersonService {

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
