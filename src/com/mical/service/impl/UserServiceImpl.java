package com.mical.service.impl;

import com.mical.dao.UserDao;
import com.mical.dao.impl.UserDaoImpl;
import com.mical.pojo.User;
import com.mical.service.UserService;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * �� �� �ƣ�UserServiceImpl
 * �� �� ����TODO
 * ����ʱ�䣺2019/5/13 14:49
 * �����ˣ�Mical
 */
public class UserServiceImpl implements UserService {

    //������־����
    private Logger logger = Logger.getLogger(UserServiceImpl.class);

    //����DAO�����
    private UserDao ud = new UserDaoImpl();

    //�û���¼
    @Override
    public User checkUserLoginService(String uname, String pwd) {
        //��ӡ��־
        logger.debug(uname + "�����˵�¼����");
        User u = ud.checkUserLoginDao(uname, pwd);
        //�ж�
        if (u != null) {
            logger.debug(uname + "��¼�ɹ�");
        } else {
            logger.debug(uname + "��¼ʧ��");
        }
        return u;
    }

    @Override
    public int userChangePwdService(String newPwd, int uid) {
        logger.debug(uid + ": ���������޸�");
        int index = ud.userChangePwdDao(newPwd, uid);
        if (index > 0) {
            logger.debug(uid + ": �����޸ĳɹ�");
        } else {
            logger.debug(uid + ": �����޸�ʧ��");
        }
        return index;
    }

    @Override
    public List<User> userShowService() {
        List<User> lu = ud.userShowDao();
        logger.debug("��ʾ�����û���Ϣ��" + lu);
        return lu;
    }

    @Override
    public int userRegService(User u) {
        return ud.userRegDao(u);
    }
}
