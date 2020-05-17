package com.andrew.servlet.addUserServlet;

import com.andrew.domain.UserInfo;
import com.andrew.domain.UserLogin;
import com.andrew.service.UserService;
import com.andrew.service.impl.UserServiceImpl;
import com.andrew.util.Md5EncryptUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * AddUserServlet Class
 *
 * @author andrew
 * @date 2019/7/29
 */
@WebServlet("/AddUserServlet")
public class AddUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        String number=request.getParameter("number");
        String authority=request.getParameter("authority");

        UserService userService=new UserServiceImpl();
        userService.insertUserLogin(new UserLogin(-1,number, Md5EncryptUtils.encryptByMD5("1111"),authority,"default.jpg","checked",false));
        int userId=userService.getUserLoginId(number);

        String name=request.getParameter("name");
        String phone=request.getParameter("phone");
        String gender=request.getParameter("gender");
        int age=Integer.parseInt(request.getParameter("age"));
        int deptId=Integer.parseInt(request.getParameter("dept"));
        UserInfo userInfo=new UserInfo(userId,number,phone,gender,age,deptId,name);
        userService.insertUserInfo(userInfo);

        response.sendRedirect("corePage/manager/viewUserInfo.html");
    }
}
