package com.andrew.domain;

import java.util.List;

/**
 * SystemMessage Class
 *
 * @author andrew
 * @date 2019/8/1
 */
public class SystemMessage {

    private List<String> userList;

    public SystemMessage() {
    }

    public SystemMessage(List userList) {
        this.userList = userList;
    }


    public List getUserList() {
        return userList;
    }


    public void setUserList(List userList) {
        this.userList = userList;
    }

    @Override
    public String toString() {
        return "SystemMessage{" +
                "userList=" + userList +
                '}';
    }
}
