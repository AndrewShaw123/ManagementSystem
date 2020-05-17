package com.andrew.service;

import com.andrew.domain.Dept;

import java.util.List;

/**
 * DeptService Class
 *
 * @author andrew
 * @date 2019/7/21
 */
public interface DeptService {
    /**
     *返回所有学院的名称
     *
     * @return List<Dept> 返回学院名称集合
     */
    List<Dept> getAllDeptName();
}
