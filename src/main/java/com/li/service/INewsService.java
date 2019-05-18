package com.li.service;

import com.li.entities.News;
import com.li.vo.AnimalInfo;
import com.li.vo.AnimalTree;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

public interface INewsService {

    public List<News> getTpo5(String  type);

    public void addNews(News news);

}
