package com.mical.service.impl;

import com.mical.dao.UserDao;
import com.mical.dao.impl.UserDaoImpl;
import com.mical.pojo.User;
import com.mical.service.UserService;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * 类 名 称：UserServiceImpl
 * 类 描 述：TODO
 * 创建时间：2019/5/13 14:49
 * 创建人：Mical
 */
public class UserServiceImpl implements UserService {

    //声明日志对象
    private Logger logger = Logger.getLogger(UserServiceImpl.class);

    //声明DAO层对象
    private UserDao ud = new UserDaoImpl();

    //用户登录
    @Override
    public User checkUserLoginService(String uname, String pwd) {
        //打印日志
        logger.debug(uname + "发起了登录请求");
        User u = ud.checkUserLoginDao(uname, pwd);
        //判断
        if (u != null) {
            logger.debug(uname + "登录成功");
        } else {
            logger.debug(uname + "登录失败");
        }
        return u;
    }

    @Override
    public int userChangePwdService(String newPwd, int uid) {
        logger.debug(uid + ": 发起密码修改");
        int index = ud.userChangePwdDao(newPwd, uid);
        if (index > 0) {
            logger.debug(uid + ": 密码修改成功");
        } else {
            logger.debug(uid + ": 密码修改失败");
        }
        return index;
    }

    @Override
    public List<User> userShowService() {
        List<User> lu = ud.userShowDao();
        logger.debug("显示所有用户信息：" + lu);
        return lu;
    }

    @Override
    public int userRegService(User u) {
        return ud.userRegDao(u);
    }
}
