package ru.alexeyp.searchrepo.di.main.providers;

import javax.inject.Inject;
import javax.inject.Provider;
import ru.alexeyp.data.network.GitHubApi;
import ru.alexeyp.data.repositories.main.MainRepositoryImpl;

public class MainRepositoryProvider implements Provider<MainRepositoryImpl> {

    private final GitHubApi _api;

    @Inject
    public MainRepositoryProvider(GitHubApi api) {
        _api = api;
    }

    @Override
    public MainRepositoryImpl get() {
        return new MainRepositoryImpl(_api);
    }
}
