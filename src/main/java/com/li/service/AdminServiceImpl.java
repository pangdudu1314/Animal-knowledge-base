package com.li.service;

import com.li.dao.AdminUserMybatisDao;
import com.li.dao.AnimalCheckMybatisDao;
import com.li.dao.AnimalDao;
import com.li.dao.AnimalVisitMybatisDao;
import com.li.dao.RdfOwlDao;
import com.li.entities.AdminUser;
import com.li.entities.AnimalCheck;
import com.li.entities.AnimalVisit;
import com.li.utils.Config;
import com.li.utils.DateUtils;
import com.li.utils.IDRandomUtils;
import com.li.utils.ImageDownload;
import com.li.vo.AnimalInfo;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("adminService")
public class AdminServiceImpl implements IAdminService {

  private Logger logger = Logger.getLogger(AdminServiceImpl.class);

  @Autowired
  private AdminUserMybatisDao adminUserMybatisDao;


  @Override
  public List<AdminUser> queryUser(AdminUser adminUser) {
    return adminUserMybatisDao.queryUser(adminUser);
  }

}
