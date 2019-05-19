package com.li.service;

import com.li.entities.News;
import com.li.vo.AnimalInfo;
import com.li.vo.AnimalTree;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

public interface INewsService {

    public List<News> getTpo5(String  type);
    public Map getPage(String  type,int page, int rows);

    public void addNews(News news);
    public News getNews(String id);

}
