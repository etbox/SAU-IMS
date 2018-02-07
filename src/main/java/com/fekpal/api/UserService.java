package com.fekpal.api;

import com.fekpal.common.base.BaseService;
import com.fekpal.dao.model.User;
import com.fekpal.web.session.SessionContent;

import java.util.List;

/**
 * Created by APone on 2017/8/25.
 * UserService接口
 * 该接口主要提功增删查改所有角色的基本身份信息
 * （可修改：允许应用人员修改）
 * （可插入：允许应用人员插入）
 * （由系统插入：不允许用户人员操作）
 * （由系统修改：不允许应用人员修改）
 * User（即user数据表）可供操作字段：
 * userId 用户标识        /由系统插入
 * userName 用户名        /可插入
 * password 密码          /可插入，可修改
 * phone 手机号码         /可插入，可修改
 * email 邮箱地址         /可插入，可修改
 * userKey 盐             /由系统插入，由系统修改
 * loginIp 登录ip         /由系统插入，由系统修改
 * loginTime 登录时间     /由系统插入，由系统修改
 * registerIp 注册ip      /由系统插入
 * registerTime 注册时间  /由系统插入
 * authority 用户权限     /由系统插入
 * userState 用户状态     /由系统插入，由系统修改
 */
public interface UserService extends BaseService<User> {

    /**
     * 根据用户名称获得用户身份记录
     *
     * @param user 用户身份记录 传入参数：用户名userName
     * @return 用户身份记录
     */
    User selectByUserName(User user);

    /**
     * 根据用户名和密码获得用户身份记录
     *
     * @param user 用户身份记录 传入参数：用户名userName，密码password
     * @return 用户身份记录
     */
    User selectByUserNameAndPwd(User user);

    /**
     * 根据邮箱地址获得用户身份记录
     *
     * @param user 用户身份记录 传入参数：邮箱地址email
     * @return 用户身份记录
     */
    User selectByEmail(User user);

    /**
     * 判断是否存在相同用户名称
     *
     * @param user 用户身份记录 传入参数：用户名userName
     * @return 是否存在相同名称
     */
    boolean isExitAccount(User user);

    /**
     * 判断是否存在相同的邮箱地址
     *
     * @param user 用户身份记录 传入参数：邮箱地址email
     * @return 是否存在相同邮箱地址
     */
    boolean isExitEmail(User user);

    /**
     * 判断是否存在相同的手机号码
     *
     * @param user 用户身份记录 传入参数：手机号码phone
     * @return 是否存在相同手机号码
     */
    boolean isExitPhone(User user);

    /**
     * 获取所有用户身份记录集，按分页获取
     *
     * @param offset Integer 跳过读数
     * @param limit  Integer 读取数
     * @return 用户身份记录集
     */
    List<User> loadAllUser(Integer offset, Integer limit);
}
