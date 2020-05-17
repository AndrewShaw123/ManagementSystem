package com.andrew.servlet.updateInfoServlet;

import com.andrew.service.UserService;
import com.andrew.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * FindNumberExceptThisServlet Class
 *
 * @author andrew
 * @date 2019/7/28
 */
@WebServlet("/FindNumberExceptThisServlet")
public class FindNumberExceptThisServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        String number=request.getParameter("number");
        int id=Integer.parseInt(request.getParameter("id"));
        Map<String,Object> map=new HashMap<>(2);

        UserService userService=new UserServiceImpl();
        boolean flag=userService.queryExist(number,"user_info","stunumber",id);

        if(flag){
            map.put("numberExist",1);
            map.put("msg","学号已经存在");
        }else{
            map.put("numberExist",0);
            map.put("msg","学号可用");
        }
        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.writeValue(response.getWriter(),map);
    }
}
