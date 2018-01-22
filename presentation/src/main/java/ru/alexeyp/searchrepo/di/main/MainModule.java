package ru.alexeyp.searchrepo.di.main;

import ru.alexeyp.data.network.GitHubApi;
import ru.alexeyp.domain.interactors.main.MainInteractor;
import ru.alexeyp.domain.interactors.main.MainRepository;
import ru.alexeyp.searchrepo.di.main.providers.GitHubApiProvider;
import ru.alexeyp.searchrepo.di.main.providers.MainInteractorProvider;
import ru.alexeyp.searchrepo.di.main.providers.MainRepositoryProvider;
import toothpick.config.Module;

public class MainModule extends Module {

    public MainModule() {
        bind(GitHubApi.class).toProvider(GitHubApiProvider.class).providesSingletonInScope();
        bind(MainRepository.class).toProvider(MainRepositoryProvider.class).providesSingletonInScope();
        bind(MainInteractor.class).toProvider(MainInteractorProvider.class).providesSingletonInScope();
    }
}
