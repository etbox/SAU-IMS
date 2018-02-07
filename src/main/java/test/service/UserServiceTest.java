package test.service;

import com.fekpal.api.UserService;
import com.fekpal.dao.mapper.UserMapper;
import com.fekpal.dao.model.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import test.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by APone on 2017/9/5.
 */
public class UserServiceTest extends BaseServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Before
    public void init() {
        System.out.println("4568985");
        userService.insert(Model.user);
        System.out.println("123123");
    }


    @Test
    public void test() {
        System.out.println(userService.selectByPrimaryKey(Model.user.getUserId()));
        //Model.user.setPassword("I am cortana");
        //userService.updateByPrimaryKey(Model.user);
        //System.out.println(userService.selectByEmail(Model.user.getEmail()));
    }
}
