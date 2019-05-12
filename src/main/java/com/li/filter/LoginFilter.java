package com.li.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class LoginFilter implements Filter {
  public FilterConfig config;
  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    config = filterConfig;
  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
      FilterChain filterChain) throws IOException, ServletException {
    String logonStrings = config.getInitParameter("filterUrl");        // 登录登陆页面
    HttpServletRequest hrequest = (HttpServletRequest) servletRequest;

    String url=hrequest.getServletPath();
     if((";"+logonStrings+";").contains(";"+url+";")){
      //需要拦截的url
      //判断是否有登录信息
      String username=(String)hrequest.getSession().getAttribute("username0000");
      if(username==null){
        hrequest.getSession().setAttribute("lastUrl",url);
        HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper((HttpServletResponse) servletResponse);
        wrapper.sendRedirect(hrequest.getContextPath()+"/admin/login");
        return ;
      }
    }
    filterChain.doFilter(servletRequest, servletResponse);

  }

  @Override
  public void destroy() {
    this.config = null;
  }
}
