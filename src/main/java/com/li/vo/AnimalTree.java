package com.li.vo;

import java.util.ArrayList;
import java.util.List;

public class AnimalTree {
    String name;
    List<AnimalTree> children;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AnimalTree> getChildren() {
        return children;
    }

    public void setChildren(List<AnimalTree> children) {
        this.children = children;
    }
    public void addChild(AnimalTree child) {
        if(this.children ==null){
            this.children=new ArrayList<AnimalTree>();
        }
        this.children.add(child);
    }


}
