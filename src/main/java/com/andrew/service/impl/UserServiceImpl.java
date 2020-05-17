package com.andrew.service.impl;

import com.andrew.dao.UserDao;
import com.andrew.dao.impl.UserDaoImpl;
import com.andrew.domain.ShowUser;
import com.andrew.domain.UserInfo;
import com.andrew.domain.UserLogin;
import com.andrew.service.UserService;

import java.util.List;


/**
 * UserServiceImpl Class
 *
 * @author andrew
 * @date 2019/7/20
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao =new UserDaoImpl();

    @Override
    public UserLogin queryByUsername(String username,String authority) {
        return userDao.queryByUsername(username,authority);
    }

    @Override
    public boolean queryExist(String param,String table,String column) {
        return userDao.queryExist(param,table,column);
    }

    @Override
    public boolean queryExist(String param, String table, String column, int id) {
        return userDao.queryExist(param,table,column,id);
    }

    @Override
    public void insertUserLogin(UserLogin userLogin) {
        userDao.insertUserLogin(userLogin);
    }

    @Override
    public void insertUserInfo(UserInfo userInfo) {
        userDao.insertUserInfo(userInfo);
    }

    @Override
    public void changeFirst(int userId) {
        userDao.changeFirst(userId);
    }

    @Override
    public List<ShowUser> getAllUser(int start,int rows) {
        return userDao.getAllUser(start,rows);
    }

    @Override
    public void deleteUser(int id) {
        userDao.deleteUserInfo(id);
        userDao.updateUserLogin(id);
    }

    @Override
    public void updateUser(ShowUser showUser) {
        userDao.updateUserInfo(showUser);
        userDao.updateUserLogin(showUser);
    }

    @Override
    public ShowUser getCurrentUser(int id) {
        return userDao.getCurrentUser(id);
    }

    @Override
    public int getPermitCount() {
        return userDao.getPermitCount();
    }

    @Override
    public List<UserLogin> getPermitList() {
        return userDao.getPermitList();
    }

    @Override
    public void permitUser(int id) {
        userDao.permitUser(id);
    }

    @Override
    public void refuseUser(int id) {
        userDao.refuseUser(id);
    }

    @Override
    public int getUserLoginId(String loginName) {
        return userDao.getUserLoginId(loginName);
    }

    @Override
    public byte[] getPasswordById(int id) {
        return userDao.getPasswordById(id);
    }

    @Override
    public void changePassword(int id,String newPassword) {
        userDao.changePassword(id,newPassword);
    }

    @Override
    public List<String> getSuggest(String input,String column) {
        return userDao.getSuggest(input,column);
    }

    @Override
    public List<ShowUser> getSearchUser(String input, String column) {
        return userDao.getSearchUser(input,column);
    }

    @Override
    public int getUserInfoCount() {
        return userDao.getUserInfoCount();
    }

    @Override
    public void updateUserLogin(UserLogin userLogin) {
        userDao.updateUserLogin(userLogin);
    }

    @Override
    public void updateUserInfo(ShowUser showUser) {
        userDao.updateUserInfo(showUser);
    }

    @Override
    public String getUserPicByName(String name) {
        return userDao.getUserPicByName(name);
    }

    @Override
    public UserLogin getUserLoginById(int id) {
        return userDao.getUserLoginById(id);
    }

    @Override
    public String getUsernameById(int id) {
        return userDao.getUsernameById(id);
    }
}
