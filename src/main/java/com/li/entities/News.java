package com.li.entities;

import java.util.Date;

public class News {
  private String id;
  private String type;//1 国内   2  国际
  private String news;//内容
  private Date time;
  private String theme;//主题
  private String adminId;

  public String getAdminId() {
    return adminId;
  }

  public void setAdminId(String adminId) {
    this.adminId = adminId;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getNews() {
    return news;
  }

  public void setNews(String news) {
    this.news = news;
  }

  public Date getTime() {
    return time;
  }

  public void setTime(Date time) {
    this.time = time;
  }

  public String getTheme() {
    return theme;
  }

  public void setTheme(String theme) {
    this.theme = theme;
  }
}
