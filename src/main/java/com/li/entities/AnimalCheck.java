package com.li.entities;

public class AnimalCheck {

  private String id;
  private String animalName;
  private String dataFrom;//数据来源  图片识图，用户新增，用户修改，用户删除
  private String status;//已审核，未审核
  private String animalImage;
  private String animalIntro;
  private String adminid;

  public String getAdminid() {
    return adminid;
  }

  public void setAdminid(String adminid) {
    this.adminid = adminid;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getAnimalName() {
    return animalName;
  }

  public void setAnimalName(String animalName) {
    this.animalName = animalName;
  }

  public String getDataFrom() {
    return dataFrom;
  }

  public void setDataFrom(String dataFrom) {
    this.dataFrom = dataFrom;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getAnimalImage() {
    return animalImage;
  }

  public void setAnimalImage(String animalImage) {
    this.animalImage = animalImage;
  }

  public String getAnimalIntro() {
    return animalIntro;
  }

  public void setAnimalIntro(String animalIntro) {
    this.animalIntro = animalIntro;
  }



}
