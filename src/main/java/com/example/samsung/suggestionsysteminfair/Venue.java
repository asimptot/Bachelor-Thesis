package com.example.samsung.suggestionsysteminfair;

/**
 * Created by rajaee on 3/19/16.
 */
public class Venue {
    private String name;
    private String id;

    public Venue(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
