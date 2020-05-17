package com.andrew.servlet.commonServlet;

import com.andrew.service.UserService;
import com.andrew.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;

/**
 * GetCurrentPictureServlet Class
 *
 * @author andrew
 * @date 2019/8/14
 */
@WebServlet("/GetCurrentPictureServlet")
public class GetCurrentPictureServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String name=request.getParameter("name");
        name= URLDecoder.decode(name);
        UserService userService=new UserServiceImpl();
        String picture=userService.getUserPicByName(name);

        ObjectMapper objectMapper=new ObjectMapper();
        String json=objectMapper.writeValueAsString(picture);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }
}
