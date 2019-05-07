package com.li.service;

import com.li.dao.AnimalVisitMybatisDao;
import com.li.dao.AnimalDao;
import com.li.entities.AnimalVisit;
import com.li.utils.DateUtils;
import com.li.vo.AnimalInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Service("iAnimalService")
public class AnimalServiceImpl implements IAnimalService {
    private Logger logger=Logger.getLogger(AnimalServiceImpl.class);
    @Autowired
   private AnimalDao animalDao;
    @Autowired
    private AnimalVisitMybatisDao animalVisitMybatisDao;
    @Override
    public AnimalInfo selectName(String name) throws UnsupportedEncodingException {
        //创建AnimalInfo对象
        AnimalInfo animalInfo= animalDao.selectAllInfo(name);
        //判断如果animalInfo有图片信息，那么认为是访问的动物，需要更新动物访问量
        if (animalInfo.getImage() != null && animalInfo.getImage().trim().length() > 0) {
            //更新动物访问量
            String weekDay= DateUtils.getWeekOfDate();
            AnimalVisit animalVisit = animalVisitMybatisDao.queryAnimalVisitByNameAndWeek(name,weekDay);
            if (animalVisit == null) {
                //首次访问，返回结果为空
                animalVisit = new AnimalVisit();
                animalVisit.setAnimalName(name);
                animalVisit.setAnimalVisits(0);
                animalVisit.setWeekDay(weekDay);
                animalVisitMybatisDao.addAnimalVisit(animalVisit);
            }
            animalVisit.setAnimalVisits(animalVisit.getAnimalVisits() + 1);
            animalVisitMybatisDao.updateAnimalVisit(animalVisit);

        }
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
            AnimalInfo  temp= animalDao.selectAllInfo(kname);
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
        animalDao.spinner(name,resp);
    }

    @Override
    public void spinnerKe(String name, HttpServletResponse response) throws IOException {
        animalDao.spinnerKe(name,response);
    }

    @Override
    public void deleteAnimalByName(String name) {
        animalDao.deleteAnimalByName(name);
    }

    //获取要查找的动物信息
    public void addAnimal(AnimalInfo animalInfo,String type){
        animalDao.addAnimal(animalInfo, type);
    }

    @Override
    public void updateAnimal(String name, String image, String intro) {
        animalDao.updateAnimal(name,image,intro);
    }

    @Override
    public List<String> queryAnimalVisit(String name){
        List<String> result=new ArrayList<String>();
        result.add("0");//周1
        result.add("0");//周2
        result.add("0");//周3
        result.add("0");//周4
        result.add("0");//周5
        result.add("0");//周6
        result.add("0");//周7


        List<AnimalVisit>  animalVisitList=animalVisitMybatisDao.queryAnimalVisitByName(name);
        for(AnimalVisit animalVisit:animalVisitList){
            String weekDay=animalVisit.getWeekDay();
            int animalVisits=animalVisit.getAnimalVisits();
            if("周一".equals(weekDay)){
                result.set(0,animalVisits+"");
            }
            if("周二".equals(weekDay)){
                result.set(1,animalVisits+"");
            }
            if("周三".equals(weekDay)){
                result.set(2,animalVisits+"");
            }
            if("周四".equals(weekDay)){
                result.set(3,animalVisits+"");
            }
            if("周五".equals(weekDay)){
                result.set(4,animalVisits+"");
            }
            if("周六".equals(weekDay)){
                result.set(5,animalVisits+"");
            }
            if("周日".equals(weekDay)){
                result.set(6,animalVisits+"");
            }
        }

        return result;
    }
}
