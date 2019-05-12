package com.li.service;

import com.li.vo.AnimalInfo;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface IAnimalService {
    /**
     * 根据名称查询动物信息
     * @param name
     * @return 动物详细信息或者类别信息
     */
    /*, HttpServletRequest req,*/
    public AnimalInfo selectName(String name ) throws UnsupportedEncodingException;
    public void spinnerName(String name,HttpServletResponse resp) throws IOException;
    public void spinnerKe(String name,HttpServletResponse response) throws IOException;
    public void deleteAnimalByName(String name);
    public void addAnimal(AnimalInfo animalInfo,String type);
    public void updateAnimal(String name,String image,String intro);
    public List<String> queryAnimalVisit(String name);

    public List<AnimalInfo> getTpo8();

    /**
     * 从百度获取动物信息，并保存到rdf文件中
     * @param animalInfos
     */
    public void getAnimalFromBaidu2Drf(List<AnimalInfo> animalInfos);
}
