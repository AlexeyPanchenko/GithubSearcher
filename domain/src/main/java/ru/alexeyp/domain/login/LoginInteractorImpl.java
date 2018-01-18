package ru.alexeyp.domain.login;

import io.reactivex.Completable;

public class LoginInteractorImpl implements LoginInteractor {

    private LoginRepository repository;

    public LoginInteractorImpl(LoginRepository repository) {
        this.repository = repository;
    }

    @Override
    public Completable signIn() {
        return repository.signIn();
    }
}
