package test.dao;

import com.fekpal.dao.user.UserDao;
import com.fekpal.domain.ExampleWrapper;
import com.fekpal.domain.pojo.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static test.dao.Domain.anUser;
import static test.dao.Domain.user;

/**
 * Created by APone on 2017/8/16.
 */
public class UserDaoTest extends BaseDaoTest {

    @Autowired
    private UserDao userDao;

    @Before
    public void init() {
        userDao.insert(user);
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
