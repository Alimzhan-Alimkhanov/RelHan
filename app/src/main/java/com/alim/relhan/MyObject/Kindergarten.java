package com.alim.relhan.MyObject;

public class Kindergarten {

    private String title_ru;
    private String title_kz;
    private String street;
    private String number;
    private String freeplace;
    private String cor1;
    private String cor2;

    public Kindergarten(String title_ru, String title_kz, String street, String number, String freeplace, String cor1, String cor2) {
        this.title_ru = title_ru;
        this.title_kz = title_kz;
        this.street = street;
        this.number = number;
        this.freeplace = freeplace;
        this.cor1 = cor1;
        this.cor2 = cor2;
    }

    public Kindergarten()
    {

    }


    public String getTitle_ru() {
        return title_ru;
    }

    public void setTitle_ru(String title_ru) {
        this.title_ru = title_ru;
    }

    public String getTitle_kz() {
        return title_kz;
    }

    public void setTitle_kz(String title_kz) {
        this.title_kz = title_kz;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getFreeplace() {
        return freeplace;
    }

    public void setFreeplace(String freeplace) {
        this.freeplace = freeplace;
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
}
