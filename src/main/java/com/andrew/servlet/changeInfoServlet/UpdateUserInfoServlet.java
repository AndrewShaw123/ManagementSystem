package com.andrew.servlet.changeInfoServlet;

import com.andrew.domain.ShowUser;
import com.andrew.domain.UserLogin;
import com.andrew.service.UserService;
import com.andrew.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * UpdateUserInfoServlet Class
 *
 * @author andrew
 * @date 2019/8/12
 */
@WebServlet("/UpdateUserInfoServlet")
public class UpdateUserInfoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        HttpSession session=request.getSession();
        String sessionId=session.getId();

        int userid=((UserLogin)(request.getSession().getAttribute(sessionId))).getId();
        String number=request.getParameter("number");
        String name=request.getParameter("username");
        String phone=request.getParameter("phone");
        String gender=request.getParameter("gender");
        int age=Integer.parseInt(request.getParameter("age"));
        int deptId=Integer.parseInt(request.getParameter("dept"));

        ShowUser showUser=new ShowUser();
        showUser.setId(userid);
        showUser.setDept(deptId+"");
        showUser.setStuNumber(number);
        showUser.setUsername(name);
        showUser.setAge(age);
        showUser.setPhone(phone);
        showUser.setGender(gender);

        UserService userService=new UserServiceImpl();
        userService.updateUserInfo(showUser);


        response.sendRedirect("corePage/manager/main.html");

    }
}
