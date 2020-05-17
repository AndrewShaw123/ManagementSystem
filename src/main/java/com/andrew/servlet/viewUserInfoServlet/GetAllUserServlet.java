package com.andrew.servlet.viewUserInfoServlet;

import com.andrew.domain.ShowUser;
import com.andrew.service.UserService;
import com.andrew.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * GetAllUserServlet Class
 *
 * @author andrew
 * @date 2019/7/24
 */
@WebServlet("/GetAllUserServlet")
public class GetAllUserServlet extends HttpServlet {
    private final int ROWS=10;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int start=Integer.parseInt(request.getParameter("start"));
        UserService userService=new UserServiceImpl();
        List<ShowUser> list=userService.getAllUser(start*ROWS,ROWS);

        ObjectMapper objectMapper=new ObjectMapper();
        String json=objectMapper.writeValueAsString(list);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }
}
