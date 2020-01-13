package com.ajaygaikwad.calender2020.Pojo;

public class ExpandableListModel {

    public int image;
    public String title;

    public ExpandableListModel(int image, String title) {
        this.image = image;
        this.title = title;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {

        return image;
    }

    public String getTitle() {
        return title;
    }

}
