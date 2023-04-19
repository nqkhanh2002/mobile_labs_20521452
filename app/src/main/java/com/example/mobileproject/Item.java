package com.example.mobileproject;

public class Item {
    private String name;
    private String phone;

    public Item(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public Item() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    @Override
    public String toString() {
        return name + " : " + phone;
    }
}
