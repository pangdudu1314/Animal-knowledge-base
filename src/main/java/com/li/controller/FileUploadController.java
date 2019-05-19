package com.li.controller;

import com.baidu.aip.imageclassify.AipImageClassify;
import com.li.service.IAnimalService;
import com.li.utils.Config;
import com.li.utils.JsonUtils;
import com.li.vo.AnimalInfo;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

/**
 * 上传控制类
 *
 * @author cxf
 */
@Controller
@RequestMapping(value = "/fileUpload")
public class FileUploadController {

  private Logger logger = Logger.getLogger(FileUploadController.class);
  public static final String APP_ID = "15833210";
  public static final String API_KEY = "GWTdjAOc8ymfNrEjSwuyqg5y";
  public static final String SECRET_KEY = "TMe1max8p1YMwGGO0Oon3W4wZuMwZZpV";

  @Autowired
  public IAnimalService IAnimalService;

  /**
   * 上传图片，识别动物信息。
   */
  @SuppressWarnings("rawtypes")
  @RequestMapping(value = "singleUpload", method = RequestMethod.POST)
  public void singleUpload(MultipartHttpServletRequest multipartRequest,
      HttpServletResponse response, HttpSession session) {
    response.setContentType("text/html;charset=UTF-8");

    try {
      for (Iterator it = multipartRequest.getFileNames(); it.hasNext(); ) {
        String key = (String) it.next();
        MultipartFile file = multipartRequest.getFile(key);//上传的 文件
        String originalFilename = file.getOriginalFilename();//上传图片名称

        //上传文件路径
        String absolutePath = Config.IMP_PATH;
        logger.info("save file path=" + absolutePath);
        File savedir = new File(absolutePath);
        //判断文件路径是否存在，不存在新建
        if (!savedir.exists()) {
          savedir.mkdirs();
        }
        //上传文件名
        InputStream inputFile = file.getInputStream();
        File saveFile = new File(absolutePath, originalFilename);

        //将文件放到一个文件目录中去
        FileUtils.copyInputStreamToFile(inputFile, saveFile);
        List<AnimalInfo> animalInfo = getBaiduAnimalInfo(originalFilename);

        response.getWriter().write(JsonUtils.getString(animalInfo));
      }
      logger.info("save file success");
    } catch (Exception e) {
      logger.info("save file error", e);
    }

  }

  /**
   * 上传图片到图片路径下
   */
  @SuppressWarnings("rawtypes")
  @RequestMapping(value = "uploadImage2ImageDr", method = RequestMethod.POST)
  public void uploadImage2ImageDr(MultipartHttpServletRequest multipartRequest,
      HttpServletResponse response, HttpSession session) {
    response.setContentType("text/html;charset=UTF-8");
    try {
      for (Iterator it = multipartRequest.getFileNames(); it.hasNext(); ) {
        String key = (String) it.next();
        MultipartFile file = multipartRequest.getFile(key);
        String originalFilename = file.getOriginalFilename();
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("top_num", "3");
        options.put("baike_num", "5");

        //上传文件路径
        logger.info("save file path=" + Config.ABSOLUTE_PATH);
        File savedir = new File(Config.ABSOLUTE_PATH);
        //判断是否存在，不存在新建
        if (!savedir.exists()) {
          savedir.mkdirs();
        }
        //上传文件名
        InputStream inputFile = file.getInputStream();
        File existFile = new File(Config.ABSOLUTE_PATH + File.separator + originalFilename);
        Map map = new HashMap();
        if (existFile.exists()) {
          map.put("result", "文件已存在,请对文件重命名");
        } else {
          File saveFile = new File(Config.ABSOLUTE_PATH, originalFilename);
          //将文件放到一个文件目录中去
          FileUtils.copyInputStreamToFile(inputFile, saveFile);
          map.put("result", "上传成功");
          inputFile.close();
        }
        response.getWriter().write(JsonUtils.getString(map));
      }
      logger.info("save file success");
    } catch (Exception e) {
      logger.info("save file error", e);
    }

  }

  public List<AnimalInfo> getBaiduAnimalInfo(String originalFilename) throws Exception {
    // 初始化一个AipImageClassifyClient
    AipImageClassify client = new AipImageClassify(APP_ID, API_KEY, SECRET_KEY);

    // 可选：设置网络连接参数
    client.setConnectionTimeoutInMillis(2000);
    client.setSocketTimeoutInMillis(60000);
    // 传入可选参数调用接口
    HashMap<String, String> options = new HashMap<String, String>();
    options.put("top_num", "4");
    options.put("baike_num", "5");

    DiskFileItemFactory factory = new DiskFileItemFactory();
    ServletFileUpload upload = new ServletFileUpload(factory);
    String filePath = Config.IMP_PATH + File.separator + originalFilename;
    File file = new File(filePath);
    InputStream input = new FileInputStream(file);
    byte[] byt = new byte[input.available()];
    input.read(byt);
    JSONObject res = client.animalDetect(byt, options);
    JSONArray result = (JSONArray) res.get("result");
    List<AnimalInfo> animalInfos = new ArrayList<AnimalInfo>();
    if (result.length() > 0) {
      for (int i = 0; i < result.length(); i++) {
        JSONObject r = (JSONObject) result.get(i);
        String name = r.get("name").toString();
        String score = r.get("score").toString();
        String imageUrl = "";
        String description = "";
        if (r.get("baike_info") != null) {
          try {
            imageUrl = ((JSONObject) r.get("baike_info")).get("image_url").toString();
            description = ((JSONObject) r.get("baike_info")).get("description").toString();
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
        AnimalInfo animalInfoTemp = new AnimalInfo();
        animalInfoTemp.setName(name);

        if (i == 0) {
          //存储相似度最高的图片
          animalInfoTemp.setImage(originalFilename);
        } else {
          animalInfoTemp.setImage(imageUrl);
        }
        animalInfoTemp.setScore(score);
        animalInfoTemp.setIntro(description);
        //判断描述是否存在
        if(description.trim().length()>0) {
          animalInfos.add(animalInfoTemp);
        }
      }
      IAnimalService.getAnimalFromBaidu2Drf(animalInfos);
    }
    return animalInfos;
  }
    /*public static void main(String []aa)throws Exception{
        FileUploadController fileUploadController=new FileUploadController();
        fileUploadController.getBaiduAnimalInfo("baixian.jpg");
    }*/
}
