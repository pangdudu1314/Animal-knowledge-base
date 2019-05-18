package com.li.utils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class UserUtils implements ServletContextAware {
  public static ServletContext sc;

  @Override
  public void setServletContext(ServletContext context) {
    this.sc = context;
  }

  public static String getUsername() {
    HttpServletRequest request = getRequest();
    String user = (String) request.getSession().getAttribute("username0000");
    return user;
  }

  public static String getUserId() {
    HttpServletRequest request = getRequest();
    String userId = (String) request.getSession().getAttribute("userid0000");
    return userId;
  }
  public static HttpServletRequest getRequest() {
    return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
  }
}
