package test.dao;

import com.fekpal.dao.user.ClubDao;
import com.fekpal.dao.user.UserDao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static test.dao.Domain.club;
import static test.dao.Domain.user;

/**
 * Created by APone on 2017/8/21.
 */
public class ClubDaoTest extends BaseDaoTest {

    @Autowired
    ClubDao clubDao;

    @Autowired
    UserDao userDao;

    @Before
    public void init() {
        userDao.insert(user);
        club.setUserId(user.getUserId());
        clubDao.insert(club);
    }

    @Test
    public void testClubDao() {
/*
        Assert.assertNull(clubDao.getClubByClubId(10));
        System.out.println((club = clubDao.getClubByClubId(club.getClubId())).toString());
        Assert.assertTrue(!clubDao.findClubByClubName("T社", 0, 2).isEmpty());
        Assert.assertTrue(clubDao.findClubByClubName("zj", 0, 2).isEmpty());


        System.out.println(clubDao.getClubByClubName(club.getClubName()).toString());
        Assert.assertNull(clubDao.getClubByClubName("asd"));
        Assert.assertNotNull(clubDao.getClubByUserId(user.getUserId()));

        user.setUserId(0);
        Assert.assertNull(clubDao.getClubByUserId(user.getUserId()));
        user.setUserId(club.getUserId());
        Assert.assertNotNull(clubDao.getClubByUserId(user.getUserId()));
        System.out.println(clubDao.getClubByUserId(user.getUserId()));

        clubDao.update(club.getClubId());
        clubDao.update(club.getClubId());
        clubDao.update(club.getClubId());
        clubDao.update(club.getClubId());

        System.out.println(club.toString());
        club = clubDao.getClubByUserId(user.getUserId());
        System.out.println(club.toString());

        Assert.assertFalse(clubDao.exitClubName("ip"));
        Assert.assertTrue(clubDao.exitClubName("IT社"));

        Assert.assertTrue(!clubDao.loadAll(0, 2).isEmpty());

        club.setClubName("呵呵社");
        club.setDescription("注解在此");
        club.setMembers(club.getMembers() + 1);
        clubDao.update(club);

        Assert.assertNotNull(clubDao.getClubByClubName("呵呵社"));
        Assert.assertNull(clubDao.getClubByClubName("IT社"));
        System.out.println(clubDao.getClubByClubName("呵呵社").toString());*/
    }
}
