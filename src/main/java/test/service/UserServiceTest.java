package test.service;

import com.fekpal.domain.pojo.User;
import com.fekpal.service.UserService;
import com.fekpal.web.session.HttpSessionLocal;
import com.fekpal.web.session.SessionNullException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpSession;

import static test.dao.Domain.person;
import static test.dao.Domain.user;

/**
 * Created by APone on 2017/9/5.
 */
public class UserServiceTest extends BaseServiceTest{

    @Autowired
    JedisPool jedisPool;

    Jedis jedis;

    @Before
    public void init() {
        jedis=jedisPool.getResource();
        jedis.set("test","hello redis");
    }

    @Test
    public void test() {
        System.out.println(jedis.get("test"));
        jedis.del("test");
    }
}
