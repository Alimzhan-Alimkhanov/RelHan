package com.alim.relhan.MyObject;

public class User  {

    private String Email;
    private String Password;
    private String Name;
    private String favorite;

    public User()
    {

    }

    public User(String email, String password, String name, String favorite) {
        Email = email;
        Password = password;
        Name = name;
        this.favorite = favorite;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }


    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }

    public String getName() {
        return Name;
    }

    public String getFavorite() {
        return favorite;
    }
}
