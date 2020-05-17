package com.andrew.domain;

/**
 * UserInfo Class
 *
 * @author andrew
 * @date 2019/7/22
 */
public class UserInfo {
    private int userId;
    private String stuNumber;
    private String phone;
    private String gender;
    private int age;
    private int deptId;
    private String username;

    public UserInfo(int userId, String stuNumber, String phone, String gender, int age, int deptId, String username) {
        this.userId = userId;
        this.stuNumber = stuNumber;
        this.phone = phone;
        this.gender = gender;
        this.age = age;
        this.deptId = deptId;
        this.username = username;
    }

    public UserInfo() {
    }

    public int getUserId() {
        return userId;
    }

    public String getStuNumber() {
        return stuNumber;
    }

    public String getPhone() {
        return phone;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public int getDeptId() {
        return deptId;
    }

    public String getUsername() {
        return username;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setStuNumber(String stuNumber) {
        this.stuNumber = stuNumber;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userId=" + userId +
                ", stuNumber='" + stuNumber + '\'' +
                ", phone='" + phone + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", deptId=" + deptId +
                ", username='" + username + '\'' +
                '}';
    }
}
