package com.andrew.servlet.commonServlet;

import com.andrew.domain.UserLogin;
import com.andrew.service.UserService;
import com.andrew.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

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
 * FindLoginNameExceptThisServlet Class
 *
 * @author andrew
 * @date 2019/8/12
 */
@WebServlet("/FindLoginNameExceptThisServlet")
public class FindLoginNameExceptThisServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");

        HttpSession session=request.getSession();
        String sessionId=session.getId();

        String username=request.getParameter("username");
        int id=((UserLogin)(request.getSession().getAttribute(sessionId))).getId();

        Map<String,Object> map=new HashMap<>(2);

        UserService userService=new UserServiceImpl();
        boolean flag=userService.queryExist(username,"user_login","username",id);

        if(flag){
            map.put("userExist",1);
            map.put("msg","姓名已存在");
        }else{
            map.put("userExist",0);
            map.put("msg","姓名可用");
        }
        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.writeValue(response.getWriter(),map);
    }
}
