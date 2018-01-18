package ru.alexeyp.searchrepo.screens.login;

import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

import ru.alexeyp.searchrepo.di.Scopes;
import ru.alexeyp.searchrepo.router.login.LoginRouter;
import toothpick.Scope;
import toothpick.Toothpick;

public class LoginViewModel extends ViewModel {

    @Inject
    LoginRouter router;

    LoginViewModel() {
        Scope scope = Toothpick.openScope(Scopes.LOGIN_ACTIVITY_SCOPE);
        Toothpick.inject(this, scope);
    }

    @Override
    protected void onCleared() {
        Toothpick.closeScope(Scopes.LOGIN_SCOPE);
        super.onCleared();
    }
}
