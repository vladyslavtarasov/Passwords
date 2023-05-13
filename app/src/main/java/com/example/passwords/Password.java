package com.example.passwords;

public class Password {
    private final int id;
    private final String title;

    public Password(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
