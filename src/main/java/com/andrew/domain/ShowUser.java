package com.andrew.domain;

/**
 * ShowUser Class
 *
 * @author andrew
 * @date 2019/7/24
 */
public class ShowUser {

    private int id;
    private String username;
    private String authority;
    private String stuNumber;
    private String phone;
    private String gender;
    private int age;
    private String dept;
    private String picture;

    public ShowUser() {
    }

    public ShowUser(int id, String username, String authority, String stuNumber, String phone, String gender, int age, String dept, String picture) {
        this.id = id;
        this.username = username;
        this.authority = authority;
        this.stuNumber = stuNumber;
        this.phone = phone;
        this.gender = gender;
        this.age = age;
        this.dept = dept;
        this.picture = picture;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getAuthority() {
        return authority;
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

    public String getDept() {
        return dept;
    }

    public String getPicture() {
        return picture;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
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

    public void setDept(String dept) {
        this.dept = dept;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "ShowUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", authority='" + authority + '\'' +
                ", stuNumber='" + stuNumber + '\'' +
                ", phone='" + phone + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", dept='" + dept + '\'' +
                ", picture='" + picture + '\'' +
                '}';
    }
}
