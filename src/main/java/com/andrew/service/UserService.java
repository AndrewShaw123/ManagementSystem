package com.andrew.service;

import com.andrew.domain.ShowUser;
import com.andrew.domain.UserInfo;
import com.andrew.domain.UserLogin;

import java.util.List;

/**
 * UserService Class
 *
 * @author andrew
 * @date 2019/7/20
 */
public interface UserService {

    /**
     *通过传入登录的用户名来查询数据库中的的信息
     *
     * @param username 查询的名字
     * @param authority 查询的权限
     * @return UserLogin 查询结果不存在则为null
     */
    UserLogin queryByUsername(String username,String authority);

    /**
     * 通过传入的参数判断数据库中是否存在该参数
     *
     * @param param 查询的数据
     * @param table 查询表格
     * @param column 查询的列
     * @return boolean 查询结果是否存在
     */
    boolean queryExist(String param,String table,String column);

    /**
     * 查询除了该id号对应的参数以外还有没有相等的数据，有则返回true
     *
     * @param param 查询的数据
     * @param table 查询表格
     * @param column 查询的列
     * @param id 查询id
     * @return 查询结果
     */
    boolean queryExist(String param,String table,String column,int id);

    /**
     * 注册的用户登录信息插入数据库
     *
     * @param userLogin 注册的数据
     */
    void insertUserLogin(UserLogin userLogin);

    /**
     * 个人信息插入数据库
     *
     * @param userInfo 个人信息
     */
    void insertUserInfo(UserInfo userInfo);

    /**
     * 修改第一次登录为false
     *
     * @param userId 用户id号
     */
    void changeFirst(int userId);

    /**
     * 获得所有用户信息
     *
     * @param start 第几页开始
     * @param rows 一页多少行数据
     * @return 用户信息对象
     */
    List<ShowUser> getAllUser(int start,int rows);

    /**
     *删除该id号的用户信息，并且把该用户的账号设置为管理员未审批的状态
     *
     * @param id 要删除的用户id号
     */
    void deleteUser(int id);

    /**
     *管理员更新信息
     *
     * @param showUser 更新的数据
     */
    void updateUser(ShowUser showUser);

    /**
     *根据用户的id号获取该id号的信息
     *
     * @param id 用户的id号
     * @return 当前登录对象
     */
    ShowUser getCurrentUser(int id);

    /**
     * 返回未被管理员审批的数量
     *
     * @return 未审批的用户数量
     */
    int getPermitCount();

    /**
     *给管理员查看未审批人的信息
     *
     * @return 用户注册信息列表
     */
    List<UserLogin> getPermitList();

    /**
     * 审批通过用户
     *
     * @param id 用户注册id号
     */
    void permitUser(int id);

    /**
     * 审批不通过用户
     *
     * @param id 用户注册id号
     */
    void refuseUser(int id);

    /**
     * 通过登录名称查找对应的id号
     *
     * @param loginName 登录名称
     * @return 查到的id号
     */
    int getUserLoginId(String loginName);

    /**
     * 通过id号查找密码
     *
     * @param id 用户id号
     * @return 密码
     */
    byte[] getPasswordById(int id);

    /**
     * 通过id号修改密码
     *
     * @param id 用户id号
     * @param newPassword 新的密码
     */
    void changePassword(int id,String newPassword);

    /**
     * 通过输入的字符串在数据库中模糊查询相关的数据返回
     *
     * @param input 查询的字符串
     * @param column 查找的字段名
     * @return 返回相关数据列表
     */
    List<String> getSuggest(String input,String column);

    /**
     *通过输入的字符串在数据库中模糊查询相关的数据返回
     *
     * @param input 查询的字符串
     * @param column 查找的字段名
     * @return 返回相关数据列表
     */
    List<ShowUser> getSearchUser(String input,String column);

    /**
     * 获得总共有多少条成员信息
     *
     * @return 成员信息总数
     */
    int getUserInfoCount();

    /**
     * 修改自己的登录信息
     *
     * @param userLogin 需要修改的数据
     */
    void updateUserLogin(UserLogin userLogin);

    /**
     *修改自己的个人信息
     *
     * @param showUser 更新的个人信息数据
     */
    void updateUserInfo(ShowUser showUser);

    /**
     * 通过用户名来获得其头像
     *
     * @param name 用户名
     * @return 头像
     */
    String getUserPicByName(String name);

    /**
     * 通过id号获取UserLogin用户的登录信息
     *
     * @param id 用户id号
     * @return 用户的登录信息
     */
    UserLogin getUserLoginById(int id);

    /**
     *根据id获取个人信息中的名字
     *
     * @param id 用户id
     * @return 个人信息中的名字
     */
    String getUsernameById(int id);
}
