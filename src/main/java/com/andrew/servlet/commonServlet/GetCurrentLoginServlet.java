package com.andrew.servlet.commonServlet;

import com.andrew.domain.UserLogin;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * GetCurrentLoginServlet Class
 *
 * @author andrew
 * @date 2019/7/24
 */
@WebServlet("/GetCurrentLoginServlet")
public class GetCurrentLoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session=request.getSession();
        String sessionId=session.getId();

        UserLogin userLogin=(UserLogin)request.getSession().getAttribute(sessionId);

        Map<String,String> map=new HashMap<>(2);
        map.put("loginName",userLogin.getUsername());
        map.put("picture",userLogin.getPicture());


        ObjectMapper objectMapper=new ObjectMapper();
        String json=objectMapper.writeValueAsString(map);

        System.out.println(userLogin);

        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }
}
