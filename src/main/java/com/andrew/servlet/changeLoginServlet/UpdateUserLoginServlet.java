package com.andrew.servlet.changeLoginServlet;

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
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * UpdateUserLoginServlet Class
 *
 * @author andrew
 * @date 2019/8/11
 */
@WebServlet("/UpdateUserLoginServlet")
public class UpdateUserLoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserLogin userLogin= ImageUploadUtils.getUserLoginAllParam(request);

        HttpSession session=request.getSession();
        String sessionId=session.getId();

        int id=((UserLogin)(request.getSession().getAttribute(sessionId))).getId();
        String oldName=((UserLogin)(request.getSession().getAttribute(sessionId))).getUsername();
        String newName=userLogin.getUsername();
        userLogin.setId(id);

        UserService userService=new UserServiceImpl();
        userService.updateUserLogin(userLogin);

        //重新修改新的Session
        UserLogin newUserLoginSession=userService.getUserLoginById(id);
        session.setAttribute(sessionId,newUserLoginSession);

        //同时聊天的历史记录的文件名跟着改变
        String chatHistoryPath=request.getServletContext().getRealPath("history");
        File files = new File(chatHistoryPath);
        String[] names = files.list();
        for(String name:names){
            if(name.startsWith(oldName)||name.endsWith(oldName)){
                String newPath=name.replaceAll(oldName,newName);
                File file=new File(chatHistoryPath+"/"+name);
                file.renameTo(new File(chatHistoryPath+"/"+newPath));
            }
        }


        response.sendRedirect("corePage/manager/main.html");
    }
}
