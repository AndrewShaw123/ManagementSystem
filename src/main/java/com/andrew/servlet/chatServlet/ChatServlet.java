package com.andrew.servlet.chatServlet;

import com.andrew.domain.UserLogin;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * ChatServlet Class
 *
 * @author andrew
 * @date 2019/8/1
 */
@WebServlet("/ChatServlet")
public class ChatServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        HttpSession session=request.getSession();
        String sessionId=session.getId();

        UserLogin userLogin=(UserLogin) request.getSession().getAttribute(sessionId);
        String username=userLogin.getUsername();
        response.sendRedirect("corePage/chat.html?username="+URLEncoder.encode(URLEncoder.encode(username)));
    }
}
