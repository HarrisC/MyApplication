package com.example.inspiron.myapplication;

/**
 * Created by inspiron on 14/6/2017.
 */

public class ItemModel {
    private int image;
    private String name;

    public ItemModel(int image, String name) {
        this.image = image;
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
