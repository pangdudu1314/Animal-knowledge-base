package com.li.utils;

import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

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
    //RequestContextHolder是Spring提供的可以获取HttpServletRequest的一个工具
    return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
  }
}
