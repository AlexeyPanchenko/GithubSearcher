package ru.alexeyp.searchrepo.router.login;

import android.support.v7.app.AppCompatActivity;

import ru.alexeyp.searchrepo.screens.main.MainActivity;

public class LoginRouterImpl extends LoginRouter {

    private AppCompatActivity activity;

    public LoginRouterImpl(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Override
    public void navigateToMain() {
        startActivity(activity, MainActivity.class);
    }
}
