package com.mical.servlet;

import com.mical.pojo.User;
import com.mical.service.UserService;
import com.mical.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * 类 名 称：UserServlet
 * 类 描 述：TODO
 * 创建时间：2019/5/13 14:33
 * Servlet的重定向是服务器根目录，请求转发是项目根目录
 * 创建人：Mical
 */
@WebServlet("/user")
public class UserServlet extends HttpServlet {

    //声明日志对象
    private Logger logger = Logger.getLogger(UserServlet.class);

    //获取service层对象
    private UserService us = new UserServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置请求编码格式
        req.setCharacterEncoding("utf-8");
        //设置响应编码格式
        resp.setContentType("text/html;charset=utf-8");
        //获取操作符
        String oper = req.getParameter("oper");

        if ("login".equals(oper)) {
            //调用登录处理方法
            checkUserLogin(req, resp);
        } else if ("out".equals(oper)) {
            //调用退出功能
            userOut(req, resp);
        } else if ("reg".equals(oper)) {
            //调用注册方法
            userReg(req, resp);
        } else if ("pwd".equals(oper)) {
            //调用密码修改方法
            userChangePwd(req, resp);
        } else if ("show".equals(oper)) {
            //调用显示所有用户功能
            userShow(req, resp);
        } else {
            logger.debug("没有找到对应的操作符：" + oper);
        }
    }

    private void userReg(HttpServletRequest req, HttpServletResponse resp) {
        //获取请求信息
        String uname = req.getParameter("uname");
        String pwd = req.getParameter("pwd");
        String sex = req.getParameter("sex");
        int age = req.getParameter("age").equals("") ? 0 : Integer.parseInt(req.getParameter("age"));
        String birth = req.getParameter("birth");
        String[] bs = null;
        if (!birth.equals("")) {
            bs = birth.split("/");
            birth = bs[2] + "-" + bs[0] + "-" + bs[1];
        }

        User u = new User(0, uname, pwd, sex, age, birth);

        //处理请求信息，调用 service 处理
        int index = us.userRegService(u);

        //响应处理结果
        if (index > 0) {
            //获取session
            HttpSession hs = req.getSession();
            hs.setAttribute("flag", 2);
            System.out.println(u);
            //重定向
            try {
                resp.sendRedirect("/jsp/login.jsp");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void userShow(HttpServletRequest req, HttpServletResponse resp) {
        //处理请求
        //调用service
        List<User> lu = us.userShowService();
        if (lu != null) {
            //将查询的用户数据保存到 request 对象
            req.setAttribute("lu", lu);
            try {
                req.getRequestDispatcher("/main/user/showUser.jsp").forward(req, resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void userChangePwd(HttpServletRequest req, HttpServletResponse resp) {
        //获取新密码数据
        String newPwd = req.getParameter("newPwd");
        //从session中获取用户信息
        User u = (User) req.getSession().getAttribute("user");
        int uid = u.getUid();
        //处理请求，调用service对象处理
        int index = us.userChangePwdService(newPwd, uid);
        if (index > 0) {
            //获取session对象
            HttpSession hs = req.getSession();
            hs.setAttribute("flag", 1);

            //重定向到登录页面
            try {
                resp.sendRedirect("/jsp/login.jsp");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void userOut(HttpServletRequest req, HttpServletResponse resp) {
        //获取session对象
        HttpSession session = req.getSession();
        //强制销毁session
        session.invalidate();
        //重定向到登录页面
        try {
            resp.sendRedirect("/jsp/login.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void checkUserLogin(HttpServletRequest req, HttpServletResponse resp) {
        //获取请求信息
        String uname = req.getParameter("uname");
        String pwd = req.getParameter("pwd");
        //校验
        User u = us.checkUserLoginService(uname, pwd);
        if (u != null) {
            //获取session对象
            HttpSession hs = req.getSession();
            //将用户数据存储到session中
            hs.setAttribute("user", u);
            //重定向
            try {
                resp.sendRedirect("/jsp/main/main.jsp");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            //添加标识符到request中
            req.setAttribute("flag", 0);
            //请求转发
            try {
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
