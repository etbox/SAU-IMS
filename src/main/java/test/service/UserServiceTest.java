package test.service;

import com.fekpal.dao.mapper.PersonMapper;
import com.fekpal.dao.mapper.UserMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by APone on 2017/9/5.
 */
public class UserServiceTest extends BaseServiceTest {


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PersonMapper personMapper;

    @Before
    public void init() {
        userMapper.insert(Model.user);
        personMapper.insert(Model.person);
    }


    @Test
    public void test() {
    }
}
