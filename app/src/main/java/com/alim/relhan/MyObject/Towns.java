package com.alim.relhan.MyObject;

public class Towns
{
    private  String name;
    private  String sel_name;
    private  int image;
    private String link;

    public Towns(String name,String sel_name, int image,String link) {
        this.name = name;
        this.sel_name = sel_name;
        this.image = image;
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }

    public String getLink() {
        return link;
    }

    public String getSel_name() {
        return sel_name;
    }

    public void setSel_name(String sel_name) {
        this.sel_name = sel_name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setLink(String link) {
        this.link = link;
    }
}