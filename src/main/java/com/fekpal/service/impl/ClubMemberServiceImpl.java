package com.fekpal.service.impl;

import com.fekpal.dao.ClubMemberDao;
import com.fekpal.domain.pojo.ClubMember;
import com.fekpal.domain.pojo.Person;
import com.fekpal.service.ClubMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by APone on 2017/9/17.
 */
@Service
public class ClubMemberServiceImpl implements ClubMemberService {

    @Autowired
    private ClubMemberDao clubMemberDao;

    @Override
    public ClubMember getMemberByPersonAndCLub(int personId, int clubId) {
        return clubMemberDao.getMemberByPersonAndCLub(personId, clubId);
    }

    @Override
    public List<ClubMember> getClubMemberByPersonId(int personId) {
        return clubMemberDao.getClubMemberByPersonId(personId);
    }

    @Override
    public List<Person> getAllMemberByClubId(int clubId) {
        return clubMemberDao.getAllMemberByClubId(clubId);
    }

    @Override
    public ClubMember getMemberById(int id) {
        return clubMemberDao.getMemberById(id);
    }

    @Override
    public void addNewClubMember(ClubMember clubMember) {
        clubMemberDao.addClubMember(clubMember);
    }

    @Override
    public void updateClubMember(ClubMember clubMember) {
        clubMemberDao.updateClubMember(clubMember);
    }

    @Override
    public List<ClubMember> getAllAuditingByClubId(int clubId) {
        return clubMemberDao.getAllAuditingByClubId(clubId);
    }
}
