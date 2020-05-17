package com.andrew.filter;

import com.andrew.domain.UserLogin;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * LoginFilter Class
 *
 * @author andrew
 * @date 2019/7/21
 */
@WebFilter(urlPatterns = "/corePage/*")
public class LoginFilter implements Filter {

    @Override
    public void destroy() {
    }
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //禁止缓存
        ((HttpServletResponse)resp).setHeader("Pragma","No-cache");
        ((HttpServletResponse)resp).setHeader("Cache-Control","no-cache");
        ((HttpServletResponse)resp).setHeader("Expires","0");

        HttpSession session=((HttpServletRequest)req).getSession();
        String sessionId=session.getId();
        UserLogin userLogin =(UserLogin) session.getAttribute(sessionId);

        if(userLogin ==null) {
            System.out.println("未登录");
            ((HttpServletResponse)resp).sendRedirect("../login.html?otherLogin=1");
        }else{

            String loninName=userLogin.getUsername();
            ServletContext application=session.getServletContext();
            Map<String,String> AlreadyLogin=(Map<String,String>)application.getAttribute("AlreadyLogin");
            for(String name:AlreadyLogin.keySet()){
                if(loninName.equals(name)){
                    String getSessionId=AlreadyLogin.get(name);
                    if(!getSessionId.equals(sessionId)){
                        //在其他地方登录
                        ((HttpServletResponse)resp).sendRedirect("../login.html?otherLogin=1");
                        session.invalidate();
                    }else{
                        System.out.println("已登录");
                        chain.doFilter(req, resp);
                        break;
                    }
                }
            }

        }




    }
    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}
