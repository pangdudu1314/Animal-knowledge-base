package com.li.service;

import com.li.entities.AdminUser;
import com.li.vo.AnimalInfo;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

public interface IAdminService {
    public List<Map> queryUser(AdminUser adminUser);

}
