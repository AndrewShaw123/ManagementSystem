package com.andrew.servlet.goBackServlet;

import com.andrew.domain.UserLogin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * BackToMainServlet Class
 *
 * @author andrew
 * @date 2019/8/13
 */
@WebServlet("/BackToMainServlet")
public class BackToMainServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session=request.getSession();
        String sessionId=session.getId();

        String authority=((UserLogin)(request.getSession().getAttribute(sessionId))).getAuthority();
        if(authority.equals("manager")){
            response.sendRedirect("corePage/manager/main.html");
        }else{
            response.sendRedirect("corePage/member/main.html");
        }
    }
}
