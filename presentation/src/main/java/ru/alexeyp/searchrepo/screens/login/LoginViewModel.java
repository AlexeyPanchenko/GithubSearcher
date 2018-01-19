package ru.alexeyp.searchrepo.screens.login;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import ru.alexeyp.domain.exceptions.LoginValidateException;
import ru.alexeyp.domain.exceptions.PasswordValidateException;
import ru.alexeyp.domain.login.LoginInteractor;
import ru.alexeyp.domain.model.Credential;
import ru.alexeyp.searchrepo.di.Scopes;
import ru.alexeyp.searchrepo.router.login.LoginRouter;
import ru.alexeyp.searchrepo.utils.base.LCEViewModel;
import toothpick.Toothpick;

public class LoginViewModel extends LCEViewModel {

    private LoginRouter _router;
    private LoginInteractor _interactor;

    @Inject
    LoginViewModel(LoginRouter router, LoginInteractor interactor) {
        _router = router;
        _interactor = interactor;
        setContentState();
    }

    void onSignInClicked(String username, String password) {
        _interactor.signIn(new Credential(username.trim(), password.trim()))
                .doOnSubscribe(disposable -> setProgressState())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSignInSuccess, this::onSignInError);
    }

    void onSignInAnonClicked() {
        _router.navigateToMain();
    }

    @Override
    protected void onCleared() {
        Toothpick.closeScope(Scopes.LOGIN_SCOPE);
        super.onCleared();
    }

    private void onSignInSuccess() {
        setContentState();
        _router.navigateToMain();
    }

    private void onSignInError(Throwable throwable) {
        if (throwable instanceof LoginValidateException) {
            setState(LoginState.ERROR_NAME);
        } else if (throwable instanceof PasswordValidateException) {
            setState(LoginState.ERROR_PASSWORD);
        } else {
            setErrorState(throwable);
        }
    }
}
