package test.dao;

import com.fekpal.domain.ExampleWrapper;
import com.fekpal.domain.pojo.User;
import org.junit.Test;

/**
 * Created by APone on 2018/1/31 18:43.
 */
public class ExampleTest extends BaseDaoTest {

    @Test
    public void test(){
        ExampleWrapper<User> wrapper = new ExampleWrapper<>();
        wrapper.eq("user_name", "23").and().eq("password", "12");
        System.out.println(wrapper.getSqlSegment());
    }
}
