package com.fekpal.service.impl;

import com.fekpal.dao.user.ClubDao;
import com.fekpal.domain.Club;
import com.fekpal.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by APone on 2017/9/5.
 * ClubService实现类
 */
@Service
public class ClubServiceImpl implements ClubService {

    @Autowired
    private ClubDao clubDao;

    @Override
    public Club getClubByClubId(int clubId) {
        return clubDao.getClubByClubId(clubId);
    }

    @Override
    public Club getClubByClubName(String clubName) {
        return clubDao.getClubByClubName(clubName);
    }

    @Override
    public Club getClubByUserId(int userId) {
        return clubDao.getClubByUserId(userId);
    }

    @Override
    public List<Club> findClubByClubName(String clubName, int start, int count) {
        return clubDao.findClubByClubName(clubName, start, count);
    }

    @Override
    public boolean checkSameClubName(String clubName) {
        return clubDao.exitClubName(clubName);
    }

    @Override
    public void updateClubInfo(Club club) {
        clubDao.update(club);
    }

    @Override
    public void likeNumberPlus(int clubId) {
        clubDao.update(clubId);
    }

    @Override
    public List<Club> loadAllClub(int start, int count) {
        return clubDao.loadAll(start, count);
    }
}
