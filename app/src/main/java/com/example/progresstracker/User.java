package com.example.progresstracker;

public class User {

    private String name;
    private String group;
    private String pts;

    public User(String name, String group, String pts) {
        this.name = name;
        this.group = group;
        this.pts = pts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getPts() {
        return pts;
    }

    public void setPts(String pts) {
        this.pts = pts;
    }
}
