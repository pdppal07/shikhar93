package com.example.shikharjai.contact;

public class model {
    String name;
    long phone;
    String email;

    public model() {
    }

    public model(String name, long phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public long getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

}
