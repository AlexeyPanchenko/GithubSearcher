package ru.alexeyp.domain.interactors.user;

import io.reactivex.Completable;
import ru.alexeyp.domain.exceptions.LoginValidateException;
import ru.alexeyp.domain.exceptions.PasswordValidateException;
import ru.alexeyp.domain.model.Credential;

public class UserInteractorImpl implements UserInteractor {

    private UserRepository _repository;

    public UserInteractorImpl(UserRepository repository) {
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

    @Override
    public Completable logOut() {
        return _repository.logOut();
    }
}
