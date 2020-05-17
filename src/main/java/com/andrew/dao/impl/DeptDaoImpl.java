package com.andrew.dao.impl;

import com.andrew.dao.DeptDao;
import com.andrew.domain.Dept;
import com.andrew.util.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * DeptDaoImpl Class
 *
 * @author andrew
 * @date 2019/7/21
 */
public class DeptDaoImpl implements DeptDao {

    private Connection conn=null;
    private PreparedStatement ps=null;
    private ResultSet rs=null;

    @Override
    public List<Dept> getAllDeptName() {
        List<Dept> list=new ArrayList<>();
        try {
            conn = JdbcUtils.getConnection();
            String sql = "select * from user_dept";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Dept dept=new Dept();
                dept.setDeptId(rs.getInt("dept_id"));
                dept.setDept(rs.getString("dept"));
                list.add(dept);
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
}
