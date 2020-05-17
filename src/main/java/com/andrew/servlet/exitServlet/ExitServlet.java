package com.andrew.servlet.exitServlet;

import com.andrew.domain.UserLogin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * ExitServlet Class
 *
 * @author andrew
 * @date 2019/7/24
 */
@WebServlet("/ExitServlet")
public class ExitServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        String login=((UserLogin) session.getAttribute(session.getId())).getUsername();
        Map<String,String> AlreadyLogin=(Map<String,String>)session.getServletContext().getAttribute("AlreadyLogin");
        if(AlreadyLogin!=null){
            AlreadyLogin.remove(login);
            session.getServletContext().setAttribute("AlreadyLogin",AlreadyLogin);
        }
        session.invalidate();
        response.sendRedirect("login.html");
    }
}
