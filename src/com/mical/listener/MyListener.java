package com.mical.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * �� �� �ƣ�MyListener
 * �� �� ������������������������
 * ����ʱ�䣺2019/5/16 16:32
 * �����ˣ�Mical
 */
@WebListener
public class MyListener implements HttpSessionListener, ServletContextListener {
    //session������ʱ��������
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        //��ȡ ServletContext ����
        ServletContext sc = se.getSession().getServletContext();
        //��ȡ����ͳ�������ı���
        int count = (int) sc.getAttribute("count");
        //����
        sc.setAttribute("count", ++count);
    }

    //session������ʱ�������Լ�
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        //��ȡ ServletContext ����
        ServletContext sc = se.getSession().getServletContext();
        //��ȡ����ͳ�������ı���
        int count = (int) sc.getAttribute("count");
        //����
        sc.setAttribute("count", --count);
    }

    //application�����ʼ��
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //��ȡapplication
        ServletContext sc = sce.getServletContext();
        //�� application ����洢��������ͳ����������
        sc.setAttribute("count", 0);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
