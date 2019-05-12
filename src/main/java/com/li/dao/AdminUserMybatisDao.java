package com.li.dao;

import com.li.entities.AdminUser;
import com.li.entities.AnimalCheck;
import java.util.List;
import java.util.Map;

public interface AdminUserMybatisDao {

    //添加待审核
    public List<Map> queryUser(AdminUser adminUser);


}

