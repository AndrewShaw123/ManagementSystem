package com.andrew.servlet.permitServlet;

import com.andrew.domain.UserLogin;
import com.andrew.service.UserService;
import com.andrew.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * GetPermitCountServlet Class
 *
 * @author andrew
 * @date 2019/7/28
 */
@WebServlet("/GetPermitCountServlet")
public class GetPermitCountServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserService userService=new UserServiceImpl();
        int count=userService.getPermitCount();

        ObjectMapper objectMapper=new ObjectMapper();
        String json=objectMapper.writeValueAsString(count);


        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }
}
