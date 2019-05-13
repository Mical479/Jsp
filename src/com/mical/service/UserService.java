package com.mical.service;

import com.mical.pojo.User;

import java.util.List;

/**
 * �� �� �ƣ�UserService
 * �� �� ����TODO
 * ����ʱ�䣺2019/5/13 14:48
 * �����ˣ�Mical
 */
public interface UserService {
    /**
     * У���û���¼
     * @param uname �û���
     * @param pwd ����
     * @return ���ز�ѯ�����û���Ϣ
     */
    User checkUserLoginService(String uname, String pwd);

    /**
     * �޸��û�����
     * @param newPwd ������
     * @param uid �û�id
     * @return ����id
     */
    int userChangePwdService(String newPwd, int uid);

    /**
     * ��ȡ�����û���Ϣ
     * @return �����û��б�
     */
    List<User> userShowService();

    /**
     * �û�ע��
     * @param u �û�����
     * @return ����int
     */
    int userRegService(User u);
}
