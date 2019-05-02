package com.li.dao;

import com.li.entities.AnimalVisit;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AnimalVisitMybatisDao {
    /**
     * 查询动物访问量,注意多参数，必须制定
     */
    public AnimalVisit queryAnimalVisitByNameAndWeek(@Param("animalName") String animalName,@Param("weekDay") String weekDay);
    public List<AnimalVisit> queryAnimalVisitByName(@Param("animalName") String animalName);

    //增加动物访问量
    public void addAnimalVisit(AnimalVisit animalVisit);

    //更新到动物访问量
    public void updateAnimalVisit(AnimalVisit animalVisit);
}
