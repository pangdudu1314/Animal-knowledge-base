package com.li.service;

import com.li.dao.SelectDao;
import com.li.vo.AnimalInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Service("selectService")
public class SelectServiceImpl implements SelectService {
    private Logger logger=Logger.getLogger(SelectServiceImpl.class);
    @Autowired
   private SelectDao selectDao;

    @Override
    public AnimalInfo selectName(String name) throws UnsupportedEncodingException {
        //创建AnimalInfo对象
        AnimalInfo animalInfo=selectDao.selectAllInfo(name);
        //相似信息
        List<AnimalInfo> similaryts= animalInfo.getSimilartys();
        if(similaryts!=null&&similaryts.size()>0){
            //保存查询的相似动物或者科目信息
            List<AnimalInfo> similarytsQuery = getAnimalInfos(similaryts);
            //相似信息，重新赋值
            animalInfo.setSimilartys(similarytsQuery);

        }
        //同科动物
        List<AnimalInfo> siblings= animalInfo.getSiblings();
        if(siblings!=null&& siblings.size()>0){
            //保存查询的相似动物或者科目信息
            List<AnimalInfo> siblingsQuery = getAnimalInfos(siblings);
            //相似信息，重新赋值
            animalInfo.setSiblings(siblingsQuery);

        }
        return animalInfo;
    }

   //查询动物或者科目信息
    private List<AnimalInfo> getAnimalInfos(List<AnimalInfo> animalInfos) throws UnsupportedEncodingException {
      /*此处报错不知怎么修改
      * List<AnimalInfo> kindsQuery = new ArrayList<>()；*/
        List<AnimalInfo> kindsQuery = new ArrayList<AnimalInfo>();
        //如果有关联信息，那就查询
        for (int i = 0; i < animalInfos.size(); i++) {
            //animalInfos是list形式的有序列表,用get取值
            AnimalInfo s = animalInfos.get(i);

            String kname = s.getName();
            AnimalInfo  temp= selectDao.selectAllInfo(kname);
            if(temp.getName()!=null){
                kindsQuery.add(temp);
            }else{
                logger.error("kname="+kname+"未配置!");
            }

        }
        return kindsQuery;
    }

    @Override
    public void spinnerName(String name, HttpServletResponse resp) throws IOException {
        selectDao.spinner(name,resp);
    }

    @Override
    public void spinnerKe(String name, HttpServletResponse response) throws IOException {
        selectDao.spinnerKe(name,response);
    }

    @Override
    public void deleteAnimalByName(String name) {
        selectDao.deleteAnimalByName(name);
    }

    //获取要查找的动物信息
    public void addAnimal(AnimalInfo animalInfo,String type){
        selectDao.addAnimal(animalInfo, type);
    }

    @Override
    public void updateAnimal(String name, String image, String intro) {
        selectDao.updateAnimal(name,image,intro);
    }
}
