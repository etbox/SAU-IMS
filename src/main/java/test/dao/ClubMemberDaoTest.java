package test.dao;

import com.fekpal.cons.ClubRole;
import com.fekpal.dao.ClubDao;
import com.fekpal.dao.ClubMemberDao;
import com.fekpal.dao.PersonDao;
import com.fekpal.domain.ClubMember;
import com.fekpal.domain.Person;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.List;

import static test.dao.Domain.*;

/**
 * Created by APone on 2017/9/4.
 */
public class ClubMemberDaoTest extends BaseDaoTest {


    @Autowired
    private PersonDao personDao;

    @Autowired
    private ClubDao clubDao;

    @Autowired
    private ClubMemberDao clubMemberDao;

    @Before
    public void init() {
        personDao.add(person);
        clubDao.add(club);
        clubMember.setClub(club);
        clubMember.setPerson(person);
        clubMemberDao.addClubMember(clubMember);
    }

    @Test
    public void testClubMember() {

        List<ClubMember> list;

        list = clubMemberDao.getAllAuditingByClubId(club.getClubId());
        System.out.println(list.size());
        System.out.println(list);
        list = clubMemberDao.getAllAuditingByClubId(0);
        System.out.println(list.size());

        List<Person> list1;
        list1 = clubMemberDao.getAllMemberByClubId(club.getClubId());
        System.out.println(list1.size());
        System.out.println(list1);
        list1 = clubMemberDao.getAllMemberByClubId(0);
        System.out.println(list1.size());


        List<ClubMember> list2;
        list2 = clubMemberDao.getClubMemberByPersonId(person.getPersonId());
        System.out.println(list2.size());
        System.out.println(list2);
        list2 = clubMemberDao.getClubMemberByPersonId(0);
        System.out.println(list2.size());

        clubMember = clubMemberDao.getMemberById(clubMember.getId());
        Assert.assertNotNull(clubMember);
        System.out.println(clubMember);
        clubMember = clubMemberDao.getMemberById(0);
        Assert.assertNull(clubMember);

        clubMember = clubMemberDao.getMemberByPersonAndCLub(person.getPersonId(), club.getClubId());
        Assert.assertNotNull(clubMember);
        System.out.println(clubMember);
        clubMember = clubMemberDao.getMemberByPersonAndCLub(0, club.getClubId());
        Assert.assertNull(clubMember);

        clubMember = clubMemberDao.getMemberByPersonAndCLub(person.getPersonId(), club.getClubId());
        clubMember.setMemberDuty(2);
        clubMember.setLeaveTime(Timestamp.valueOf("2017-08-02 02:03:09"));
        clubMemberDao.updateClubMember(clubMember);
        clubMember = clubMemberDao.getMemberByPersonAndCLub(person.getPersonId(), club.getClubId());
        Assert.assertNotNull(clubMember);
        System.out.println(clubMember);
    }
}
