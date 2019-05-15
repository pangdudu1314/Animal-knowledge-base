package com.li.controller;

import com.li.service.IAnimalService;
import com.li.utils.Config;
import com.li.utils.JsonUtils;
import com.li.vo.AnimalInfo;
import com.li.vo.AnimalTree;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/queryClass")
public class AnimalController {
    @Autowired
    public IAnimalService iAnimalService;
   /* @Autowired
    public RdfOwlDao rdfOwlDao;
*/
    @RequestMapping("/queryClassLevel")
    public void spinnerName(String name, HttpServletResponse response) throws IOException {
        iAnimalService.spinnerName(name, response);
    }

    @RequestMapping("/queryClassLevel1")
    public void spinnerKe(String name, HttpServletResponse response) throws IOException {
        iAnimalService.spinnerKe(name, response);
    }

    @RequestMapping("/selectAdmin")
    public String selectAdmin(String name, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        JSONArray nodes = new JSONArray();
        JSONArray links = new JSONArray();
        System.out.println("name===============" + name);
        request.setAttribute("key", name);
        AnimalInfo animalInfo = iAnimalService.selectName(name);

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
//访问量
        List<String> anialVisits=  iAnimalService.queryAnimalVisit(name);
        request.setAttribute("anialVisits", anialVisits);
        request.setAttribute("nodes", nodes);
        request.setAttribute("links", links);
        request.setAttribute("animalInfo", animalInfo);
        System.out.println("animalInfo=" + JsonUtils.getString(animalInfo));
        if (animalInfo.getKinds() != null) {
            //科
            return "kemu";
        } else {
            //动物
            return "dongwu1";
        }

    }


    @RequestMapping("/gotoFrame")
    public String gotoFrame() {
        return "frame";
    }

    @RequestMapping("/deleteAnimal")
    public String deleteAnimal() {
        return "deleteAnimal";
    }

    @RequestMapping("/addAnimal")
    public String addAnimal() {
        return "addAnimal";
    }

    @RequestMapping("/queryAnimal")
    public String frame_a() {
        return "queryAnimal";
    }

    @RequestMapping("/updateAnimal")
    public String frame_b() {
        return "updateAnimal";
    }

    @RequestMapping("/wenzi")
    public String wenzi() {
        return "wenzichaxun";
    }

    @RequestMapping("/tupian")
    public String tupian() {
        return "tupianchaxun";
    }

    @RequestMapping("/systemDiagram")
    public String systemDiagram(HttpServletRequest request, HttpServletResponse response){
        AnimalTree animalTree= iAnimalService.systemDiagram();
        request.setAttribute("animalTree",JsonUtils.getString(animalTree));
        return "systemDiagram";
    }

    @RequestMapping("/bianji")
    public String bianji(String name, HttpServletRequest request, HttpServletResponse response) throws IOException {

        System.out.println("name===============" + name);

        AnimalInfo animalInfo = iAnimalService.selectName(name);
        request.setAttribute("animalInfo", animalInfo);

        return "bianji";
    }

    @RequestMapping("/updateAnimalMethod")
    public void updateAnimalMethod(String name, String file, String intro, HttpServletResponse response) {
        try {
            //查询一下名称是否存在，存在就不再进行添加
            Map map = new HashMap();

            iAnimalService.updateAnimal(name,"images//" + file,intro);
            map.put("result", "修改成功");
            //传给前台json数据
            String json = JsonUtils.getString(map);
            System.out.println(json);
            response.setContentType("text/javascript;charset=utf-8");
            response.getWriter().write(json);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @RequestMapping("/deleteAnimalMethod")
    public void deleteAnimalMethod(String name, HttpServletRequest request, HttpServletResponse response) {
        try {
            Map map = new HashMap();
            iAnimalService.deleteAnimalByName(name);
            map.put("result", "删除成功");
            //传给前台json数据
            String json = JsonUtils.getString(map);
            System.out.println(json);
            response.setContentType("text/javascript;charset=utf-8");
            response.getWriter().write(json);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @RequestMapping("/getTpo8")
    public void getTpo8(HttpServletRequest request, HttpServletResponse response) {
        try {
            System.out.println("========================");

            String json = JsonUtils.getString(iAnimalService.getTpo8());
            System.out.println(json);
            response.setContentType("text/javascript;charset=utf-8");
            response.getWriter().write(json);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @RequestMapping("/addAnimalMethod")
    public void addAnimalMethod(String kemu, String name, String file, String message, HttpServletRequest request, HttpServletResponse response) {
        try {

            //查询一下名称是否存在，存在就不再进行添加
            Map map = new HashMap();
            AnimalInfo existAnimalInfo = iAnimalService.selectName(name);
            if (existAnimalInfo.getName() != null) {
                //动物存在
                map.put("result", "动物已存在");
                //原来上传的 文件，也需要删除掉，因为已经先上传了图片，再添加的动物，动物没添加上，图片是不是需要删除呢？
                //但是动物没上传成功
                //一般都是需要做一下删除的，我修改一下动物名称，不换图片，就不能上传了吗，必须换图片吗？对不对,所以要删除原来上传的图片
                String absolutePath = Config.ABSOLUTE_PATH;
                File existFile = new File(absolutePath + File.separator + file);
                existFile.delete();
            } else {
                //动物不存在


                //  IAnimalService.deleteAnimalByName(name);
                AnimalInfo animalInfo = new AnimalInfo();
                animalInfo.setName(name);
                animalInfo.setImage("images//" + file);
                animalInfo.setIntro(message);
                iAnimalService.addAnimal(animalInfo, kemu);
                map.put("result", "添加成功");
            }
            //传给前台json数据
            String json = JsonUtils.getString(map);
            System.out.println(json);
            response.setContentType("text/javascript;charset=utf-8");
            response.getWriter().write(json);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
