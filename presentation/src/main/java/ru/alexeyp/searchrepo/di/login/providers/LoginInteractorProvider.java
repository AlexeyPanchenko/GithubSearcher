package ru.alexeyp.searchrepo.di.login.providers;

import javax.inject.Inject;
import javax.inject.Provider;

import ru.alexeyp.domain.login.LoginInteractorImpl;
import ru.alexeyp.domain.login.LoginRepository;

public class LoginInteractorProvider implements Provider<LoginInteractorImpl> {

    private LoginRepository _repository;

    @Inject
    public LoginInteractorProvider(LoginRepository repository) {
        _repository = repository;
    }

    @Override
    public LoginInteractorImpl get() {
        return new LoginInteractorImpl(_repository);
    }
}
