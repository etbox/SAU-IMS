package test.dao;

import com.fekpal.common.base.ExampleWrapper;
import com.fekpal.common.constant.AvailableState;
import com.fekpal.dao.mapper.MessageMapper;
import com.fekpal.dao.model.Message;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by APone on 2018/2/17 0:19.
 */
public class TestMessage extends TestDao {

    @Autowired
    public MessageMapper messageMapper;

    @Before
    public void init() {
        messageMapper.insert(Model.message);
        List<Message> list = new ArrayList<>();
        list.add(Model.message);
        list.add(Model.message1);
        messageMapper.insertLoop(list);
    }

    @Test
    public void test1() {
        messageMapper.selectByPrimaryKey(Model.message.getMessageId());
        messageMapper.selectByExample(new ExampleWrapper<>(), 0, 10);
        Model.message.setMessageState(AvailableState.UNAVAIABLE);
        messageMapper.updateByPrimaryKey(Model.message);
        messageMapper.selectByExample(new ExampleWrapper<>(), 0, 10);
        Model.message.setMessageState(AvailableState.AUDITING);
        messageMapper.updateByPrimaryKeySelective(Model.message);
        messageMapper.selectByExample(new ExampleWrapper<>(), 0, 10);
        messageMapper.deleteByPrimaryKey(Model.message.getMessageId());
        messageMapper.selectByExample(new ExampleWrapper<>(), 0, 10);
    }
}

