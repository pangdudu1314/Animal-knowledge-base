package com.li.utils;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;

public class ImageDownload {

  //链接url下载图片，从网站上下载图片
  public static String downloadPicture(String urlList, String imageName) {
    URL url = null;
    int imageNumber = 0;
    try {
      File existDir=new File(Config.ABSOLUTE_PATH);
      if(!existDir.exists()){
        existDir.mkdirs();
      }
      url = new URL(urlList);
      String fileName= IDRandomUtils.createRandomStr()+".jpg";
      DataInputStream dataInputStream = new DataInputStream(url.openStream());
      imageName = Config.ABSOLUTE_PATH + File.separator +fileName;
      FileOutputStream fileOutputStream = new FileOutputStream(new File(imageName));
      ByteArrayOutputStream output = new ByteArrayOutputStream();
      byte[] buffer = new byte[1024];
      int length;
      while ((length = dataInputStream.read(buffer)) > 0) {
        output.write(buffer, 0, length);
      }
      byte[] context = output.toByteArray();
      fileOutputStream.write(output.toByteArray());
      dataInputStream.close();
      fileOutputStream.close();
      return fileName;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "";
  }
}
