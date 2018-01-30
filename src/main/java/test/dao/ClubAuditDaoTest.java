package test.dao;

import com.fekpal.cons.AuditState;
import com.fekpal.dao.ClubAuditDao;
import com.fekpal.dao.user.ClubDao;
import com.fekpal.domain.pojo.ClubAudit;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.List;

import static test.dao.Domain.club;
import static test.dao.Domain.clubAudit;

/**
 * Created by APone on 2017/8/27.
 */
public class ClubAuditDaoTest extends BaseDaoTest {

    @Autowired
    private ClubAuditDao clubAuditDao;

    @Autowired
    private ClubDao clubDao;

    @Before
    public void init() {
        clubDao.insert(club);
        clubAudit.setClub(club);
        clubAuditDao.addClubAudit(clubAudit);
    }

    @Test
    public void testClubAudit() {

        List<ClubAudit> clubAudits=clubAuditDao.findClubAuditByClubName("IT");
        System.out.println(clubAudits.size());
        System.out.println(clubAudits);
        clubAudits=clubAuditDao.findClubAuditByClubName("dss");
        System.out.println(clubAudits.size());

        clubAudits=clubAuditDao.getClubAuditByClubId(club.getClubId());
        System.out.println(clubAudits.size());
        System.out.println(clubAudits);
        clubAudits=clubAuditDao.getClubAuditByClubId(0);
        System.out.println(clubAudits.size());

        clubAudit = clubAuditDao.getClubAuditById(clubAudit.getId());
        Assert.assertNotNull(clubAudit);
        System.out.println(clubAudit);
        clubAudit=clubAuditDao.getClubAuditById(0);
        Assert.assertNull(clubAudit);


        clubAudits=clubAuditDao.loadAllCLubAudit(0, 1);
        System.out.println(clubAudits.size());
        System.out.println(clubAudits);

        clubAudit=clubAudits.get(0);
        clubAudit.setAuditResult(AuditState.PASS);
        clubAudit.setAuditTime(Timestamp.valueOf("2017-01-01 02:02:02"));
        clubAuditDao.updateClubAudit(clubAudit);
        clubAudit=clubAuditDao.getClubAuditById(clubAudit.getId());
        Assert.assertNotNull(clubAudit);
        System.out.println(clubAudit);
    }
}
