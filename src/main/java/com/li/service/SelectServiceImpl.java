package com.li.service;

import com.li.dao.RdfOwlDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
@Service("selectService")
public class SelectServiceImpl implements SelectService {
    private Logger logger=Logger.getLogger(SelectServiceImpl.class);
    @Autowired
    private RdfOwlDao rdfOwlDao;

    //获取要查找的动物信息
  /*  @Override
    public Map<String, List<String>> selectName(String name) {
        return null;

    }

    @Override
    public void spinnerName(String name, String queryClassLevel, HttpServletResponse resp) {

    }*/
}
