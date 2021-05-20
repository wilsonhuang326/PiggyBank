package com.example.piggybank;

public enum Action {
    INSERT("insert"),UPDATE("update");
    private  String typeName;

    Action(String typeName) {
        this.typeName = typeName;
    }
    public boolean equalsType(String otherName) {
        return typeName.equals(otherName);
    }

    public String toString() {
        return this.typeName;
    }
}
