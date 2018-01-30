package test.dao;

import com.fekpal.dao.DepartmentDao;
import com.fekpal.domain.pojo.Department;
import com.fekpal.domain.pojo.Major;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by APone on 2017/8/22.
 */
public class DepartmentDaoTest extends BaseDaoTest {

    @Autowired
    private DepartmentDao departmentDao;

    @Before
    public void init() {

    }

    @Test
    public void testDepartmentDao() {
        Department departments = departmentDao.getDepartmentByDepartmentId(1);
        Assert.assertNotNull(departments);

        for (Department department : departmentDao.loadAllDepartment()) {
            System.out.println(department.getMajorList().size());
            System.out.println(department.getDepartmentId() + " " + department.getDepartmentName() + " " + department.getDepartmentAvailable());
            for (Major major : department.getMajorList()) {
                System.out.println(major.getMajorId() + " " + major.getMajorName() + " " + major.getMajorAvailable());
            }
        }
    }
}
