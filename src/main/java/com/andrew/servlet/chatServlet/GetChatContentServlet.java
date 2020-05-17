package com.andrew.servlet.chatServlet;

import com.andrew.util.IoUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * GetChatContentServlet Class
 *
 * @author andrew
 * @date 2019/8/16
 */
@WebServlet("/GetChatContentServlet")
public class GetChatContentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String from=request.getParameter("from");
        String to=request.getParameter("to");
        String historyPath=request.getServletContext().getRealPath("history");
        String readPath=historyPath+"/"+from+"-"+to+".txt";
        if(!IoUtils.checkIfExist(readPath)){
            readPath=historyPath+"/"+to+"-"+from+".txt";
        }
        String content=IoUtils.readHistory(readPath);

        ObjectMapper objectMapper=new ObjectMapper();
        String json=objectMapper.writeValueAsString(content);

        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);


    }
}
