package com.andrew.servlet.signupServlet;

import com.andrew.domain.UserLogin;
import com.andrew.service.UserService;
import com.andrew.service.impl.UserServiceImpl;
import com.andrew.util.ImageUploadUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * RegisterServlet Class
 *
 * @author andrew
 * @date 2019/7/22
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        UserLogin userLogin= ImageUploadUtils.getRegisterAllParam(request);

        System.out.println("-->"+userLogin+"<--");

        UserService userService=new UserServiceImpl();
        userService.insertUserLogin(userLogin);

        response.sendRedirect("login.html?signup=1");
    }



}
