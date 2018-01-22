package ru.alexeyp.searchrepo.screens.login;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import ru.alexeyp.domain.exceptions.LoginValidateException;
import ru.alexeyp.domain.exceptions.PasswordValidateException;
import ru.alexeyp.domain.interactors.user.UserInteractor;
import ru.alexeyp.domain.model.Credential;
import ru.alexeyp.searchrepo.router.login.LoginRouter;
import ru.alexeyp.searchrepo.utils.base.LCEViewModel;

public class LoginViewModel extends LCEViewModel {

    private LoginRouter _router;
    private UserInteractor _interactor;

    @Inject
    LoginViewModel(LoginRouter router, UserInteractor interactor) {
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

    void onInfoDialogClosed() {
        setContentState();
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
