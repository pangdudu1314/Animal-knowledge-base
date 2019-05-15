package com.li.service;

import com.li.dao.AnimalCheckMybatisDao;
import com.li.dao.RdfOwlDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("animalCheckService")
public class AnimalCheckServiceImpl implements IAnimalCheckService {

  private Logger logger = Logger.getLogger(AnimalCheckServiceImpl.class);

  @Autowired
  private AnimalCheckMybatisDao animalCheckMybatisDao;

  @Autowired
  private RdfOwlDao rdfOwlDao;

  /**
   *
   * @param start
   * @param size
   * @return 动物审核
   */
  @Override
  public Map getAnimalCheck(int start,int size) {
    //查询总数
    int total = animalCheckMybatisDao.getAnimalCheckCount();
    List rows=animalCheckMybatisDao.getAnimalChecks(start-1,size);
    //分页查询
    Map map=new HashMap();
    map.put("total",total);
    map.put("rows",rows);
    return map;
  }

  /**
   *
   * @param id
   * @param name
   * @param kemu
   *
   */
  @Override
  public void updateAnimalImageFrom(String id,String name, String kemu) {
    String[]kemuPath=kemu.split("-");
    for(int i=0;i<kemuPath.length-1;i++){
      //重复添加，不会造成问题，owlapi已经处理了重复问题了
      rdfOwlDao.addClass(kemuPath[i],kemuPath[i+1]);
    }
    rdfOwlDao.updateIndividualInfo(name,RdfOwlDao.INDIVIDUAL_TYPE,"CLASS_",null,kemuPath[kemuPath.length-1]);
    //更新表字段状态为已处理
    animalCheckMybatisDao.updateAnimalCheckStatus(id);
  }
}
