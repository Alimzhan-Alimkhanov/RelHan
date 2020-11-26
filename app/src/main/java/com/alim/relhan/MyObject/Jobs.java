package com.alim.relhan.MyObject;

public class Jobs {

    private String id;
    private String title;
    private String type;
    private String Place;
    private String add_per;
    private String salary;
    private String time;
    private String date;
    private String id_img;
    private String know_level;
    private String expert;
    private String spec_skill;
    private String soc_skillz;
    private String work_street;
    private String contact_name;
    private String contact_email;
    private String contact_number;
    private String count_place;
    private String cor1;
    private String cor2;

    private String town;


    public Jobs(String id, String title, String type, String place, String add_per, String salary, String time,
                String date, String id_img, String know_level, String expert, String spec_skill,
                String soc_skillz, String work_street, String contact_name, String contact_email,
                String contact_number, String count_place, String cor1, String cor2,String town) {
        this.id = id;
        this.title = title;
        this.type = type;
        Place = place;
        this.add_per = add_per;
        this.salary = salary;
        this.time = time;
        this.date = date;
        this.id_img = id_img;
        this.know_level = know_level;
        this.expert = expert;
        this.spec_skill = spec_skill;
        this.soc_skillz = soc_skillz;
        this.work_street = work_street;
        this.contact_name = contact_name;
        this.contact_email = contact_email;
        this.contact_number = contact_number;
        this.count_place = count_place;
        this.cor1 = cor1;
        this.cor2 = cor2;
        this.town = town;
    }

    public Jobs()
    {

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPlace() {
        return Place;
    }

    public void setPlace(String place) {
        Place = place;
    }

    public String getAdd_per() {
        return add_per;
    }

    public void setAdd_per(String add_per) {
        this.add_per = add_per;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId_img() {
        return id_img;
    }

    public void setId_img(String id_img) {
        this.id_img = id_img;
    }

    public String getKnow_level() {
        return know_level;
    }

    public void setKnow_level(String know_level) {
        this.know_level = know_level;
    }

    public String getExpert() {
        return expert;
    }

    public void setExpert(String expert) {
        this.expert = expert;
    }

    public String getSpec_skill() {
        return spec_skill;
    }

    public void setSpec_skill(String spec_skill) {
        this.spec_skill = spec_skill;
    }

    public String getSoc_skillz() {
        return soc_skillz;
    }

    public void setSoc_skillz(String soc_skillz) {
        this.soc_skillz = soc_skillz;
    }

    public String getWork_street() {
        return work_street;
    }

    public void setWork_street(String work_street) {
        this.work_street = work_street;
    }

    public String getContact_name() {
        return contact_name;
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    public String getContact_email() {
        return contact_email;
    }

    public void setContact_email(String contact_email) {
        this.contact_email = contact_email;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public String getCount_place() {
        return count_place;
    }

    public void setCount_place(String count_place) {
        this.count_place = count_place;
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

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }
}
