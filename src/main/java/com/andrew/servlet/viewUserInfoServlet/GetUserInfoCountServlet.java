package com.andrew.servlet.viewUserInfoServlet;

import com.andrew.service.UserService;
import com.andrew.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * GetUserInfoCountServlet Class
 *
 * @author andrew
 * @date 2019/8/11
 */
@WebServlet("/GetUserInfoCountServlet")
public class GetUserInfoCountServlet extends HttpServlet {
    private final int ROWS=10;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserService userService=new UserServiceImpl();
        int totalCount=userService.getUserInfoCount();
        int totalpage;
        if(totalCount%ROWS==0) {
            totalpage=totalCount/ROWS;
        }
        else {
            totalpage=totalCount/ROWS;
            totalpage++;
        }
        ObjectMapper objectMapper=new ObjectMapper();
        String json=objectMapper.writeValueAsString(totalpage);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }
}
