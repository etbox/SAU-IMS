package test.dao;

import com.fekpal.cons.AvailableState;
import com.fekpal.dao.message.MessageDao;
import com.fekpal.dao.user.UserDao;
import com.fekpal.domain.pojo.Message;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static test.dao.Domain.message;
import static test.dao.Domain.user;

/**
 * Created by APone on 2017/9/4.
 */
public class MessageDaoTest extends BaseDaoTest {

    @Autowired
    private MessageDao messageDao;

    @Autowired
    private UserDao userDao;

    @Before
    public void init() {
        userDao.insert(user);
        message.setUserId(user.getUserId());
        messageDao.addMessage(message);
    }

    @Test
    public void testMessageDao() {
        List<Message> messageList = messageDao.findMessageByMessageTitle("紧急", user.getUserId(),0, 2);
        System.out.println(messageList.size());
        Assert.assertNotNull(messageList.get(0));
        System.out.println(messageList);
        messageList = messageDao.findMessageByMessageTitle("1", user.getUserId(),0, 2);
        System.out.println(messageList.size());

        message = messageDao.getMessageByMessageId(message.getMessageId());
        Assert.assertNotNull(message);
        System.out.println(message);
        message = messageDao.getMessageByMessageId(0);
        Assert.assertNull(message);

        messageList = messageDao.getMessagesByUserId(user.getUserId(), 0, 2);
        System.out.println(messageList.size());
        Assert.assertNotNull(messageList.get(0));
        System.out.println(messageList);
        messageList = messageDao.getMessagesByUserId(0, 0, 2);
        System.out.println(messageList.size());

        message = messageDao.getMessagesByUserId(user.getUserId(), 0, 2).get(0);
        message.setMessageState(AvailableState.UNAVAIABLE);
        messageDao.updateMessage(message);
        message = messageDao.getMessageByMessageId(message.getMessageId());
        System.out.println(message);
    }
}
