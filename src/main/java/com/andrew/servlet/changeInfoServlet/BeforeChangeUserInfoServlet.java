package com.andrew.servlet.changeInfoServlet;

import com.andrew.domain.UserLogin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * BeforeChangeUserInfoServlet Class
 *
 * @author andrew
 * @date 2019/8/12
 */
@WebServlet("/BeforeChangeUserInfoServlet")
public class BeforeChangeUserInfoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session=request.getSession();
        String sessionId=session.getId();

        int id=((UserLogin)(request.getSession().getAttribute(sessionId))).getId();
        response.sendRedirect("corePage/changeUserInfo.html?id="+id);
    }
}
