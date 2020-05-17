package com.andrew.servlet.permitServlet;

import com.andrew.service.UserService;
import com.andrew.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * RefuseServlet Class
 *
 * @author andrew
 * @date 2019/7/28
 */
@WebServlet("/RefuseServlet")
public class RefuseServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id=Integer.parseInt(request.getParameter("id"));
        UserService userService=new UserServiceImpl();
        userService.refuseUser(id);
        response.sendRedirect("corePage/manager/waitPermitList.html");
    }
}
