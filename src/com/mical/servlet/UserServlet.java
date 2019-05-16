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
 * �� �� �ƣ�UserServlet
 * �� �� ����TODO
 * ����ʱ�䣺2019/5/13 14:33
 * Servlet���ض����Ƿ�������Ŀ¼������ת������Ŀ��Ŀ¼
 * �����ˣ�Mical
 */
@WebServlet("/user")
public class UserServlet extends HttpServlet {

    //������־����
    private Logger logger = Logger.getLogger(UserServlet.class);

    //��ȡservice�����
    private UserService us = new UserServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //������������ʽ
        req.setCharacterEncoding("utf-8");
        //������Ӧ�����ʽ
        resp.setContentType("text/html;charset=utf-8");
        //��ȡ������
        String oper = req.getParameter("oper");

        if ("login".equals(oper)) {
            //���õ�¼������
            checkUserLogin(req, resp);
        } else if ("out".equals(oper)) {
            //�����˳�����
            userOut(req, resp);
        } else if ("reg".equals(oper)) {
            //����ע�᷽��
            userReg(req, resp);
        } else if ("pwd".equals(oper)) {
            //���������޸ķ���
            userChangePwd(req, resp);
        } else if ("show".equals(oper)) {
            //������ʾ�����û�����
            userShow(req, resp);
        } else {
            logger.debug("û���ҵ���Ӧ�Ĳ�������" + oper);
        }
    }

    private void userReg(HttpServletRequest req, HttpServletResponse resp) {
        //��ȡ������Ϣ
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

        //����������Ϣ������ service ����
        int index = us.userRegService(u);

        //��Ӧ������
        if (index > 0) {
            //��ȡsession
            HttpSession hs = req.getSession();
            hs.setAttribute("flag", 2);
            System.out.println(u);
            //�ض���
            try {
                resp.sendRedirect("/jsp/login.jsp");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void userShow(HttpServletRequest req, HttpServletResponse resp) {
        //��������
        //����service
        List<User> lu = us.userShowService();
        if (lu != null) {
            //����ѯ���û����ݱ��浽 request ����
            req.setAttribute("lu", lu);
            try {
                req.getRequestDispatcher("/main/user/showUser.jsp").forward(req, resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void userChangePwd(HttpServletRequest req, HttpServletResponse resp) {
        //��ȡ����������
        String newPwd = req.getParameter("newPwd");
        //��session�л�ȡ�û���Ϣ
        User u = (User) req.getSession().getAttribute("user");
        int uid = u.getUid();
        //�������󣬵���service������
        int index = us.userChangePwdService(newPwd, uid);
        if (index > 0) {
            //��ȡsession����
            HttpSession hs = req.getSession();
            hs.setAttribute("flag", 1);

            //�ض��򵽵�¼ҳ��
            try {
                resp.sendRedirect("/jsp/login.jsp");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void userOut(HttpServletRequest req, HttpServletResponse resp) {
        //��ȡsession����
        HttpSession session = req.getSession();
        //ǿ������session
        session.invalidate();
        //�ض��򵽵�¼ҳ��
        try {
            resp.sendRedirect("/jsp/login.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void checkUserLogin(HttpServletRequest req, HttpServletResponse resp) {
        //��ȡ������Ϣ
        String uname = req.getParameter("uname");
        String pwd = req.getParameter("pwd");
        //У��
        User u = us.checkUserLoginService(uname, pwd);
        if (u != null) {
            //��ȡsession����
            HttpSession hs = req.getSession();
            //���û����ݴ洢��session��
            hs.setAttribute("user", u);
            //�ض���
            try {
                resp.sendRedirect("/jsp/main/main.jsp");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            //��ӱ�ʶ����request��
            req.setAttribute("flag", 0);
            //����ת��
            try {
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
