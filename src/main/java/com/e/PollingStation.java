package com.e;


public class PollingStation {

    private String centerName;
    private double x;
    private double y;

    public PollingStation(String centerName, double x, double y) {
        this.centerName = centerName;
        this.x = x;
        this.y = y;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public int getX() {
        return (int)x;
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

    @Override
    public String toString() {
        return "PollingStation{" +
                "centerName='" + centerName + '\'' +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
