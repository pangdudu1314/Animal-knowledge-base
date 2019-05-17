package com.li.service;

import com.li.dao.AnimalCheckMybatisDao;
import com.li.dao.AnimalVisitMybatisDao;
import com.li.dao.AnimalDao;
import com.li.dao.RdfOwlDao;
import com.li.entities.AnimalCheck;
import com.li.entities.AnimalVisit;
import com.li.utils.Config;
import com.li.utils.DateUtils;
import com.li.utils.IDRandomUtils;
import com.li.utils.ImageDownload;
import com.li.vo.AnimalInfo;
import java.io.File;
import java.io.InputStream;
import java.util.HashMap;

import com.li.vo.AnimalTree;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("iAnimalService")
public class AnimalServiceImpl implements IAnimalService {

  private Logger logger = Logger.getLogger(AnimalServiceImpl.class);
  @Autowired
  private AnimalDao animalDao;
  @Autowired
  private RdfOwlDao rdfOwlDao;
  @Autowired
  private AnimalVisitMybatisDao animalVisitMybatisDao;
  @Autowired
  private AnimalCheckMybatisDao animalCheckMybatisDao;

  @Override
  public AnimalInfo selectName(String name){
    //创建AnimalInfo对象
    AnimalInfo animalInfo = animalDao.selectAllInfo(name);
    //判断如果animalInfo有图片信息，那么认为是访问的动物，需要更新动物访问量
    if (animalInfo.getImage() != null && animalInfo.getImage().trim().length() > 0) {
      //更新动物访问量
      String weekDay = DateUtils.getWeekOfDate();
      AnimalVisit animalVisit = animalVisitMybatisDao.queryAnimalVisitByNameAndWeek(name, weekDay);
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
    List<AnimalInfo> similaryts = animalInfo.getSimilartys();
    if (similaryts != null && similaryts.size() > 0) {
      //保存查询的相似动物或者科目信息
      List<AnimalInfo> similarytsQuery = getAnimalInfos(similaryts);
      //相似信息，重新赋值
      animalInfo.setSimilartys(similarytsQuery);

    }
    //同科动物
    List<AnimalInfo> siblings = animalInfo.getSiblings();
    if (siblings != null && siblings.size() > 0) {
      //保存查询的相似动物或者科目信息
      List<AnimalInfo> siblingsQuery = getAnimalInfos(siblings);
      //相似信息，重新赋值
      animalInfo.setSiblings(siblingsQuery);

    }
    return animalInfo;
  }

  //查询动物或者科目信息
  private List<AnimalInfo> getAnimalInfos(List<AnimalInfo> animalInfos)
     {
    /*此处报错不知怎么修改
     * List<AnimalInfo> kindsQuery = new ArrayList<>()；*/
    List<AnimalInfo> kindsQuery = new ArrayList<AnimalInfo>();
    //如果有关联信息，那就查询
    for (int i = 0; i < animalInfos.size(); i++) {
      //animalInfos是list形式的有序列表,用get取值
      AnimalInfo s = animalInfos.get(i);

      String kname = s.getName();
      AnimalInfo temp = animalDao.selectAllInfo(kname);
      if (temp.getName() != null) {
        kindsQuery.add(temp);
      } else {
        logger.error("kname=" + kname + "未配置!");
      }

    }
    return kindsQuery;
  }

  @Override
  public void spinnerName(String name, HttpServletResponse resp)  {
    animalDao.spinner(name, resp);
  }

  @Override
  public void spinnerKe(String name, HttpServletResponse response)  {
    animalDao.spinnerKe(name, response);
  }

  @Override
  public void deleteAnimalByName(String name) {
    animalDao.deleteAnimalByName(name);
  }

  //获取要查找的动物信息
  public void addAnimal(AnimalInfo animalInfo, String type) {
    animalDao.addAnimal(animalInfo, type);
  }

  @Override
  public void updateAnimal(String name, String image, String intro) {
    animalDao.updateAnimal(name, image, intro);
  }

  @Override
  public List<String> queryAnimalVisit(String name) {
    List<String> result = new ArrayList<String>();
    result.add("0");//周1
    result.add("0");//周2
    result.add("0");//周3
    result.add("0");//周4
    result.add("0");//周5
    result.add("0");//周6
    result.add("0");//周7

    List<AnimalVisit> animalVisitList = animalVisitMybatisDao.queryAnimalVisitByName(name);
    for (AnimalVisit animalVisit : animalVisitList) {
      String weekDay = animalVisit.getWeekDay();
      int animalVisits = animalVisit.getAnimalVisits();
      if ("周一".equals(weekDay)) {
        result.set(0, animalVisits + "");
      }
      if ("周二".equals(weekDay)) {
        result.set(1, animalVisits + "");
      }
      if ("周三".equals(weekDay)) {
        result.set(2, animalVisits + "");
      }
      if ("周四".equals(weekDay)) {
        result.set(3, animalVisits + "");
      }
      if ("周五".equals(weekDay)) {
        result.set(4, animalVisits + "");
      }
      if ("周六".equals(weekDay)) {
        result.set(5, animalVisits + "");
      }
      if ("周日".equals(weekDay)) {
        result.set(6, animalVisits + "");
      }
    }

    return result;
  }

  @Override
  public List<AnimalInfo> getTpo8() {
    List<Map> listTemp = animalVisitMybatisDao.queryAnimalSumVisit();
    List<AnimalInfo> result = new ArrayList<AnimalInfo>();
    for (Map map : listTemp) {
      String name = map.get("animal_name").toString();
      try {
        AnimalInfo animalInfo = animalDao.selectAllInfo(name);
        result.add(animalInfo);
      } catch (Exception e) {
      }
    }
    return result;
  }

  @Override
  public void getAnimalFromBaidu2Drf(List<AnimalInfo> animalInfos) {
    //先获取相似图片集合
    List<String> similartys = new ArrayList<String>();
    for (int i = 0; i < animalInfos.size(); i++) {
      similartys.add(animalInfos.get(i).getName());
    }
    for (int i = 0; i < animalInfos.size(); i++) {
      AnimalInfo animalInfo = animalInfos.get(i);
      AnimalInfo animalInfoRdf = animalDao.selectAllInfo(animalInfo.getName());
      if (animalInfoRdf.getName() == null) {
        //说明没有此动物
        String imageUrl = animalInfo.getImage();
        if (imageUrl.contains("http")) {
          String imageName = ImageDownload.downloadPicture(imageUrl, animalInfo.getName());
          animalInfo.setImage("images//" + imageName);
        } else {
          try {
            String filePath = Config.IMP_PATH + File.separator + animalInfo.getImage();
            File impfile = new File(filePath);
            File saveFile = new File(Config.ABSOLUTE_PATH, animalInfo.getImage());
            //将文件放到一个文件目录中去
            if(!saveFile.exists()){
              FileUtils.copyFile(impfile, saveFile);
            }
          } catch (Exception e) {
            e.printStackTrace();
          }
          animalInfo.setImage("images//" + animalInfo.getImage());
        }
        rdfOwlDao.loadOWLOntology();

        rdfOwlDao.addIndividualInfo(animalInfo.getName(),"动物");
        rdfOwlDao.updateIndividualInfo(animalInfo.getName(),RdfOwlDao.INDIVIDUAL_PROPERTY,"image",null,animalInfo.getImage());
        rdfOwlDao.updateIndividualInfo(animalInfo.getName(),RdfOwlDao.INDIVIDUAL_PROPERTY,"intro",null,animalInfo.getIntro());
        for (int j = 0; j < similartys.size(); j++) {
          String ss=similartys.get(j);
          rdfOwlDao.updateIndividualInfo(animalInfo.getName(),RdfOwlDao.INDIVIDUAL_LINK,"similarity",ss,ss);
        }
        rdfOwlDao.saveOWLOntology();

        AnimalCheck animalCheck=new AnimalCheck();
        animalCheck.setId(IDRandomUtils.createRandomStr());
        animalCheck.setAnimalName(animalInfo.getName());
        animalCheck.setDataFrom("图片识图");
        animalCheck.setStatus("未处理");
        animalCheck.setAnimalImage(animalInfo.getImage());
        animalCheck.setAnimalIntro(animalInfo.getIntro());
        animalCheckMybatisDao.addAnimalCheck(animalCheck);
      }else{
        animalInfo.setImage(animalInfoRdf.getImage());
      }
    }
  }

  @Override
  public AnimalTree systemDiagram() {
    AnimalTree animalTree= new AnimalTree();
    animalTree.setName("动物");//动物层级
    animalTree.setLevel(0);
    rdfOwlDao.loadOWLOntology();
    List<String> childNames_1= rdfOwlDao.queryLink(animalTree.getName());
    for(int i=0;i<childNames_1.size();i++){
      String childName_1=childNames_1.get(i);//鸟类层级
      AnimalTree child_1= new AnimalTree();
      child_1.setName(childName_1);
      child_1.setLevel(1);
      animalTree.addChild(child_1);
      List<String> childNames_2= rdfOwlDao.queryLink(childName_1);
      for(int j=0;j<childNames_2.size();j++){
        String childName_2=childNames_2.get(j);//鸟类层级
        AnimalTree child_2= new AnimalTree();
        child_2.setName(childName_2);
        child_2.setLevel(2);

        child_1.addChild(child_2);

        List<String> childNames_3 = rdfOwlDao.queryIndividualsByType(childName_2);
        for(int k=0;k<childNames_3.size();k++){
          String childName_3=childNames_3.get(k);//动物名称
          AnimalTree child_3= new AnimalTree();
          child_3.setName(childName_3);
          child_3.setLevel(3);
          child_2.addChild(child_3);
        }
      }
    }
    return animalTree;
  }

}
