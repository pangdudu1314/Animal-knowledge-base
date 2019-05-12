package com.li.service;

import com.li.entities.AdminUser;
import java.util.List;
import java.util.Map;

public interface IAnimalCheckService {
     public Map getAnimalCheck(int start,int size);
     public void updateAnimalImageFrom(String id,String name,String kemu);

}
