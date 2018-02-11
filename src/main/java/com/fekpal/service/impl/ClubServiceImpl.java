package com.fekpal.service.impl;

import com.fekpal.common.base.BaseServiceImpl;
import com.fekpal.dao.mapper.ClubMapper;
import com.fekpal.dao.mapper.UserMapper;
import com.fekpal.common.base.ExampleWrapper;
import com.fekpal.dao.model.Club;
import com.fekpal.dao.model.User;
import com.fekpal.api.ClubService;
import com.fekpal.service.model.domain.ClubReg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by APone on 2017/9/5.
 * ClubService实现类
 */
@Service
public class ClubServiceImpl extends BaseServiceImpl<ClubMapper, Club> implements ClubService {

    @Autowired
    ClubMapper clubDao;

    @Autowired
    UserMapper userDao;

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

    @Override
    public int saveRegAuditFile() {
        return 0;
    }

    @Override
    public void sendRegCaptchaByEmail(String email) {

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
