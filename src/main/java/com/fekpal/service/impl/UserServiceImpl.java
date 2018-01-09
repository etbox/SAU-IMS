package com.fekpal.service.impl;

import com.fekpal.cons.SystemRole;
import com.fekpal.dao.*;
import com.fekpal.domain.*;
import com.fekpal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by APone on 2017/9/5.
 * UserService实现类
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PersonDao personDao;

    @Autowired
    private ClubDao clubDao;

    @Autowired
    private SauDao sauDao;

    @Autowired
    private ClubAuditDao clubAuditDao;

    @Override
    public User getUserByUserId(int userId) {
        int authority = userDao.getUserByUserId(userId).getAuthority();

        User user = new User();
        switch (authority) {
            case SystemRole.PERSON:
                //user = personDao.getPersonAllInfoByUserId(userId);
                break;
            case SystemRole.CLUB:
                ///user = clubDao.getClubAllInfoByUserId(userId);
                break;
            case SystemRole.SAU:
                //user = sauDao.getSauAllInfoByUserId(userId);
                break;
            default:
                user = new User();
                break;
        }

        return user;
    }

    @Override
    public User getUserByUserName(String userName) {
        return userDao.getUserByUserName(userName);
    }

    @Override
    public User getUserByUserNameAndPassword(String userName, String password) {
        return userDao.getUserByIdentity(userName, password);
    }

    @Override
    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    @Override
    public void addNewPerson(Person person) {
        userDao.add(person);
        personDao.add(person);
    }

    @Override
    public void addNewClub(Club club) {
        userDao.add(club);
        clubDao.add(club);
    }

    @Override
    public void addNewSau(Sau sau) {
        userDao.add(sau);
        sauDao.add(sau);
    }

    @Override
    public void updateUserInfo(User user) {
        userDao.update(user);
    }

    @Override
    public boolean checkSameAccount(String userName) {
        return userDao.isExit(userName);
    }

    @Override
    public boolean checkSameEmail(String email) {
        return userDao.exitEmail(email);
    }

    @Override
    public List<User> loadAllUser() {
        return userDao.loadAll(0, 50);
    }
}
