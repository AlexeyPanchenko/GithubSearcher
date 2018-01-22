package ru.alexeyp.searchrepo.di.user;

import ru.alexeyp.searchrepo.router.login.LoginRouter;
import ru.alexeyp.searchrepo.router.login.LoginRouterImpl;
import ru.alexeyp.searchrepo.screens.login.LoginActivity;
import toothpick.config.Module;

public class LoginActivityModule extends Module {

    public LoginActivityModule(LoginActivity activity) {
        bind(LoginRouter.class).toInstance(new LoginRouterImpl(activity));
    }
}
