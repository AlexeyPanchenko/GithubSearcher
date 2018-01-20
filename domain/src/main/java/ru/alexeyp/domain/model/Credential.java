package ru.alexeyp.domain.model;


public class Credential {

    private final String _login;
    private final String _password;

    public Credential(String login, String password) {
        _login = login;
        _password = password;
    }

    public String getLogin() {
        return _login;
    }

    public String getPassword() {
        return _password;
    }
}
