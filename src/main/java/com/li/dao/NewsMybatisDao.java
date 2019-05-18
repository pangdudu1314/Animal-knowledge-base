package com.li.dao;

import com.li.entities.AnimalCheck;
import com.li.entities.News;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NewsMybatisDao {

  public List<News> getNews5();

  public List<News> getWroldNews5();

  public void addNews(News news);

}

