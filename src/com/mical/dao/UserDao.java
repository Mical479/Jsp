package com.mical.dao;

import com.mical.pojo.User;

import java.util.List;

/**
 * 类 名 称：UserDao
 * 类 描 述：TODO
 * 创建时间：2019/5/13 14:50
 * 创建人：Mical
 */
public interface UserDao {

    /**
     * 根据用户名和密码查询用户信息
     * @param uname 用户名
     * @param pwd 密码
     * @return 返回查询到的用户信息
     */
    User checkUserLoginDao(String uname, String pwd);

    /**
     * 根据用户id修改用户密码
     * @param newPwd 新密码
     * @param uid 用户id
     * @return 返回id
     */
    int userChangePwdDao(String newPwd, int uid);

    /**
     * 获取所有用户信息
     * @return 返回用户列表
     */
    List<User> userShowDao();

    /**
     * 用户注册
     * @param u 用户对象
     * @return 返回int
     */
    int userRegDao(User u);
}
