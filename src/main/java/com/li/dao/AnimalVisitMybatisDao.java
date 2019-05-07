package com.li.dao;

import com.li.entities.AnimalVisit;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AnimalVisitMybatisDao {
    /**
     * 查询动物访问量,注意多参数，必须制定
     */
    /*　如果要正确的传入参数，那么就要给参数命名，因为不用xml配置文件，那么我们就要用别的方式来给参数命名，这个方式就是@Param注解*/
    public AnimalVisit queryAnimalVisitByNameAndWeek(@Param("animalName") String animalName,@Param("weekDay") String weekDay);
    public List<AnimalVisit> queryAnimalVisitByName(@Param("animalName") String animalName);

    //增加动物访问量
    public void addAnimalVisit(AnimalVisit animalVisit);

    //更新到动物访问量
    public void updateAnimalVisit(AnimalVisit animalVisit);
}
