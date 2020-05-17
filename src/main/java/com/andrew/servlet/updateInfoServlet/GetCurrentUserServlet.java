package com.andrew.servlet.updateInfoServlet;

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

/**
 * GetCurrentUserServlet Class
 *
 * @author andrew
 * @date 2019/7/27
 */
@WebServlet("/GetCurrentUserServlet")
public class GetCurrentUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id=Integer.parseInt(request.getParameter("id"));
        UserService userService=new UserServiceImpl();
        ShowUser showUser=userService.getCurrentUser(id);

        ObjectMapper objectMapper=new ObjectMapper();
        String json=objectMapper.writeValueAsString(showUser);

        System.out.println("change current user----> "+showUser);

        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }
}
