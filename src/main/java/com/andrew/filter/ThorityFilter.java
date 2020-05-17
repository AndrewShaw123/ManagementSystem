package com.andrew.filter;

import com.andrew.domain.UserLogin;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * ThorityFilter Class
 *
 * @author andrew
 * @date 2019/7/20
 */
@WebFilter(urlPatterns = "/corePage/manager/*")
public class ThorityFilter implements Filter {

    private final String MANAGER="manager";

    @Override
    public void destroy() {

    }
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpSession session=((HttpServletRequest)req).getSession();
        String sessionId=session.getId();

        UserLogin userLogin=(UserLogin) session.getAttribute(sessionId);
        if(userLogin==null){
            ((HttpServletResponse)resp).sendRedirect("../../login.html");
        }else{
            if(MANAGER.equals((userLogin.getAuthority()))){
                System.out.println("管理员");
                chain.doFilter(req, resp);
            }else {
                System.out.println("普通成员");
                ((HttpServletResponse)resp).sendRedirect("../member/main.html");
            }
        }
    }
    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}
