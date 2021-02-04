package ru.geekbrains.java4.lesson6.db.enums;

public enum Category {

    FOOD(1, "Food");

    public final int id;
    public final String title;

    Category(int id, String title) {
        this.id = id;
        this.title = title;
    }

}
