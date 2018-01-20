package ru.alexeyp.data.settings.model;

import ru.alexeyp.data.settings.IPreference;

public class User implements IPreference {

    public static final String KEY = User.class.getSimpleName();
    private final String _login;
    private final String _token;

    public User(String login, String token) {
        _login = login;
        _token = token;
    }

    @Override
    public String getKey() {
        return KEY;
    }

    public String getToken() {
        return _token;
    }

    public String getLogin() {
        return _login;
    }
}
