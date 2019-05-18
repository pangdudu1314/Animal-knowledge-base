package com.li.dao;

import com.li.entities.AnimalCheck;
import com.li.entities.AnimalVisit;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface AnimalCheckMybatisDao {

    //添加待审核
    public void addAnimalCheck(AnimalCheck animalCheck);
    public int getAnimalCheckCount();
    public List<AnimalCheck> getAnimalChecks(@Param("start")int start,@Param("size")int size);
    public  void updateAnimalCheckStatus(String id);
    public  void updateAnimalCheckStatusBack(String id);
    public  AnimalCheck getAnimalCheckById(String id);
    public  int existUnCheckAnimalCount(String animalName);
}

