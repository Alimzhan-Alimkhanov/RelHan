package com.alim.relhan.MyObject;

public class News {

    public  String title;
    public  String text;
    public  String link_res;
    public  String id_img;
    public  String date;
    public  String count_img;

    public News()
    {

    }


    public News(String title, String text, String link_res, String id_img, String date, String count_img) {
        this.title = title;
        this.text = text;
        this.link_res = link_res;
        this.id_img = id_img;
        this.date = date;
        this.count_img = count_img;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setLink_res(String link_res) {
        this.link_res = link_res;
    }

    public void setId_img(String id_img) {
        this.id_img = id_img;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setCount_img(String count_img) {
        this.count_img = count_img;
    }

    public String getCount_img() {
        return count_img;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String getLink_res() {
        return link_res;
    }

    public String getId_img() {
        return id_img;
    }

    public String getDate() {
        return date;
    }
}
