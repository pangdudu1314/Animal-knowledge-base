package com.li.service;

import com.li.entities.AdminUser;
import com.li.entities.AnimalCheck;
import java.util.List;
import java.util.Map;

public interface IAnimalCheckService {
     public Map getAnimalCheck(int start,int size);
     public void updateAnimalImageFrom(String id,String name,String kemu);

     /**
      * 是否存在未被审核的动物
      * @param name
      * @return
      */
     public boolean existUnCheckAnimal(String name);
     //添加待审核
     public void addAnimalCheck(AnimalCheck animalCheck);

     /**
      * 审批动物信息
      * @param id
      */
     public void reviewAnimal(String id);

     /**
      * 审批动物信息不通过
      * @param id
      */
     public void reviewbackAnimal(String id);

}
