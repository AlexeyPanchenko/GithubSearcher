package ru.alexeyp.searchrepo.router.start;

import android.support.v7.app.AppCompatActivity;
import ru.alexeyp.searchrepo.screens.login.LoginActivity;
import ru.alexeyp.searchrepo.screens.main.MainActivity;

public class StartRouterImpl extends StartRouter {

    private AppCompatActivity _activity;

    public StartRouterImpl(AppCompatActivity activity) {
        _activity = activity;
    }

    @Override
    public void navigateToLogin() {
        startActivity(_activity, LoginActivity.class);
    }

    @Override
    public void navigateToMain() {
        startActivity(_activity, MainActivity.class);
    }
}
