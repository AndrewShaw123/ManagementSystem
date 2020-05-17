package com.andrew.servlet.searchServlet;

import com.andrew.domain.ShowUser;
import com.andrew.service.UserService;
import com.andrew.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * SearchServlet Class
 *
 * @author andrew
 * @date 2019/8/6
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String searchText=request.getParameter("searchText");
        String searchType=request.getParameter("column");

        UserService userService=new UserServiceImpl();
        List<ShowUser> list= userService.getSearchUser(searchText,searchType);

        ObjectMapper objectMapper=new ObjectMapper();
        String json=objectMapper.writeValueAsString(list);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }
}