package com.mical.dao.impl;

import com.mical.dao.UserDao;
import com.mical.pojo.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 类 名 称：UserDaoImpl
 * 类 描 述：TODO
 * 创建时间：2019/5/13 14:51
 * 创建人：Mical
 */
@SuppressWarnings("all")
public class UserDaoImpl implements UserDao {

    private static Connection conn = null;

    static {
        //加载驱动
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useSSL=false&serverTimezone=GMT", "root", "hua");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //根据用户名和密码查询用户信息
    @Override
    public User checkUserLoginDao(String uname, String pwd) {

        PreparedStatement ps = null;
        ResultSet rs = null;

        //声明变量
        User u = null;

        try {
            //创建sql命令
            String sql = "select * from t_user where uname=? and pwd=?";

            //创建sql命令对象
            ps = conn.prepareStatement(sql);

            //给占位符赋值
            ps.setString(1, uname);
            ps.setString(2, pwd);

            //执行sql
            rs = ps.executeQuery();

            //遍历结果
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
//            关闭资源
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
