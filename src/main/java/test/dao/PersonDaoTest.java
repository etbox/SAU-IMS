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
    public void testPersonDao() {
/*
        Person p = personDao.getPersonByNickName("佳佳");
        Assert.assertNotNull(p);
        System.out.println(p);
        p = personDao.getPersonByNickName("1");
        Assert.assertNull(p);

        p = personDao.getPersonByPersonId(person.getPersonId());
        Assert.assertNotNull(p);
        System.out.println(p);
        p = personDao.getPersonByPersonId(0);
        Assert.assertNull(p);

        p = personDao.getPersonByUserId(user.getUserId());
        Assert.assertNotNull(p);
        System.out.println(p);
        p = personDao.getPersonByUserId(0);
        Assert.assertNull(p);


        likeClubDao.addLikeClub(user.getUserId(), club.getClubId());
        likeClubDao.addLikeClub(user.getUserId(), 2);//不存在的社团id

        Assert.assertTrue(personDao.exitNickName("佳佳"));
        Assert.assertFalse(personDao.exitNickName("1"));

        List<Integer> integerList = likeClubDao.loadAllLikeByPersonId(user.getUserId());
        System.out.println(integerList.size());
        System.out.println(integerList);
        integerList = likeClubDao.loadAllLikeByPersonId(0);
        System.out.println(integerList.size());

        List<Person> personList = personDao.loadAll(0, 2);
        System.out.println(personList.size());
        System.out.println(personList);

        p = personDao.getPersonByUserId(user.getUserId());
        p.setNickname("哈哈");
        personDao.update(p);
        p = personDao.getPersonByNickName("哈哈");
        System.out.println(p);
*/
    }
}
