package com.li.vo;

import java.util.ArrayList;
import java.util.List;

public class AnimalInfo {
    String name;//名称
    List<AnimalInfo> kinds;//关系

    List<AnimalInfo> similartys;//相似度
    String image;//图片信息
    String intro;//描述
    String xiang;//相关鸟类
    String guan;//雉科动物
    List<AnimalInfo> dongwus;//雉科动物的图片

    public String getXiang() {
        return xiang;
    }

    public void setXiang(String xiang) {
        this.xiang = xiang;
    }

    public String getGuan() {
        return guan;
    }

    public void setGuan(String guan) {
        this.guan = guan;
    }

    public List<AnimalInfo> getDongwus() {
        return dongwus;
    }

    public void setDongwus(List<AnimalInfo> dongwus) {
        this.dongwus = dongwus;
    }

    public void addDongwus(AnimalInfo dongwu){
        if (this.dongwus == null) {
            this.dongwus = new ArrayList<AnimalInfo>();
        }
        this.dongwus.add(dongwu);
    }

    /*@Override
    public String toString() {
        return "AnimalInfo{" +
                "name='" + name + '\'' +
                ", kinds=" + kinds +
                ", similartys=" + similartys +
                ", image='" + image + '\'' +
                ", intro='" + intro + '\'' +
                ", from='" + from + '\'' +
                '}';
    }*/

    @Override
    public String toString() {
        return "AnimalInfo{" +
                "name='" + name + '\'' +
                ", kinds=" + kinds +
                ", similartys=" + similartys +
                ", image='" + image + '\'' +
                ", intro='" + intro + '\'' +
                ", xiang='" + xiang + '\'' +
                ", dongwus='" + dongwus + '\'' +
                ", guan='" + guan + '\'' +
                '}';
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AnimalInfo> getKinds() {
        return kinds;
    }

    public void setKinds(List<AnimalInfo> kinds) {
        this.kinds = kinds;
    }

    public void addKind(AnimalInfo kind) {
        if (this.kinds == null) {
            this.kinds = new ArrayList<AnimalInfo>();
        }
        this.kinds.add(kind);
    }

    public List<AnimalInfo> getSimilartys() {
        return similartys;
    }

    public void setSimilartys(List<AnimalInfo> similarty) {
        this.similartys = similarty;
    }

    public void addSimilarty(AnimalInfo similarty) {
        if (this.similartys == null) {
            this.similartys = new ArrayList<AnimalInfo>();
        }
        this.similartys.add(similarty);
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }




}
