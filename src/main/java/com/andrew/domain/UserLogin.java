package com.andrew.domain;


import java.util.Arrays;

/**
 * UserLogin Class
 *
 * @author andrew
 * @date 2019/7/20
 */
public class UserLogin {
    private int id;
    private String username;
    private byte[] password;
    private String authority;
    private String picture;
    private String checked;
    private boolean first;

    public UserLogin() {
    }

    public UserLogin(int id, String username, byte[] password, String authority, String picture, String checked, boolean first) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authority = authority;
        this.picture = picture;
        this.checked = checked;
        this.first = first;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public byte[] getPassword() {
        return password;
    }

    public String getAuthority() {
        return authority;
    }

    public String getPicture() {
        return picture;
    }

    public String getChecked() {
        return checked;
    }

    public boolean isFirst() {
        return first;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    @Override
    public String toString() {
        return "UserLogin{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password=" + Arrays.toString(password) +
                ", authority='" + authority + '\'' +
                ", picture='" + picture + '\'' +
                ", checked='" + checked + '\'' +
                ", first=" + first +
                '}';
    }
}
