package com.li.service;

import com.li.vo.AnimalInfo;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public interface SelectService {
    /**
     * 根据名称查询动物信息
     * @param name
     * @return 动物详细信息或者类别信息
     */
    /*, HttpServletRequest req,*/
    public AnimalInfo selectName(String name ) throws UnsupportedEncodingException;
    public void spinnerName(String name, String queryClassLevel,HttpServletResponse resp) throws IOException;
}