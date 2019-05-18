package com.li.controller;

import com.li.entities.AdminUser;
import com.li.service.IAdminService;
import com.li.service.IAnimalCheckService;
import com.li.utils.JsonUtils;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/animalCheck")
public class AnimalCheckController {

  private Logger logger = Logger.getLogger(AnimalCheckController.class);

  @Autowired
  private IAnimalCheckService animalCheckService;

  @RequestMapping("/gotoIndex")
  public String getAminalCheck(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    return "animalCheck/index";
  }

  @RequestMapping("/getAminalCheck")
  public void getAminalCheck(int page, int rows, HttpServletRequest request,
      HttpServletResponse response) throws IOException {
    Map map = animalCheckService.getAnimalCheck((page-1)*rows, rows);
    try {
      String json = JsonUtils.getString(map);
      System.out.println(json);
      response.setContentType("text/javascript;charset=utf-8");
      response.getWriter().write(json);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  @RequestMapping("/updateCheckAnimal")
  public void updateCheckAnimal(String id,String kemu, String animalName, HttpServletRequest request, HttpServletResponse response) {
    try {
      Map map = new HashMap();
      animalCheckService.updateAnimalImageFrom(  id,animalName,kemu);
      //传给前台json数据
      String json = JsonUtils.getString(map);
      System.out.println(json);
      response.setContentType("text/javascript;charset=utf-8");
      response.getWriter().write(json);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @RequestMapping("/reviewAnimal")
  public void reviewAnimal(String id, HttpServletRequest request, HttpServletResponse response) {
    try {
      Map map = new HashMap();
      animalCheckService.reviewAnimal(id);
      //传给前台json数据
      String json = JsonUtils.getString(map);
      System.out.println(json);
      response.setContentType("text/javascript;charset=utf-8");
      response.getWriter().write(json);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  @RequestMapping("/reviewbackAnimal")
  public void reviewbackAnimal(String id, HttpServletRequest request, HttpServletResponse response) {
    try {
      Map map = new HashMap();
      animalCheckService.reviewbackAnimal(id);
      //传给前台json数据
      String json = JsonUtils.getString(map);
      System.out.println(json);
      response.setContentType("text/javascript;charset=utf-8");
      response.getWriter().write(json);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
