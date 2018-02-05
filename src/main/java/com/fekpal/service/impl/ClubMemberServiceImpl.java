package com.fekpal.service.impl;

import com.fekpal.dao.mapper.ClubMemberMapper;
import com.fekpal.dao.model.ClubMember;
import com.fekpal.dao.model.Person;
import com.fekpal.api.ClubMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by APone on 2017/9/17.
 */
@Service
public class ClubMemberServiceImpl implements ClubMemberService {

    @Autowired
    private ClubMemberMapper clubMemberMapper;

    @Override
    public ClubMember getMemberByPersonAndCLub(int personId, int clubId) {
        return clubMemberMapper.getMemberByPersonAndCLub(personId, clubId);
    }

    @Override
    public List<ClubMember> getClubMemberByPersonId(int personId) {
        return clubMemberMapper.getClubMemberByPersonId(personId);
    }

    @Override
    public List<Person> getAllMemberByClubId(int clubId) {
        return clubMemberMapper.getAllMemberByClubId(clubId);
    }

    @Override
    public ClubMember getMemberById(int id) {
        return clubMemberMapper.getMemberById(id);
    }

    @Override
    public void addNewClubMember(ClubMember clubMember) {
        clubMemberMapper.addClubMember(clubMember);
    }

    @Override
    public void updateClubMember(ClubMember clubMember) {
        clubMemberMapper.updateClubMember(clubMember);
    }

    @Override
    public List<ClubMember> getAllAuditingByClubId(int clubId) {
        return clubMemberMapper.getAllAuditingByClubId(clubId);
    }
}
