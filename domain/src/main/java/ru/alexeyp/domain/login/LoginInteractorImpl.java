package ru.alexeyp.domain.login;

import io.reactivex.Completable;
import ru.alexeyp.domain.exceptions.LoginValidateException;
import ru.alexeyp.domain.exceptions.PasswordValidateException;
import ru.alexeyp.domain.model.Credential;

public class LoginInteractorImpl implements LoginInteractor {

    private LoginRepository _repository;

    public LoginInteractorImpl(LoginRepository repository) {
        _repository = repository;
    }

    @Override
    public Completable signIn(Credential credential) {
        if (credential.getLogin() == null || credential.getLogin().isEmpty()) {
            return Completable.error(LoginValidateException::new);
        } else if (credential.getPassword() == null || credential.getPassword().isEmpty()) {
            return Completable.error(PasswordValidateException::new);
        }
        return _repository.signIn(credential);
    }

    @Override
    public Completable checkUser() {
        return _repository.checkUser();
    }
}
