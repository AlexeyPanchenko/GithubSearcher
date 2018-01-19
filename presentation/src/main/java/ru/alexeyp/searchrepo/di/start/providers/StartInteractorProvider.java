package ru.alexeyp.searchrepo.di.start.providers;


import javax.inject.Inject;
import javax.inject.Provider;

import ru.alexeyp.domain.start.StartInteractorImpl;
import ru.alexeyp.domain.start.StartRepository;

public class StartInteractorProvider implements Provider<StartInteractorImpl> {

    private StartRepository _repository;

    @Inject
    public StartInteractorProvider(StartRepository repository) {
        _repository = repository;
    }

    @Override
    public StartInteractorImpl get() {
        return new StartInteractorImpl(_repository);
    }
}
