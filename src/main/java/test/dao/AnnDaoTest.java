package test.dao;

import com.fekpal.cons.AuditState;
import com.fekpal.dao.AnniversaryAuditDao;
import com.fekpal.dao.user.ClubDao;
import com.fekpal.domain.AnniversaryAudit;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.List;

import static test.dao.Domain.anniversaryAudit;
import static test.dao.Domain.club;

/**
 * Created by APone on 2017/8/26.
 */
public class AnnDaoTest extends BaseDaoTest {

    @Autowired
    private AnniversaryAuditDao anniversaryAuditDao;

    @Autowired
    private ClubDao clubDao;

    @Before
    public void init() {
        clubDao.add(club);
        anniversaryAuditDao.addAnniversaryAudit(anniversaryAudit);
    }

    @Test
    public void testAnnDao() {

        System.out.println(anniversaryAudit);

        AnniversaryAudit anniversaryAudit1 = anniversaryAuditDao.getAnnAuditingByClubId(anniversaryAudit.getClub().getClubId());
        Assert.assertNotNull(anniversaryAudit1);
        System.out.println(anniversaryAudit1);
        Assert.assertNull(anniversaryAuditDao.getAnnAuditingByClubId(0));

        anniversaryAudit1 = anniversaryAuditDao.getAnnByAnnId(anniversaryAudit.getId());
        Assert.assertNotNull(anniversaryAudit1);
        System.out.println(anniversaryAudit1);
        Assert.assertNull(anniversaryAuditDao.getAnnByAnnId(0));

        List<AnniversaryAudit> list = anniversaryAuditDao.getAnniversaryAuditByState(AuditState.AUDITING, 0, 1);
        List<AnniversaryAudit> list1 = anniversaryAuditDao.getAnniversaryAuditByState(AuditState.PASS, 0, 2);
        List<AnniversaryAudit> list2 = anniversaryAuditDao.getAnniversaryAuditByState(AuditState.REJECT, 0, 2);
        System.out.println(list.isEmpty() + " " + list1.isEmpty() + " " + list2.isEmpty());

        list = anniversaryAuditDao.getAnnByClubId(anniversaryAudit.getClub().getClubId(), 0, 2);
        System.out.println(list.isEmpty());
        System.out.println(list.get(0));
        Assert.assertTrue(anniversaryAuditDao.getAnnByClubId(0, 0, 2).isEmpty());


        list = anniversaryAuditDao.loadAllAnniversaryAudit(0, 10);
        System.out.println(list.isEmpty());
        System.out.println(list.get(0));

        list = anniversaryAuditDao.findAnnByClubName("it", 0, 2);
        Assert.assertFalse(list.isEmpty());
        System.out.println(list.get(0));
        Assert.assertTrue(anniversaryAuditDao.findAnnByClubName("3", 0, 2).isEmpty());

        anniversaryAudit1 = anniversaryAuditDao.getAnnByAnnId(anniversaryAudit.getId());
        anniversaryAudit1.setAuditResult("哈哈");
        anniversaryAudit1.setAuditState(AuditState.PASS);
        anniversaryAudit1.setAuditTime(Timestamp.valueOf("1996-02-03 01:02:10"));
        anniversaryAuditDao.updateAnniversaryAudit(anniversaryAudit1);

        System.out.println(anniversaryAuditDao.getAnnByAnnId(anniversaryAudit1.getId()));
    }
}
