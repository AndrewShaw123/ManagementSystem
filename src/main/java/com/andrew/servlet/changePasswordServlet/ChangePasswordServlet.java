package com.andrew.servlet.changePasswordServlet;

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
 * ChangePasswordServlet Class
 *
 * @author andrew
 * @date 2019/7/29
 */
@WebServlet("/ChangePasswordServlet")
public class ChangePasswordServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session=request.getSession();
        String sessionId=session.getId();

        String oldPassword=request.getParameter("oldPassword");
        String newPassword=request.getParameter("newPassword");
        int id=((UserLogin)(request.getSession()).getAttribute(sessionId)).getId();

        UserService userService=new UserServiceImpl();
        byte[] getPassword=userService.getPasswordById(id);
        if(Md5EncryptUtils.validatePassword(oldPassword,getPassword)){
            userService.changePassword(id,newPassword);
            response.sendRedirect("corePage/changePasswordServlet.html?oldPasswordWrong=0");
        }else {
            response.sendRedirect("corePage/changePasswordServlet.html?oldPasswordWrong=1");
        }





    }
}
