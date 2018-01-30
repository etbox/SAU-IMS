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
    public void select(){

    }

    @Test
    public void update(){

    }

    @Test
    public void delete(){

    }

    @Test
    public void count(){

    }
}
