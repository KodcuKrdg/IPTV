package com.example.coffeeshop;

public class UserInfo {
    private String Id;
    private String Email;
    private String Password;

    public UserInfo() {
    }

    public UserInfo(String id, String email, String password) {
        Id = id;
        Email = email;
        Password = password;
    }

    public String getId() {
        return Id;
    }
    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }
}
