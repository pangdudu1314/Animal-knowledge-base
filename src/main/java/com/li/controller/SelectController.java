package com.li.controller;

import com.li.dao.RdfOwlDao;
import com.li.service.SelectService;
import com.li.utils.JsonUtils;
import com.li.vo.AnimalInfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@RequestMapping("/queryClass")
public class SelectController {
    @Autowired
    public SelectService selectService;
    @Autowired
    public RdfOwlDao rdfOwlDao;
    @RequestMapping("/queryClassLevel")
    public void spinnerName(String name,  HttpServletResponse response) throws IOException {
        selectService.spinnerName(name,response);
    }
    @RequestMapping("/selectAdmin")
    public String selectAdmin(String name, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        JSONArray nodes = new JSONArray();
        JSONArray links = new JSONArray();
        System.out.println("name===============" + name);
        request.setAttribute("key",name);
        AnimalInfo animalInfo = selectService.selectName(name);

        /*     System.out.println("animalInfo========================" + animalInfo);*/

        if (animalInfo.getKinds() != null) {
            //name对应的动物或者科目节点信息
            JSONObject animalNode = new JSONObject();
            animalNode.put("category", 0);
            animalNode.put("name", animalInfo.getName());
            animalNode.put("value", 80);
            animalNode.put("symbol", animalInfo.getImage());
            animalNode.put("symbolSize", "[50, 50]");
            nodes.add(animalNode);
            //关系
            List<AnimalInfo> kinds = animalInfo.getKinds();
            if (kinds != null && kinds.size() > 0) {
                for (int i = 0; i < kinds.size(); i++) {
                    AnimalInfo k = kinds.get(i);
                    //这里填充 nodes和links等信息
                    JSONObject node = new JSONObject();
                    node.put("category", 0);
                    node.put("name", k.getName());
                    node.put("value", 60);
                    node.put("symbol", k.getImage());
                    node.put("symbolSize", "[30, 30]");

                    nodes.add(node);

                    //链路关联信息
                    JSONObject LinkNode = new JSONObject();
                    LinkNode.put("source", animalInfo.getName());
                    LinkNode.put("target", k.getName());
                   // LinkNode.put("weight", 0.0000000000000000000000000001);
                    links.add(LinkNode);
                }
            }



        } else {
            //name对应的动物或者科目节点信息
            JSONObject animalNode = new JSONObject();
            animalNode.put("category", 0);
            animalNode.put("name", animalInfo.getName());
            animalNode.put("value", 80);
            animalNode.put("symbol", animalInfo.getImage());
            animalNode.put("symbolSize", "[150, 150]");
            nodes.add(animalNode);
        }

        request.setAttribute("nodes", nodes);
        request.setAttribute("links", links);
        request.setAttribute("animalInfo", animalInfo);
        System.out.println("animalInfo="+ JsonUtils.getString(animalInfo));
        if (animalInfo.getKinds() != null) {
            //科
            return "kemu";
        }else{
            //动物
            return "dongwu1";
        }

    }

    @RequestMapping("/gotoFrame")
    public String gotoFrame() {
        return "frame";
    }

    @RequestMapping("/frame_a")
    public String frame_a()  {
        return "frame_a";
    }

    @RequestMapping("/frame_b")
    public String frame_b()  {
        return "frame_b";
    }

    @RequestMapping("/wenzi")
    public String wenzi()  {
        return "wenzichaxun";
    }

    @RequestMapping("/tupian")
    public String  tupian()  {
        return "tupianchaxun";
    }


}
