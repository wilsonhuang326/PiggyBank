package com.example.piggybank;

public enum CategoryType {
    EXPENSE("expense"),INCOME("income");
    private  String typeName;

    CategoryType(String typeName) {
        this.typeName = typeName;
    }
    public boolean equalsType(String otherName) {
        return typeName.equals(otherName);
    }

    public String toString() {
        return this.typeName;
    }
}
