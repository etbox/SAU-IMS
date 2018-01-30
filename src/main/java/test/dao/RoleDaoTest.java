package test.dao;

import com.fekpal.dao.RoleDao;
import com.fekpal.domain.pojo.Authority;
import com.fekpal.domain.pojo.Resource;
import com.fekpal.domain.pojo.Role;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * Created by APone on 2017/8/17.
 */
public class RoleDaoTest extends BaseDaoTest {

    @Autowired
    private RoleDao roleDao;

    @Before
    public void init() {

    }

    @Test
    public void testGetRole() {
        List<Role> roleList = roleDao.loadAll();
        for (Role role : roleList) {
            System.out.println(role.getRoleId() + " " + role.getRoleName() + " " + role.getRoleAvailable());

            for (Authority authority : role.getAuthorityList()) {
                System.out.println(authority.getAuthorityId() + " " + authority.getAuthorityName() + " " + authority.getAuthorityAvailable());

                for (Resource resource : authority.getResourceList()) {
                    System.out.println(resource.getResourceId() + " " + resource.getResourceURL() + " " + resource.getResourceName() + " " + resource.getResourceAvailable());
                }
            }
            System.out.println();
        }
    }
}
