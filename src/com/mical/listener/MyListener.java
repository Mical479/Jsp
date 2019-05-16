package com.mical.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 类 名 称：MyListener
 * 类 描 述：监听器，监听在线人数
 * 创建时间：2019/5/16 16:32
 * 创建人：Mical
 */
@WebListener
public class MyListener implements HttpSessionListener, ServletContextListener {
    //session被创建时人数自增
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        //获取 ServletContext 对象
        ServletContext sc = se.getSession().getServletContext();
        //获取在线统计人数的变量
        int count = (int) sc.getAttribute("count");
        //自增
        sc.setAttribute("count", ++count);
    }

    //session被销毁时，人数自减
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        //获取 ServletContext 对象
        ServletContext sc = se.getSession().getServletContext();
        //获取在线统计人数的变量
        int count = (int) sc.getAttribute("count");
        //自增
        sc.setAttribute("count", --count);
    }

    //application对象初始化
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //获取application
        ServletContext sc = sce.getServletContext();
        //在 application 对象存储变量用来统计在线人数
        sc.setAttribute("count", 0);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
