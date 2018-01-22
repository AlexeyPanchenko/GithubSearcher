package ru.alexeyp.searchrepo.di.user.providers;

import javax.inject.Inject;
import javax.inject.Provider;

import ru.alexeyp.domain.interactors.user.UserInteractorImpl;
import ru.alexeyp.domain.interactors.user.UserRepository;

public class UserInteractorProvider implements Provider<UserInteractorImpl> {

    private UserRepository _repository;

    @Inject
    public UserInteractorProvider(UserRepository repository) {
        _repository = repository;
    }

    @Override
    public UserInteractorImpl get() {
        return new UserInteractorImpl(_repository);
    }
}
