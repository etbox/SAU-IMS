package test.dao;

import com.fekpal.dao.user.UserDao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static test.dao.Domain.user;

/**
 * Created by APone on 2017/8/16.
 */
public class UserDaoTest extends BaseDaoTest {

    @Autowired
    private UserDao userDao;


    @Before
    public void init() {
        userDao.add(user);
    }

    @Test
    public void testUserDao() {

        System.out.println(userDao.getUserByEmail("zjboy@163.com").toString());
        System.out.println(userDao.getUserByUserId(user.getUserId()).toString());
        System.out.println(userDao.getUserByUserName("zjboy").toString());
        System.out.println(userDao.getUserByIdentity("zjboy", "123456").toString());

        Assert.assertNull("error1", userDao.getUserByEmail("zj"));
        Assert.assertNull("error2", userDao.getUserByUserId(0));
        Assert.assertNull("error3", userDao.getUserByUserName("s"));
        Assert.assertNull("error4", userDao.getUserByIdentity("12", "43"));
        Assert.assertNull("error5", userDao.getUserByIdentity("12", "123456"));
        Assert.assertNull("error6", userDao.getUserByIdentity("zjboy", "43"));

        Assert.assertTrue(userDao.isExit("zjboy"));
        Assert.assertTrue(userDao.exitEmail("zjboy@163.com"));
        Assert.assertFalse(userDao.isExit("zjbo"));
        Assert.assertFalse(userDao.exitEmail("zjboy@163.co"));

        user.setPassword("12");
        userDao.update(user);
        System.out.println(user.toString());

        Assert.assertNull("密码错误", userDao.getUserByIdentity("zjboy", "123456"));
        Assert.assertNotNull("密码正确", userDao.getUserByIdentity("zjboy", "12"));
        System.out.println(userDao.getUserByIdentity("zjboy", "12").toString());
    }
}
