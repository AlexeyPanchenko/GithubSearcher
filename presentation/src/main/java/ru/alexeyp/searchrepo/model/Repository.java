package ru.alexeyp.searchrepo.model;

public class Repository {

    private final String _name;
    private final String _description;
    private final String _avatar;

    public Repository(String name, String description, String avatar) {
        _name = name;
        _description = description;
        _avatar = avatar;
    }

    public String getName() {
        return _name;
    }

    public String getDescription() {
        return _description;
    }

    public String getAvatar() {
        return _avatar;
    }

    @Override
    public String toString() {
        return "Repository(name = " + _name +
                ", description = " + _description +
                ", avatar = " + _avatar + ")";
    }
}
