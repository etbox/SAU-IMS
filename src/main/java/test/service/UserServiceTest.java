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
public class UserServiceTest extends BaseServiceTest{

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Before
    public void init() {
        List<User> list= new ArrayList<>();
        list.add(Model.user);
        userService.insertLoop(list);
    }

    @Test
    public void test() {
        System.out.println(userService.selectByPrimaryKey(Model.user.getUserId()));
    }
}
