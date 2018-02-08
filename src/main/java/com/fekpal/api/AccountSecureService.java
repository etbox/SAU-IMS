package com.fekpal.api;

import com.fekpal.common.base.BaseService;
import com.fekpal.dao.model.User;
import com.fekpal.service.model.domain.AccountRecord;

import java.io.OutputStream;

/**
 * Created by APone on 2018/2/7 0:49.
 * 账号安全服务接口，用于用户登录，登出，安全修改等操作
 */
public interface AccountSecureService extends BaseService<User> {

    /**
     * 用户身份信息进行用户登录
     *
     * @param record 信息封装
     *               传入参数：用户名userName,密码password, 验证码code, 当前时间currentTime
     * @return 是否登录成功
     * 参考参数：Operation.SUCCESSFULLY 成功 Operation.FAILED 失败 Operation.CAPTCHA_INCORRECT 验证码错误
     */
    int login(AccountRecord record);

    /**
     * 生成登录验证码，图片形式
     *
     * @param out 输出流
     */
    void sendLoginCaptchaImage(OutputStream out);

    /**
     * 用户是否仍然处于登录状态
     *
     * @return 是否处于登录状态
     */
    boolean isLogin();

    /**
     * 用户登出系统
     *
     * @return 是否登出成功
     */
    boolean logout();

    /**
     * 重置身份记录密码,适用于未登录的用户
     *
     * @param record 信息封装
     *               传入参数：新密码oldPassword, 验证码code, 当前时间currentTime
     * @return 是否修改成功
     * 参考参数：Operation.SUCCESSFULLY 成功 Operation.FAILED 失败
     */
    int resetPwd(AccountRecord record);

    /**
     * （申请）忘记身份记录密码，适用于未登录用户，根据填写邮箱（查伪)发送验证码
     *
     * @param record 信息封装
     *               传入参数：邮箱email
     * @return 是否可以拥有合法修改
     * 参考参数：Operation.SUCCESSFULLY 成功 Operation.FAILED 失败
     */
    int forgetPwdByEmail(AccountRecord record);

    /**
     * （申请）忘记身份记录密码，适用于未登录用户，根据填写手机（查伪)发送验证码
     *
     * @param record 信息封装
     *               传入参数：手机phone
     * @return 是否可以拥有合法修改
     * 参考参数：Operation.SUCCESSFULLY 成功 Operation.FAILED 失败
     */
    int forgetPwdByPhone(AccountRecord record);

    /**
     * 确认（申请）修改密码，在真正修改密码前的认证
     *
     * @return 是否发送认证
     */
    boolean confirmUpdatePwd();

    /**
     * 更新（修改）密码记录，适用于登录用户
     *
     * @param record 信息封装
     *               传入参数：新密码newPassword，旧密码oldPassword，验证码code, 当前时间currentTime
     * @return 是否更新成功
     * 参考参数：Operation.SUCCESSFULLY 成功 Operation.FAILED 失败
     */
    int updatePwd(AccountRecord record);

    /**
     * 确认（申请）修改邮箱地址，在真正修改邮箱地址前的认证
     *
     * @return 是否发送确认
     */
    boolean confirmUpdateEmail();

    /**
     * 更新（修改）邮箱地址记录，适用于登录用户
     *
     * @param record 信息封装
     *               传入参数：新邮箱email， 验证码code， 当前时间currentTime
     * @return 是否修改成功
     * 参考参数：Operation.SUCCESSFULLY 成功 Operation.FAILED 失败
     */
    int updateEmail(AccountRecord record);

    /**
     * 确认（申请）修改手机号码，在真正修改手机号码前的认证
     *
     * @return 是否发送认证
     */
    boolean confirmUpdatePhone();

    /**
     * 更新（修改）手机号码，适用于登录用户
     *
     * @param record 信息封装
     *               传入参数：新手机phone，验证码code， 当前时间currentTime
     * @return 是否修改成功
     * 参考参数：Operation.SUCCESSFULLY 成功 Operation.FAILED 失败
     */
    int updatePhone(AccountRecord record);
}
