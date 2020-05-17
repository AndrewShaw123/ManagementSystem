package com.andrew.servlet.fillUpInfoServlet;

import com.andrew.domain.UserInfo;
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
 * FillUpInfoServlet Class
 *
 * @author andrew
 * @date 2019/7/23
 */
@WebServlet("/FillUpInfoServlet")
public class FillUpInfoServlet extends HttpServlet {

    private final String MANAGER="manager";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        HttpSession session=request.getSession();
        String sessionId=session.getId();

        String username=request.getParameter("name");
        String number=request.getParameter("number");
        String phone=request.getParameter("phone");
        String gender=request.getParameter("gender");
        int age=Integer.parseInt(request.getParameter("age"));
        int deptId=Integer.parseInt(request.getParameter("dept"));
        UserLogin userLogin=(UserLogin) request.getSession().getAttribute(sessionId);
        int userId=userLogin.getId();

        UserInfo userInfo=new UserInfo(userId,number,phone,gender,age,deptId,username);
        System.out.println("————————————————————————————");
        System.out.println(userInfo);
        System.out.println("————————————————————————————");


        UserService userService=new UserServiceImpl();
        userService.insertUserInfo(userInfo);

        //修改first属性
        userService.changeFirst(userId);

        if(MANAGER.equals(userLogin.getAuthority())){
            response.sendRedirect("corePage/manager/main.html");
        }else{
            response.sendRedirect("corePage/member/main.html");
        }

    }
}
