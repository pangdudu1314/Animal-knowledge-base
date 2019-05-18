package com.li.controller;

import com.li.entities.News;
import com.li.service.IAnimalCheckService;
import com.li.service.INewsService;
import com.li.utils.IDRandomUtils;
import com.li.utils.JsonUtils;
import com.li.utils.UserUtils;
import java.io.IOException;
import java.util.Date;
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
@RequestMapping("/news")
public class NewsController {

  private Logger logger = Logger.getLogger(NewsController.class);

  @Autowired
  private INewsService newsService;


  @RequestMapping("/getnews")
  public void getAminalCheck(String type, HttpServletRequest request,
      HttpServletResponse response) throws IOException {
    try {
      List<News> newsList =newsService.getTpo5(type);
      String json = JsonUtils.getString(newsList);
      System.out.println(json);
      response.setContentType("text/javascript;charset=utf-8");
      response.getWriter().write(json);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @RequestMapping("/addNews")
  public void addNews(String theme,String type,String news, HttpServletRequest request,
      HttpServletResponse response) throws IOException {
    try {
      News news1=new News();
      news1.setId(IDRandomUtils.createRandomStr());
      news1.setNews(news);
      news1.setTheme(theme);
      news1.setType(type);
      news1.setTime(new Date());
      news1.setAdminId(UserUtils.getUserId());
      newsService.addNews(news1);
      String json = JsonUtils.getString(news1);
      System.out.println(json);
      response.setContentType("text/javascript;charset=utf-8");
      response.getWriter().write(json);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @RequestMapping("/gotoAddNews")
  public String addAnimal() {
    return "news/add";
  }
}
