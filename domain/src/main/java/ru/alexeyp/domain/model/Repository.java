package ru.alexeyp.domain.model;

public class Repository {

    private final String name;
    private final String fullName;
    private final String description;
    private final String avatarUrl;

    public Repository(String name, String fullName, String description, String avatarUrl) {
        this.name = name;
        this.fullName = fullName;
        this.description = description;
        this.avatarUrl = avatarUrl;
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        return fullName;
    }

    public String getDescription() {
        return description;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }
}
