package com.fekpal.service.impl;

import com.fekpal.common.base.BaseServiceImpl;
import com.fekpal.dao.mapper.ClubMapper;
import com.fekpal.common.base.ExampleWrapper;
import com.fekpal.dao.model.Club;
import com.fekpal.api.ClubService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by APone on 2017/9/5.
 * ClubService实现类
 */
@Service
public class ClubServiceImpl extends BaseServiceImpl<ClubMapper, Club> implements ClubService {

    @Override
    public Club selectByClubName(String clubName) {
        ExampleWrapper<Club> example = new ExampleWrapper<>();
        example.eq("club_name", clubName);
        return mapper.selectFirstByExample(example);
    }

    @Override
    public Club selectByUserId(int id) {
        ExampleWrapper<Club> example = new ExampleWrapper<>();
        example.eq("user_id", id);
        return mapper.selectFirstByExample(example);
    }

    @Override
    public List<Club> queryByClubName(String clubName, int offset, int limit) {
        ExampleWrapper<Club> example = new ExampleWrapper<>();
        example.like("club_name", clubName);
        return mapper.selectByExample(example, offset, limit);
    }

    @Override
    public boolean isExitClubName(String clubName) {
        ExampleWrapper<Club> example = new ExampleWrapper<>();
        example.eq("club_name", clubName);
        int row = mapper.countByExample(example);
        return row >= 1;
    }

    @Override
    public List<Club> loadAllClub(int offset, int limit) {
        ExampleWrapper<Club> example = new ExampleWrapper<>();
        return mapper.selectByExample(example, offset, limit);
    }
}
