package com.alim.relhan.MyObject;

public class UserInfo {

    private String email;
    private String find_text;

    public UserInfo()
    {

    }


    public UserInfo(String email, String find_text) {
        this.email = email;
        this.find_text = find_text;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFind_text() {
        return find_text;
    }

    public void setFind_text(String find_text) {
        this.find_text = find_text;
    }
}
