package com.li.vo;

import java.util.ArrayList;
import java.util.List;

public class AnimalInfo {
    String name;//名称
    List<AnimalInfo> kinds;//关系

    List<AnimalInfo> similartys;//相似度
    String image;//图片信息
    String intro;//描述
    String kemu;
    public String getKemu() {
        return kemu;
    }

    public void setKemu(String kemu) {
        this.kemu = kemu;
    }



    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    String score;//相似度，百度识图返回信息使用
    List<AnimalInfo> siblings;//雉科动物的图片

    public List<AnimalInfo> getSiblings() {
        return siblings;
    }

    public void setSiblings(List<AnimalInfo> siblings) {
        this.siblings = siblings;
    }
    public void addSibling(AnimalInfo sibling) {
        if (this.siblings == null) {
            this.siblings = new ArrayList<AnimalInfo>();
        }
        String name=sibling.getName();
        boolean exist=false;
        for(AnimalInfo a:this.siblings){
            if(a.getName().equalsIgnoreCase(name)){
                exist=true;
            }
        }
        if(!exist){
            this.siblings.add(sibling);
        }
    }
    @Override
    public String toString() {
        return "AnimalInfo{" +
                "name='" + name + '\'' +
                ", kinds=" + kinds +
                ", similartys=" + similartys +
                ", image='" + image + '\'' +
                ", intro='" + intro + '\'' +
                ", siblings=" + siblings +
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
