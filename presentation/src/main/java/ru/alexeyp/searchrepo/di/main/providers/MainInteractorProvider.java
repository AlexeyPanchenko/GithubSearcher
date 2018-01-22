package ru.alexeyp.searchrepo.di.main.providers;

import javax.inject.Inject;
import javax.inject.Provider;

import ru.alexeyp.domain.interactors.main.MainInteractorImpl;
import ru.alexeyp.domain.interactors.main.MainRepository;

public class MainInteractorProvider implements Provider<MainInteractorImpl> {

    private final MainRepository _repository;

    @Inject
    public MainInteractorProvider(MainRepository repository) {
        _repository = repository;
    }

    @Override
    public MainInteractorImpl get() {
        return new MainInteractorImpl(_repository);
    }
}
