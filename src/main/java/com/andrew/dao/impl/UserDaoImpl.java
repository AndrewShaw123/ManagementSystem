package com.andrew.dao.impl;

import com.andrew.dao.UserDao;
import com.andrew.domain.ShowUser;
import com.andrew.domain.UserInfo;
import com.andrew.domain.UserLogin;
import com.andrew.util.JdbcUtils;
import com.andrew.util.Md5EncryptUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * UserDaoImpl Class
 *
 * @author andrew
 * @date 2019/7/20
 */
public class UserDaoImpl implements UserDao {

    private Connection conn=null;
    private PreparedStatement ps=null;
    private ResultSet rs=null;

    @Override
    public UserLogin queryByUsername(String username,String authority) {
        UserLogin userLogin=null;
        try {
            conn = JdbcUtils.getConnection();
            String sql = "SELECT * FROM user_login WHERE username=? AND authority=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2,authority);
            rs = ps.executeQuery();
            if (rs.next()) {
                userLogin=new UserLogin();
                setUserLogin(userLogin,rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return userLogin;
    }

    @Override
    public boolean queryExist(String param,String table,String column) {
        boolean flag=false;
        try {
            conn = JdbcUtils.getConnection();
            String sql = "select * from "+table+" where "+column+"=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,param);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

    @Override
    public boolean queryExist(String param, String table, String column, int id) {
        boolean flag=false;
        try {
            conn = JdbcUtils.getConnection();
            String sql = "select * from "+table+" where "+column+"=? and userid!=?";
            if(table.equals("user_login")){
                sql = "select * from "+table+" where "+column+"=? and id!=?";
            }
            ps = conn.prepareStatement(sql);
            ps.setString(1,param);
            ps.setInt(2,id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

    @Override
    public void insertUserLogin(UserLogin userLogin) {
        try {
            conn = JdbcUtils.getConnection();
            String sql = "INSERT INTO user_login(`username`, `password`, `authority`, `picture`, `checked`, `first`) VALUES (?, ?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1,userLogin.getUsername());
            ps.setBytes(2, userLogin.getPassword());
            ps.setString(3,userLogin.getAuthority());
            ps.setString(4,userLogin.getPicture());
            ps.setString(5,userLogin.getChecked());
            ps.setBoolean(6,userLogin.isFirst());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void insertUserInfo(UserInfo userInfo) {
        try {
            conn = JdbcUtils.getConnection();
            String sql = "INSERT INTO user_info(`userid`, `stunumber`, `phone`, `gender`, `age`, `dept_id`, `username`) VALUES (?, ?, ?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,userInfo.getUserId());
            ps.setString(2, userInfo.getStuNumber());
            ps.setString(3,userInfo.getPhone());
            ps.setString(4,userInfo.getGender());
            ps.setInt(5,userInfo.getAge());
            ps.setInt(6,userInfo.getDeptId());
            ps.setString(7,userInfo.getUsername());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void changeFirst(int userId) {
        try {
            conn = JdbcUtils.getConnection();
            String sql = "UPDATE user_login SET first=? WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setBoolean(1,false);
            ps.setInt(2,userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<ShowUser> getAllUser(int start,int rows) {
        List<ShowUser> list=new ArrayList<>();
        try {
            conn = JdbcUtils.getConnection();
            String sql = "SELECT id,i.username,authority,stunumber,phone,gender,age,dept,picture " +
                         "FROM user_login AS l JOIN user_info AS i JOIN user_dept AS d ON l.id=i.userid and i.dept_id=d.dept_id"+
                         " LIMIT "+start+","+rows;
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ShowUser showUser=new ShowUser();
                setShowUser(showUser,rs);
                list.add(showUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public void deleteUserLogin(int id) {
        try {
            conn = JdbcUtils.getConnection();
            String sql = "Delete from user_login WHERE id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteUserInfo(int id) {
        try {
            conn = JdbcUtils.getConnection();
            String sql = "Delete from user_info WHERE userid=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void updateUserLogin(int id) {
        try {
            conn = JdbcUtils.getConnection();
            String sql = "UPDATE user_login SET checked=? , first=? WHERE id= ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,"unchecked");
            ps.setBoolean(2,true);
            ps.setInt(3,id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void updateUserInfo(ShowUser showUser) {
        try {
            conn = JdbcUtils.getConnection();
            String sql = "UPDATE user_info SET stunumber=? , gender=? , phone=?, age=? , username=?, dept_id=? WHERE userid= ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,showUser.getStuNumber());
            ps.setString(2,showUser.getGender());
            ps.setString(3,showUser.getPhone());
            ps.setInt(4,showUser.getAge());
            ps.setString(5,showUser.getUsername());
            ps.setInt(6,Integer.parseInt(showUser.getDept()));
            ps.setInt(7,showUser.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void updateUserLogin(ShowUser showUser) {
        try {
            conn = JdbcUtils.getConnection();
            String sql = "UPDATE user_login SET authority=? , picture=? WHERE id= ?";
            if(showUser.getPicture()==null){
                sql="UPDATE user_login SET authority=? WHERE id= ?";
            }
            ps = conn.prepareStatement(sql);
            ps.setString(1,showUser.getAuthority());
            if(showUser.getPicture()!=null){
                ps.setString(2,showUser.getPicture());
                ps.setInt(3,showUser.getId());
            }else{
                ps.setInt(2,showUser.getId());
            }
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public ShowUser getCurrentUser(int id) {
        ShowUser showUser=new ShowUser();
        try {
            conn = JdbcUtils.getConnection();
            String sql = "SELECT id,i.username,authority,stunumber,phone,gender,age,dept,picture " +
                    "FROM user_login AS l JOIN user_info AS i JOIN user_dept AS d ON l.id=i.userid and i.dept_id=d.dept_id WHERE id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            rs = ps.executeQuery();
            if (rs.next()) {
                setShowUser(showUser,rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return showUser;
    }

    @Override
    public int getPermitCount() {
        int count=0;
        try {
            conn = JdbcUtils.getConnection();
            String sql = "select count(*) from user_login where checked=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,"unchecked");
            rs = ps.executeQuery();
            if (rs.next()) {
                count=rs.getInt("count(*)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return count;
    }

    @Override
    public List<UserLogin> getPermitList() {
        List<UserLogin> list=new ArrayList<>();
        try {
            conn = JdbcUtils.getConnection();
            String sql ="select id,username,authority,picture from user_login where checked=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,"unchecked");
            rs = ps.executeQuery();
            while (rs.next()) {
                UserLogin userLogin=new UserLogin();
                userLogin.setId(rs.getInt("id"));
                userLogin.setUsername(rs.getString("username"));
                if(rs.getString("authority").equals("manager")){
                    userLogin.setAuthority("管理员");
                }else{
                    userLogin.setAuthority("成员");
                }
                userLogin.setPicture(rs.getString("picture"));
                list.add(userLogin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public void permitUser(int id) {
        try {
            conn = JdbcUtils.getConnection();
            String sql = "UPDATE user_login SET checked=? WHERE id= ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,"checked");
            ps.setInt(2,id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void refuseUser(int id) {
        deleteUserLogin(id);
    }

    @Override
    public int getUserLoginId(String loginName) {
        int id=-1;
        try {
            conn = JdbcUtils.getConnection();
            String sql = "select id from user_login where username=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,loginName);
            rs = ps.executeQuery();
            if (rs.next()) {
                id=rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return id;
    }

    @Override
    public byte[] getPasswordById(int id) {
        byte[] password=null;
        try {
            conn = JdbcUtils.getConnection();
            String sql = "select password from user_login where id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            rs = ps.executeQuery();
            if (rs.next()) {
                password=rs.getBytes("password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return password;
    }

    @Override
    public void changePassword(int id,String newPassword) {
        try {
            conn = JdbcUtils.getConnection();
            String sql = "UPDATE user_login SET password=? WHERE id= ?";
            ps = conn.prepareStatement(sql);
            ps.setBytes(1, Md5EncryptUtils.encryptByMD5(newPassword));
            ps.setInt(2,id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<String> getSuggest(String input,String column) {
        List<String> list=new ArrayList<>();
        try {
            conn = JdbcUtils.getConnection();
            String sql = "SELECT "+column+" FROM user_info WHERE "+column+" LIKE ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,input + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                String get=rs.getString(column);
                list.add(get);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public List<ShowUser> getSearchUser(String input, String column) {
        List<ShowUser> list=new ArrayList<>();
        try {
            conn = JdbcUtils.getConnection();
            if(column.equals("username")){
                column="i.username";
            }
            if(input.equals("男")){
                input="male";
            }
            if(input.equals("女")){
                input="female";
            }
            String sql="SELECT id,i.username,authority,stunumber,phone,gender,age,dept,picture FROM user_login AS l JOIN user_info AS i JOIN user_dept AS d ON l.id=i.userid and i.dept_id=d.dept_id WHERE "+column+" LIKE ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,input+"%");
            rs = ps.executeQuery();
            while (rs.next()) {
                ShowUser showUser=new ShowUser();
                setShowUser(showUser,rs);
                list.add(showUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public int getUserInfoCount() {
        int count=0;
        try {
            conn = JdbcUtils.getConnection();
            String sql = "select count(*) from user_info";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                count=rs.getInt("count(*)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return count;
    }

    @Override
    public void updateUserLogin(UserLogin userLogin) {
        try {
            conn = JdbcUtils.getConnection();
            String sql = "UPDATE user_login SET username=?,picture=? WHERE id= ?";
            if(userLogin.getPicture()==null){
                sql="UPDATE user_login SET username=? WHERE id= ?";
            }
            ps = conn.prepareStatement(sql);
            ps.setString(1,userLogin.getUsername());
            if(userLogin.getPicture()==null){
                ps.setInt(2,userLogin.getId());
            }else{
                ps.setString(2,userLogin.getPicture());
                ps.setInt(3,userLogin.getId());
            }
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getUserPicByName(String name) {
        String pic="";
        try {
            conn = JdbcUtils.getConnection();
            String sql = "SELECT picture FROM user_login WHERE username=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,name);
            rs = ps.executeQuery();
            if (rs.next()) {
                pic=rs.getString("picture");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return pic;
    }

    @Override
    public UserLogin getUserLoginById(int id) {
        UserLogin userLogin=null;
        try {
            conn = JdbcUtils.getConnection();
            String sql = "SELECT * FROM user_login WHERE id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            rs = ps.executeQuery();
            if (rs.next()) {
                userLogin=new UserLogin();
                setUserLogin(userLogin,rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return userLogin;
    }

    @Override
    public String getUsernameById(int id) {
        String username="";
        try {
            conn = JdbcUtils.getConnection();
            String sql = "select username from user_info where userid=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            rs = ps.executeQuery();
            if (rs.next()) {
                username=rs.getString("username");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return username;
    }

    private ShowUser setShowUser(ShowUser showUser,ResultSet rs) throws SQLException {
        showUser.setId(rs.getInt("id"));
        showUser.setUsername(rs.getString("username"));
        showUser.setStuNumber(rs.getString("stunumber"));
        showUser.setDept(rs.getString("dept"));
        showUser.setAge(rs.getInt("age"));
        showUser.setPhone(rs.getString("phone"));
        showUser.setPicture(rs.getString("picture"));
        if(rs.getString("authority").equals("manager")){
            showUser.setAuthority("管理员");
        }else{
            showUser.setAuthority("成员");
        }

        if("female".equals(rs.getString("gender"))){
            showUser.setGender("女");
        }else{
            showUser.setGender("男");
        }
        return showUser;
    }

    private UserLogin setUserLogin(UserLogin userLogin,ResultSet rs) throws SQLException {
        userLogin.setId(rs.getInt("id"));
        userLogin.setUsername(rs.getString("username"));
        userLogin.setPassword(rs.getBytes("password"));
        userLogin.setAuthority(rs.getString("authority"));
        userLogin.setPicture(rs.getString("picture"));
        userLogin.setChecked(rs.getString("checked"));
        userLogin.setFirst(rs.getBoolean("first"));
        return userLogin;
    }


}
