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
    private boolean hasVoted;
    private int voterId;

    public int getVoterId() {
        return voterId;
    }

    public void setVoterId(int voterId) {
        this.voterId = voterId;
    }

    public boolean hasVoted() {
        return hasVoted;
    }

    public void setVoted(boolean voted) {
        this.hasVoted = voted;
    }


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

    public User(String username, String password, UserType userType, String cnic, String name, String contact, double x, double y,int voterId) {
        this.username = username;
        this.password = password;
        this.userType = userType;
        this.cnic = cnic;
        this.name = name;
        this.contact = contact;
        this.x = x;
        this.y = y;
        this.voterId=voterId;
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
