package ru.alexeyp.searchrepo.di.start;


import ru.alexeyp.data.start.StartRepositoryImpl;
import ru.alexeyp.domain.start.StartInteractor;
import ru.alexeyp.domain.start.StartRepository;
import ru.alexeyp.searchrepo.di.start.providers.StartInteractorProvider;
import toothpick.config.Module;

public class StartModule extends Module {

    public StartModule() {
        bind(StartRepository.class).toInstance(new StartRepositoryImpl());
        bind(StartInteractor.class).toProvider(StartInteractorProvider.class).providesSingletonInScope();
    }
}
