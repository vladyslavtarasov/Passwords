package com.example.passwords;

public class Password {
    private final int id;

    private final String website;
    private final String username;
    private final String title;

    public Password(int id, String website, String username, String title) {
        this.id = id;
        this.website = website;
        this.username = username;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getWebsite() {
        return website;
    }

    public String getUsername() {
        return username;
    }

    public String getTitle() {
        return title;
    }
}
