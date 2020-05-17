package com.andrew.servlet.updateInfoServlet;

import com.andrew.domain.ShowUser;
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
 * UpdateUserServlet Class
 *
 * @author andrew
 * @date 2019/7/27
 */
@WebServlet("/UpdateUserServlet")
public class UpdateUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ShowUser showUser= ImageUploadUtils.getUpdateUserAllParam(request);

        System.out.println("update Info:"+showUser);

        UserService userService=new UserServiceImpl();
        userService.updateUser(showUser);

        response.sendRedirect("corePage/manager/viewUserInfo.html");

    }

}
