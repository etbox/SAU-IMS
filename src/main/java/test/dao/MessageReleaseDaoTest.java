package test.dao;

import com.fekpal.cons.MessageType;
import com.fekpal.cons.ObjectAvailable;
import com.fekpal.dao.MessageDao;
import com.fekpal.dao.MessageReleaseDao;
import com.fekpal.dao.UserDao;
import com.fekpal.domain.MessageRelease;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static test.dao.Domain.*;

/**
 * Created by APone on 2017/9/4.
 */
public class MessageReleaseDaoTest extends BaseDaoTest {

    @Autowired
    private MessageDao messageDao;

    @Autowired
    private MessageReleaseDao messageReleaseDao;

    @Autowired
    private UserDao userDao;

    @Before
    public void init() {
        userDao.add(user);
        userDao.add(anUser);

        message.setUserId(anUser.getUserId());
        message1.setUserId(anUser.getUserId());
        messageDao.addMessage(message);
        messageDao.addMessage(message1);

        messageRelease.setMessage(message);
        messageRelease1.setMessage(message1);
        messageRelease.setReceiveId(user.getUserId());
        messageRelease1.setReceiveId(user.getUserId());

        List<MessageRelease> list = new ArrayList<>();
        list.add(messageRelease);
        list.add(messageRelease1);
        messageReleaseDao.addMessageRelease(list);
    }

    @Test
    public void testMessageReleaseDao() {

        List<MessageRelease> list = messageReleaseDao.findMessageByMessageTitle("通知tg   ", user.getUserId(), 0, 2);
        System.out.println(list.size());
        System.out.println(list);
        list = messageReleaseDao.findMessageByMessageTitle("sdf", user.getUserId(), 0, 2);
        System.out.println(list.size());

        MessageRelease temp = messageReleaseDao.getMessageByMessageReleaseId(messageRelease.getId());
        Assert.assertNotNull(temp);
        System.out.println(temp);
        temp = messageReleaseDao.getMessageByMessageReleaseId(messageRelease1.getId());
        Assert.assertNotNull(temp);
        System.out.println(temp);
        temp = messageReleaseDao.getMessageByMessageReleaseId(0);
        Assert.assertNull(temp);

        list = messageReleaseDao.getMessagesByReceiveId(user.getUserId(), 0, 2);
        System.out.println(list.size());
        System.out.println(list);
        list = messageReleaseDao.getMessagesByReceiveId(0, 0, 2);
        System.out.println(list.size());

        MessageRelease temp1 = new MessageRelease();
        temp1.setAvailable(ObjectAvailable.UNAVAIABLE);
        temp1.setReadFlag(MessageType.HAVE_READ);
        List<Integer> list1 = new ArrayList<>();
        list1.add(messageRelease.getId());
        list1.add(messageRelease1.getId());
        messageReleaseDao.updateMessageRelease(temp1, list1);
        list = messageReleaseDao.getMessagesByReceiveId(user.getUserId(), 0, 2);
        System.out.println(list.size());
        System.out.println(list);
    }
}
