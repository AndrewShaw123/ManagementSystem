package com.andrew.servlet.commonServlet;

import com.andrew.domain.Dept;
import com.andrew.service.DeptService;
import com.andrew.service.impl.DeptServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * GetAllDeptServlet Class
 *
 * @author andrew
 * @date 2019/7/21
 */
@WebServlet("/GetAllDeptServlet")
public class GetAllDeptServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DeptService deptService=new DeptServiceImpl();
        List<Dept> list=deptService.getAllDeptName();

        ObjectMapper objectMapper=new ObjectMapper();
        String json=objectMapper.writeValueAsString(list);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }
}
