package com.Testapp.Book.Entity;

public class User {
    private Integer userId;
    private String name;
    private String email;

    public User() {
    }

    public User(String name, String email, Integer userId) {
        this.name = name;
        this.email = email;
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(String password) {
        this.userId = userId;
    }
}
