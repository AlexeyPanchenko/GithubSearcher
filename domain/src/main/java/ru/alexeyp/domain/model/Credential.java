package ru.alexeyp.domain.model;


public class Credential {

    private final String login;
    private final String password;

    public Credential(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
