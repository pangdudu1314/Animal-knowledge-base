package com.li.controller;

import com.li.entities.AdminUser;
import com.li.service.IAdminService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

  private Logger logger=Logger.getLogger(AdminController.class);

  @Autowired
  private IAdminService adminService;

  /**
   *
   * @param username
   * @param password
   * @param request
   * @param response
   * @return
   * @throws IOException
   * 管理员登录
   */
    @RequestMapping("/login")
    public String login(String username,String password,HttpServletRequest request,HttpServletResponse response) throws IOException {

     //当两个Web组件之间为转发关系时，转发目标组件通过getAttribute()方法来和转发源组件共享request范围内的数据。
      //getAttribute是返回对象,getParameter返回字符串
      String username0000=(String)request.getSession().getAttribute("username0000");
      if(username0000!=null){
        //用户信息存在，跳转到管理员界面
        return "redirect:/animalCheck/gotoIndex";
      }else{
        //用户信息不存在
        //是否传递传递用户名，密码，传递过来说明是用户进行了页面登录操作
        if(username!=null){
          //进行数据库查询操作
          AdminUser adminUser=new AdminUser();
          adminUser.setUsername(username);
          adminUser.setPassword(password);
          List<AdminUser>  queryUser= adminService.queryUser(adminUser);
         if(queryUser!=null&&queryUser.size()>0){
           //用户存在
           request.getSession().setAttribute("username0000",username);
           request.getSession().setAttribute("userid0000",queryUser.get(0).getId());
           String url= (String) request.getSession().getAttribute("lastUrl");

           return "redirect:"+url;
         }else{
           //用户名密码错误
           request.setAttribute("errorInfo","用户名或者密码错误");
           return "admin/login";
         }
        }else{
          //说明用户没有传递用户名和密码，也没有登录缓存信息。那么调转到登录界面
          return "admin/login";
        }
      }
    }


}
