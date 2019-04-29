package com.li.dao;


import com.li.utils.JsonUtils;
import com.li.vo.AnimalInfo;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

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
        if (mapListInfo.keySet().size() == 0) {
            // 没有结果，说明查询的是科
            //查询科目对应的动物名称 返回的是集合 并没有图片信息
            List<String> animalnames = rdfOwlDao.queryIndividualsType(name);
            //循环动物名称集合查询对应动物信息
            for (String animalname : animalnames) {
                AnimalInfo animalInfoTemp = selectAllInfo(animalname);
                animalInfo.addKind(animalInfoTemp);
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
                    } */else if (type.startsWith("intro")) {
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

    public void spinner(String name, String queryClassLevel, HttpServletResponse resp) throws IOException {
        List list = rdfOwlDao.queryLink(name);
        String json = JsonUtils.getString(list);
        System.out.println(json);
        resp.setContentType("text/javascript;charset=utf-8");
        resp.getWriter().write(json);


    }

}
