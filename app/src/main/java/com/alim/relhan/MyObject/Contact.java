package com.alim.relhan.MyObject;

import android.content.Context;

public class Contact {

    private String cor1;
    private String cor2;
    private String email;
    private String id_img;
    private String name;
    private String name_kz;
    private String number;
    private String number_jobget;
    private String number_people;
    private String number_soc;
    private String ruk_name;
    private String ruk_rang;
    private String site;
    private String street;
    private String town;


    public Contact(String cor1, String cor2,String email, String id_img, String name,String name_kz, String number, String number_jobget,
                   String number_people, String number_soc, String ruk_name, String ruk_rang, String site,
                   String street, String town) {
        this.cor1 = cor1;
        this.cor2 = cor2;
        this.email = email;
        this.id_img = id_img;
        this.name = name;
        this.name_kz = name_kz;
        this.number = number;
        this.number_jobget = number_jobget;
        this.number_people = number_people;
        this.number_soc = number_soc;
        this.ruk_name = ruk_name;
        this.ruk_rang = ruk_rang;
        this.site = site;
        this.street = street;
        this.town = town;
    }

    public Contact()
    {

    }




    public String getCor1() {
        return cor1;
    }

    public void setCor1(String cor1) {
        this.cor1 = cor1;
    }

    public String getCor2() {
        return cor2;
    }

    public void setCor2(String cor2) {
        this.cor2 = cor2;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId_img() {
        return id_img;
    }

    public void setId_img(String id_img) {
        this.id_img = id_img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName_kz() {
        return name_kz;
    }

    public void setName_kz(String name_kz) {
        this.name_kz = name_kz;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber_jobget() {
        return number_jobget;
    }

    public void setNumber_jobget(String number_jobget) {
        this.number_jobget = number_jobget;
    }

    public String getNumber_people() {
        return number_people;
    }

    public void setNumber_people(String number_people) {
        this.number_people = number_people;
    }

    public String getNumber_soc() {
        return number_soc;
    }

    public void setNumber_soc(String number_soc) {
        this.number_soc = number_soc;
    }

    public String getRuk_name() {
        return ruk_name;
    }

    public void setRuk_name(String ruk_name) {
        this.ruk_name = ruk_name;
    }

    public String getRuk_rang() {
        return ruk_rang;
    }

    public void setRuk_rang(String ruk_rang) {
        this.ruk_rang = ruk_rang;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }
}
