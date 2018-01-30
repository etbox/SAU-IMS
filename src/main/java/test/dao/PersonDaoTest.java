package test.dao;

import com.fekpal.dao.user.ClubDao;
import com.fekpal.dao.LikeClubDao;
import com.fekpal.dao.user.PersonDao;
import com.fekpal.dao.user.UserDao;
import com.fekpal.domain.pojo.Person;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static test.dao.Domain.club;
import static test.dao.Domain.person;
import static test.dao.Domain.user;

/**
 * Created by APone on 2017/8/28.
 */
public class PersonDaoTest extends BaseDaoTest {

    @Autowired
    private PersonDao personDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ClubDao clubDao;

    @Autowired
    private LikeClubDao likeClubDao;

    @Before
    public void init() {
        userDao.insert(user);
        person.setUserId(user.getUserId());
        personDao.insert(person);
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
