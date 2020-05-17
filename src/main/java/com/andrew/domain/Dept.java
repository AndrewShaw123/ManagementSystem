package com.andrew.domain;

/**
 * Dept Class
 *
 * @author andrew
 * @date 2019/7/21
 */
public class Dept {
    private int deptId;
    private String dept;

    public Dept(int deptId, String dept) {
        this.deptId = deptId;
        this.dept = dept;
    }

    public Dept() {
    }

    public int getDeptId() {
        return deptId;
    }

    public String getDept() {
        return dept;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "deptId=" + deptId +
                ", dept='" + dept + '\'' +
                '}';
    }
}
