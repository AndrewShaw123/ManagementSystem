package com.andrew.dao;

import com.andrew.domain.Dept;

import java.util.List;

/**
 * DeptDao Class
 *
 * @author andrew
 * @date 2019/7/21
 */
public interface DeptDao {
    /**
     *返回所有学院的名称
     *
     * @return List<Dept>学院名称列表
     */
    List<Dept> getAllDeptName();
}
