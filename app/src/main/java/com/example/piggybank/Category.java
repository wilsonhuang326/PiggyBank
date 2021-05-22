package com.example.piggybank;

public class Category {
    private int cid;
    private String name;
    private String iconPath;
    private String type;

    public Category() {
    }

    public Category(String name, String iconPath, String type) {
        this.name = name;
        this.iconPath = iconPath;
        this.type = type;
    }

    public Category(int id, String name, String iconPath, String type) {
        this.cid = id;
        this.name = name;
        this.iconPath = iconPath;
        this.type = type;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int id) {
        this.cid = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Category{" +
                "cid=" + cid +
                ", name='" + name + '\'' +
                ", iconPath='" + iconPath + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
