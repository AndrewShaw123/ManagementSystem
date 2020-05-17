package com.andrew.servlet.chatServlet;

import com.andrew.util.IoUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * SetChatContentServlet Class
 *
 * @author andrew
 * @date 2019/8/16
 */
@WebServlet("/SetChatContentServlet")
public class SetChatContentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String content=request.getParameter("content");
        String from=request.getParameter("from");
        String to=request.getParameter("to");

        System.out.println("Content--->"+content);

        String historyPath=request.getServletContext().getRealPath("history");
        String writePath=historyPath+"/"+from+"-"+to+".txt";

        if(!IoUtils.checkIfExist(writePath)){
            writePath=historyPath+"/"+to+"-"+from+".txt";
        }

        System.out.println("HistorysavePath--->"+writePath);
        System.out.println("History--->"+content);

        IoUtils.writeHistory(writePath,content);

    }
}
