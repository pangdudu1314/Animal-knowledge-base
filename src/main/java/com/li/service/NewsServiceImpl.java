package com.li.service;

import com.li.dao.AnimalCheckMybatisDao;
import com.li.dao.AnimalVisitMybatisDao;
import com.li.dao.NewsMybatisDao;
import com.li.dao.RdfOwlDao;
import com.li.entities.AnimalCheck;
import com.li.entities.News;
import com.li.vo.AnimalInfo;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("newService")
public class NewsServiceImpl implements INewsService {

  private Logger logger = Logger.getLogger(NewsServiceImpl.class);


  @Autowired
  private NewsMybatisDao newsMybatisDao;
  @Override
  public List<News> getTpo5(String type) {

    if("1".equalsIgnoreCase(type)){
      return newsMybatisDao.getNews5();
    }else{
      return newsMybatisDao.getWroldNews5();
    }

  }

  @Override
  public Map getPage(String type, int page, int size) {
    //查询总数
    int total =newsMybatisDao.getNewsCount(type);
    List rows=newsMybatisDao.getNewsPage(type,page,size);
    //分页查询
    Map map=new HashMap();
    map.put("total",total);
    map.put("rows",rows);
    return map;
  }

  @Override
  public void addNews(News news) {
    newsMybatisDao.addNews(news);
  }

  @Override
  public News getNews(String id) {
    return newsMybatisDao.getNews(id);
  }
}
