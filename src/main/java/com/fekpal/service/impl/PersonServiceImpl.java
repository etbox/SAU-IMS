package com.fekpal.service.impl;

import com.fekpal.common.base.BaseServiceImpl;
import com.fekpal.common.base.CRUDException;
import com.fekpal.common.base.ExampleWrapper;
import com.fekpal.common.constant.FIleDefaultPath;
import com.fekpal.common.constant.Operation;
import com.fekpal.common.session.SessionLocal;
import com.fekpal.common.utils.ImageFileUtil;
import com.fekpal.dao.mapper.PersonMapper;
import com.fekpal.dao.model.Person;
import com.fekpal.api.PersonService;
import com.fekpal.service.model.domain.PersonMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by APone on 2017/9/5.
 * PersonService实现类
 */
@Service
public class PersonServiceImpl extends BaseServiceImpl<PersonMapper, Person> implements PersonService {

    @Autowired
    private HttpSession session;

    @Override
    public String updateLogo(PersonMsg msg) {
        try {
            int uid = SessionLocal.local(session).getUserIdentity().getUid();
            Person person = mapper.selectByPrimaryKey(uid);
            return ImageFileUtil.handle(msg.getLogo(), FIleDefaultPath.PERSON_LOGO_FILE, person.getLogo());
        } catch (Exception e) {
            e.printStackTrace();
            throw new CRUDException(e.getMessage());
        }
    }

    @Override
    public int updatePersonInfo(PersonMsg msg) {
        int uid = SessionLocal.local(session).getUserIdentity().getUid();
        ExampleWrapper<Person> example = new ExampleWrapper<>();
        example.eq("nickname", msg.getNickname()).and().ne("person_id",uid);
        int row = mapper.countByExample(example);

        if  (row != 0  ){
            return Operation.INPUT_INCORRECT;
        }

        Person person = new Person();
        person.setPersonId(uid);
        person.setRealName(msg.getRealName());
        person.setStudentId(msg.getStudentId());
        person.setNickname(msg.getNickname());
        person.setGender(msg.getGender());
        person.setBirthday(msg.getBirthday());
        person.setDepartment(msg.getDepartment());
        person.setMajor(msg.getMajor());
        person.setAddress(msg.getAddress());
        person.setEnrollmentYear(msg.getEnrollmentYear());
        person.setDescription(msg.getDescription());
        row = mapper.updateByPrimaryKeySelective(person);

        if (row > 1) throw new CRUDException("更新普通用户信息异常：" + row);
        return row == 0 ? Operation.FAILED : Operation.SUCCESSFULLY;
    }

    @Override
    public Person selectByPrimaryId() {
        int uid = SessionLocal.local(session).getUserIdentity().getUid();
        return mapper.selectByPrimaryKey(uid);
    }

    @Override
    public Person selectByUserId(int id) {
        ExampleWrapper<Person> example = new ExampleWrapper<>();
        example.eq("user_id", id);
        return mapper.selectFirstByExample(example);
    }

    @Override
    public Person selectByNickname(String name) {
        ExampleWrapper<Person> example = new ExampleWrapper<>();
        example.eq("nickname", name);
        return mapper.selectFirstByExample(example);
    }

    @Override
    public List<Person> queryByNickname(String name, int offset, int limit) {
        ExampleWrapper<Person> example = new ExampleWrapper<>();
        example.like("nickname", name);
        return mapper.selectByExample(example, offset, limit);
    }


    @Override
    public boolean isExitNickname(String name) {
        ExampleWrapper<Person> example = new ExampleWrapper<>();
        example.eq("nickname", name);
        int row = mapper.countByExample(example);
        return row >= 1;
    }

    @Override
    public List<Person> loadAllPerson(int offset, int limit) {
        ExampleWrapper<Person> example = new ExampleWrapper<>();
        return mapper.selectByExample(example, offset, limit);
    }
}
