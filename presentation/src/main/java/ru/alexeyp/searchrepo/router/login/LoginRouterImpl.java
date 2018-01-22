package ru.alexeyp.searchrepo.router.login;

import android.support.v7.app.AppCompatActivity;
import ru.alexeyp.searchrepo.screens.main.MainActivity;

public class LoginRouterImpl extends LoginRouter {

    private AppCompatActivity _activity;

    public LoginRouterImpl(AppCompatActivity activity) {
        _activity = activity;
    }

    @Override
    public void navigateToMain() {
        startActivityWithClearBackstack(_activity, MainActivity.class);
    }
}
