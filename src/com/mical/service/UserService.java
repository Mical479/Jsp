package com.mical.service;

import com.mical.pojo.User;

import java.util.List;

/**
 * 类 名 称：UserService
 * 类 描 述：TODO
 * 创建时间：2019/5/13 14:48
 * 创建人：Mical
 */
public interface UserService {
    /**
     * 校验用户登录
     * @param uname 用户名
     * @param pwd 密码
     * @return 返回查询到的用户信息
     */
    User checkUserLoginService(String uname, String pwd);

    /**
     * 修改用户密码
     * @param newPwd 新密码
     * @param uid 用户id
     * @return 返回id
     */
    int userChangePwdService(String newPwd, int uid);

    /**
     * 获取所有用户信息
     * @return 返回用户列表
     */
    List<User> userShowService();

    /**
     * 用户注册
     * @param u 用户对象
     * @return 返回int
     */
    int userRegService(User u);
}
