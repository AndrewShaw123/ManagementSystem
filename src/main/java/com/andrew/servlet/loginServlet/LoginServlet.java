package com.andrew.servlet.loginServlet;

import com.andrew.domain.UserLogin;
import com.andrew.service.UserService;
import com.andrew.service.impl.UserServiceImpl;
import com.andrew.util.Md5EncryptUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * LoginServlet Class
 *
 * @author andrew
 * @date 2019/7/19
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    private final String CHECKED="checked";
    private final String MANAGER="manager";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet( request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        String authority=request.getParameter("authority");

        UserService userService=new UserServiceImpl();
        UserLogin userLogin=userService.queryByUsername(username,authority);

        System.out.println("登录--->"+userLogin);

        if(userLogin!=null){
            if(Md5EncryptUtils.validatePassword(password,userLogin.getPassword())){
                //密码正确
                System.out.println("密码正确");
                HttpSession session=request.getSession();

                String sessionId=session.getId();
                System.out.println("sessionId="+sessionId);

                session.setAttribute(sessionId,userLogin);


                ServletContext application=session.getServletContext();
                Map<String,String> AlreadyLogin=(Map<String,String>)application.getAttribute("AlreadyLogin");

                if(AlreadyLogin==null){
                    AlreadyLogin=new HashMap<>();
                }


                AlreadyLogin.put(userLogin.getUsername(),sessionId);
                application.setAttribute("AlreadyLogin",AlreadyLogin);


                if(CHECKED.equals(userLogin.getChecked())){

                    if(userLogin.isFirst()){
                        //第一次登录填写个人信息
                        response.sendRedirect("corePage/fillUpInfo.html");
                    }else{
                        if(MANAGER.equals(userLogin.getAuthority())){
                            //管理员进入
                            response.sendRedirect("corePage/manager/main.html");
                        }else{
                            //成员进入
                            response.sendRedirect("corePage/member/main.html");
                        }
                    }
                }else{
                    //未通过审核
                    AlreadyLogin.remove(userLogin.getUsername());
                    application.setAttribute("AlreadyLogin",AlreadyLogin);
                    session.invalidate();
                    response.sendRedirect("login.html?error=NotPermit");
                }

            }else{
                //密码错误
                System.out.println("密码错误");
                response.sendRedirect("login.html?error=PasswordWrong");
            }
        }else{
            //用户名不存在
            System.out.println("用户不存在");
            response.sendRedirect("login.html?error=UserNotExist");
            //responseMessage(response,1,0,"User Not Exist");
        }

    }

}
