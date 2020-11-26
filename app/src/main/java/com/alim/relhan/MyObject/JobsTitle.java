package com.alim.relhan.MyObject;

public class JobsTitle {

    private String type;
    private String Place;
    private String salary;
    private String time;
    private String town;
    private String date;
    private String add_per;
    private String know_level;
    private String expert;
    private String spec_skill;
    private String soc_skillz;
    private String work_street;
    private String contact_name;
    private String contact_email;
    private String contact_number;
    private String count_place;

    public JobsTitle(String type, String place, String town,String add_per, String salary, String time, String date, String know_level,
                     String expert, String spec_skill, String soc_skillz, String work_street, String contact_name,
                     String contact_email, String contact_number, String count_place) {
        this.type = type;
        Place = place;
        this.town = town;
        this.add_per = add_per;
        this.salary = salary;
        this.time = time;
        this.date = date;
        this.know_level = know_level;
        this.expert = expert;
        this.spec_skill = spec_skill;
        this.soc_skillz = soc_skillz;
        this.work_street = work_street;
        this.contact_name = contact_name;
        this.contact_email = contact_email;
        this.contact_number = contact_number;
        this.count_place = count_place;
    }

    public JobsTitle()
    {

    }


    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
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
}
