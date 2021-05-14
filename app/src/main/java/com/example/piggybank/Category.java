package com.example.piggybank;

public class Category {
    private int id;
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
        this.id = id;
        this.name = name;
        this.iconPath = iconPath;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
