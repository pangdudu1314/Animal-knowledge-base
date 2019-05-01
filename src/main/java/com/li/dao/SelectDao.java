package com.li.dao;


import com.li.utils.JsonUtils;
import com.li.vo.AnimalInfo;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

@Component("selectDao")
public class SelectDao {
  /* public static void main(String []aa){
        SelectDao dao=new SelectDao();
        System.out.println(dao.selectAllInfo("猫科" ));
    }*/

    /*    JSONArray nodes = new JSONArray();
        JSONArray links = new JSONArray();*/
    RdfOwlDao rdfOwlDao = new RdfOwlDao();

    //查询所有信息
    public AnimalInfo selectAllInfo(String name) throws UnsupportedEncodingException {
        AnimalInfo animalInfo = new AnimalInfo();
        animalInfo.setName(name);
        Map<String, List<String>> mapListInfo = rdfOwlDao.getIndividualInfo(name);
        //如果查询的动物名称不存在，那么结果也是0，科目也是0，怎么区分呢？

        if (mapListInfo.keySet().size() == 0) {
            // 没有结果，说明查询的是科
            //查询科目对应的动物名称 返回的是集合 并没有图片信息
            List<String> animalnames = rdfOwlDao.queryIndividualsByType(name);
            //如果animalnames 也没有信息，能说明查的什么都没有哈
            //循环动物名称集合查询对应动物信息
            if (animalnames != null && animalnames.size() > 0) {
                for (String animalname : animalnames) {
                    AnimalInfo animalInfoTemp = selectAllInfo(animalname);
                    animalInfo.addKind(animalInfoTemp);
                }
            } else {
                animalInfo.setName(null);//什么都没有，对名称设置null
            }
        } else {
            //有结果说明查询的是动物
            //这里如果查询的是猫科，name返回的是猫科和kind等信息集合
            for (String type : mapListInfo.keySet()) {
                List<String> values = mapListInfo.get(type);
                for (String value : values) {
                    System.out.println("type=" + type + ",value=" + value);//打印确认看一下
                    if (type.startsWith("image")) {//以kind开头的类别
                        animalInfo.setImage(value);

                    } /*else if (type.startsWith("CLASS_")) {//如果查询的是动物，那么class_显示的是动物对应的科
                        AnimalInfo kind = new AnimalInfo();
                        //value是对应<uni:name>value</uni：name>
                        kind.setName(value);
                        animalInfo.addKind(kind);
                    } */ else if (type.startsWith("intro")) {
                        animalInfo.setIntro(value);

                    } else if (type.startsWith("similarity")) {

                        AnimalInfo similarty = new AnimalInfo();
                        similarty.setName(value);
                        animalInfo.addSimilarty(similarty);
                    } else if (type.startsWith("siblings")) {

                        AnimalInfo siblings = new AnimalInfo();
                        siblings.setName(value);
                        animalInfo.addSibling(siblings);
                    }
                }
            }
        }
        return animalInfo;
    }

    public void spinner(String name, HttpServletResponse resp) throws IOException {
        List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();//不懂

        if (name != null && !name.equals("")) {//name!="" 字符串判断是都相等或者不相等，不是这么判断的
            //说明查询的是动物科目，比如稚科，雀科等
            List<String> list = rdfOwlDao.queryIndividualsByType(name);
            for (String li : list) {
                //遍历动物名称，返回动物信息
                String type = li;
                Map map = new HashMap();
                map.put("id", type);
                map.put("text", type);
                resultList.add(map);
            }
        } else {
            //没有传参数，查询的是动物大类
            List<String> lists = rdfOwlDao.queryLink("动物");//动物对应的鸟类，
            for (String species : lists) {
                //遍历鸟类，查询鸟类对应的雀科，稚科等信息
                List<String> list = rdfOwlDao.queryLink(species);
                for (String li : list) {
                    //遍历雀科，稚科等信息，返回结果
                    String type = li;
                    Map map = new HashMap();
                    map.put("id", type);
                    map.put("text", type);
                    map.put("group", species);//为什么有group 是因为分组的那个下拉框需要group分组
                    resultList.add(map);
                }
            }
        }
        String json = JsonUtils.getString(resultList);
        System.out.println(json);
        resp.setContentType("text/javascript;charset=utf-8");
        resp.getWriter().write(json);
    }

    public void spinnerKe(String name, HttpServletResponse resp) throws IOException {
        spinner(name, resp);
    }

    public void deleteAnimalByName(String name) {
        rdfOwlDao.removeIndividualInfo(name);
    }

    public void addAnimal(AnimalInfo animalInfo, String type) {
        rdfOwlDao.addIndividualInfo(animalInfo.getName(), type);
        rdfOwlDao.addIndividualInfo(animalInfo.getName(), RdfOwlDao.INDIVIDUAL_PROPERTY, "image", animalInfo.getImage());
        rdfOwlDao.addIndividualInfo(animalInfo.getName(), RdfOwlDao.INDIVIDUAL_PROPERTY, "intro", animalInfo.getIntro());
    }
    public void updateAnimal(String name,String image,String intro){
        rdfOwlDao.updateIndividualInfo(name,RdfOwlDao.INDIVIDUAL_PROPERTY,"image",null,image);
        rdfOwlDao.updateIndividualInfo(name,RdfOwlDao.INDIVIDUAL_PROPERTY,"intro",null,intro);
    }
}
