package test.service;

import com.fekpal.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static test.dao.Domain.person;
import static test.dao.Domain.user;

/**
 * Created by APone on 2017/9/5.
 */
public class UserServiceTest extends BaseServiceTest{

    @Autowired
    private UserService userService;

    @Before
    public void init() {
        userService.addNewPerson(person);
    }

    @Test
    public void test() {

    }
}
