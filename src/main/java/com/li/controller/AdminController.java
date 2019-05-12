package com.li.controller;

import com.li.entities.AdminUser;
import com.li.service.IAdminService;
import com.li.service.IAnimalService;
import com.li.utils.Config;
import com.li.utils.JsonUtils;
import com.li.vo.AnimalInfo;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

  private Logger logger=Logger.getLogger(AdminController.class);

  @Autowired
  private IAdminService adminService;

    @RequestMapping("/login")
    public String login(String username,String password,HttpServletRequest request,HttpServletResponse response) throws IOException {
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
         List queryUser= adminService.queryUser(adminUser);
         if(queryUser!=null&&queryUser.size()>0){
           //用户存在
           request.getSession().setAttribute("username0000",username);
           return "redirect:/animalCheck/gotoIndex";
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
