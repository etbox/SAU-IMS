package com.fekpal.service.impl;

import com.fekpal.dao.user.ClubDao;
import com.fekpal.dao.user.UserDao;
import com.fekpal.domain.ExampleWrapper;
import com.fekpal.domain.pojo.Club;
import com.fekpal.domain.pojo.User;
import com.fekpal.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by APone on 2017/9/5.
 * ClubService实现类
 */
@Service
public class ClubServiceImpl extends BaseServiceImpl<ClubDao, Club> implements ClubService {

    @Autowired
    ClubDao clubDao;

    @Autowired
    UserDao userDao;

    /**
     * 根据社团名称获得社团信息记录
     *
     * @param clubName String 社团名称
     * @return 社团信息记录
     */
    @Override
    public Club selectByClubName(String clubName) {
        ExampleWrapper<Club> example = new ExampleWrapper<>();
        example.eq("club_name", clubName);
        return clubDao.selectFirstByExample(example);
    }

    /**
     * 根据社团用户的用户身份id获得社团信息记录
     *
     * @param id int 用户身份id
     * @return 社团信息记录
     */
    @Override
    public Club selectByUserId(int id) {
        ExampleWrapper<Club> example=new ExampleWrapper<>();
        example.eq("user_id",id);
        return clubDao.selectFirstByExample(example);
    }

    /**
     * 插入新的社团用户信息以及用户身份信息
     * 只有插入数等于2才成功，其他一律错误
     *
     * @param user User 用户身份信息
     * @param club Club 社团用户信息
     * @return 插入数量
     */
    @Override
    public int insertInfo(User user, Club club) {

        int row=userDao.insert(user);

        return row;
    }

    @Override
    public List<Club> queryByClubName(String clubName, int offset, int limit) {
        return null;
    }

    @Override
    public boolean isExitClubName(String clubName) {
        return false;
    }

    @Override
    public List<Club> loadAllClub(int offset, int limit) {
        return null;
    }
}
