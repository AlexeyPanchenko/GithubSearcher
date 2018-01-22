package ru.alexeyp.searchrepo.screens.login;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import javax.inject.Inject;

import ru.alexeyp.domain.interactors.user.UserInteractor;
import ru.alexeyp.searchrepo.router.login.LoginRouter;

public class LoginViewModelFactory implements ViewModelProvider.Factory {

    private LoginRouter _router;
    private UserInteractor _interactor;

    @Inject
    public LoginViewModelFactory(LoginRouter router, UserInteractor interactor) {
        _router = router;
        _interactor = interactor;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new LoginViewModel(_router, _interactor);
    }
}
