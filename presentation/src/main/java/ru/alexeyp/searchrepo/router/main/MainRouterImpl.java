package ru.alexeyp.searchrepo.router.main;

import android.support.v7.app.AppCompatActivity;
import ru.alexeyp.searchrepo.screens.login.LoginActivity;

public class MainRouterImpl extends MainRouter {

    private AppCompatActivity _activity;

    public MainRouterImpl(AppCompatActivity activity) {
        _activity = activity;
    }

    @Override
    public void navigateToLogin() {
        startActivityWithClearBackstack(_activity, LoginActivity.class);
    }
}
