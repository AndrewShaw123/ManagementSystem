package com.andrew.servlet.signupInfoServlet;

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
 * FindValidateCodeServlet Class
 *
 * @author andrew
 * @date 2019/7/21
 */
@WebServlet("/FindValidateCodeServlet")
public class FindValidateCodeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        String code=request.getParameter("code");
        Map<String,Object> map=new HashMap<>(2);
        String sessionCode=(String) (request.getSession().getAttribute("validateCode"));

        if(sessionCode.equalsIgnoreCase(code)){
            map.put("codeWrong",0);
            map.put("msg","验证码正确");
        }else{
            map.put("codeWrong",1);
            map.put("msg","验证码错误");
        }
        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.writeValue(response.getWriter(),map);
    }
}
