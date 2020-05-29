package com.hrm.filter;

import com.hrm.commons.beans.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

public class LogiinFilter  implements Filter {


    String[] IG_URL={"/index.jsp","/loginForm.jsp","/login.do","/",".css",".js",".jpg"};


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {


        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestURL=  request.getRequestURI();
        for (String s:IG_URL){
            if (requestURL.endsWith(s)){
                filterChain.doFilter(request,response);
                return;
            }

        }
        User login_user = (User) request.getSession().getAttribute("login_user");
        if(login_user!= null){
            filterChain.doFilter(request,response);
        }else {
            request.setAttribute("login_error","您还未登陆，请登陆后访问！");
            request.getRequestDispatcher("/index.jsp").forward(request,response);
        }


    }
}
