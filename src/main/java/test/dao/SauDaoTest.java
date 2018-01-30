package test.dao;

import com.fekpal.dao.user.SauDao;
import com.fekpal.dao.user.UserDao;
import com.fekpal.domain.pojo.Sau;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static test.dao.Domain.sau;
import static test.dao.Domain.user;

/**
 * Created by APone on 2017/9/3.
 */
public class SauDaoTest extends BaseDaoTest {

    @Autowired
    private SauDao sauDao;

    @Autowired
    private UserDao userDao;

    @Before
    public void init() {
        userDao.insert(user);
        sauDao.insert(sau);
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
