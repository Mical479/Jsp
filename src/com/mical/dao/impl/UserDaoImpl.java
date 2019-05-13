package com.mical.dao.impl;

import com.mical.dao.UserDao;
import com.mical.pojo.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * �� �� �ƣ�UserDaoImpl
 * �� �� ����TODO
 * ����ʱ�䣺2019/5/13 14:51
 * �����ˣ�Mical
 */
@SuppressWarnings("all")
public class UserDaoImpl implements UserDao {

    private static Connection conn = null;

    static {
        //��������
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useSSL=false&serverTimezone=GMT", "root", "hua");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //�����û����������ѯ�û���Ϣ
    @Override
    public User checkUserLoginDao(String uname, String pwd) {

        PreparedStatement ps = null;
        ResultSet rs = null;

        //��������
        User u = null;

        try {
            //����sql����
            String sql = "select * from t_user where uname=? and pwd=?";

            //����sql�������
            ps = conn.prepareStatement(sql);

            //��ռλ����ֵ
            ps.setString(1, uname);
            ps.setString(2, pwd);

            //ִ��sql
            rs = ps.executeQuery();

            //�������
            while (rs.next()) {
                u = new User();
                u.setUid(rs.getInt("uid"));
                u.setUname(rs.getString("uname"));
                u.setPwd(rs.getString("pwd"));
                u.setSex(rs.getString("sex"));
                u.setAge(rs.getInt("age"));
                u.setBirth(rs.getString("birth"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            �ر���Դ
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return u;
    }

    @Override
    public int userChangePwdDao(String newPwd, int uid) {
        PreparedStatement ps = null;
        int index = -1;
        try{
            String sql = "update t_user set pwd=? where uid=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, newPwd);
            ps.setInt(2, uid);
            index = ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return index;
    }

    @Override
    public List<User> userShowDao() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<User> lu = null;

        try{
            String sql = "select * from t_user";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            lu = new ArrayList<>();
            while (rs.next()){
                User u = new User();
                u.setUid(rs.getInt("uid"));
                u.setUname(rs.getString("uname"));
                u.setPwd(rs.getString("pwd"));
                u.setSex(rs.getString("sex"));
                u.setAge(rs.getInt("age"));
                u.setBirth(rs.getString("birth"));
                lu.add(u);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return lu;
    }

    @Override
    public int userRegDao(User u) {
        PreparedStatement ps = null;

        int index = -1;

        try{
            String sql = "insert into t_user values(default, ?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, u.getUname());
            ps.setString(2, u.getPwd());
            ps.setString(3, u.getSex());
            ps.setInt(4, u.getAge());
            ps.setString(5, u.getBirth());

            index = ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return index;
    }
}
