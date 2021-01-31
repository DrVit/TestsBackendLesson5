package lesson5.base.enums;

public enum Category {

    FOOD("1", "Food");

    public final String id;
    public final String title;

    Category(String id, String title) {
        this.id = id;
        this.title = title;
    }

}
