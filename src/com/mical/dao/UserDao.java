package com.mical.dao;

import com.mical.pojo.User;

import java.util.List;

/**
 * �� �� �ƣ�UserDao
 * �� �� ����TODO
 * ����ʱ�䣺2019/5/13 14:50
 * �����ˣ�Mical
 */
public interface UserDao {

    /**
     * �����û����������ѯ�û���Ϣ
     * @param uname �û���
     * @param pwd ����
     * @return ���ز�ѯ�����û���Ϣ
     */
    User checkUserLoginDao(String uname, String pwd);

    /**
     * �����û�id�޸��û�����
     * @param newPwd ������
     * @param uid �û�id
     * @return ����id
     */
    int userChangePwdDao(String newPwd, int uid);

    /**
     * ��ȡ�����û���Ϣ
     * @return �����û��б�
     */
    List<User> userShowDao();

    /**
     * �û�ע��
     * @param u �û�����
     * @return ����int
     */
    int userRegDao(User u);
}
