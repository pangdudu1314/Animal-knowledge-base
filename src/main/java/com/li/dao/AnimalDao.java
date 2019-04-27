package com.li.dao;

import java.util.List;

public class AnimalDao {
    String name;//名称
   /* List<AnimalDao> kinds;//关系*/

    List<AnimalDao> similarity;//相似度
    String image;//图片信息
    String intro;//描述
    List<AnimalDao> siblings;//雉科动物的图片

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AnimalDao> getSimilarity() {
        return similarity;
    }

    public void setSimilarity(List<AnimalDao> similarity) {
        this.similarity = similarity;
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

    public List<AnimalDao> getSiblings() {
        return siblings;
    }

    public void setSiblings(List<AnimalDao> siblings) {
        this.siblings = siblings;
    }
}
