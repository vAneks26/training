package org.example;

public class Workout {
    private int id;
    private int userId;
    private String date;
    private String description;

    public Workout(int id, int userId, String date, String description) {
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
