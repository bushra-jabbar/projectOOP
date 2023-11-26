package com.e;

public class User {
    private final String username;
    private final String password;
    private final UserType userType;
    private String cnic;
    private String name;
    private String contact;

    private double x;
    private double y;

    public int getX() {
        return (int) x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public int getY() {
        return (int) y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String getCnic() {
        return cnic;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public User(String username, String password, UserType userType, String cnic, String name, String contact, double x, double y) {
        this.username = username;
        this.password = password;
        this.userType = userType;
        this.cnic = cnic;
        this.name = name;
        this.contact = contact;
        this.x = x;
        this.y = y;
    }

    // Constructor for VOTER type including CNIC, name, and contact
    public User(String username, String password, UserType userType, String cnic, String name, String contact) {
        this.username = username;
        this.password = password;
        this.userType = userType;
        this.cnic = cnic;
        this.name = name;
        this.contact = contact;
    }
    public User(String username, String password, UserType userType) {
        this.username = username;
        this.password = password;
        this.userType = userType;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public UserType getUserType() {
        return userType;
    }
}
