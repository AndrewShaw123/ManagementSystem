package com.andrew.service.impl;

import com.andrew.dao.DeptDao;
import com.andrew.dao.impl.DeptDaoImpl;
import com.andrew.domain.Dept;
import com.andrew.service.DeptService;

import java.util.List;

/**
 * DeptServiceImpl Class
 *
 * @author andrew
 * @date 2019/7/21
 */
public class DeptServiceImpl implements DeptService {

    DeptDao deptDao=new DeptDaoImpl();

    @Override
    public List<Dept> getAllDeptName() {
        return deptDao.getAllDeptName();
    }
}
